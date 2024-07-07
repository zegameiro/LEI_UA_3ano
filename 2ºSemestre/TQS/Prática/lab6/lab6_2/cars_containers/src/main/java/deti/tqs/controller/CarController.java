package deti.tqs.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import deti.tqs.models.Car;
import deti.tqs.services.CarManagerService;

@RestController
@RequestMapping("/api")
public class CarController {

    private final CarManagerService carManagerService;

    CarController(CarManagerService carManagerService) {
        this.carManagerService = carManagerService;
    }

    @GetMapping(path = "/cars", produces = "application/json")
    List<Car> getAllCars() {
        return carManagerService.getAllCars();
    }

    @GetMapping(path = "/car/{id}")
    ResponseEntity<Car> getCarbyId(@PathVariable(value = "id") Long carId) throws ResourceNotFoundException {
            Car car = carManagerService.getCarDetails(carId)
            .orElseThrow(() -> new ResourceNotFoundException("Car not found for this id: " + carId));

        return ResponseEntity.ok().body(car);
    }

    @PostMapping("/addCar")
    ResponseEntity<Car> createCar(@RequestBody Car car) {
        HttpStatus status = HttpStatus.CREATED;
        Car saved = carManagerService.save(car);
        return new ResponseEntity<>(saved, status);
    }
}
