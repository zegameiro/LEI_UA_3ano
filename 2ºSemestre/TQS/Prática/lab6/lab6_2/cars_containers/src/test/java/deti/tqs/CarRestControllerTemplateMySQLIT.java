package deti.tqs;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import deti.tqs.models.Car;
import deti.tqs.repositories.CarRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class CarRestControllerTemplateMySQLIT {
    
    @LocalServerPort
    int randomServerPort;

    // Rest Client
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository carRepository;

    @AfterEach
    void resetDb() {
        carRepository.deleteAll();
    }

    @Test
    void whenValidInput_thenCreateCar() {
        Car car1 = new Car("Tesla", "Model X");
        ResponseEntity<Car> entity = restTemplate.postForEntity("/api/addCar", car1, Car.class);

        List<Car> found = carRepository.findAll();
        assertThat(found).extracting(Car::getMaker).containsOnly("Tesla");
    }

    @Test
    void whenGetCarByID_thenReturnCar() {
        Car car1 = new Car("Tesla", "Model X");
        carRepository.saveAndFlush(car1);

        ResponseEntity<Car> response = restTemplate.getForEntity("/api/car/" + car1.getCardId(), Car.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(car1);
    }

    @Test
    void givenCars_whenGetCars_thenStatus200() {
        createTestCar("Tesla", "Model X");
        createTestCar("Mercedez", "A200");
        createTestCar("Ford", "Focus");

        ResponseEntity<List<Car>> response = restTemplate
            .exchange("/api/cars", HttpMethod.GET,null, new ParameterizedTypeReference<>() {
        });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).extracting(Car::getMaker).containsExactly("Tesla", "Mercedez", "Ford");

    }

    private void createTestCar(String maker, String model) {
        Car car = new Car(maker, model);
        carRepository.saveAndFlush(car);
    }
}
