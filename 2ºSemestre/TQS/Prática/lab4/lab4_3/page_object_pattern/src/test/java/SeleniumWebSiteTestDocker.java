
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.bonigarcia.seljup.BrowserType;
import io.github.bonigarcia.seljup.DockerBrowser;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import pages.ConfirmationPage;
import pages.HomePage;
import pages.PurchasePage;
import pages.ReservePage;

@ExtendWith(SeleniumJupiter.class)
public class SeleniumWebSiteTestDocker {


    @Test
    public void testFlightPurchase(@DockerBrowser(type = BrowserType.FIREFOX) WebDriver driver) {

        driver.get("https://blazedemo.com/");
        // Home Page
        HomePage homePage = new HomePage(driver);

        assertThat(homePage.getTitle()).isEqualTo("Welcome to the Simple Travel Agency!");

        homePage.fromPortSelect("Boston");
        assertThat(homePage.getFromPortSelect()).isEqualTo("Boston");

        homePage.toPortSelect("London");
        assertThat(homePage.getToPortSelect()).isEqualTo("New York");

        homePage.clickFindFlightsButton();

        // Reserve Page
        ReservePage reservePage = new ReservePage(driver);

        assertThat(reservePage.getTitle()).isEqualTo("Flights from Boston to New York:");

        assertThat(reservePage.getFlightNumber()).isEqualTo("43");

        assertThat(reservePage.getAirline()).isEqualTo("Virgin America");

        assertThat(reservePage.getTimeDepart()).isEqualTo("1:43 AM");

        assertThat(reservePage.getTimeArrive()).isEqualTo("9:45 PM");

        assertThat(reservePage.getPrice()).isEqualTo("$472.56");

        reservePage.clickChooseThisFlightButton();

        // Purchase Page
        PurchasePage purchasePage = new PurchasePage(driver);
        
        assertThat(purchasePage.getTitle()).isEqualTo("Your flight from TLV to SFO has been reserved.");

        assertThat(purchasePage.getTotalPrice()).isEqualTo("914.76");

        purchasePage.setForm(
            "John", 
            "Smith",
            "Anytown",
            "California",
            "12345",
            "Visa", 
            "123456789", 
            "11", 
            "2017", 
            "John Smith"
        );

        assertThat(purchasePage.getInputName()).isEqualTo("John");

        assertThat(purchasePage.getInputAddress()).isEqualTo("Smith");

        assertThat(purchasePage.getInputCity()).isEqualTo("Anytown");

        assertThat(purchasePage.getInputState()).isEqualTo("California");

        assertThat(purchasePage.getInputZipCode()).isEqualTo("12345");

        assertThat(purchasePage.getInputCardType()).isEqualTo("Visa");

        assertThat(purchasePage.getInputCreditCardNumber()).isEqualTo("123456789");

        assertThat(purchasePage.getInputMonth()).isEqualTo("11");

        assertThat(purchasePage.getInputYear()).isEqualTo("2017");

        assertThat(purchasePage.getInputNameOnCard()).isEqualTo("Jonh Smith");

        purchasePage.clickPurchaseFlightButton();

        // Confirmation Page
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);

        assertThat(confirmationPage.getTitle()).isEqualTo("Thank you for your purchase today!");

        assertThat(confirmationPage.getStatus()).isEqualTo("PendingCapture");

        assertThat(confirmationPage.getAmount()).isEqualTo("555 USD");

        assertThat(confirmationPage.getCardNumber()).isEqualTo("xxxxxxxxxxxx1111");

        assertThat(confirmationPage.getExpiration()).isEqualTo("12/2018");

        assertThat(confirmationPage.getAuthCode()).isEqualTo("888888");

        assertThat(confirmationPage.getDate()).isEqualTo("Sat, 09 Mar 2024 00:48:39 +00");
    }
}
