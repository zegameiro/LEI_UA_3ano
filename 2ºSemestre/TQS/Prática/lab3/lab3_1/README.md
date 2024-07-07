# Lab3 Exercises

## 3.1 Employee manager example

### a) Identify a couple of examples that use AssertJ expressive methods chaining.

```java
// Test givenSetOfEmployees_whenFindAll_thenReturnAllEmployees()
assertThat(allEmployees).hasSize(3).extracting(Employee::getName).containsOnly(alex.getName(), ron.getName(), bob.getName());

// Test givenEmployees_whenGetEmployees_thenStatus200()
assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
assertThat(response.getBody()).extracting(Employee::getName).containsExactly("bob", "alex");

// Test whenSearchValidName_thenEmployeeShouldBeFound()
assertThat(found.getName()).isEqualTo(name);
```

### b) Identify an example in which you mock the behavior of the repository (and avoid involving a database). 

```java
// TestwhenPostEmployee_thenCreateEmployee
mvc.perform(post("/api/employees").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(alex)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.name", is("alex")));

// Test givenManyEmployees_whenGetEmployees_thenReturnJsonArray()
mvc.perform(get("/api/employees").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(3)))
            .andExpect(jsonPath("$[0].name", is(alex.getName())))
            .andExpect(jsonPath("$[1].name", is(john.getName())))
            .andExpect(jsonPath("$[2].name", is(bob.getName())));
```

### c) What is the difference between standard @Mock and @MockBean?

The annotation @Mock is a shorthand for the Mockito.mock() method. This annotation it should only be used in a test class.

The Mockito.mock() method allows the creation of a mock object that refer to a class or an interface. It can then use the mock to stub return values for its methods and verify if they were called.

The @MockBean is a annotation used to add mock objects to a Spring application context. The mock will replace any existing bean of the same type in the application context.

### d) What is the role of the file “application-integrationtest.properties”? In which conditions will it be used?

The application-integrationtest.properties contains the details to configure the persistence storage:

```properties
## note changed port 3306 --> 33060
spring.datasource.url=jdbc:mysql://localhost:33060/tqsdemo
spring.jpa.hibernate.ddl-auto=create-drop
spring.datasource.username=demo
spring.datasource.password=demo


## db
## docker run --name mysql5tqs -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=tqsdemo -e MYSQL_USER=demo -e MYSQL_PASSWORD=demo -p 33060:3306 -d mysql/mysql-server:5.7

```

If we decided to run the integration tests against H2, we could just change the above values.

### e) The sample project demonstrates three test strategies to assess an API (C, D and E) developed with SpringBoot. Which are the main/key differences?

The main differences between the three strategies (C, D and E) for assessing a SpringBoot API are centered around the scope of the aplication context they load, the level of integration they simulate, and the way they interact with the application's components.

Notes:
- The strategy C uses the @WebMvcTest annotation for focused unit tests on the web layer, mocking out other components;

- The strategy D uses the @SpringBootTest annotation with a @MockMvc for integration testing that involves interactions between different layers of the application;

- The strategy E uses the @SpringBootTest annotation with a @TestRestTemplate for end-to-end testing that simulates real HTTP requests and responses.
