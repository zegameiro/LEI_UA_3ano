package deti.tqs.backend.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "bus_trip")
public class BusTrip {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private int busId;

  @OneToMany(cascade = CascadeType.PERSIST)
  private List<Seat> seats;
  private String fromCity;
  private String toCity;
  private double price;
  private String date;
  private String time;

  public BusTrip() {
    // Empty constructor needed for JPA
  }

  public int getId() {
    return id;
  }

  public int getBusId() {
    return busId;
  }

  public String getFromCity() {
    return fromCity;
  }

  public String getToCity() {
    return toCity;
  }

  public List<Seat> getSeats() {
    return seats;
  }

  public double getPrice() {
    return price;
  }

  public String getTime() {
    return time;
  }

  public String getDate() {
    return date;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setBusId(int busId) {
    this.busId = busId;
  }

  public void setFromCity(String fromCity) {
    this.fromCity = fromCity;
  }

  public void setSeats(List<Seat> seats) {
    this.seats = seats;
  }

  public void setToCity(String toCity) {
    this.toCity = toCity;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public void setDate(String date) {
    this.date = date;
  }
}
