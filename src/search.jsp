<%@ taglib prefix="tagfiles" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="snakes" uri="snakes-listmovies" %>
<!doctype html>
<html>
  <head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    
    <link href="css/bootstrap.css" rel="stylesheet" />
    <link href="css/snakes.css" rel="stylesheet" />

    <title>Movie List | Does it have snakes?</title>
  </head>
  <body>
    <tagfiles:header />

    <div class="container snakes">
      <h2>Check before you watch!</h2>
      <p>Don't get caught off guard by a snake in your favorite show.</p>
      <form action="search" method="get">
        Movie Title: 
        <input type="text" name="query" />
        <input type="submit" value="Submit" />
      </form>
      <table align="center">
        <snakes:listmovies query="${query}">
          <tr><td><a href="http://www.imdb.com/title/tt${imdb}/" target="_blank">${movie}</a></td><td>${snakes}</td></tr>
        </snakes:listmovies>
      </table>
    </div>
    <div class="sample">
      <p>Sample Java application.</p>
    </div>
  </body>
</html>
