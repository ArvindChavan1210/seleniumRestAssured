package org.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractClass {

    WebDriver driver;
    WebDriverWait wait;

    public AbstractClass(WebDriver driver){
        this.driver=driver;
        wait=new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void wait_until_Element_Visible(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void wait_until_Element_Invisible(WebElement element){
        wait.until(ExpectedConditions.invisibilityOf(element));
    }
    public void wait_until_Element_Clickable(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public void wait_until_URL_changes_to(String url){
        wait.until(ExpectedConditions.urlToBe(url));
    }
    public Actions specific_action(){
        return new Actions(driver);
    }
    public Select Select_Action(WebElement element){
        return new Select(element);
    }
}
