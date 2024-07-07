package deti.tqs;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import deti.tqs.models.Car;
import deti.tqs.repositories.CarRepository;

@DataJpaTest
class CarRepositoryTest {
    
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    @Test
    void whenFindModelXByID_thenReturnModelXCar() {
        Car car = new Car("Tesla", "Model X");
        entityManager.persistAndFlush(car);

        Car found = carRepository.findByCarId(car.getCardId());
        assertThat(found).isEqualTo(car);
    }

    @Test
    void whenInvalidID_thenReturnNull() {
        Car example = carRepository.findByCarId(20L);
        assertThat(example).isNull();
    }

    @Test
    void whenFindAllCars_thenReturnAllCarsFound() {
        Car car1 = new Car("Tesla", "Model X");
        Car car2 = new Car("Ford", "Focus");
        Car car3 = new Car("Toyota", "Yaris");
        Car car4 = new Car("Chevrolett", "Camaro");

        entityManager.persistAndFlush(car1);
        entityManager.persistAndFlush(car2);
        entityManager.persistAndFlush(car3);
        entityManager.persistAndFlush(car4);


        assertThat(carRepository.findAll()).hasSize(4)
            .extracting(Car::getModel)
            .containsOnly(car1.getModel(), car2.getModel(), car3.getModel(), car4.getModel());
    }
}
