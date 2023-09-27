package qa.testcases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class ParallelTest {

    WebDriver driver;

    @Test(priority = 1)
    public void testChrome() {
        System.out.println("The thread ID for Chrome is "+ Thread.currentThread().getId());
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.lambdatest.com/");
        driver.manage().window().maximize();
        Assert.assertEquals(driver.getTitle(), "Next-Generation");
    }

    @Test(priority = 2)
    public void testFirefox() {
        System.out.println("The thread ID for Firefox is "+ Thread.currentThread().getId());
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get("https://www.lambdatest.com/");
        driver.manage().window().maximize();
        Assert.assertEquals(driver.getTitle(), "Next-Generation");
    }

    @AfterClass
    public void close() {
        driver.quit();
    }
}