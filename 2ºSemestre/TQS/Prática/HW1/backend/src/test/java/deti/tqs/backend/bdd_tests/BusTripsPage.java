package deti.tqs.backend.bdd_tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BusTripsPage {
  
  private final WebDriver driver;
  
  public BusTripsPage(WebDriver driver) {
    this.driver = driver;
  }

  // Choose Castelo Branco - Figueira da Foz trip
  public void chooseTripButton() {
    driver.findElement(By.cssSelector(".btn-sm")).click();
  }

  public String getFromCity() {
    return driver.findElement(By.id("from_city")).getText();
  }

  public String getToCity() {
    return driver.findElement(By.id("to_city")).getText();
  }

  public String getBusName() {
    return driver.findElement(By.id("bus_name")).getText();
  }

}
