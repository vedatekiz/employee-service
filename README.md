# employee-service
This application demonstrates the usage of spring boot microservice, rabbitmq and JWT based spring security.
Each and every crud endpoint publishes an event to a rabbitmq instance hosted on cloudamqp.

Requirements
------------
* [Java Platform (JDK) 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [Apache Maven 3.x](http://maven.apache.org/)


 Usage 
-----------
* There are two authenticarion roles `ROLE_ADMIN` and `ROLE_CLIENT`
* `secured/employees/listAll` and `secured/employees/get/{employeeId}` endpoints are out of security context.
* ` POST secured/employees`, ` PUT secured/employees/{employeeId}`, ` DELETE secured/employees/{employeeId}` enpoints require `ROLE_ADMIN` role 
* ` POST /users/signup` endpoint to register a user and get a valid JWT token, and ` POST /users/signin` endpoint with parameters username and password to get a valid JWT token
* There is a built in admin user with `vedatekiz/vedatpw` that has a `ROLE_ADMIN` role 

#### Restrictions


* There is a unique constraint on `email` field on `Employee` entity
* `Birthdate` field in the `EmployeeDTO` should be in the format of `yyyy-MM-dd`  

Build & Run
-----------
* `mvn spring-boot:run`
* Point your browser to [http://localhost:8080/] (http://localhost:8080/)


Documentation
-----------
Api documentation is avaliable at [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)