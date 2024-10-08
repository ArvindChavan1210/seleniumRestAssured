//package TryTestingThis;
//
//import commons.baseClass;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
//import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import java.io.IOException;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.time.Duration;
//import java.util.ArrayList;
//import java.util.List;
//
//public class TryTestingCommon {
//
//    static WebDriver driver = baseClass.getBrowser();
//    ;
//    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//    List<WebElement> items;
//    List<WebElement> Select_items;
//
//
//    @Test
//    public void Try_Test1() throws InterruptedException {
//        List<String> itemsReq = List.of("Sauce Labs Fleece Jacket", "Sauce Labs Backpack");
//        driver.get("https://www.saucedemo.com/");
//        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("login-button"))));
//        String userName = driver.findElement(By.id("login_credentials")).getText().split(":", 2)[1].strip()
//                .split("\n", 6)[0].strip();
//        System.out.println("Username :" + userName);
//        String password = driver.findElement(By.cssSelector(".login_password")).getText()
//                .split(":", 2)[1].strip();
//        System.out.println("Password :" + password);
//        driver.findElement(By.id("user-name")).sendKeys(userName);
//        driver.findElement(By.id("password")).sendKeys(password);
//        driver.findElement(By.id("login-button")).click();
//        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".inventory_item_img"))));
//        items = driver.findElements(By.xpath("//div[@class='inventory_item_description'] //div //a //div"));
//        Select_items = driver.findElements(By.xpath("//div[@class='inventory_item_description'] //div //a"));
//        if (items.size() == Select_items.size()) {
//            for (int i = 0; i < items.size(); i++) {
//                if (items.get(i).getText().equalsIgnoreCase("Sauce Labs Fleece Jacket")) {
//                    Select_items.get(i).click();
//                    break;
//                }
//            }
//        } else {
//            System.out.println("List is Not Matching Please Check Items");
//        }
//        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".inventory_details_img"))));
//        System.out.println("Product Selected :" + driver.findElement(By.xpath("//div[contains(text(),'Sauce Labs')]")).getText());
//        System.out.println("Price of product :" + driver.findElement(By.xpath("//div[contains(text(),'$')]")).getText());
//        driver.findElement(By.xpath("//button[contains(text(),'Add')]")).click();
//        Assert.assertTrue(driver.findElement(By.cssSelector(".btn_small")).
//                getText().equalsIgnoreCase("Remove"), "Option not Turned to Remove, Please Check WebPage");
//        driver.findElement(By.cssSelector(".btn_large")).click();
//        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".inventory_item_img"))));
//        items = driver.findElements(By.xpath("//div[@class='inventory_item_description'] //div //a //div"));
//        Select_items = driver.findElements(By.xpath("//div[@class='inventory_item_description'] //div //a"));
//        if (items.size() == Select_items.size()) {
//            for (int i = 0; i < items.size(); i++) {
//                if (items.get(i).getText().equalsIgnoreCase("Sauce Labs Backpack")) {
//                    Select_items.get(i).click();
//                    break;
//                }
//            }
//        } else {
//            System.out.println("List is Not Matching Please Check Items");
//        }
//        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".inventory_details_img"))));
//        System.out.println("Product Selected :" + driver.findElement(By.xpath("//div[contains(text(),'Sauce Labs')]")).getText());
//        System.out.println("Price of product :" + driver.findElement(By.xpath("//div[contains(text(),'$')]")).getText());
//        driver.findElement(By.xpath("//button[contains(text(),'Add')]")).click();
//        Assert.assertTrue(driver.findElement(By.cssSelector(".btn_small")).
//                getText().equalsIgnoreCase("Remove"), "Option not Turned to Remove, Please Check WebPage");
//        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
//        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".cart_list"))));
//        List<WebElement> items_inCart = driver.findElements(By.xpath("//div[@class='inventory_item_name']"));
//        List<String> list_of_items_inCart = new ArrayList<>();
//        for (WebElement webElement : items_inCart) {
//            list_of_items_inCart.add(webElement.getText());
//        }
//        Assert.assertEquals(list_of_items_inCart, itemsReq, "Different Items Selected");
//        driver.findElement(By.xpath("//button[contains(text(),'Checkout')]")).click();
//        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("continue"))));
//        driver.findElement(By.id("continue")).click();
//        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".error-message-container"))));
//        Assert.assertTrue(driver.findElement(By.cssSelector(".error-message-container")).isDisplayed(), "Error Message display Failed");
//        driver.findElement(By.id("first-name")).sendKeys("Test 1");
//        driver.findElement(By.id("last-name")).sendKeys("Test 2");
//        driver.findElement(By.id("postal-code")).sendKeys("123456");
//        driver.findElement(By.id("continue")).click();
//        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("finish"))));
//        List<WebElement> final_price=driver.findElements(By.cssSelector(".inventory_item_price"));
//        double price=0;
//        for (WebElement element : final_price) {
//            price = price + Double.parseDouble(element.getText().split("\\$", 2)[1]);
//        }
//        double total_price=Double.parseDouble(driver.findElement(By.cssSelector(".summary_subtotal_label")).
//                getText().split("\\$",2)[1].strip());
//        Assert.assertEquals(price,total_price,"Total Mismatch Please Check Values");
//        driver.findElement(By.id("finish")).click();
//        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("back-to-products"))));
//        System.out.println(driver.findElement(By.cssSelector(".complete-header")).getText());
//    }
//
//    @Test
//    public void findBrokenLink() throws IOException {
//        driver.get("https://www.saucedemo.com/");
//        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("login-button"))));
//        String userName = driver.findElement(By.id("login_credentials")).getText().split(":", 2)[1].strip()
//                .split("\n", 6)[0].strip();
//        System.out.println("Username :" + userName);
//        String password = driver.findElement(By.cssSelector(".login_password")).getText()
//                .split(":", 2)[1].strip();
//        System.out.println("Password :" + password);
//        driver.findElement(By.id("user-name")).sendKeys(userName);
//        driver.findElement(By.id("password")).sendKeys(password);
//        driver.findElement(By.id("login-button")).click();
//        List<WebElement> links = driver.findElements(By.tagName("a"));
//        for(int i=0; i< links.size(); i++){
//            String url=links.get(i).getAttribute("href");
//            URL link = new URL(url);
//            HttpURLConnection httpURLConnection = (HttpURLConnection) link.openConnection();
//            httpURLConnection.setConnectTimeout(3000); // Set connection timeout to 3 seconds
//            httpURLConnection.connect();
//            if (httpURLConnection.getResponseCode() == 200) {
//                System.out.println(url + " - " + httpURLConnection.getResponseMessage());
//            } else {
//                System.out.println(url + " - " + httpURLConnection.getResponseMessage() + " - " + "is a broken link");
//            }
//        }
//    }
//
////    @AfterTest
////    public void closeBrowser() {
////        try {
////            Thread.sleep(2000);
////        } catch (InterruptedException e) {
////            System.out.println(e.getMessage());
////        }
////        driver.quit();
////    }
//}
//
//
