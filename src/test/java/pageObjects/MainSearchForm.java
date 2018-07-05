package pageObjects;


import org.apache.bcel.generic.RETURN;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.TestMainPage;

import java.util.List;

public class MainSearchForm {

    private WebDriver driver;
    private static final By buSelect = By.xpath("//div[@class='nav']//label[@for='buRadioType']");
    private static final By newSelect = By.xpath("//div[@class='nav']//label[@for='naRadioType']");
    private static final By transpotSelect = By.id("categories");
    private static final By markSelect = By.xpath("//input[@id='brandTooltipBrandAutocompleteInput-brand']/following-sibling::label");
    private static final By markSelectInput = By.id("brandTooltipBrandAutocompleteInput-brand");
    private static final By markSelect2 = By.xpath("//ul[contains(@class,'autocomplete-select')]/li/a[text()='Audi']");
    private static final By modelSelect = By.xpath("//*[@id='brandTooltipBrandAutocomplete-model']/label");
    private static final By modelSelect3 = By.xpath("//div[@id='brandTooltipBrandAutocomplete-model']//ul//following-sibling::li/a");
  //  private static final By regionSelectRigion = By.xpath("//div[@class='autocomplete-search mhide']//ul/li/a");
    private static final By regionLabelField = By.xpath("//div[contains(@class,'secondary-column')]//div[@class='m-indent']//label[@for='regionAutocompleteAutocompleteInput-1']");
    private static final By regionInputField = By.xpath("//input[@id='regionAutocompleteAutocompleteInput-1']");
    private static final By regionSelectRigion = By.xpath("//form[@id='mainSearchForm']//div[@id='regionAutocompleteAutocomplete-1']//following-sibling::ul/li/a[text()='Киев']");
    private static final By yearSelectFirst = By.id("yearFrom");
    private static final By yearSelectSecond = By.id("yearTo");
    private static final By priceSelectFirst = By.id("priceFrom");
    private static final By priceSelectSecond = By.id("priceTo");
    private static final By searchButton = By.xpath("//form[@id='mainSearchForm']/div[@class='footer-form']/button");
    private static final Logger Log = Logger.getLogger(MainSearchForm.class);

    public MainSearchForm(WebDriver driver) {
        this.driver = driver;
    }

    public void waitTimeBy (By element){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        Log.info("[INFO] Wait until ellement to be clickable");
    }
    public void waitTime (WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        Log.info("[INFO] Wait until ellement to be clickable");
    }

    public void searchTransport(String transportName) {

        Select selectTransportField = new Select(driver.findElement(transpotSelect));
        selectTransportField.selectByVisibleText(transportName);
        Log.info("[INFO] Select transport");

    }

    public void searchOnMark(String nameMark) {
        waitTimeBy(markSelect);
        driver.findElement(markSelect).click();
        waitTimeBy(markSelectInput);
        driver.findElement(markSelectInput).sendKeys(nameMark);
        waitTimeBy(markSelect2);
        driver.findElement(markSelect2).click();
        Log.info("[INFO] Select car brand");


    }

    public void searchOnModel(String nameOfModel) {
        waitTimeBy( modelSelect);
        driver.findElement(modelSelect).click();
        List<WebElement> listModels = driver.findElements(modelSelect3);
        for (WebElement elm : listModels)

        {
            String dataValue = elm.getAttribute("innerHTML");

            if (dataValue.contains(nameOfModel)) {
                waitTime(elm);
                elm.click();
                Log.info("[INFO] Select car model");
                return;
            }

        }


    }

     public void selectRegion(String cityName){
        waitTimeBy(regionLabelField);
        driver.findElement(regionLabelField).click();
        waitTimeBy(regionInputField);
        driver.findElement(regionInputField).sendKeys(cityName);
        waitTimeBy(regionSelectRigion);
        driver.findElement(regionSelectRigion).click();
         Log.info("[INFO] Select region");


//          List<WebElement> listModels = driver.findElements(regionSelectRigion);
//          for (WebElement elm : listModels)
//          {
//              String cityValue = elm.getAttribute("innerHTML");
//              if (cityValue.contains(cityName)) {
//                  waitTime(elm);
//                  elm.click();
//              }
//
//          }

      }
    public void selectYear(String from, String to) {
        Select yearSelectFrom = new Select(driver.findElement(yearSelectFirst));
        yearSelectFrom.selectByVisibleText(from);
        Log.info("[INFO] Select year from");
        Select yearSelectTo = new Select(driver.findElement(yearSelectSecond));
        yearSelectTo.selectByVisibleText(to);
        Log.info("[INFO] Select year to");
    }

    public void enterPrice(String priceFrom, String priceTo) {
        driver.findElement(priceSelectFirst).sendKeys(priceFrom);
        Log.info("[INFO] Select price from");
        driver.findElement(priceSelectSecond).sendKeys(priceTo);
        Log.info("[INFO] Select price to");
    }

    public void enterSearchParameters(String transportName, String yearFrom, String yearTo, String priceFrom, String priceTo,String markName,String modelName,String regionName) {
        searchTransport(transportName);
        selectYear(yearFrom, yearTo);
        enterPrice(priceFrom, priceTo);
        searchOnMark(markName);
        searchOnModel(modelName);
        selectRegion(regionName);
        driver.findElement(searchButton).click();

    }
}
