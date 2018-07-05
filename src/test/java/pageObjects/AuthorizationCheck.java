package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthorizationCheck {
    private  WebDriver driver;
    private By enterIn = By.xpath("//span[@class='tl']");
    private By registrateIn = By.xpath("//div[@class='social-login']/following::div[@class='login-link']/a");
    private By telephone = By.xpath("//div[@class='login-rows']//following-sibling::input");
    private By searchButton = By.xpath("//div[@class='sub-button']//following-sibling::input");
    private By email = By.id("emailloginform-email");
    private By passward = By.id("emailloginform-password");
    private By submit =By.xpath("//a[@id='remember_password']/preceding::button");


    public  AuthorizationCheck(WebDriver driver){
        this.driver=driver;
    }


    public void waitTimeAuthoriationBy (By element){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(element));

    }


    public void clickEnterIn(){
        waitTimeAuthoriationBy(enterIn);
        driver.findElement(enterIn).click();
        driver.switchTo().frame("login_frame");
    }


     public void clickOnRegistrate(){
         WebDriverWait wait = new WebDriverWait(driver, 20);
         wait.until(ExpectedConditions.visibilityOfElementLocated(registrateIn));
         driver.findElement(registrateIn).click();

     }
     public void inputTelephone(String number){
        waitTimeAuthoriationBy(telephone);
        driver.findElement(telephone).sendKeys(number);
         }

         public void clickOnSearhButton(){
        waitTimeAuthoriationBy(searchButton);
        driver.findElement(searchButton).click();
         }

         public void logInRegistration(String telephoneNumber){
        clickEnterIn();
        clickOnRegistrate();
        inputTelephone(telephoneNumber);
        clickOnSearhButton();
    }


         public void invalidLogginEnter(String email1,String passward1){
        clickEnterIn();
        waitTimeAuthoriationBy(email);
        driver.findElement(email).sendKeys(email1);
        waitTimeAuthoriationBy(passward);
        driver.findElement(passward).sendKeys(passward1);
        waitTimeAuthoriationBy(submit);
        driver.findElement(submit).click();
         }



    public void returnOnParentPage(){

        driver.switchTo().parentFrame();
    }
}
