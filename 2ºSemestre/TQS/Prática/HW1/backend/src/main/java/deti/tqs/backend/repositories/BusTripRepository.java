package deti.tqs.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import deti.tqs.backend.models.BusTrip;

@Repository
public interface BusTripRepository extends JpaRepository<BusTrip, Integer>{
  
  @Query("SELECT bustrip FROM BusTrip bustrip WHERE (:fromCity IS NULL OR bustrip.fromCity = :fromCity) AND (:toCity IS NULL OR bustrip.toCity = :toCity) AND (:date IS NULL OR bustrip.date = :date)")
  public List<BusTrip> findByFromCityAndToCityAndDate(@Param("fromCity") String fromCity, @Param("toCity") String toCity, @Param("date") String date);

  @Query("SELECT DISTINCT bustrip.date FROM BusTrip bustrip")
  public List<String> findDates();

  @Query("SELECT DISTINCT bustrip.fromCity FROM BusTrip bustrip")
  public List<String> findFromCities();

  @Query("SELECT DISTINCT bustrip.toCity FROM BusTrip bustrip")
  public List<String> findToCities();

  public BusTrip findById(int id);

  public List<BusTrip> findAll();

  public List<BusTrip> findByFromCity(String fromCity);

  public List<BusTrip> findByToCity(String toCity);

  public List<BusTrip> findByDate(String date);

  public void deleteByFromCity(String fromCity);

  public void deleteByToCity(String toCity);
  
}
