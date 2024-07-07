package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    
    private static String PAGE_URL = "https://blazedemo.com/";

    private WebDriver driver;

    @FindBy(xpath = "/html/body/div[2]/div/h1")
    private WebElement title;

    @FindBy(name = "fromPort")
    private WebElement fromPortSelect;

    @FindBy(xpath = "/html/body/div[3]/form/div/input")
    private WebElement findFlightsButton;

    @FindBy(name = "toPort")
    private WebElement toPortSelect;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        driver.get(PAGE_URL);
        PageFactory.initElements(driver, this);
    }

    public String getTitle() {
        return title.getText();
    }

    public String getFromPortSelect() {
        return fromPortSelect.getText();
    }

    public String getToPortSelect() {
        return toPortSelect.getText();
    }

    public void fromPortSelect(String fromPort) {
        fromPortSelect.sendKeys(fromPort);
    }

    public void toPortSelect(String toPort) {
        toPortSelect.sendKeys(toPort);
    }

    public void clickFindFlightsButton() {
        findFlightsButton.click();
    }
}
