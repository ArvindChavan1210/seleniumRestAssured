//package eCommerce;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.Status;
//import commons.baseClass;
//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.openqa.selenium.*;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.edge.EdgeOptions;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
//import org.utilities.YamlReader;
//
//import java.time.Duration;
//import java.util.List;
//
//public class eCommerceGeneral {
//
//    public static void main(String[] args) {
//        WebDriverManager.chromedriver().setup();
//        ChromeOptions options=new ChromeOptions();
////        WebDriverManager.edgedriver().setup();
////        EdgeOptions options=new EdgeOptions();
//        options.addArguments("disable-popup-blocking");
//        options.addArguments("start-maximized");
////        WebDriver driver=new EdgeDriver(options);
//        WebDriver driver=new ChromeDriver(options);
//        baseClass baseclass=new baseClass();
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
//        driver.get("https://rahulshettyacademy.com/client");
//        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
//        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("banner"))));
//        driver.findElement(By.id("userEmail")).sendKeys("arvindchavan7@gmail.com");
//        driver.findElement(By.id("userPassword")).sendKeys("Indore@123");
//        driver.findElement(By.id("login")).click();
//        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@id='toast-container']"))));
//        System.out.println(driver.findElement(By.xpath("//div[@id='toast-container'] //div //div")).getText());
//        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@id='toast-container']"))));
//        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("card"))));
//        List<WebElement> products=driver.findElements(By.xpath("//div[@class='card-body'] //h5 //b"));
//        List<WebElement> viewButton=driver.findElements(By.xpath("//button[text()=' View']"));
//        for(int i=0; i< products.size(); i++){
//            if(products.get(i).getText().equalsIgnoreCase("IPHONE 13 PRO")){
//                viewButton.get(i).click();
//                break;
//            }
//        }
//        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("img-fluid"))));
//        String product_Selected=driver.findElement(By.cssSelector("div h2")).getText();
//        String price_on_products_page=driver.findElement(By.xpath("//div[@class='col-lg-6 rtl-text'] //div //h3")).getText().split(" ",2)[1].strip();
//        System.out.println("Price of "+ product_Selected+ " : "+ price_on_products_page);
//        driver.findElement(By.cssSelector(".btn-primary")).click();
//        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@id='toast-container']"))));
//        System.out.println(driver.findElement(By.xpath("//div[@id='toast-container'] //div //div")).getText());
//        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@id='toast-container']"))));
//        List<WebElement> navigation_icons=driver.findElements(By.xpath("//ul //li //button"));
//        for (WebElement navigationIcon : navigation_icons) {
//            String icon_name = navigationIcon.getText().strip().split(" ", 2)[0].strip();
//            if (icon_name.equalsIgnoreCase("cart")) {
//                navigationIcon.click();
//                break;
//            }
//        }
//        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("itemImg"))));
//        String item_in_Cart=driver.findElement(By.xpath("//div[@class='cartSection'] //h3")).getText();
//        String price_of_item_in_Cart=driver.findElement(By.xpath("//div[@class='cartSection']//p[contains(text(),'MRP')]")).getText().strip().split("\\$",2)[1].strip();
//        ExtentReports reports=baseClass.extentReporter();
//        try{
//        reports.setSystemInfo("Tester","Arvind Chavan");
//        reports.setSystemInfo("Environment","QA");
//        Assert.assertEquals(price_of_item_in_Cart,price_on_products_page,"Price Mismatch, Please check item selected");
//        reports.createTest("Price of Item in Cart",price_of_item_in_Cart).log(Status.PASS,"Price Available");
//        Assert.assertEquals(item_in_Cart,product_Selected,"Products Mismatch, Please check item selected");
//        reports.createTest("Item in Cart",item_in_Cart).log(Status.PASS,"Product in cart Matching with "+product_Selected);
//        } catch(AssertionError e){
//            System.out.println(e.getMessage());
//            reports.createTest("Validation of Product Added to Cart").fail(e.getMessage())
//                    .addScreenCaptureFromBase64String(baseclass.takeScreenshot_as_Base64(driver));
//        }
//
//
//        driver.findElement(By.xpath("//button[contains(text(),'Checkout')]")).click();
//        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains(text(),'Place Order')]"))));
//        driver.findElement(By.xpath("//div[contains(text(),'Credit Card Number ')]//following-sibling::input")).clear();
//        driver.findElement(By.xpath("//div[contains(text(),'Credit Card Number ')]//following-sibling::input")).sendKeys("4542 9931 9292 2222");
//        WebElement month= driver.findElement(By.xpath("//div[contains(text(),'Expiry Date ')]//following-sibling::select"));
//        Select sc=new Select(month);
//        sc.selectByVisibleText("10");
//        WebElement date=driver.findElement(By.xpath("//div[contains(text(),'Expiry Date ')]//following-sibling::select[2]"));
//        Select sc1=new Select(date);
//        sc1.selectByVisibleText("06");
//        driver.findElement(By.xpath("//div[contains(text(),'CVV Code ')]//following-sibling::input")).
//                sendKeys(YamlReader.readYamlData("cvv"));
//        driver.findElement(By.xpath("//div[contains(text(),'Name on Card ')]//following-sibling::input"))
//                        .sendKeys(YamlReader.readYamlData("name"));
//        Actions act=new Actions(driver);
//        act.moveToElement(driver.findElement(By.xpath("//div[@class='form-group']"))).click().build().perform();
//        act.moveToElement(driver.findElement(By.xpath("//div[@class='form-group'] //input"))).sendKeys("ind").build().perform();
//        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(".list-group .ta-item"))));
//        List<WebElement> countries=driver.findElements(By.cssSelector(".list-group .ta-item span"));
//        for (WebElement country : countries) {
//            if (country.getText().strip().equalsIgnoreCase("India")) {
//                act.moveToElement(country).click().build().perform();
//                break;
//            }
//        }
//
////       wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".list-group .ta-item"))));
//        driver.findElement(By.xpath("//a[contains(text(),'Place Order')]")).click();
//        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@id='toast-container']"))));
//        System.out.println(driver.findElement(By.xpath("//div[@id='toast-container'] //div //div")).getText());
//        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@id='toast-container']"))));
//        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("img-fluid"))));
//        System.out.println(driver.findElement(By.xpath("//tbody //tr //td  //h1")).getText());
//        String order_id_on_final_page=driver.findElement(By.xpath("//tbody //tr //td  //label[@class='ng-star-inserted']")).getText().split("\\|",3)[1].strip();
//        System.out.println(" Final Page Order ID "+order_id_on_final_page);
//        driver.findElement(By.xpath("//label[contains(text(),'Orders History')]")).click();
//        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("img-fluid"))));
//        List<WebElement> orderIds=driver.findElements(By.xpath("//tbody //tr //th"));
//        List<WebElement> viewButtons=driver.findElements(By.xpath("//button[text()='View']"));
//            for(int i=0; i<orderIds.size(); i++){
//                if(orderIds.get(i).getText().equalsIgnoreCase(order_id_on_final_page)){
//                    viewButtons.get(i).click();
//                    break;
//                }
//
//        }
//        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("artwork-card-image"))));
//        System.out.println(driver.findElement(By.xpath("//p[contains(text(),'Thank you')]")).getText());
//        String order_id_On_Summary_page=driver.findElement(By.xpath("//div[@class='col-md-6'] //small//following-sibling::div")).getText();
//        System.out.println("Order ID on Order Summary Page :"+order_id_On_Summary_page);
//        System.out.println("Order ID on Final Order Page   :" +order_id_on_final_page);
//        JavascriptExecutor jsexecutor=(JavascriptExecutor) driver;
//        jsexecutor.executeScript("window.scrollBy(0,200)");
//
//        try {
//            Assert.assertEquals(order_id_On_Summary_page.strip(),order_id_on_final_page.strip()+" Order Id's mismatch, Please check Summary Page");
//            reports.createTest("Final OrderID Validation").
//                    log(Status.PASS,"Order_ID_Final_Page="+order_id_on_final_page+"\nOrder_ID_Summry_Page="+order_id_On_Summary_page);
//        } catch (AssertionError e){
//            reports.createTest("Final OrderID Validation").fail("Order_ID_Final_Page="+order_id_on_final_page+"\nOrder_ID_Summry_Page="+order_id_On_Summary_page+" Mismatch")
//                    .addScreenCaptureFromBase64String(baseclass.takeScreenshot_as_Base64(driver));
//        }
//
//        reports.flush();
//        driver.quit();
//    }
//
//}
