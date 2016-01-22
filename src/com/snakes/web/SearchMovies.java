package com.snakes.web; 
import com.snakes.model.Movie;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SearchMovies extends HttpServlet {
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NumberFormatException {
    String query = request.getParameter("query");
    request.setAttribute("query", query);
    RequestDispatcher view = request.getRequestDispatcher("search.jsp");
    view.forward(request,response);
  }
}