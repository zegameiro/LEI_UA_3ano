package deti.tqs.backend.repostories_tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import deti.tqs.backend.models.Bus;
import deti.tqs.backend.repositories.BusRepository;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TestBusRepository {
  
  @Autowired
  private BusRepository busRepository;

  @Test
  @DisplayName("When a new bus is saved, then it should be find it by its ID")
  void whenNewBusSaved_thenFindById() {
    Bus bus = new Bus();
    bus.setName("Flix Bus 1");
    bus.setCapacity(50);

    busRepository.save(bus);

    Bus found = busRepository.findById(bus.getId());

    assertThat(found).isEqualTo(bus);
  }

  @Test
  @DisplayName("When a new bus is saved, then it should be foundt by its name")
  void whenNewBusSaved_thenFindByName() {
    Bus bus = new Bus();
    bus.setName("Flix Bus 2");
    bus.setCapacity(92);

    busRepository.save(bus);

    Bus found = busRepository.findByName(bus.getName());

    assertThat(found.getName()).isEqualTo(bus.getName());
    assertThat(found).isEqualTo(bus);
  }

  @Test
  @DisplayName("When multiple buses are saved, then it should return a list containing all the added buses")
  void whenMultipleBusesSaved_thenFindAll() {
    Bus bus1 = new Bus();
    bus1.setName("Flix Bus 3");
    bus1.setCapacity(78);

    Bus bus2 = new Bus();
    bus2.setName("Flix Bus 4");
    bus2.setCapacity(45);

    Bus bus3 = new Bus();
    bus3.setName("Flix Bus 5");
    bus3.setCapacity(30);

    busRepository.save(bus1);
    busRepository.save(bus2);
    busRepository.save(bus3);

    assertThat(busRepository.findAll().size()).isEqualTo(3);
    assertThat(busRepository.findAll()).contains(bus1, bus2, bus3);
  }

  @Test
  @DisplayName("When a bus is deleted by its ID, then it should not be found by its ID")
  void whenBusDeleted_thenFindById() {
    Bus bus = new Bus();
    bus.setName("Flix Bus 3");
    bus.setCapacity(12);

    busRepository.save(bus);

    busRepository.deleteById(bus.getId());
    Bus found = busRepository.findById(bus.getId());

    assertThat(found).isNull();
  }

  @Test
  @DisplayName("When all buses are deleted, then the list of buses should be empty")
  void whenAllBusesDeleted_thenFindAll() {
    Bus bus1 = new Bus();
    bus1.setName("Flix Bus 4");
    bus1.setCapacity(78);

    Bus bus2 = new Bus();
    bus2.setName("Flix Bus 5");
    bus2.setCapacity(45);

    Bus bus3 = new Bus();
    bus3.setName("Flix Bus 6");
    bus3.setCapacity(30);

    busRepository.save(bus1);
    busRepository.save(bus2);
    busRepository.save(bus3);

    busRepository.deleteAll();

    assertThat(busRepository.findAll()).isEmpty();
  }

}
