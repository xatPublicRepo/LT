package qa.testcases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.sql.Driver;
import java.util.HashMap;

public class ParallelTest {

    By searchBar = By.cssSelector("input#twotabsearchtextbox");
    By searchButtn = By.cssSelector("input#nav-search-submit-button");
    By firstResult = By.cssSelector(".celwidget.s-spacing-small.s-widget-container.s-widget-container-height-small.slot\\=MAIN.template\\=SEARCH_RESULTS.widgetId\\=search-results_1 .a-link-normal.a-text-normal.s-link-style.s-underline-link-text.s-underline-text > .a-color-base.a-size-medium.a-text-normal");
    By options = By.cssSelector("a[title='See All Buying Options']");
    By price = By.cssSelector(".a-price.aok-align-center.centralizedApexPricePriceToPayMargin  .a-price-fraction");

    WebDriver driver;
    ChromeOptions browserOptions = new ChromeOptions();

    String iphonePrice;

    @BeforeTest
    public void setUp() {

        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("118.0");
        HashMap<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("username", "rastogiakshat00");
        ltOptions.put("accessKey", "MBGdYVgHVm7dr6ce5iP6hwRzMfsYoqtZsNlD20lJwhcP4ofIcX");
        ltOptions.put("visual", true);
        ltOptions.put("video", true);
        ltOptions.put("project", "LT-Parallel Tests");
        ltOptions.put("console", "error");
        ltOptions.put("selenium_version", "4.0.0");
        ltOptions.put("w3c", true);
        browserOptions.setCapability("LT:Options", ltOptions);

    }

    @Test(priority = 1)
    public void testChrome() {
        System.out.println("The thread ID for Chrome is "+ Thread.currentThread().getId());
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
//        driver.get("https://www.lambdatest.com/");
//        driver.manage().window().maximize();
//        Assert.assertEquals(driver.getTitle(), "Next-Generation");
        driver.get("https://www.amazon.com/");
        driver.manage().window().maximize();
        driver.findElement(searchBar).sendKeys("iphone13");
        driver.findElement(searchButtn).click();
        driver.findElement(firstResult).click();
        driver.findElement(options).click();
        iphonePrice = driver.findElement(price).getText();
        //assert or log the price here
    }

    @Test(priority = 2)
    public void testFirefox() {
        System.out.println("The thread ID for Firefox is "+ Thread.currentThread().getId());
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
//        driver.get("https://www.lambdatest.com/");
//        driver.manage().window().maximize();
//        Assert.assertEquals(driver.getTitle(), "Next-Generation");
        driver.get("https://www.amazon.com/");
        driver.manage().window().maximize();
        driver.findElement(searchBar).sendKeys("iphone13");
        driver.findElement(searchButtn).click();
        driver.findElement(firstResult).click();
        driver.findElement(options).click();
        iphonePrice = driver.findElement(price).getText();
        //assert or log the price here
    }

    @AfterClass
    public void close() {
        driver.quit();
    }
}