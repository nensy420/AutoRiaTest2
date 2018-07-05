package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class MainToolTip {
    private WebDriver driver;
    By expectedTooltipVikiPath = By.xpath("//div[@class='content-bar']//*[contains(text(),'Википедия')]");
    By expectedTooltipVakansiiPath = By.xpath("//div[@class='content-bar']//*[contains(text(),'Вакансии')]");
    private String actualTitle;



    public String getActualTitle() {
        return actualTitle;
    }

    public void setActualTitle(String actualTitle) {
        this.actualTitle = actualTitle;
    }

    public MainToolTip(WebDriver driver){
        this.driver=driver;
    }

  public void  toolTipViki() {
      JavascriptExecutor jse = (JavascriptExecutor) driver;
      WebElement vikipedia = driver.findElement(expectedTooltipVikiPath);
      jse.executeScript("arguments[0].scrollIntoView(true);", vikipedia);
      setActualTitle(vikipedia.getAttribute("title"));
  }
  public void toolTipVakansii(){
      JavascriptExecutor jse = (JavascriptExecutor) driver;
      WebElement vakansii = driver.findElement(expectedTooltipVakansiiPath);
      jse.executeScript("arguments[0].scrollIntoView(true);", vakansii);
      setActualTitle(vakansii.getAttribute("title"));


  }
}
