package deti.tqs.backend.bdd_tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
  
  private final WebDriver driver;

  public HomePage(WebDriver driver) {
    this.driver = driver;
  }

  public void openWebsite() {
    driver.get("http:localhost:3000/");
  }

  public void selectFromCity(String fromCity) {
    driver.findElement(By.cssSelector(".form-control:nth-child(1) > .select")).click();
    driver.findElement(By.xpath("//option[. = '" + fromCity + "']")).click();
  }

  public void selectToCity(String toCity) {
    driver.findElement(By.cssSelector(".form-control:nth-child(3) > .select")).click();
    driver.findElement(By.xpath("//option[. = '" + toCity + "']")).click();
  }

  public void clickSearchButton() {
    driver.findElement(By.cssSelector(".btn-accent")).click();
  }

  public void clickSeeAllTripsButton() {
    driver.findElement(By.cssSelector(".btn-primary")).click();
  }
}
