package deti.tqs.backend.bdd_tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ReservationPage {
  
  private final WebDriver driver;

  public ReservationPage(WebDriver driver) {
    this.driver = driver;
  }

  public void chooseSeat(String seatNumber) {
    driver.findElement(By.id(seatNumber)).click();
  }

  public String getPrice() {
    return driver.findElement(By.cssSelector(".flex:nth-child(4) > span:nth-child(2)")).getText();
  }

  public boolean isSeatAvailable(String seatNumber) {
    return driver.findElement(By.id(seatNumber)).isEnabled();
  }

  public void fillInFirstName(String firstName) {
    driver.findElement(By.id("first_name")).sendKeys(firstName);
  }

  public void fillInLastName(String lastName) {
    driver.findElement(By.id("last_name")).sendKeys(lastName);
  }

  public void fillInEmail(String email) {
    driver.findElement(By.id("email")).sendKeys(email);
  }

  public void fillInPhone(String phone) {
    driver.findElement(By.id("phone_number")).sendKeys(phone);
  }

  public void clickBuyReservationButton() {
    driver.findElement(By.cssSelector(".btn-success")).click();
  }

}