package deti.tqs;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RestAssuredTest {

  public String BASE_URL = "https://jsonplaceholder.typicode.com/todos";
  
  @Test
  public void testAllToDos() {
    when()
      .get(BASE_URL)
    .then()
      .statusCode(200);
  }

  @Test
  public void testToDo4() {
    when()
      .get(BASE_URL + "/4")
    .then()
      .statusCode(200)
      .body("id", equalTo(4))
      .body("title", equalTo("et porro tempora"));
  }

  @Test
  public void testAllToDosIds198_199() {
    when()
      .get(BASE_URL)
    .then()
      .statusCode(200)
      .body("id", hasItems(198, 199));
  }

  @Test
  public void testAllToDosTimeLessThan2s() {
    when()
      .get(BASE_URL)
    .then()
      .statusCode(200)
      .time(lessThan(2000L));
  }

}
