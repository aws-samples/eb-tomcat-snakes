cd src
mkdir -p WEB-INF/classes
echo .
javac -d WEB-INF/classes com/snakes/model/Media.java
echo .
javac -classpath "WEB-INF/classes;WEB-INF/lib/*" -d WEB-INF/classes com/snakes/model/Movie.java
echo .
javac -classpath "WEB-INF/classes;WEB-INF/lib/*" -d WEB-INF/classes com/snakes/web/ListMovies.java
echo .
javac -classpath "WEB-INF/classes;WEB-INF/lib/*" -d WEB-INF/classes com/snakes/web/AddMovie.java
echo .
javac -classpath "WEB-INF/classes;WEB-INF/lib/*" -d WEB-INF/classes com/snakes/web/SearchMovies.java
echo .
jar -cf ROOT.war *.jsp images css js WEB-INF .ebextensions/*.config .ebextensions/*.json
echo .
if [ -d "/Library/Tomcat/webapps" ]; then
  cp ROOT.war /Library/Tomcat/webapps
  echo .
fi
mv ROOT.war ../
echo .
echo "SUCCESS"
