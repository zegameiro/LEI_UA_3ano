package deti.tqs.backend.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import deti.tqs.backend.models.BusTrip;
import deti.tqs.backend.models.Reservation;
import deti.tqs.backend.repositories.BusTripRepository;
import deti.tqs.backend.repositories.ReservationRepository;

@Service
public class ReservationService {
  
  private static final Logger logger = LoggerFactory.getLogger(ReservationService.class);

  @Autowired
  private ReservationRepository reservationRepository;

  @Autowired
  private BusTripRepository busTripRepository;

  public boolean checkSeatAvailable(int tripId, int seatNumber) {
    logger.info("Checking if seat {0} is available for trip {1}", seatNumber, tripId);
    return reservationRepository.findBySeatAndIdBusTrip(seatNumber, tripId) == null;
  }

  public List<Reservation> findTicketsById(int tripId) {
    logger.info("Searching tickets for trip {}", tripId);
    return reservationRepository.findByIdBusTrip(tripId);
  }

  public List<Reservation> findAllTickets() {
    logger.info("Searching all tickets");
    return reservationRepository.findAll();
  }

  public Reservation buyReservation(Reservation reservation) {
    try {
      reservationRepository.save(reservation);

      BusTrip bustrip = busTripRepository.findById(reservation.getIdBusTrip());

      bustrip.getSeats().get(reservation.getSeat() - 1).setIsTaken(true);
      busTripRepository.save(bustrip);
      reservationRepository.save(reservation);

      logger.info("Reservation bought successfully");

      return reservation;

    } catch (Exception e) {
      logger.error("Error buying reservation: {}", e.getMessage());
      return null;
    }
  }

}
