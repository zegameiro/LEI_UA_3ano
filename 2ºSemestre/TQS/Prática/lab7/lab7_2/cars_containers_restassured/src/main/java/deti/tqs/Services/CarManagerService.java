package deti.tqs.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import deti.tqs.Models.Car;
import deti.tqs.Repositories.CarRepository;

@Service
public class CarManagerService {
    
    @Autowired
    CarRepository carRepository;

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
