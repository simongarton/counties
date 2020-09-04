## Building and running the application

To build this application, start the terminal, move into the root directory, and type :

mvn package 

This will produce a JAR file, and the terminal messages will tell you where it is : in my case, it's at :

/Users/simongarton/projects/java/tests/counties/target/register-0.0.1-SNAPSHOT.jar

Now type :

java -jar /Users/simongarton/projects/java/tests/counties/target/register-0.0.1-SNAPSHOT.jar

and then in a browser or a tool such as Postman, navigate to :

http://localhost:8080/users?surname=Baggins

## API endpoints

Use the swagger documentation available at :

http://localhost:8080/swagger-ui.html
http://localhost:8080/v2/api-docs

## Libraries

Lombok : used to generate boilerplate code, such as getters and setters. Your codebase can then be much smaller, reflecting just the business logic.
SpringFox : used for Swagger API documentation.

