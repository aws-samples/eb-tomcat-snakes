package com.snakes.web; 
import com.snakes.model.Movie;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.*;

public class ListMovies extends SimpleTagSupport {
  Movie[] movies = null;
  String query = null;

  public void setQuery(String query) {
    this.query = query;
  }

  public void doTag() throws JspException, IOException {
    if (query != null)
      movies = Movie.getMovies(query);
    else
      movies = Movie.getMovies();
    for (int i = 0; i < movies.length; i++) {
      getJspContext().setAttribute("movie", movies[i].getName());
      getJspContext().setAttribute("imdb", movies[i].getImdb());
      getJspContext().setAttribute("snakes", movies[i].getSnakes());
      getJspBody().invoke(null);
    }
  }
}
