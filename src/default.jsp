<%@ taglib prefix="tagfiles" tagdir="/WEB-INF/tags" %>
<!doctype html>
<html>
  <head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    
    <link href="css/bootstrap.css" rel="stylesheet" />
    <link href="css/snakes.css" rel="stylesheet" />

    <title>Does it have snakes?</title>
  </head>

  <script>
    function switchto(div) {
     if (div == 'love') {
          document.getElementById('love').style.display = 'block';
          document.getElementById('hate').style.display = 'none';
        }
        else {
          document.getElementById('hate').style.display = 'block';
          document.getElementById('love').style.display = 'none';
        }
     }
  </script>

  <body>
    <tagfiles:header />
    <div id="hate" class="container snakes snake-hater">
      <h1 class="cover-heading">Does it have snakes?</h1>
      <p class="lead">A safety resource for snake haters.</p>
      <!--Intro -->
      <h2>Do you or a loved one hate snakes?</h2>
      <p>Snakes are a controversial topic. Some people love'em, some people just can't stand them. How do you get by in a world full of snakes?</p>
      <h3 class="flyout toggle">Love snakes? <a href="#" onclick="switchto('love');return false;">Click Here</a></h3>
      <p>You wouldn't let a snake into your house, so why would you want one on your television screen? If you go out to see a movie, don't you want to know whether or not you can expect to be bombarded with images of awful snakes?</p>
      <div id="badsnake" parallax="true"></div>
      <!--Browse movies -->
      <h2>Find something to watch</h2>
      <p>Don't get caught off guard by a snake in your evening entertainment. <i>Does it Have Snakes?</i> provides a <a href="movies">list of movies</a> that are certified snake-free. Browse our list of movies when you want to find something to watch without worrying about whether it has snakes in it or not. Great for kids, the skittish, and field mice.</p>
      <div id="badsnake2" parallax="true"></div>
      <!--Search for a movie -->
      <h2>Already got a movie in mind?</h2>
      <p>When you have a specific movie in mind, you can search for it with our handy <a href="search">search page</a>. If the movie is in our database, we will tell you whether or not it has snakes in it. Not all movies have been registered, but we have a dedicated core group of users who are watching movies and noting whether or not they show snakes on screen.</p>
      <div id="badsnake3" parallax="true"></div>
      <!--Add a movie -->
      <h2>Warn others!</h2>
      <p>Join us to log your sightings and provide warning to other unsuspecting viewers. If you watch a movie that hasn't yet been registered, you can <a href="add">let us know</a> whether it had snakes or not. Optionally include a note for other users indicating when the snakes appeared, how vicious they looked and how many people died as a result.</p>
      <!--TODO: IMDB Integration -->
    </div>
    <div id="love" class="container snakes snake-lover">
      <h1 class="cover-heading">Does it have snakes!?</h1>
      <p class="lead">A great resource for snake lovers.</p>
      <!--Intro -->
      <h2>Do you or a loved one love snakes?</h2>
      <p>Snakes are a controversial topic. Some people love'em, some people just can't stand them. How do you get buy in a world where snakes are persecuted?</p>
      <h3 class="flyout toggle">Hate snakes? <a href="#" onclick="switchto('hate');return false;">Click Here</a></h3>
      <p>You would let a snake into your house, so why wouldn't you want one on your television screen? If you go out to see a movie, don't you want to know whether or not you can expect to be bombarded with images of amazing snakes?</p>
      <div id="goodsnake" parallax="true"></div>
      <!--Browse movies -->
      <h2>Find something to watch</h2>
      <p>Don't take a chance with your evening entertainment. <i>Does it Have Snakes?</i> provide a <a href="movies">list of movies</a> that are certified snake-ful. Browse our list of movies when you want to find something to watch without worrying about whether it has snakes in it or not. Great for kids, the brave, and mongeese.</p>
      <div id="goodsnake2" parallax="true"></div>
      <!--Search for a movie -->
      <h2>Already got a movie in mind?</h2>
      <p>When you have a specific movie in mind, you can search for it with our handy <a href="search">search bar</a>. If the movie is in our database, we will tell you whether or not it has snakes in it. Not all movies have been registered, but we have a dedicated core group of users who are watching movies and noting whether or not they show snakes on screen.</p>
      <div id="goodsnake3" parallax="true"></div>
      <!--Add a movie -->
      <h2>Spread the word!</h2>
      <p>Join us to log your sightings and provide warning to other discerning viewers. If you watch a movie that hasn't yet been registered, you can <a href="add">let us know</a> whether it had snakes or not. Optionally include a note for other users indicating when the snakes appeared, how vicious they looked and how many people died as a result.</p>
      <!--TODO: IMDB Integration -->
    </div>

    <div class="sample">
      <p>Sample Java application.</p>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/init.js"></script>
  </body>
</html>
