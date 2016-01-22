package com.snakes.web; 
import com.snakes.model.Movie;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddMovie extends HttpServlet {
  private static final Logger logger = LogManager.getLogger("snakes");
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NumberFormatException {
    String title = request.getParameter("title");
    if (title.matches(".*[^a-zA-Z0-9_\\s].*")) {
      logger.warn("Bad Title String Detected.");
      title = "bad title";
    }
    String imdbinput = request.getParameter("imdb");
    Integer imdbparsed = null;
    if (imdbinput.matches("\\d{7}")) {
      imdbparsed = Integer.parseInt(imdbinput);
    }
    else if (imdbinput.matches(".*\\d{7}.*"))
      imdbparsed = Integer.parseInt(imdbinput.replaceFirst(".*(\\d{7}).*", "$1"));

    boolean snakes = Boolean.parseBoolean(request.getParameter("snakes"));
    Movie.addMovie(title, imdbparsed, snakes);
    response.sendRedirect("add");
  }
}