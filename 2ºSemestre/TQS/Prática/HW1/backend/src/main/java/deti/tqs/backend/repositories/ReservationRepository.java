package deti.tqs.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import deti.tqs.backend.models.Reservation;


@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

  public Reservation findById(int id);

  public List<Reservation> findByIdBusTrip(int idBusTrip);

  public Reservation findBySeatAndIdBusTrip(int seatNumber, int idBusTrip);

  public List<Reservation> findAll();

}
