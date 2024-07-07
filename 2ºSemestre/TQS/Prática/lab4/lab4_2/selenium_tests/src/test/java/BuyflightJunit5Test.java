
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.seljup.SeleniumJupiter;

import org.openqa.selenium.JavascriptExecutor;

import java.util.*;


@ExtendWith(SeleniumJupiter.class)
public class BuyflightJunit5Test {
  private WebDriver driver;
  JavascriptExecutor js;
  @BeforeEach
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
  }
  @AfterEach
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void buyflight() {
    driver.get("https://blazedemo.com/");
    driver.manage().window().setSize(new Dimension(1920, 1048));
    {
      List<WebElement> elements = driver.findElements(By.cssSelector("p:nth-child(3)"));
      assert(elements.size() > 0);
    }
    driver.findElement(By.cssSelector(".jumbotron")).click();
    {
      WebElement dropdown = driver.findElement(By.name("fromPort"));
      dropdown.findElement(By.xpath("//option[. = 'Boston']")).click();
    }
    {
      String value = driver.findElement(By.name("fromPort")).getAttribute("value");
      assertThat(value).isEqualTo("Boston");
    }
    {
      WebElement element = driver.findElement(By.name("fromPort"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).clickAndHold().perform();
    }
    {
      WebElement element = driver.findElement(By.name("fromPort"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    {
      WebElement element = driver.findElement(By.name("fromPort"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).release().perform();
    }
    {
      WebElement dropdown = driver.findElement(By.name("toPort"));
      dropdown.findElement(By.xpath("//option[. = 'New York']")).click();
    }
    {
      WebElement element = driver.findElement(By.name("toPort"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).clickAndHold().perform();
    }
    {
      WebElement element = driver.findElement(By.name("toPort"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    {
      WebElement element = driver.findElement(By.name("toPort"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).release().perform();
    }
    {
      String value = driver.findElement(By.name("toPort")).getAttribute("value");
      assertThat(value).isEqualTo("New York");
    }
    {
      WebElement element = driver.findElement(By.name("toPort"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).clickAndHold().perform();
    }
    {
      WebElement element = driver.findElement(By.name("toPort"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    {
      WebElement element = driver.findElement(By.name("toPort"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).release().perform();
    }
    driver.findElement(By.cssSelector(".btn-primary")).click();
    {
      List<WebElement> elements = driver.findElements(By.cssSelector("html"));
      assert(elements.size() > 0);
    }
    driver.findElement(By.cssSelector("h3")).click();
    driver.findElement(By.cssSelector("h3")).click();
    driver.findElement(By.cssSelector("tr:nth-child(3) .btn")).click();
    {
      List<WebElement> elements = driver.findElements(By.cssSelector(".container:nth-child(2)"));
      assert(elements.size() > 0);
    }
    driver.findElement(By.cssSelector("h2")).click();
    {
      List<WebElement> elements = driver.findElements(By.cssSelector("p:nth-child(5)"));
      assert(elements.size() > 0);
    }
    driver.findElement(By.cssSelector("p:nth-child(3)")).click();
    {
      List<WebElement> elements = driver.findElements(By.cssSelector(".control-group:nth-child(3) > .controls"));
      assert(elements.size() > 0);
    }
    driver.findElement(By.cssSelector("body")).click();
    driver.findElement(By.id("inputName")).click();
    driver.findElement(By.id("inputName")).sendKeys("John");
    driver.findElement(By.id("address")).click();
    driver.findElement(By.id("address")).sendKeys("Smith");
    driver.findElement(By.id("city")).click();
    driver.findElement(By.id("city")).sendKeys("Anytown");
    driver.findElement(By.id("state")).click();
    driver.findElement(By.id("state")).sendKeys("California");
    driver.findElement(By.id("zipCode")).click();
    driver.findElement(By.id("zipCode")).sendKeys("12345");
    {
      WebElement element = driver.findElement(By.id("cardType"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).clickAndHold().perform();
    }
    {
      WebElement element = driver.findElement(By.id("cardType"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    {
      WebElement element = driver.findElement(By.id("cardType"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).release().perform();
    }
    driver.findElement(By.id("cardType")).click();
    driver.findElement(By.id("creditCardNumber")).click();
    driver.findElement(By.id("creditCardNumber")).sendKeys("123456789");
    driver.findElement(By.id("nameOnCard")).click();
    driver.findElement(By.id("nameOnCard")).sendKeys("John Smith");
    driver.findElement(By.cssSelector(".btn-primary")).click();
    {
      List<WebElement> elements = driver.findElements(By.cssSelector("tr:nth-child(2) > td:nth-child(2)"));
      assert(elements.size() > 0);
    }
    driver.findElement(By.cssSelector("h1")).click();
    {
      List<WebElement> elements = driver.findElements(By.cssSelector(".hero-unit"));
      assert(elements.size() > 0);
    }
    driver.findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(2)")).click();
    driver.findElement(By.cssSelector("tr:nth-child(5) > td:nth-child(2)")).click();
  }
}
