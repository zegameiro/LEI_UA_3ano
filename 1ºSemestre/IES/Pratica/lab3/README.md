# Lab 3 Notes

## Jakarta Persistance API
Jakarta Persistence API is a Jakarta EE application programming interface specification that describes the management of relational data in enterprise Java applications.

Persistence in this context covers three areas:
- The API itself, defined in the jakarta.persistence package
- The Jakarta Persistence Query Language
- Object/relational metadata. 

## Exercise 1 c)

### The "UserController" class gets an instance of "userRepository" through its constructor; how is this new repository instantiated?

The userRepository is instantiated and injected into the UserController using SpringBoot dependency injection. This is achieved through constructor injection. The Spring Data JPA automatically provides an implementation for the UserRepository interface, which extends from JpaRepository .

### List the methods invoked in the userRepository object by the UserController. Where are these methods defined?

The UserController typically uses methods provided by the userRepository to interact with the database. Here are some of the commom methods:
- findAll(): Returns a list of all the users;
- findById(): Returns a user by his Id;
- save(): Saves a new user or update an existing one;
- delete(): Deletes an existing user.

These methods are not explicity defined in the UserController class, they are defined in the UserRepository interface. The Spring Data JPA automatically provides implementations for these methods.

### Where is the data being saved?

The data is typically being saved in an H2 in-memory database because the dependency H2 Database was added to the project pom.xml. This is a temporary database used for development and testing. THe actual database configuration, including connection, details and schema, is typically specified are normally specified in the aplication.properties file.

### Where is the rule for the "non empty" email defined?

The rule for not empty email address is typically defined using JavaBean Validation annotations. These can be found in the entity class Person, like ```@NotBlank``` or ```@NonEMpty``` and their purpose is to assure that it should not be empty. These annotations are part of the JavaBean Validation API, and Spring automatically validadtes the entity according to these rules when data is saved or updated using JPA

### MySQL Container
Instead of installing MySQL locally I used a Docker Container using this command:
```bash
sudo docker run --name mysql5 -e MYSQL_ROOT_PASSWORD=secret1 -e MYSQL_DATABASE=demo -e MYSQL_USER=demo -e MYSQL_PASSWORD=secret2 -p 33060:3306 -d mysql/mysql-server:5.7
```

- The option ```--name``` defines the name of the container which in this case is mysql5;
- The option ```-e MYSQL_ROOT_PASSWORD=secret1``` defines the password for the user ROOT has secret1 for the user to enter MySQL cli;
- The option ```-e MYSQL_DATABASE=demo``` defines the name of the database that will be created;
- The option ```-e MYSQL_USER=demo``` defines a new user named demo to enter the MySQL cli;
- The option ```-e MYSQL_PASSWORD=secret2``` defines the password for the user demo to enter the MYSQL cli;
- The option ```-p 33060:3306``` defines the ports that the container will be listening;
-  The option ```-d mysql/mysql-server:5.7``` tells the DOcker to run nthe image from mysql.
