package deti.tqs.backend.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import deti.tqs.backend.models.BusTrip;
import deti.tqs.backend.models.Reservation;
import deti.tqs.backend.models.Seat;
import deti.tqs.backend.services.BusTripService;
import deti.tqs.backend.services.ReservationFormValidator;
import deti.tqs.backend.services.ReservationService;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/api/reservations")
public class ReservationController {
  
  private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);

  @Autowired
  private ReservationService reservationService;

  @Autowired
  private BusTripService busTripService;

  @Autowired
  private ReservationFormValidator reservationFormValidator;

  @PostMapping("/buy")
  ResponseEntity<Reservation> buyReservation(@RequestBody Reservation reservation) {

    logger.info("Buying reservation for trip %d, and seat %d", reservation.getIdBusTrip(), reservation.getSeat());

    if (!busTripService.tripExists(reservation.getIdBusTrip())) {
      logger.error("Could not find trip with id {}",reservation.getIdBusTrip());
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trip not found!");
    }

    if (!reservationFormValidator.validateEmail(reservation.getEmail())) {
      logger.error("Invalid email format");
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid email format!");
    }

    if (!reservationFormValidator.validatePhone(reservation.getPhone())) {
      logger.error("Invalid phone format");
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid phone format!");
    }

    BusTrip busTrip = busTripService.getBusTripById(reservation.getIdBusTrip(), "EUR");

    int requestedSeat = reservation.getSeat() - busTrip.getSeats().get(0).getId();
    reservation.setSeat(requestedSeat);

    List<Seat> seats = busTrip.getSeats();

    Seat seat = seats.get(requestedSeat); 

    if (seat.getIsTaken()) {
      logger.error("Seat already taken");
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Seat already taken!");
    }

    Reservation r = reservationService.buyReservation(reservation);

    return ResponseEntity.ok(r);
  }

  @GetMapping("/list")
  ResponseEntity<List<Reservation>> listReservations() {
    List<Reservation> reservations = reservationService.findAllTickets();
    logger.info("List of reservations requested");
    return ResponseEntity.ok(reservations);
  }
}
