package deti.tqs.backend.bdd_tests;

import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.assertj.core.api.Assertions.assertThat;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.seljup.SeleniumJupiter;

@ExtendWith(SeleniumJupiter.class)
public class BuyBusTicketsServiceSteps {

  private WebDriver driver;
  private HomePage homePage;
  private BusTripsPage busTripsPage;
  private ReservationPage reservationPage;

  @Given("a user enters on the website and sees the homepage")
  public void user_entered_website() {
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    homePage = new HomePage(driver);
    busTripsPage = new BusTripsPage(driver);
    reservationPage = new ReservationPage(driver);
    homePage.openWebsite();
  }

  @When("the user selects the departure city {string}")
  public void user_selects_departure_city(String departureCity) {
    homePage.selectFromCity(departureCity);
  }

  @And("the user selects the destination city {string}")
  public void user_selects_arrival_city(String arrivalCity) {
    homePage.selectToCity(arrivalCity);
  }

  @And("clicks on the search button")
  public void user_clicks_search_button() {
    homePage.clickSearchButton();
  }

  @Then("the user sees the available trips")
  public void user_sees_available_trips() {
    assertThat(busTripsPage.getFromCity()).isEqualTo("Castelo Branco");
    assertThat(busTripsPage.getToCity()).isEqualTo("Figueira da Foz");
    assertThat(busTripsPage.getBusName()).isEqualTo("Bus Bud 2");
  }

  @And("clicks on the button choose trip")
  public void user_clicks_choose_trip_button() {
    busTripsPage.chooseTripButton();
  }

  @Then("the user confirms the price of the trips")
  public void user_confirms_price_trips() {
    assertThat(reservationPage.getPrice()).isEqualTo("18");
  }

  @And("fills in the form with the personal data")
  public void user_fills_form_personal_data() {
    reservationPage.fillInFirstName("John");
    reservationPage.fillInLastName("Smith");
    reservationPage.fillInEmail("john.smith@ua.pt");
    reservationPage.fillInPhone("912345678");
  }

  @And("chooses seat number 14")
  public void user_chooses_seat_number_14() {
    reservationPage.chooseSeat("14");
  }

  @And("clicks on the button buy reservation")
  public void user_clicks_buy_reservation_button() {
    reservationPage.clickBuyReservationButton();
  }
}