package deti.tqs.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import deti.tqs.backend.models.Bus;
import deti.tqs.backend.repositories.BusRepository;

@Service
public class BusService {
  
  @Autowired
  private BusRepository busRepository;

  public Bus getBusById(int id) {
    return busRepository.findById(id);
  }

  public Iterable<Bus> getAllBuses() {
    return busRepository.findAll();
  }

  public Bus addBus(Bus bus) {
    return busRepository.save(bus);
  }
  
}
