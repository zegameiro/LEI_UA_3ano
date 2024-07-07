package deti.tqs.backend.services_tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import deti.tqs.backend.models.Bus;
import deti.tqs.backend.repositories.BusRepository;
import deti.tqs.backend.services.BusService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TestsBusService {

  @Mock
  private BusRepository busRepository;

  @InjectMocks
  private BusService busService;

  @Test
  @DisplayName("When a new bus is saved, then it should be find it by its ID")
  void whenNewBusSaved_thenFindById() {
    Bus bus = new Bus();
    bus.setName("Flix Bus 1");
    bus.setCapacity(50);

    when(busRepository.findById(1)).thenReturn(bus);

    assertThat(busService.getBusById(1)).isEqualTo(bus);

    verify(busRepository, times(1)).findById(anyInt());
  }

  @Test
  @DisplayName("Check get all buses")
  void checkGetAllBuses() {
    Bus bus1 = new Bus();
    bus1.setName("Flix Bus 3");
    bus1.setCapacity(78);

    Bus bus2 = new Bus();
    bus2.setName("Flix Bus 4");
    bus2.setCapacity(45);

    Bus bus3 = new Bus();
    bus3.setName("Flix Bus 5");
    bus3.setCapacity(30);

    when(busRepository.findAll()).thenReturn(List.of(bus1, bus2, bus3));

    assertThat(busService.getAllBuses()).contains(bus1, bus2, bus3).hasSize(3);

    verify(busRepository, times(1)).findAll();
  }

  @Test
  @DisplayName("Check save bus")
  void checkSaveBus() {
    Bus bus = new Bus();
    bus.setName("Flix Bus 6");
    bus.setCapacity(60);

    when(busRepository.save(bus)).thenReturn(bus);

    assertThat(busService.addBus(bus)).isEqualTo(bus);

    verify(busRepository, times(1)).save(bus);
  }

  
  
}
