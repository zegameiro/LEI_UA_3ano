package deti.tqs.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import deti.tqs.backend.models.Bus;

@Repository
public interface BusRepository extends JpaRepository<Bus, Integer> {
  
  public Bus findById(int id);

  public Bus findByName(String name);

  public List<Bus> findAll();

  public void deleteById(int id);

  public void deleteAll();

}
