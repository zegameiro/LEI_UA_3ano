package deti.tqs.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservations")
public class Reservation {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private int idBusTrip;
  private String firstName;
  private String lastName;
  private String email;
  private String phone;
  private int seat;

  public Reservation() {
    // Empty constructor needed for JPA
  }

  public int getId() {
    return id;
  }

  public int getIdBusTrip() {
    return idBusTrip;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmail() {
    return email;
  }

  public String getPhone() {
    return phone;
  }

  public int getSeat() {
    return seat;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setIdBusTrip(int idBusTrip) {
    this.idBusTrip = idBusTrip;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public void setSeat(int seat) {
    this.seat = seat;
  }
}
