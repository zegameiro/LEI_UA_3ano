package deti.tqs;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import deti.tqs.Models.Car;
import deti.tqs.Repositories.CarRepository;
import deti.tqs.Services.CarManagerService;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @Mock(lenient = true)
    private CarRepository carRepository;   
    
    @InjectMocks
    private CarManagerService carManagerService;

    @BeforeEach
    public void setUp() {
        Car car1 = new Car("Tesla","X");
        car1.setCardId(14L);

        Car car2 = new Car("Ford","Focus");
        Car car3 = new Car("Toyota","Yaris");
        Car car4 = new Car("Chevrolett","Camaro");

        List<Car> allCars = List.of(car1, car2, car3, car4);

        when(carRepository.findByCarId(car1.getCardId())).thenReturn(car1);
        when(carRepository.findByCarId(car2.getCardId())).thenReturn(car2);
        when(carRepository.findByCarId(car3.getCardId())).thenReturn(car3);
        when(carRepository.findByCarId(car4.getCardId())).thenReturn(car4);
        when(carRepository.findAll()).thenReturn(allCars);
    }

    @Test
    public void whenSearchValidId_thenCarShouldBeFound() {
        long id = 14L;

        Optional<Car> searchResult = carManagerService.getCarDetails(id);
        assertThat(searchResult.isPresent()).isTrue();
        assertThat(searchResult.get().getCardId()).isEqualTo(id);

        verify(carRepository, Mockito.times(1)).findByCarId(Mockito.anyLong());
    }

    @Test
    public void whenSearchInvalidId_thenCarShouldNotBeFound() {
        long id = -1L;

        Optional<Car> searchResult = carManagerService.getCarDetails(id);
        assertThat(searchResult.isPresent()).isFalse();

        verify(carRepository, Mockito.times(1)).findByCarId(Mockito.anyLong());
    }

    @Test
    public void whenGetAllCars_thenReturnAllCars() {
        Car car1 = new Car("Tesla","X");
        Car car2 = new Car("Ford","Focus");
        Car car3 = new Car("Toyota","Yaris");
        Car car4 = new Car("Chevrolett","Camaro");

        List<Car> allCars = carManagerService.getAllCars();

        assertThat(allCars).hasSize(4)
                .extracting(Car::getMaker)
                .contains(car1.getMaker(), car2.getMaker(), car3.getMaker(), car4.getMaker());

        verify(carRepository, Mockito.times(1)).findAll();
    }



}
