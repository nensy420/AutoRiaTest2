package tests;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.AuthorizationCheck;
import pageObjects.MainPageLinks;
import pageObjects.MainSearchForm;
import pageObjects.MainToolTip;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class TestMainPage {
    private WebDriver driver;
    private String baceURL = "https://auto.ria.com/";
    private static final By listOfRezaltSearchForm = By.xpath("//div[@class='content']/div[@class='head-ticket']/div[contains(@class,'ticket-title')]/a");
    private static final By errorMassegeOnSearchPage = By.xpath("//div[@id='emptyResultsBlock']//div[@class='title' and contains(text(),' К сожалению, по Вашему запросу ')]/span[contains(text(),'Объявлений не найдено')]");
    private static final By errorMessegeLoggin = By.xpath("//p[@class='error']");
    private static final Logger Log = Logger.getLogger(TestMainPage.class);


    @BeforeMethod
    public void openPage() {
        System.setProperty("webdriver.gecko.driver", "C:\\home\\user\\bin\\geckodriver.exe");
        driver = new FirefoxDriver();


        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(baceURL);
        Log.info("[INFO] Launch Auto Ria page");

    }

    @AfterMethod
    public void closePage() {
        driver.quit();
        Log.info("[INFO] Close Auto Ria");
    }

    @Test()
    public void invalidRegistration() {

        AuthorizationCheck registration = new AuthorizationCheck(driver);
        registration.logInRegistration("123kjh");
        WebElement fieldAttantion = driver.findElement(errorMessegeLoggin);
        String attantionMessage = fieldAttantion.getAttribute("innerHTML");
        Assert.assertTrue(attantionMessage.contains("неверный  мобильный номер телефона"));


    }


    @Test()
    public void linkFollowing() {
        MainPageLinks test = new MainPageLinks(driver);
        test.link();
        String url = driver.getCurrentUrl();
        String actualURL = "https://auto.ria.com/car/used/";
        Assert.assertTrue(url.equals(actualURL));

    }

    @Test
    public void testToolTipViki() {
        MainToolTip tip = new MainToolTip(driver);
        tip.toolTipViki();
        String expectedTooltip = "Википедия об AUTO.RIA";
        Assert.assertTrue(tip.getActualTitle().contains(expectedTooltip));

    }

    @Test
    public void testToolTipVakansii() {
        MainToolTip tip = new MainToolTip(driver);
        tip.toolTipVakansii();
        String expectedTooltip = "Вакансии на проекте AUTO.RIA";
        Assert.assertTrue(tip.getActualTitle().contains(expectedTooltip));

    }

    @Test
    public void validSearch() {
        MainSearchForm search = new MainSearchForm(driver);
        search.enterSearchParameters("Легковые", "2015", "2017", "200", "50000", "Audi", "Q7", "Киев");
        search.waitTimeBy(listOfRezaltSearchForm);
        List<WebElement> listOfRezalts = driver.findElements(listOfRezaltSearchForm);
        String expectedTitle = "Audi Q7";
        for (WebElement elm : listOfRezalts) {
            search.waitTime(elm);
            String actualTitle = elm.getAttribute("title");
            Assert.assertTrue(actualTitle.contains(expectedTitle));

        }

    }

    @Test
    public void invalidYearSearch() {
        MainSearchForm search = new MainSearchForm(driver);
        search.enterSearchParameters("Легковые", "2010", "2003", "200", "50000", "Audi", "Q7", "Киев");
        //  WebDriverWait waiter = new WebDriverWait(driver, 10);
        //  waiter.until(ExpectedConditions.visibilityOfElementLocated(errorMassegeOnSearchPage));
        Assert.assertTrue(driver.findElement(errorMassegeOnSearchPage).isDisplayed());


    }
}