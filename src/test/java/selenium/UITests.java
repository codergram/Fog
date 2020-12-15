/*
 * Copyright (c) 2020. Team CoderGram
 *
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 * @author Sigurd Arik Twena Nielsen (cph-at89@cphbusiness.dk)
 * @author Jacob Lange Nielsen (cph-jn352@cphbusiness.dk)
 */

package selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

@DisplayName("Selenium UI test")
class UITests {

  private static final String OS = System.getProperty("os.name").toLowerCase();
  private static WebDriver driver;
  static JavascriptExecutor js;

  @BeforeAll
  static void setUp() {
    String path = "src/test/resources/geckodriver";

    if (OS.contains("win")) {
      path += ".exe";
    }

    File file = new File(path);
    System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());

    driver = new FirefoxDriver();
    js = (JavascriptExecutor) driver;

    driver.get("https://codergram.me/fog");
    driver.manage().window().setSize(new Dimension(924, 1012));
  }

  @AfterAll
  static void tearDown() {
    driver.quit();
  }

  @Test
  @DisplayName("Order carport")
  void userordercarport() {
    driver.findElement(By.linkText("Byg din carport")).click();
    {
      WebElement dropdown = driver.findElement(By.id("length"));
      dropdown.findElement(By.xpath("//option[. = '680']")).click();
    }
    driver.findElement(By.cssSelector("#length > option:nth-child(11)")).click();
    {
      WebElement dropdown = driver.findElement(By.id("width"));
      dropdown.findElement(By.xpath("//option[. = '390']")).click();
    }
    driver.findElement(By.cssSelector("#width > option:nth-child(10)")).click();
    driver.findElement(By.cssSelector(".custom-control-label")).click();
    {
      WebElement dropdown = driver.findElement(By.id("shedSize"));
      dropdown.findElement(By.xpath("//option[. = 'Halv bredde']")).click();
    }
    driver.findElement(By.cssSelector("#shedSize > option:nth-child(2)")).click();
    driver.findElement(By.cssSelector(".btn-success")).click();
    driver.findElement(By.id("name")).click();
    driver.findElement(By.id("name")).sendKeys("Test bruger");
    driver.findElement(By.id("email")).sendKeys("test@bruger.dk");
    driver.findElement(By.id("address")).sendKeys("Testvej 1234");
    driver.findElement(By.id("zip")).sendKeys("1234");
    driver.findElement(By.id("city")).sendKeys("Testby");
    driver.findElement(By.id("phone")).sendKeys("12345678");
    driver.findElement(By.cssSelector(".btn-primary")).click();
    assertEquals(
        driver.findElement(By.cssSelector(".mb-4")).getText(),
        "Tusinde tak for din forespørgelse!");
  }


  @Test
  @DisplayName("Admin login and logout")
  void adminloginandlogout() {
    driver.findElement(By.linkText("Log ind")).click();
    driver.findElement(By.id("inputEmail")).sendKeys("admin@admin.dk");
    driver.findElement(By.id("inputPassword")).sendKeys("admin");
    driver.findElement(By.cssSelector(".btn")).click();
    assertEquals(driver.findElement(By.linkText("Ansatte")).getText(), "Ansatte");
    driver.findElement(By.linkText("Log ud")).click();
    {
      List<WebElement> elements = driver.findElements(By.linkText("Log ind"));
      assert (elements.size() > 0);
    }
  }
}
