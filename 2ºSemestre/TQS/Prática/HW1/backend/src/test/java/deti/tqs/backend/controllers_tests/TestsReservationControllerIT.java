package deti.tqs.backend.controllers_tests;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import deti.tqs.backend.models.Bus;
import deti.tqs.backend.models.BusTrip;
import deti.tqs.backend.models.Reservation;
import deti.tqs.backend.models.Seat;
import deti.tqs.backend.repositories.BusRepository;
import deti.tqs.backend.repositories.BusTripRepository;
import deti.tqs.backend.repositories.ReservationRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "spring.profiles.active=test")
@AutoConfigureTestDatabase
@TestInstance(Lifecycle.PER_CLASS)
public class TestsReservationControllerIT {
  
  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private ReservationRepository reservationRepository;

  @Autowired
  private BusTripRepository busTripRepository;

  @Autowired 
  private BusRepository busRepository;

  private Reservation reservation1 =new Reservation();
  private Reservation reservation2 =new Reservation();

  private BusTrip busTrip1 = new BusTrip();
  private BusTrip busTrip2 = new BusTrip();

  @BeforeAll
  void setUp() {
    
    Bus bus1 = new Bus();
    bus1.setId(1);
    bus1.setName("Flix Bus 1");
    bus1.setCapacity(34);

    Bus bus2 = new Bus();
    bus2.setId(2);
    bus2.setName("Expresso Bus 1");
    bus2.setCapacity(49);

    busRepository.saveAndFlush(bus1);
    busRepository.saveAndFlush(bus2);

    busTrip1.setBusId(1);
    busTrip1.setFromCity("Porto");
    busTrip1.setToCity("Lisboa");
    busTrip1.setDate("2014-06-12");
    busTrip1.setTime("3h43");
    busTrip1.setPrice(18.97);

    List<Seat> seats1 = new ArrayList<Seat>(bus1.getCapacity());

    for (int i = 0; i < bus1.getCapacity(); i++) {
      Seat seat = new Seat();
      if (i % 6 == 0) 
        seat.setSeatType("First Class");

      seats1.add(seat);
    }

    busTrip1.setSeats(seats1);

    busTripRepository.saveAndFlush(busTrip1);

    busTrip2.setBusId(2);
    busTrip2.setFromCity("Leiria");
    busTrip2.setToCity("Guarda");
    busTrip2.setDate("2008-07-02");
    busTrip2.setTime("2h31");
    busTrip2.setPrice(2.83);

    List<Seat> seats2 = new ArrayList<Seat>(bus2.getCapacity());

    for (int i = 0; i < bus2.getCapacity(); i++) {
      Seat seat = new Seat();

      if (i % 7 == 0) 
        seat.setSeatType("First Class");

      seats2.add(seat);
    }

    busTrip2.setSeats(seats2);

    busTripRepository.saveAndFlush(busTrip2);

    System.out.println("Bus Trips created" +  busTrip1.getId() + " " +  busTrip2.getId());

    reservation1.setFirstName("John");
    reservation1.setLastName("Doe");
    reservation1.setEmail("john.doe@gmail.com");
    reservation1.setIdBusTrip(busTrip1.getId());
    reservation1.setSeat(1);
    reservation1.setPhone("987654321");

    reservation2.setFirstName("Jane");
    reservation2.setLastName("Doe");
    reservation2.setEmail("jane.doe@gmail.com");
    reservation2.setIdBusTrip(busTrip2.getId());
    reservation2.setSeat(4);
    reservation2.setPhone("123456789");

    reservationRepository.saveAndFlush(reservation1);
    reservationRepository.saveAndFlush(reservation2);

  }

  @SuppressWarnings("null")
  @Test
  @DisplayName("Test if all the reservations are retrieved correctly")
  void testGetReservations() throws Exception {

    ResponseEntity<Reservation[]> response = restTemplate.getForEntity("/api/reservations/list", Reservation[].class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).hasSize(2);
    assertThat(response.getBody()[0].getFirstName()).isEqualTo("John");
    assertThat(response.getBody()[1].getFirstName()).isEqualTo("Jane");

  }

  @SuppressWarnings("null")
  @Test 
  @DisplayName("Test create a reservation with success")
  void testCreateReservation() throws Exception {
    Reservation res1 = new Reservation();
    res1.setFirstName("John");
    res1.setLastName("Smith");
    res1.setEmail("john@gmail.com");
    res1.setIdBusTrip(busTrip1.getId());
    res1.setSeat(2);
    res1.setPhone("987654321");

    ResponseEntity<Reservation> response = restTemplate.postForEntity("/api/reservations/buy", res1, Reservation.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody().getLastName()).isEqualTo("Smith");
  }

  @Test
  @DisplayName("Test create 2 tickets for the same trip and seat")
  void testCreateReservationSameSeat() throws Exception {
    Reservation res1 = new Reservation();
    res1.setFirstName("John");
    res1.setLastName("Smith");
    res1.setEmail("john.smith@ua.pt");
    res1.setIdBusTrip(busTrip1.getId());
    res1.setSeat(1);
    res1.setPhone("987654321");

    ResponseEntity<Reservation> response = restTemplate.postForEntity("/api/reservations/buy", res1, Reservation.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
  }

  @Test
  @DisplayName("Test create a reservation with invalid email")
  void testCreateReservationInvalidEmail() throws Exception {
    Reservation res1 = new Reservation();
    res1.setFirstName("John");
    res1.setLastName("Smith");
    res1.setEmail("john");
    res1.setIdBusTrip(busTrip1.getId());
    res1.setSeat(2);
    res1.setPhone("987654321");

    ResponseEntity<Reservation> response = restTemplate.postForEntity("/api/reservations/buy", res1, Reservation.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
  }

  @Test
  @DisplayName("Test create a reservation with invalid phone")
  void testCreateReservationInvalidPhone() throws Exception {
    Reservation res1 = new Reservation();
    res1.setFirstName("John");
    res1.setLastName("Smith");
    res1.setEmail("johnah@gmail.com");
    res1.setIdBusTrip(busTrip1.getId());
    res1.setSeat(2);
    res1.setPhone("987654321012242");

    ResponseEntity<Reservation> response = restTemplate.postForEntity("/api/reservations/buy", res1, Reservation.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
  }

  @Test 
  @DisplayName("Test create a reservation with invalid trip id")
  void testCreateReservationInvalidTripId() throws Exception {
    Reservation res1 = new Reservation();
    res1.setFirstName("John");
    res1.setLastName("Smith");
    res1.setEmail("h@gmail.com");
    res1.setIdBusTrip(10023);
    res1.setSeat(2);
    res1.setPhone("987654321");

    ResponseEntity<Reservation> response = restTemplate.postForEntity("/api/reservations/buy", res1, Reservation.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
  }

}
