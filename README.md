## Counties Power : Users API (Technical Test)

A very simple API to search a dataset for users matching a surname.

Simon Garton  
**simon.garton@gmail.com**  
September 5, 2020

## Building and running the application

To build this application, start the terminal, move into the root directory, and type :

```
mvn package 
```

This will produce a JAR file, and the terminal messages will tell you where it is : in my case, it's at :

```
/Users/simongarton/projects/java/tests/counties/target/register-0.0.1-SNAPSHOT.jar
```

Now type :

```
java -jar /Users/simongarton/projects/java/tests/counties/target/register-0.0.1-SNAPSHOT.jar
```

and then in a browser or a tool such as Postman, navigate to (e.g.) [http://localhost:8080/users?surname=Baggins](http://localhost:8080/users?surname=Baggins)

## Implementation

## API endpoints

Use the generated [Swagger](http://localhost:8080/swagger-ui.html) documentation to see the available endpoints.

I have also included a Postman collection with examples, that can be used for testing.

## Libraries

* Lombok : used to generate boilerplate code, such as getters and setters. Your codebase can then be much smaller, reflecting just the business logic.  
* SpringFox : used for Swagger API documentation.  
* h2 and Hibernate : used for in memory database   

## Discussion points

* Current implementation is both case-sensitive and not wildcarded. A real-world solution would have business rules around how the searching should be implemented.
* I would consider a unique key, probably on email : so adding new users with the same email would raise an error.
* The test script uses the same in-memory database as the application; in reality, the tests would use the in-memory database, but the real API would have a different database. (This in turn raises issues with complexity, making sure the two databases are identical.)

