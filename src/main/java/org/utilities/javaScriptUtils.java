package org.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class javaScriptUtils {

    public static void drawBorderAroundElement(WebElement element, WebDriver driver){
        JavascriptExecutor executor=(JavascriptExecutor) driver;
        executor.executeScript("arguments[0].style.border='5px solid red';",element);
    }

    public static void scrollPage(WebDriver driver, String x, String y){
        JavascriptExecutor executor=(JavascriptExecutor) driver;
        executor.executeScript("window.scrollBy("+x+","+y+")");
    }

    public static void scrollSideWindow(WebDriver driver, String Css_element, String X, String Y){
        JavascriptExecutor executor=(JavascriptExecutor) driver;
        executor.executeScript("document.querySelector('"+Css_element+"').scrollBy("+X+","+Y+")");
    }
}
