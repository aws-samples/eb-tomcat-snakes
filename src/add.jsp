<%@ taglib prefix="tagfiles" tagdir="/WEB-INF/tags" %>

<html>

  <head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    
    <link href="css/bootstrap.css" rel="stylesheet" />
    <link href="css/snakes.css" rel="stylesheet" />

    <title>Add a Movie | Does it have snakes?</title>
  </head>

  <body>
    <tagfiles:header />

    <div class="container snakes">
      <h2>Warn others!</h2>
      <p>Log your sightings and provide warning to other unsuspecting viewers!</p>
      <form action="add.do" method="post">
        Movie Title: <br />
        <input type="text" name="title" />
        <br />
        IMDB Link: <br />
        <input type="text" name="imdb" />
        <br />
        <input type="radio" name="snakes" value="true">Snakes</input>
        <br />
        <input type="radio" name="snakes" value="false">No Snakes</input>
        <br />
        <br />
        <input type="submit" value="Submit" />
      </form>
    </div>

    <div class="sample">
      <p>Sample Java application.</p>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>

