package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PurchasePage {
    
    private WebDriver driver;

    @FindBy(xpath = "/html/body/div[2]/h2")
    private WebElement title;

    @FindBy(xpath = "/html/body/div[2]/p[5]em/")
    private WebElement totalPrice;

    @FindBy(xpath = "//*[@id=\"inputName\"]")
    private WebElement inputName;

    @FindBy(xpath = "//*[@id=\"address\"]")
    private WebElement inputAddress;

    @FindBy(xpath = "//*[@id=\"city\"]")
    private WebElement inputCity;

    @FindBy(xpath = "//*[@id=\"state\"]")
    private WebElement inputState;

    @FindBy(xpath = "//*[@id=\"zipCode\"]")
    private WebElement inputZipCode;
    
    @FindBy(xpath = "//*[@id=\"cardType\"]")
    private WebElement inputCardType;

    @FindBy(xpath = "//*[@id=\"creditCardNumber\"]")
    private WebElement inputCreditCardNumber;

    @FindBy(xpath = "//*[@id=\"creditCarMonth\"]")
    private WebElement inputMonth;

    @FindBy(xpath = "//*[@id=\"creditCardYear\"]")
    private WebElement inputYear;

    @FindBy(xpath = "//*[@id=\"nameOnCard\"]")
    private WebElement inputNameOnCard;

    @FindBy(xpath = "/html/body/div[2]/form/div[11]/div/input")
    private WebElement purchaseFlightButton;

    public PurchasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setForm(
        String name,
        String address,
        String city,
        String state,
        String zipCode,
        String cardType,
        String creditCardNumber,
        String month,
        String year,
        String nameOnCard
    ) {
        inputName.sendKeys(name);
        inputAddress.sendKeys(address);
        inputCity.sendKeys(city);
        inputState.sendKeys(state);
        inputZipCode.sendKeys(zipCode);
        inputCardType.sendKeys(cardType);
        inputCreditCardNumber.sendKeys(creditCardNumber);
        inputMonth.sendKeys(month);
        inputYear.sendKeys(year);
        inputNameOnCard.sendKeys(nameOnCard);
    }

    public String getTitle() {
        return title.getText();
    }

    public String getTotalPrice() {
        return totalPrice.getText();
    }

    public String getInputName() {
        return inputName.getAttribute("value");
    }

    public String getInputAddress() {
        return inputAddress.getAttribute("value");
    }

    public String getInputCity() {
        return inputCity.getAttribute("value");
    }

    public String getInputState() {
        return inputState.getAttribute("value");
    }

    public String getInputZipCode() {
        return inputZipCode.getAttribute("value");
    }

    public String getInputCardType() {
        return inputCardType.getAttribute("value");
    }

    public String getInputCreditCardNumber() {
        return inputCreditCardNumber.getAttribute("value");
    }

    public String getInputMonth() {
        return inputMonth.getAttribute("value");
    }

    public String getInputYear() {
        return inputYear.getAttribute("value");
    }

    public String getInputNameOnCard() {
        return inputNameOnCard.getAttribute("value");
    }

    public void clickPurchaseFlightButton() {
        purchaseFlightButton.click();
    }
}
