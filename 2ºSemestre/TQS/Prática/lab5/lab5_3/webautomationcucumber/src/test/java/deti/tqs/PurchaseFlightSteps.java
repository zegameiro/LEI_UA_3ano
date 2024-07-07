package deti.tqs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class PurchaseFlightSteps {
  private WebDriver driver = WebDriverManager.chromedriver().create();

  @Given("a user is on the Home Page")
  public void userInHomePage() {
    driver.get("https://blazedemo.com/");
  }

  @When("the user selects {string} in the departed city dropdown")
  public void selectDepartedCity(String selectedCity) {
    driver.findElement(By.name("fromPort")).sendKeys(selectedCity);
  }

  @When("the user selects {string} in the destination city dropdown")
  public void selectDestinationCity(String selectedCity) {
    driver.findElement(By.name("toPort")).sendKeys(selectedCity);
  }

  @When("the user clicks on the Find Flights button")
  public void clickFindFlights() {
    driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
  }

  @Then("the user gets redirected to the choose flight reservation page")
  public void redirectToChooseFlightPage() {
    try {
      driver.findElement(By.xpath("//*[contains(text(), 'Flights from Mexico City to New York:')]"));
    } catch (NoSuchElementException e) {
      throw new AssertionError("\"Flights from Mexico City to:\" not available in results");
    }
  }

  @When("the user selects a flight with the number {string}, from the airline company {string} with a price of ${string}")
  public void theUserSelectsFlight(String arg0, String arg1, String arg2) {
    assertEquals(arg1, driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[1]/td[2]")).getText());
    assertEquals(arg0, driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[1]/td[3]")).getText());
    assertEquals(arg2, driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[1]/td[6]")).getText());
  }

  @When("the user clicks on the Choose This Flight button")
  public void chooseFlightReservation() {
    driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[1]/td[1]/input")).click();
  }

  @Then("the user gets redirect to the purchase flight page")
  public void redirectToPurchaseFlightPage() {
    assertEquals("Your flight from TLV to SFO has been reserved.", driver.findElement(By.xpath("/html/body/div[2]/h2")).getText());
  }

  @When("the user fills the purchase flight form with the following data:")
  public void fillInFormFields(DataTable dataTable) {
    Map<String, String> information = dataTable.asMap(String.class, String.class);
    driver.findElement(By.id("inputName")).sendKeys(information.get("Name"));
    driver.findElement(By.id("address")).sendKeys(information.get("Address"));
    driver.findElement(By.id("city")).sendKeys(information.get("City"));
    driver.findElement(By.id("state")).sendKeys(information.get("State"));
    driver.findElement(By.id("zipCode")).sendKeys(information.get("Zip Code"));
    driver.findElement(By.id("cardType")).sendKeys(information.get("Card Type"));
    driver.findElement(By.id("creditCardNumber")).sendKeys(information.get("Card Number"));
    driver.findElement(By.id("creditCardMonth")).sendKeys(information.get("Card Expire Month"));
    driver.findElement(By.id("creditCardYear")).sendKeys(information.get("Card Expire Year"));
    driver.findElement(By.id("nameOnCard")).sendKeys(information.get("Name on the Card"));
    driver.findElement(By.id("rememberMe")).sendKeys(information.get("Remember Me"));
  }

  @When("the user clicks on the Purchase Flight button")
  public void purchaseFlight() {
    driver.findElement(By.xpath("/html/body/div[2]/form/div[11]/div/input")).click();
  }

  @Then("the user gets redirected to the confirmation purchase page")
  public void redirectToConfirmationPurchasePage() {
    assertEquals("Thank you for your purchase today!", driver.findElement(By.xpath("/html/body/div[2]/div/h1")).getText());
    driver.quit();
  }
}
