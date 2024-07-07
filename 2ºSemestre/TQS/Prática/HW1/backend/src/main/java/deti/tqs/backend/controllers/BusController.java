package deti.tqs.backend.controllers;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import deti.tqs.backend.models.Bus;
import deti.tqs.backend.services.BusService;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/api/bus")
public class BusController {

  private static final Logger logger = LoggerFactory.getLogger(BusController.class);

  @Autowired
  private BusService busService;

  @GetMapping("/get")
  ResponseEntity<Bus> getBus(@RequestParam int id) {
    Bus b = busService.getBusById(id);
    logger.info("Bus with id {} requested", id);

    if (b != null)
      return ResponseEntity.ok(b);
    else
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bus not found!");
  }

  @GetMapping("/getAll")
  ResponseEntity<Iterable<Bus>> listAllBuses() {
    logger.info("List of buses requested");
    return ResponseEntity.ok(busService.getAllBuses());
  }

  @PostMapping("/add")
  ResponseEntity<Bus> addBus(@RequestBody Bus bus) {
    Bus b = busService.addBus(bus);
    logger.info("Adding bus with name {} ",b.getName());
    return ResponseEntity.ok(b);
  }
}
