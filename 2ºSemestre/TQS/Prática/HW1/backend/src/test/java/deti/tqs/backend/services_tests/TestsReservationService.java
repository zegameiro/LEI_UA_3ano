package deti.tqs.backend.services_tests;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import deti.tqs.backend.models.Reservation;
import deti.tqs.backend.repositories.BusTripRepository;
import deti.tqs.backend.repositories.ReservationRepository;
import deti.tqs.backend.services.ReservationService;

@ExtendWith(MockitoExtension.class)
public class TestsReservationService {
  
  @Mock
  private ReservationRepository reservationRepository;

  @Mock
  private BusTripRepository busTripRepository;

  @InjectMocks
  private ReservationService reservationService;

  @Test
  @DisplayName("Check if all the reservations are returned")
  void testGetAllReservations() {

    Reservation r1 = new Reservation();
    Reservation r2 = new Reservation();
    Reservation r3 = new Reservation();

    when(reservationRepository.findAll()).thenReturn(List.of(r1, r2, r3));

    assertThat(reservationService.findAllTickets()).contains(r3, r1, r2).hasSize(3);

    verify(reservationRepository, times(1)).findAll();
  }

  @Test
  @DisplayName("Test if a reservation is returned, when searched by its id")
  void testGetReservationById() {
    Reservation r = new Reservation();

    when(reservationRepository.findByIdBusTrip(1)).thenReturn(List.of(r));

    assertThat(reservationService.findTicketsById(1)).contains(r).hasSize(1);

    verify(reservationRepository, times(1)).findByIdBusTrip(1);
  }

  @Test
  @DisplayName("Test if a seat is available")
  void testSeatIsAvailable() {
    when(reservationRepository.findBySeatAndIdBusTrip(1, 1)).thenReturn(null);
    when(reservationRepository.findBySeatAndIdBusTrip(1, 2)).thenReturn(new Reservation());

    assertThat(reservationService.checkSeatAvailable(1, 1)).isTrue();
    assertThat(reservationService.checkSeatAvailable(2, 1)).isFalse();

    verify(reservationRepository, times(2)).findBySeatAndIdBusTrip(anyInt(), anyInt());
  }

  @Test
  @DisplayName("Test the method to buy a reservation when given invalid data")
  void testBuyReservation() {
    Reservation res = new Reservation();
    res.setFirstName("John");
    res.setLastName("Doe");
    res.setEmail("someone@gmail.com");
    res.setPhone("234567812");
    res.setIdBusTrip(2);
    res.setSeat(1);

    when(reservationRepository.save(any())).thenReturn(null);

    assertThat(reservationService.buyReservation(null)).isNull();

    verify(reservationRepository, times(1)).save(any());
  }
}
