package qa.testcases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class ParallelTestCH {

//    WebDriver driver;

    private RemoteWebDriver driver;
    DesiredCapabilities caps = new DesiredCapabilities();

//    ChromeOptions chromeOptions = new ChromeOptions();
//    HashMap<String, Object> ltOptions = new HashMap<>();
    String iphonePrice;


    //Locators --
    By searchBar = By.cssSelector("input#twotabsearchtextbox");
    By searchButtn = By.cssSelector("input#nav-search-submit-button");
    By firstResult = By.cssSelector(".celwidget.s-spacing-small.s-widget-container.s-widget-container-height-small.slot\\=MAIN.template\\=SEARCH_RESULTS.widgetId\\=search-results_1 .a-link-normal.a-text-normal.s-link-style.s-underline-link-text.s-underline-text > .a-color-base.a-size-medium.a-text-normal");
    By options = By.cssSelector("a[title='See All Buying Options']");
    By price = By.cssSelector(".a-price.aok-align-center.centralizedApexPricePriceToPayMargin  .a-price-fraction");

    @BeforeMethod
    public void setup() throws MalformedURLException {
        String username = System.getenv("LT_USERNAME") == null ? "rastogiakshat00" : System.getenv("LT_USERNAME");
        String authkey = System.getenv("LT_ACCESS_KEY") == null ? "MBGdYVgHVm7dr6ce5iP6hwRzMfsYoqtZsNlD20lJwhcP4ofIcX" : System.getenv("LT_ACCESS_KEY");
        ;
        String hub = "@hub.lambdatest.com/wd/hub";

        caps.setCapability("platform", "Windows 11");
        caps.setCapability("browserName", "Chrome");
        caps.setCapability("version", "latest");
        caps.setCapability("build", "LT-Parallel Tests");
        caps.setCapability("plugin", "git-testng");
        caps.setCapability("visual", true);
        caps.setCapability("video", true);
        caps.setCapability("console", "error");

        String[] Tags = new String[] { "Feature", "Tag", "Moderate" };
        caps.setCapability("tags", Tags);

        driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), caps);
    }

    @Test(priority = 1)
    public void testChrome() {
        System.out.println("The thread ID for Chrome is "+ Thread.currentThread().getId());
        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver(chromeOptions);
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