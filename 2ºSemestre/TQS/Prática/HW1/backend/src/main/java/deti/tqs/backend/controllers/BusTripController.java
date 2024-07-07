package deti.tqs.backend.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import deti.tqs.backend.models.BusTrip;
import deti.tqs.backend.services.BusTripService;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/api/bustrips")
public class BusTripController {
  
  private static final Logger logger = LoggerFactory.getLogger(BusTripController.class);

  @Autowired
  private BusTripService busTripService;

  @GetMapping("/get")
  ResponseEntity<List<BusTrip>> listBusTrips(@RequestParam(required = false) String fromCity, @RequestParam(required = false) String toCity, @RequestParam(required = false) String date, @RequestParam(required = false) String currency) {
    logger.info("List of bus trips requested");
    logger.info("From city: {}", fromCity);
    logger.info("To city: {}", toCity);
    logger.info("Date: {}", date);
    return ResponseEntity.ok(busTripService.listFilteredTrips(fromCity, toCity, date, currency));
  }

  @GetMapping("/get_bus_trip")
  ResponseEntity<BusTrip> getBusTrip(@RequestParam int id, @RequestParam(required = false) String currency) {
    BusTrip busTrip = busTripService.getBusTripById(id, currency);
    logger.info("Bus trip with id {} requested", id);
    return ResponseEntity.ok(busTrip);
  }

  @GetMapping("/get_dates")
  ResponseEntity<List<String>> getDates() {
    logger.info("List of dates requested");
    return ResponseEntity.ok(busTripService.getDates());
  }

  @GetMapping("/get_from_cities")
  ResponseEntity<List<String>> getFromCities() {
    logger.info("List of from cities requested");
    return ResponseEntity.ok(busTripService.getFromCities());
  }

  @GetMapping("/get_to_cities")
  ResponseEntity<List<String>> getToCities() {
    logger.info("List of to cities requested");
    return ResponseEntity.ok(busTripService.getToCities());
  }
}
