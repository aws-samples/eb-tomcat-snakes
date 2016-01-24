# eb-tomcat-snakes
Tomcat application that shows the use of RDS in a Java EE web application in AWS Elastic Beanstalk.

**IMPORTANT**
Always run build.sh from the root of the project directory.

## INSTRUCTIONS
Install the Java 8 JDK. The java compiler is required to run the build script.
If you would like to run the web app locally, install Tomcat and Postgresql.

Run build.sh to compile the web app and create a WAR file. The script attempts to copy the WAR file to ``/Library/Tomcat`` for local testing. If you installed Tomcat to another location, change the path in ``build.sh``.

Open [localhost:8080](http://localhost:8080/) in a web browser to view the application running locally.

You can deploy the ROOT.war archive that build.sh generates to an AWS Elastic Beanstalk web server environment running the Tomcat 8 platform.

### To download and build the project
Clone the project:

	~$ git clone git@github.com:awslabs/eb-tomcat-snakes.git

Build the project:

	~$ cd eb-tomcat-snakes
	~/eb-tomcat-snakes$ ./build.sh

##### To deploy with the AWS Management Console
1. Open the [Elastic Beanstalk Management Console](https://console.aws.amazon.com/elasticbeanstalk/home)
2. Choose *Create New Application*
3. For *Application Name*, type **tomcat-snakes**. Choose *Next*.
4. Choose *Web Server Environment*
5. Set the platform to *Tomcat* and choose *Next*.
6. Choose *Upload your own* and *Choose File*.
7. Upload *ROOT.war* from your project directory and choose *Next*.
8. Type a unique *Environment URL* and choose *Next*.
9. Check *Create an RDS DB Instance with this environment* and choose *Next*.
10. Set *Instance type* to *t2.nano* and choose *Next*. Choose *Next* again to skip tag configuration.
11. Apply the following RDS settings and choose *Next* (leave the other settings default):
    - DB engine: *postgres*
    - Engine version: *9.4.5*
    - Instance class: *db.t2.micro*
    - Master username: any username
    - Master password: any password
12. Choose **Next** to create and use the default role and instance profile. 
13. Choose **Launch**.

The process takes about 15 minutes. If you want to save time during the initial environment creation, you can launch the environment without a database, and then add one after the environment is running from the Configuration page. Launching an RDS DB instance takes about 10 minutes.

##### To deploy with the EB CLI

The EB CLI requires Python 2.7 or 3.4 and the package manager ``pip``. For detailed instructions on installing the EB CLI, see [Install the EB CLI](http://docs.aws.amazon.com/elasticbeanstalk/latest/dg/eb-cli3-install.html) in the AWS Elastic Beanstalk Developer Guide.

Install the EB CLI:

	~$ pip install awsebcli

Initialize the project repository:

	~/eb-tomcat-snakes$ eb init

Add the following to ``.elasticbeanstalk/config.yml``:

	deploy:
	  artifact: ROOT.war

Create an environment with an RDS database:

	~/eb-tomcat-snakes$ eb create tomcat-snakes --sample -i t2.micro --database.engine postgres --database.instance db.t2.micro --database.username *any username* --database.password *any password*

Deploy the project WAR to your new environment:

	~/eb-tomcat-snakes$ eb deploy --staged

Open the environment in a browser:

	~/eb-tomcat-snakes$ eb open

## CONTENTS

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
	