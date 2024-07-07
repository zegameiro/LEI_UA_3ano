package deti.tqs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import deti.tqs.models.Car;
import deti.tqs.repositories.CarRepository;

@Service
public class CarManagerService {
    
    private  CarRepository carRepository;

    public Car save(Car car) {
        return this.carRepository.save(car);
    } 

    public List<Car> getAllCars() {
        return this.carRepository.findAll();
    }

    public Optional<Car> getCarDetails(Long carId) {
        return Optional.ofNullable(this.carRepository.findByCarId(carId));
    }
}
