package deti.tqs.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "seats")
public class Seat {
    
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String seatType = "Economic";
  private boolean isTaken;

  public Seat() {
    // Empty constructor needed for JPA
  }

  public int getId() {
    return id;
  }

  public String getSeatType() {
    return seatType;
  }

  public boolean getIsTaken() {
    return isTaken;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setSeatType(String seatType) {
    this.seatType = seatType;
  }

  public void setIsTaken(boolean isTaken) {
    this.isTaken = isTaken;
  }
}
