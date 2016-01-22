# eb-tomcat-snakes
Tomcat application that shows the use of RDS in a Java EE web application in AWS Elastic Beanstalk.
**IMPORTANT**

Always run build.sh from the root of the project directory.

**INSTRUCTIONS**

Install the Java 8 JDK. The java compiler is required to run the build script.
If you would like to run the web app locally, install Tomcat and Postgresql.

Start Tomcat:

	$ catalina start

Run build.sh to compile the web app and create a WAR file. The script attempts to copy the WAR file to ``/Library/Tomcat`` for local testing. If you installed Tomcat to another location, change the path in ``build.sh``.

Open [localhost:8080](http://localhost:8080/) in a web browser to view the application running locally.

You can deploy the ROOT.war archive that build.sh generates to an AWS Elastic Beanstalk web server environment running the Tomcat 8 platform.

***To deploy with the EB CLI***

The EB CLI requires Python 2.7 or 3.4 and the package manager ``pip``. For detailed instructions on installing the EB CLI, see [Install the EB CLI](http://docs.aws.amazon.com/elasticbeanstalk/latest/dg/eb-cli3-install.html) in the AWS Elastic Beanstalk Developer Guide.

Install the EB CLI:

	$ pip install awsebcli

Clone the project:

	$ git@github.com:awslabs/eb-tomcat-snakes.git

Build the project:

	eb-tomcat-snakes$ ./build.sh

Initialize the EB CLI

	eb-tomcat-snakes$ eb init

Add the following to ``.elasticbeanstalk/config.yml``:

	deploy:
	  artifact: ROOT.war

Create an environment:

	eb-tomcat-snakes$ eb create snakes --sample

Deploy the project WAR:

	eb-tomcat-snakes$ eb deploy --staged

**CONTENTS**

This project is organized as follows (some files not shown):

	├── LICENSE             - License
	├── README              - This file
	├── build.sh            - Build script
	└── src
	    ├── 404.jsp         - 404 error JSP
	    ├── add.jsp         - Add a Movie JSP
	    ├── default.jsp     - Home Page JSP
	    ├── movies.jsp      - Movies JSP
	    ├── search.jsp      - Search JSP
	    ├── WEB-INF
	    │   ├── lib         - Library JARs for JSP and Servlet APIs, Jasper, Log4J and PostgreSQL
	    │   ├── tags        - Header tag file
	    │   │   └── header.tag
	    │   ├── tlds        - Tag Library Descriptor for ListMovies simple tag
	    │   │   └── movies.tld
	    │   ├── log4j2.xml  - Log4J configuration file
	    │   └── web.xml     - Deployment descriptor
	    ├── com
	    │   └── snakes
	    │       ├── model   - Model classes
	    │       │   ├── Media.java
	    │       │   └── Movie.java
	    │       └── web     - Servlet and simple tag classes
	    │           ├── AddMovie.java
	    │           ├── ListMovies.java
	    │           └── SearchMovies.java
	    ├── css - Stylesheets
	    │   ├── movies.html - HTML page for testing stylesheet changes
	    │   └── snakes.css  - Custom styles
	    ├── images          - Some royalty free images for the header and front page
	    └── js              - Bootstrap's javascript
	