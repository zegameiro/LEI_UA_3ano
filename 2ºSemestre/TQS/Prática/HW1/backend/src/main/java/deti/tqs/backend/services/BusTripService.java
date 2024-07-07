package deti.tqs.backend.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import deti.tqs.backend.models.BusTrip;
import deti.tqs.backend.repositories.BusTripRepository;

@Service
public class BusTripService {
  
  private static final Logger logger = LoggerFactory.getLogger(BusTripService.class);

  @Autowired
  private BusTripRepository busTripRepository;

  @Autowired
  public CurrencyExchangeService currencyExchangeService;

  public boolean tripExists(int busTripId) {
    return busTripRepository.existsById(busTripId);
  }

  public BusTrip getBusTripById(int id, String currency) {
    BusTrip busTrip = busTripRepository.findById(id);

    if (currency == null || currency.isEmpty())
      return busTrip;

    double exchangeRate = 1.0;

    try {
      logger.info("Exchanging currency from EUR to {}", currency);
      exchangeRate = currencyExchangeService.exchange("EUR", currency);
    } catch (Exception e) {
      logger.error("Error exchanging currency: {}", e.getMessage());
    }

    busTrip.setPrice(busTrip.getPrice() * exchangeRate);

    logger.info("Trip with id %d requested in currency %d", id, currency);

    return busTrip;
  }

  public List<BusTrip> listFilteredTrips(String fromCity, String toCity, String date, String currency) {
    
    List<BusTrip> trips = new ArrayList<>();

    trips = busTripRepository.findByFromCityAndToCityAndDate(fromCity, toCity, date);

    if (currency == null || currency.equals("EUR")) 
      return trips;

    double exchangeRate = 1.0;

    try {
      logger.info("Exchanging currency from EUR to {}", currency);
      exchangeRate = currencyExchangeService.exchange("EUR", currency);
    } catch (Exception e) {
      logger.error("Error exchanging currency: {}", e.getMessage());
    }

    for (BusTrip trip : trips) {
      trip.setPrice(trip.getPrice() * exchangeRate);
    }

    logger.info("Trips requested in currency {}", currency);

    return trips;
  }

  public List<BusTrip> listTrips() {
    return busTripRepository.findAll();
  }

  public List<String> getDates() {
    return busTripRepository.findDates();
  }

  public List<String> getFromCities() {
    return busTripRepository.findFromCities();
  }

  public List<String> getToCities() {
    return busTripRepository.findToCities();
  }
} 
