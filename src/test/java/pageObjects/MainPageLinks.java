package pageObjects;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;


public class MainPageLinks {
    private WebDriver driver;
    private By links = By.xpath("//a[contains(@href,'http')]");
    private By linkin = By.xpath("//div[@class='wrapper']/ul/li/a[@href='https://auto.ria.com/car/used/']");


    public MainPageLinks(WebDriver driver){
        this.driver=driver;
    }

    public void listOfLinks(){
       List<WebElement> listLinks = driver.findElements(links);
       for (WebElement el: listLinks)

       {
           try {
               new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(el)).click();
           } catch (StaleElementReferenceException e) {
               System.out.println("Ссылка" + el + "не работает");
           }
           driver.navigate().back();


       }
    }
    public void link(){
        driver.findElement(linkin).click();
    }



}
