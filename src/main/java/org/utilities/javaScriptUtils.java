package org.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class javaScriptUtils {

    public static void drawBorderAroundElement(WebElement element, WebDriver driver){
        JavascriptExecutor executor=(JavascriptExecutor) driver;
        executor.executeScript("arguments[0].style.border='5px solid red';",element);
    }

    public void scrollPage(WebDriver driver, String x, String y){
        JavascriptExecutor executor=(JavascriptExecutor) driver;
        executor.executeScript("window.scrollBy("+x+","+y+")");
    }

    public void scrollSideWindow(WebDriver driver, WebElement element, String X, String Y){
        JavascriptExecutor executor=(JavascriptExecutor) driver;
        executor.executeScript("document.querySelector('"+element+"')window.scrollBy("+X+","+Y+")");
    }
}
