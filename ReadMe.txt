INTRODUCTION
------------

Spring Security with Angular JS accelerator is used some nice features of Spring Security, Spring Boot and Angular JS working together to provide a pleasant and secure user experience. In this accelerator login page is created using MVC pattern of  Angular JS. It uses Spring Security for user authentication and authorization. Spring boot make this accelerator robust and Easy to use, easy to integrate. It provide role based access to user.

INSTALLATION
------------
	SOFTWARE LIST.
 
		1) JDK 1.8
		2) Maven 3.0
		
CONFIGURATION
-------------
Open application.properties file and do below configuration.

	database properties:- Add database credentials and URL in this property.
        security properties:- Change database query and rest API path in this property.
        view resolver :- Add your views path in this property.
        response message :- You can configure all reponse messages in this property.

LDAP SERVER:
---------------
Step 1 : intall LDAP server.
Step 2 : Create Users
Step 3 : Enable property isLdapAuthentication=true in application.properties
step 4 : Change in application.properties according to ldap configuration 

DEPLOYMENT
---------------
Change deployed path in POM.xml.
	<deployFolder>Your Deployed Location</deployFolder>

Change logging file path in log4j properties.
	<Property name="log-path">G:\application_log\sping_boot</Property>


REST SERVICES
-------------

http://localhost:8080/checkLogin
http://localhost:8080/accessDenied
http://localhost:8080/admin
http://localhost:8080/user
http://localhost:8080/principal
http://localhost:8080/checkLogin?logout

## Run Command Line
-------------------
1 Go to Project folder and build project : mvn -e clean package install
2 Start server
3 Go to browser and hit URL : http://localhost:8080

MAINTAINERS
-----------

Hemant Sharma(hemant.sharma@gmail.com)

Neeraj Bangar(neeraj.bangar@gmail.com)

Kartavya Soni(kartavya.soni@gmail.com)

