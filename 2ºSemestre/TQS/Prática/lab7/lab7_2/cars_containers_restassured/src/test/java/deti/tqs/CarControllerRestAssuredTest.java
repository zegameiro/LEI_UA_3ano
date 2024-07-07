package deti.tqs;

import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

import deti.tqs.Controller.CarController;
import deti.tqs.Models.Car;
import deti.tqs.Services.CarManagerService;

@WebMvcTest(CarController.class)
public class CarControllerRestAssuredTest {
  
  @Autowired
  private MockMvc mvc;

  @MockBean
  private CarManagerService carManagerService;

  @BeforeEach
  public void setUp() {
    RestAssuredMockMvc.mockMvc(mvc);
  }

  @Test
  public void whenPostCar_thenCreateCar() {
    Car car1 = new Car("Tesla","X");
    when(carManagerService.save(car1)).thenReturn(car1);

    RestAssuredMockMvc
      .given()
        .mockMvc(mvc)
      .when()
        .get("/api/cars")
        .then()
          .statusCode(200);
  }

  @Test
  public void whenGetCarByID_thenReturnCar() {
    Car car1 = new Car("Tesla","X");
    when(carManagerService.getCarDetails(1L)).thenReturn(java.util.Optional.of(car1));

    RestAssuredMockMvc
      .given()
        .mockMvc(mvc)
      .when()
        .get("/api/car/1")
        .then()
          .statusCode(200)
          .body("maker", equalTo("Tesla"))
          .body("model", equalTo("X"));
  }

  @Test
  public void whenGetAllCars_thenReturnJsonArray() {
    Car car1 = new Car("Tesla","X");
    Car car2 = new Car("BMW","Z");

    when(carManagerService.getAllCars()).thenReturn(java.util.List.of(car1, car2));

    RestAssuredMockMvc
      .given()
        .mockMvc(mvc)
      .when()
        .get("/api/cars")
        .then()
          .statusCode(200)
          .body("$", hasSize(2))
          .body("[0].maker", equalTo("Tesla"))
          .body("[0].model", equalTo("X"))
          .body("[1].maker", equalTo("BMW"))
          .body("[1].model", equalTo("Z"));
  }


}
