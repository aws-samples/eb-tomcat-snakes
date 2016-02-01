<%@ taglib prefix="tagfiles" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="snakes" uri="snakes-listmovies" %>
<!doctype html>
<html lang="en">
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
      <h2>Movie List</h2>
      <table align="center">
        <snakes:listmovies>
          <tr><td><a href="http://www.imdb.com/title/tt${imdb}/" target="_blank">${movie}</a></td><td>${snakes}</td></tr>
        </snakes:listmovies>
      </table>
    </div>

    <div class="sample">
      <p>Sample Java application.</p>
    </div>
  </body>
</html>
