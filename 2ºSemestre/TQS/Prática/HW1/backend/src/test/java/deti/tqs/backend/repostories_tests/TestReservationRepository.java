package deti.tqs.backend.repostories_tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import deti.tqs.backend.models.Reservation;
import deti.tqs.backend.repositories.ReservationRepository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@DataJpaTest
public class TestReservationRepository {
  
  @Autowired
  private ReservationRepository reservationRepository;

  @Test
  @DisplayName("When a new reservation is saved, then it should be found it by its ID")
  void whenNewReservationSaved_thenFindById() {
    Reservation reservation = new Reservation();
    reservation.setSeat(1);
    reservation.setIdBusTrip(1);
    reservation.setFirstName("John");
    reservation.setLastName("Doe");
    reservation.setEmail("johndoe@gmail.com");
    reservation.setPhone("912345678");

    reservationRepository.save(reservation);

    Reservation found = reservationRepository.findById(reservation.getId());

    assertThat(found.getId()).isEqualTo(reservation.getId());
    assertThat(found).isEqualTo(reservation);
  }

  @Test
  @DisplayName("When a new reservation is saved, then it should be found by its seat number and bus trip ID")
  void whenNewReservationSaved_thenFindBySeatAndIdBusTrip() {
    Reservation reservation = new Reservation();
    reservation.setSeat(2);
    reservation.setIdBusTrip(2);
    reservation.setFirstName("Jane");
    reservation.setLastName("Doe");
    reservation.setEmail("jane.doe@gmail.com");
    reservation.setPhone("912335678");

    reservationRepository.save(reservation);

    Reservation found = reservationRepository.findBySeatAndIdBusTrip(reservation.getSeat(), reservation.getIdBusTrip());

    assertThat(found.getSeat()).isEqualTo(reservation.getSeat());
    assertThat(found.getIdBusTrip()).isEqualTo(reservation.getIdBusTrip());
    assertThat(found).isEqualTo(reservation);
  }

  @Test
  @DisplayName("When several reservations are saved and its searched with the seat number and the ID of the bus Trip, then it should return a list containing all the added reservations") 
  void whenMultipleReservationsSaved_thenFindBySeatAndIdBusTrip() {
    Reservation reservation1 = new Reservation();
    reservation1.setSeat(3);
    reservation1.setIdBusTrip(3);
    reservation1.setFirstName("John");
    reservation1.setLastName("Doe");
    reservation1.setEmail("johndoe@gmail.com");
    reservation1.setPhone("912345678");

    Reservation reservation2 = new Reservation();
    reservation2.setSeat(4);
    reservation2.setIdBusTrip(4);
    reservation2.setFirstName("Jane");
    reservation2.setLastName("Doe");
    reservation2.setEmail("jane.doe@gmail.com");
    reservation2.setPhone("912335678");

    reservationRepository.save(reservation1);
    reservationRepository.save(reservation2);

    assertThat(reservationRepository.findBySeatAndIdBusTrip(reservation1.getSeat(), reservation1.getIdBusTrip())).isEqualTo(reservation1);
  }

  @Test
  @DisplayName("When its searched for a reservation by the ID of the bus Trip, then it should return a list containing all the added reservations")
  void whenMultipleReservationsSaved_thenFindByIdBusTrip() {
    Reservation reservation1 = new Reservation();
    reservation1.setSeat(3);
    reservation1.setIdBusTrip(3);
    reservation1.setFirstName("John");
    reservation1.setLastName("Doe");
    reservation1.setEmail("johndoe@gmail.com");
    reservation1.setPhone("912345678");

    Reservation reservation2 = new Reservation();
    reservation2.setSeat(4);
    reservation2.setIdBusTrip(4);
    reservation2.setFirstName("Jane");
    reservation2.setLastName("Doe");
    reservation2.setEmail("jane.doe@gmail.com");
    reservation2.setPhone("912335678");

    Reservation reservation3 = new Reservation();
    reservation3.setSeat(5);
    reservation3.setIdBusTrip(3);
    reservation3.setFirstName("John");
    reservation3.setLastName("Smith");
    reservation3.setEmail("john.smith@gmail.com");
    reservation3.setPhone("912345678");

    reservationRepository.save(reservation1);
    reservationRepository.save(reservation2);
    reservationRepository.save(reservation3);

    List<Reservation> found = reservationRepository.findByIdBusTrip(reservation1.getIdBusTrip());

    assertThat(found.size()).isEqualTo(2);
    assertThat(found).contains(reservation1, reservation3);
  }

  @Test
  @DisplayName("When it exists multiple reservations, then it should return a list containing all the added reservations")
  void whenMultipleReservationsSaved_thenFindAll() {
    Reservation reservation1 = new Reservation();
    reservation1.setSeat(3);
    reservation1.setIdBusTrip(3);
    reservation1.setFirstName("John");
    reservation1.setLastName("Doe");
    reservation1.setEmail("johndoe@gmail.com");
    reservation1.setPhone("912345678");

    Reservation reservation2 = new Reservation();
    reservation2.setSeat(4);
    reservation2.setIdBusTrip(4);
    reservation2.setFirstName("Jane");
    reservation2.setLastName("Doe");
    reservation2.setEmail("jane.doe@gmail.com");
    reservation2.setPhone("912335678");

    reservationRepository.save(reservation1);
    reservationRepository.save(reservation2);

    assertThat(reservationRepository.findAll().size()).isEqualTo(2);
    assertThat(reservationRepository.findAll()).contains(reservation1, reservation2);
  }
}
