package com.snakes.model;
import java.sql.*;
import java.util.ArrayList;
import java.lang.NullPointerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import java.io.File;
import java.io.IOException;

public class Movie extends Media {
  
  static Connection con = null;
  private String _name = "null";
  private Integer _imdb = null;
  private boolean _snakes;
  private static final Logger logger = LogManager.getLogger("snakes");
  static JsonFactory factory = new JsonFactory();

  public Movie() {}
  public Movie(String name, Integer imdb, boolean snakes) {
     _name = name;
     _imdb = imdb;
     _snakes = snakes;
  }

  public void setName(String name) {
    this._name = name;
  }

  public void setImdb(Integer imdb) {
    this._imdb = imdb;
  }

  public void setSnakes(boolean snakes) {
    this._snakes = snakes;
  }

  public String getName() {
    return this._name;
  }

  public String getImdb() {
    /* Pad integer imdb # with 0s */
    String imdbpadded = String.format("%07d", _imdb);
    return imdbpadded;
  }

  public String getSnakes() {
    String snakes = null;
    if (_snakes) {
      snakes = "Snakes";
    }
    else {
      snakes = "No Snakes";
    }
    return snakes;
  }

  public String getSnakesBool() {
    String snakes = null;
    if (_snakes) {
      snakes = "true";
    }
    else {
      snakes = "false";
    }
    return snakes;
  }

  public static Movie[] getMovies() {

    ArrayList<Movie> movies = new ArrayList<Movie>();

    try {
      con = getConnection();
      // If that fails, send dummy entries
      if (con == null) {
      logger.warn("Connection Failed!");
        Movie failed = new Movie("Connection Failed", 99999999, false);
        return new Movie[] { failed };
      }
      Statement stmt = con.createStatement();
      String sql = "SELECT * FROM Movies;";
      ResultSet rs = stmt.executeQuery(sql);

      while(rs.next()){
        // Retrieve by column name
        String name = rs.getString("name");
        Integer imdb = rs.getInt("imdb");
        boolean snakes = rs.getBoolean("snakes");
        Movie movie = new Movie(name, imdb, snakes);
        movies.add(movie);
      }
    }
    catch (SQLException e) { logger.warn(e.toString());}

    return movies.toArray(new Movie[movies.size()]);
  }

  public static Movie[] getMovies(String title) {

    ArrayList<Movie> movies = new ArrayList<Movie>();

    try {
      con = getConnection();
      // If that fails, send dummy entry
      if (con == null) {
      logger.warn("Connection Failed!");
        Movie failed = new Movie("Connection Failed", 99999999, false);
        return new Movie[] { failed };
      }
      Statement stmt = con.createStatement();
      String sql = null;
      if (title.matches(".*[^a-zA-Z0-9_\\s].*"))
        return new Movie[0];
      else
        sql = "SELECT * FROM Movies WHERE UPPER(name) LIKE UPPER('"+title+"');";
      ResultSet rs = stmt.executeQuery(sql);

      while(rs.next()){
        // Retrieve by column name
        String name = rs.getString("name");
        Integer imdb = rs.getInt("imdb");
        boolean snakes = rs.getBoolean("snakes");
        Movie movie = new Movie(name, imdb, snakes);
        movies.add(movie);
      }
    }
    catch (SQLException e) { logger.warn(e.toString());}

    return movies.toArray(new Movie[movies.size()]);
  }

  public static void addMovie(String name, Integer imdb, boolean snakes) {
    con = getConnection();
    // If the connection is null, give up
    if (con == null) {
      return;
    }
    try {
      Statement create = con.createStatement();
      String insertRow1 = "INSERT INTO Movies (Name, IMDB, Snakes) VALUES ('"+name+"', '"+imdb+"', '"+snakes+"');";
      logger.trace("adding movie with statement: "+ insertRow1);
      create.addBatch(insertRow1);
      create.executeBatch();
      create.close();
    }
    catch (SQLException f) { logger.warn(f.toString());}
  }

  private static void initDatabase() {
    // Retrieve the connection (it should already exist)
    con = getConnection();
    // If the connection is null, give up
    if (con == null) {
      return;
    }
    // Attempt to read movies table
    try {
      Statement stmt = con.createStatement();
      String sql = "SELECT * FROM Movies;";
      ResultSet rs = stmt.executeQuery(sql);
    }
    // If the movies table doesn't exist, create it
    catch (SQLException e) {
      try {
        logger.warn("Initializing Database");
        // Create table
        logger.info("Creating table");
        String createTable = "CREATE TABLE Movies (Name char(50), IMDB integer, Snakes boolean);";
        Statement createStmt = con.createStatement();
        createStmt.execute(createTable);
      }
      catch (SQLException f) { logger.warn(f.toString());}
      /* Seed empty table with entries from database-seed.json at /tmp/database-seed.json
      * Seed file is copied to /tmp during deployment by db-seed.config
      * move db-seed.config to src/.ebextensions/inactive to disable
      */ 
      try{
        // Read seed file
        logger.info("reading seed file");
        File databaseSeed = new File("/tmp/database-seed.json");
        ObjectMapper mapper = new ObjectMapper();
        Movie[] movies = mapper.readValue(databaseSeed, Movie[].class);
        Statement create = con.createStatement();
        logger.info("adding movies to batch");
        for (Movie movie : movies) {
          String row = "INSERT INTO Movies (Name, IMDB, Snakes) VALUES ('" + movie.getName() + "', '"+ movie.getImdb() + "', '" + movie.getSnakesBool() + "');";
          logger.info("- "+row);  
          create.addBatch(row);
        }
        create.executeBatch();
        create.close();
        logger.warn("Initialized Database");
      }
      catch (IOException g) { logger.warn(g.toString());}
      catch (SQLException h) { logger.warn(h.toString());}
    }
  }

  private static Connection getConnection() {
    // Return existing connection after first call
    if (con != null) {
      return con;
    }
    logger.trace("Getting database connection...");
    // Get RDS connection from environment properties provided by Elastic Beanstalk
    con = getRemoteConnection();
    // If that fails, attempt to connect to a local postgres server
    if (con == null) {
      con = getLocalConnection();
    }
    // If that fails, give up
    if (con == null) {
      return null;
    }
    // Attempt to initialize the database on first connection
    initDatabase();
    return con;
  }

  private static Connection getRemoteConnection() {
    /* Read database info from /tmp/database.json (advanced, more secure option)
    * - Requires database.config to be moved into .ebextensions folder and updated to 
    * point to a JSON file in an S3 bucket that the instance profile has permission to read.
    */
    try {
      /* Load the file and create a parser. If the project is not configured to store
      * database credentials in S3, fail out and try the next method.
      */
      File databaseConfig = new File("/tmp/database.json");
      JsonParser parser = factory.createParser(databaseConfig);
      // Load the Postgresql driver class
      Class.forName("org.postgresql.Driver");
      /* Read the first value in the JSON document with Jackson. This must be a full JDBC
      *  connection string a la jdbc:postgresql://hostname:port/dbName?user=userName&password=password
      */
      JsonToken jsonToken = null;
      while ( jsonToken != JsonToken.VALUE_STRING ) 
        jsonToken = parser.nextToken();
      String jdbcUrl = parser.getValueAsString();
      // Connect to the database
      logger.trace("Getting remote connection with url from database config file.");
      Connection con = DriverManager.getConnection(jdbcUrl);
      logger.info("Remote connection successful.");
      return con;
    }
    catch (IOException e) { logger.warn("Database configuration file not found. Checking environment variables.");}
    catch (ClassNotFoundException e) { logger.warn(e.toString());}
    catch (SQLException e) { logger.warn(e.toString());}

    // Read database info from environment variables (standard configration)
    if (System.getProperty("RDS_HOSTNAME") != null) {
      try {
      Class.forName("org.postgresql.Driver");
      String dbName = System.getProperty("RDS_DB_NAME");
      String userName = System.getProperty("RDS_USERNAME");
      String password = System.getProperty("RDS_PASSWORD");
      String hostname = System.getProperty("RDS_HOSTNAME");
      String port = System.getProperty("RDS_PORT");
      String jdbcUrl = "jdbc:postgresql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
      logger.trace("Getting remote connection with connection string from environment variables.");
      Connection con = DriverManager.getConnection(jdbcUrl);
      logger.info("Remote connection successful.");
      return con;
    }
    catch (ClassNotFoundException e) { logger.warn(e.toString());}
    catch (SQLException e) { logger.warn(e.toString());}
    }
    return null;
  }

  // Connect to a local database for development purposes
  private static Connection getLocalConnection() {
    try {
      Class.forName("org.postgresql.Driver");
      logger.info("Getting local connection");
      Connection con = DriverManager.getConnection(
            "jdbc:postgresql://localhost/snakes",
            "snakes",
            "sqlpassword");
      logger.info("Local connection successful.");
      return con;
    }
    catch (ClassNotFoundException e) { logger.warn(e.toString());}
    catch (SQLException e) { logger.warn(e.toString());}
    return null;
  }
}
