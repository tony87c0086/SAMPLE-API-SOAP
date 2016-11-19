# README #

### What is this repository for? ###

* This is a public repository for demonstrating SOAP API development using JAVA Spring + security & Hibernate.
* I am more than happy and welcome for any suggestions and improvements. 
* Version 1.0.1

### How do I get set up? ###

* JDK 1.8 + Tomcat 8.
* Excute SAMPLE.sql to make database ready.
* Change dal.properties located in ../src/main/resources for setting up DB connections. 
* Import project as Maven project.
* Update & clean project if necessary.
* Run as Server or build then put into tomcat.
* Play around with Juint/SoapUI, Happy coding.

### Structure guidelines ###

* SOAP API
  * Refer to package(api, config)
  * API represents the payload of the Service.
  
* API security
  * Refer to (../src/main/resources/config/applicationSecurity.xml)
  * Security implemented using spring basic authentication filter.
  
* Spring Boot
  * Using Spring Boot to simplify dependency management.
  
### NOTE: The structure and component below is mostly same as my other REST sample. They CAN be a dependency JAR file or files depends on your application needs. This gives flexibility to swith between SOAP/REST or any other new technologies might comming. Yes, finger cross! ###

* Manager + Service 
  * Refer to package(manager, service, abstract)
  * Manager is responsible for (1)DAL, (2)mapping entity to model, & (3)business logic implementation.
  * Manager encapsulates DAL and only output model as media to communicate.
  * Service is responsible for (1)pre-validating data & (2)exposing service.
  
* Hibernate Entity + DAO + Implementation
  * Refer to package(entity, dao, impl)
  * Entity represents the DB table/view structure.
  * DAO represents the interface signature of hibernate implementation.
  * Impl represents the implementation.

* Utility tools
  * Refer to package(utility)
  * Utility represents the customized tools & date convertors for application.
  
* XML model from XSD schema
  * Refer to (model, ../src/main/resource/schemas, XMLBindings.xml)
  * Model represents the output data structure.
  * Model generated from XSD schema using JAXB.

* Constant & configuration & exception
  * Refer to package(constant, config, exception)
  * Constant represents the constants such as error code/message, enums, etc.
  * Configuration represents the configuration.
  * Exception implements customized exceptions.
  
* Unit test
  * Refer to package(test)
  * Test including mock, hibernate & utility
  
* Application setting
  * Refer to (../src/main/resources/config/*, ../src/main/resources/webapp/WEB-INF/*)

### Who do I talk to? ###

* Repo owner or admin(tony87c0086@hotmail.com)
* Other community or team contact
