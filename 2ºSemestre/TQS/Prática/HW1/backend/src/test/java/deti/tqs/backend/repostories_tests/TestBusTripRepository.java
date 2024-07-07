package deti.tqs.backend.repostories_tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import deti.tqs.backend.models.BusTrip;
import deti.tqs.backend.repositories.BusTripRepository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@DataJpaTest
public class TestBusTripRepository {
  
  @Autowired
  private BusTripRepository busTripRepository;

  @Test
  @DisplayName("When a new bus trip is saved, then it should be found it by its ID")
  void whenNewBusTripSaved_thenFindById() {
    BusTrip busTrip = new BusTrip();
    busTrip.setFromCity("Lagos");
    busTrip.setToCity("Santarém");
    busTrip.setDate("2021-06-01");
    busTrip.setTime("11h32");
    busTrip.setPrice(12.45);
    busTrip.setBusId(1);

    busTripRepository.save(busTrip);

    BusTrip found = busTripRepository.findById(busTrip.getId());

    assertThat(found.getId()).isEqualTo(busTrip.getId());
    assertThat(found).isEqualTo(busTrip);
  }

  @Test
  @DisplayName("When several buses trips are saved, then it should return a list containing all the added buses trips")
  void whenMultipleBusTripsSaved_thenFindAll() {
    BusTrip busTrip1 = new BusTrip();
    busTrip1.setFromCity("Coimbra");
    busTrip1.setToCity("Figueira da Foz");
    busTrip1.setDate("1998-12-12");
    busTrip1.setTime("8h30");
    busTrip1.setPrice(2.17);
    busTrip1.setBusId(1);

    BusTrip busTrip2 = new BusTrip();
    busTrip2.setFromCity("Lisboa");
    busTrip2.setToCity("Porto");
    busTrip2.setDate("2011-10-03");
    busTrip2.setTime("10h28");
    busTrip2.setPrice(0.12);
    busTrip2.setBusId(2);

    BusTrip busTrip3 = new BusTrip();
    busTrip3.setFromCity("Vilar dos Mouros");
    busTrip3.setToCity("Barcelos");
    busTrip3.setDate("2024-08-21");
    busTrip3.setTime("00h31");
    busTrip3.setPrice(2000.83);
    busTrip3.setBusId(3);

    BusTrip busTrip4 = new BusTrip();
    busTrip4.setFromCity("Pombal");
    busTrip4.setToCity("Leiria");
    busTrip4.setDate("2009");
    busTrip4.setTime("00h35");
    busTrip4.setPrice(3.45);
    busTrip4.setBusId(4);

    busTripRepository.save(busTrip1);
    busTripRepository.save(busTrip2);
    busTripRepository.save(busTrip3);
    busTripRepository.save(busTrip4);

    assertThat(busTripRepository.findAll().size()).isEqualTo(4);
    assertThat(busTripRepository.findAll()).contains(busTrip1, busTrip2, busTrip3);
  }

  @Test
  @DisplayName("When a bus trip is added, then it should not be found by its from city")
  void whenBusTripAdded_thenFindByFromCity() {
    BusTrip busTrip = new BusTrip();
    busTrip.setFromCity("Lisboa");
    busTrip.setToCity("Porto");
    busTrip.setDate("2011-10-03");
    busTrip.setTime("10h28");
    busTrip.setPrice(0.12);
    busTrip.setBusId(2);

    busTripRepository.save(busTrip);

    List<BusTrip> found = busTripRepository.findByFromCity(busTrip.getFromCity());

    assertThat(found.size()).isEqualTo(1);
    assertThat(found).contains(busTrip);
  }

  @Test
  @DisplayName("When a bus trip is added, then it should not be found by its to city")
  void whenBusTripAdded_thenFindBytoCity() {
    BusTrip busTrip1 = new BusTrip();
    busTrip1.setFromCity("Leiria");
    busTrip1.setToCity("Porto");
    busTrip1.setDate("1999-03-20");
    busTrip1.setTime("3h27");
    busTrip1.setPrice(2.89);
    busTrip1.setBusId(2);

    BusTrip busTrip2 = new BusTrip();
    busTrip2.setFromCity("Castelo Branco");
    busTrip2.setToCity("Porto");
    busTrip2.setDate("2007-05-20");
    busTrip2.setTime("6h34");
    busTrip2.setPrice(15.28);
    busTrip2.setBusId(2);

    busTripRepository.save(busTrip1);
    busTripRepository.save(busTrip2);

    List<BusTrip> found = busTripRepository.findByToCity(busTrip1.getToCity());

    assertThat(found.size()).isEqualTo(2);
    assertThat(found).contains(busTrip1, busTrip2);
  }

  @Test
  @DisplayName("When a bus trip is added, then it should not be found by its date")
  void whenBusTripAdded_thenFindByDate() {
    BusTrip busTrip1 = new BusTrip();
    busTrip1.setFromCity("São João da Madeira");
    busTrip1.setToCity("Espinho");
    busTrip1.setDate("1999-04-21");
    busTrip1.setTime("3h27");
    busTrip1.setPrice(2.89);
    busTrip1.setBusId(2);

    BusTrip busTrip2 = new BusTrip();
    busTrip2.setFromCity("Barracão");
    busTrip2.setToCity("Meirinhas");
    busTrip2.setDate("1999-04-21");
    busTrip2.setTime("5h09");
    busTrip2.setPrice(14.20);
    busTrip2.setBusId(3);

    BusTrip busTrip3 = new BusTrip();
    busTrip3.setFromCity("Braga");
    busTrip3.setToCity("Guarda");
    busTrip3.setDate("1999-04-21");
    busTrip3.setTime("3h27");
    busTrip3.setPrice(45.78);
    busTrip3.setBusId(1);


    busTripRepository.save(busTrip1);
    busTripRepository.save(busTrip2);
    busTripRepository.save(busTrip3);

    List<BusTrip> found = busTripRepository.findByDate(busTrip1.getDate());

    assertThat(found.size()).isEqualTo(3);
    assertThat(found).contains(busTrip1, busTrip2, busTrip3);
  }

  @Test
  @DisplayName("When a bus trip is deleted by its from city, then it should not be found by its from city")
  void whenBusTripDeleted_thenFindByFromCity() {
    BusTrip busTrip = new BusTrip();
    busTrip.setFromCity("Lisboa");
    busTrip.setToCity("Porto");
    busTrip.setDate("2011-10-03");
    busTrip.setTime("10h28");
    busTrip.setPrice(0.12);
    busTrip.setBusId(2);

    busTripRepository.save(busTrip);

    busTripRepository.deleteByFromCity(busTrip.getFromCity());

    List<BusTrip> found = busTripRepository.findByFromCity(busTrip.getFromCity());

    assertThat(found.size()).isEqualTo(0);
    assertThat(found).doesNotContain(busTrip);
  }

  @Test
  @DisplayName("When a bus trip is deleted by its to city, then it should not be found by its to city")
  void whenBusTripDeleted_thenFindByToCity() {
    BusTrip busTrip1 = new BusTrip();
    busTrip1.setFromCity("Leiria");
    busTrip1.setToCity("Porto");
    busTrip1.setDate("1999-03-20");
    busTrip1.setTime("3h27");
    busTrip1.setPrice(2.89);
    busTrip1.setBusId(2);

    BusTrip busTrip2 = new BusTrip();
    busTrip2.setFromCity("Castelo Branco");
    busTrip2.setToCity("Porto");
    busTrip2.setDate("2007-05-20");
    busTrip2.setTime("6h34");
    busTrip2.setPrice(15.28);
    busTrip2.setBusId(2);

    busTripRepository.save(busTrip1);
    busTripRepository.save(busTrip2);

    busTripRepository.deleteByToCity(busTrip1.getToCity());

    List<BusTrip> found = busTripRepository.findByToCity(busTrip1.getToCity());

    assertThat(found.size()).isEqualTo(0);
    assertThat(found).doesNotContain(busTrip1, busTrip2);
  }

  @Test
  @DisplayName("When its searched for a bustrip by its from city, then it should return a list containing all the bustrips with that from city")
  void whenFindByFromCity_thenReturnBusTrips() {
    BusTrip busTrip1 = new BusTrip();
    busTrip1.setFromCity("Pombal");
    busTrip1.setToCity("Lisboa");
    busTrip1.setDate("1999-03-20");
    busTrip1.setTime("3h27");
    busTrip1.setPrice(11000.23);
    busTrip1.setBusId(2);

    BusTrip busTrip2 = new BusTrip();
    busTrip2.setFromCity("Pombal");
    busTrip2.setToCity("Porto");
    busTrip2.setDate("2007-05-20");
    busTrip2.setTime("6h34");
    busTrip2.setPrice(15.28);
    busTrip2.setBusId(2);

    busTripRepository.save(busTrip1);
    busTripRepository.save(busTrip2);

    List<BusTrip> found = busTripRepository.findByFromCity(busTrip1.getFromCity());

    assertThat(found.size()).isEqualTo(2);
    assertThat(found).contains(busTrip1, busTrip2);
  }

  @Test
  @DisplayName("When its searched for a bustrip by its to city, then it should return a list containing all the bustrips with that to city")
  void whenFindByToCity_thenReturnBusTrips() {
    BusTrip busTrip1 = new BusTrip();
    busTrip1.setFromCity("Algarve");
    busTrip1.setToCity("Lisboa");
    busTrip1.setDate("1999-03-20");
    busTrip1.setTime("3h27");
    busTrip1.setPrice(567.2);
    busTrip1.setBusId(2);

    BusTrip busTrip2 = new BusTrip();
    busTrip2.setFromCity("Pombal");
    busTrip2.setToCity("Lisboa");
    busTrip2.setDate("2007-05-20");
    busTrip2.setTime("6h34");
    busTrip2.setPrice(789.12);
    busTrip2.setBusId(1);

    BusTrip busTrip3 = new BusTrip();
    busTrip3.setFromCity("Bombarral");
    busTrip3.setToCity("Espinho");
    busTrip3.setDate("2007-05-20");
    busTrip3.setTime("6h34");
    busTrip3.setPrice(56.21);
    busTrip3.setBusId(1);

    busTripRepository.save(busTrip1);
    busTripRepository.save(busTrip2);
    busTripRepository.save(busTrip3);

    List<BusTrip> found = busTripRepository.findByToCity(busTrip1.getToCity());

    assertThat(found.size()).isEqualTo(2);
    assertThat(found).contains(busTrip1, busTrip2);
  }

}