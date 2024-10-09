package TryTestingThis;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.utilities.YamlReader;
import org.utilities.txtReader;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class TryTestingCommon {
    static txtReader txtReader=new txtReader();
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options=new ChromeOptions();
        options.addArguments("start-maximized","disable-popup-blocking","headless");
        WebDriver driver=new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://trytestingthis.netlify.app/");
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(driver
                .findElement(By.xpath("//button[@class='btn btn-success']"))));
        String firstName= YamlReader.readYamlData("src/test/resources/config.yaml","name")
                .strip().split(" ",2)[0].strip();
        String lastName= YamlReader.readYamlData("src/test/resources/config.yaml","name")
                .strip().split(" ",2)[1].strip();
        System.out.println("First Name :"+firstName+", Last Name :"+lastName);
        driver.findElement(By.id("fname")).sendKeys(firstName);
        driver.findElement(By.id("lname")).sendKeys(lastName);
        driver.findElement(By.xpath("//label[text()='Male']//preceding-sibling::input[@id='male']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Male']//preceding-sibling::input[@id='male']")).isSelected());
        Select sc=new Select(driver.findElement(By.name("option")));
        sc.selectByIndex(1);
        System.out.println(driver.findElement(By.name("option")).getAttribute("value"));
        Assert.assertEquals(driver.findElement(By.name("option")).getAttribute("value"),"option 1","Different Option Selected");
        Select sc1=new Select(driver.findElement(By.id("owc")));
        sc1.selectByVisibleText("Option 2");
        System.out.println(driver.findElement(By.id("owc")).getAttribute("value"));
        Assert.assertEquals(driver.findElement(By.id("owc")).getAttribute("value"),"option 2", "Different Option Selected");
        driver.findElement(By.name("option3")).click();
        System.out.println(driver.findElement(By.name("option3")).getAttribute("value"));
        WebElement element2 = driver.findElement(By.xpath("//input[@list='datalists']"));
        WebElement value = driver.findElement(By.xpath("//option[@value='Vanilla']"));
        element2.sendKeys("Vanilla");
        element2.click();
        Actions act=new Actions(driver);
        act.moveToLocation(value.getLocation().x, value.getLocation().y).
                click().build().perform();
        act.click().build().perform();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("favcolor"))));
        System.out.println("Option Visible :" + element2.getAttribute("value"));
        System.out.println("Initial Value of Color: " + driver.findElement(By.id("favcolor")).getAttribute("value"));
        driver.findElement(By.id("favcolor")).sendKeys("#FF8000");
        Thread.sleep(100);
        System.out.println("Current Value of Color: " + driver.findElement(By.id("favcolor")).getAttribute("value"));
        driver.findElement(By.id("day")).sendKeys("26/09/2024");
        System.out.println("Date Displayed: " + driver.findElement(By.id("day")).getAttribute("value"));
        int current_value = Integer.parseInt(driver.findElement(By.id("a")).getAttribute("value"));
        System.out.println("Scroll Bar Current Position :" + current_value);
        driver.findElement(By.id("a")).click();
        while (current_value < 60) {
            act.sendKeys(Keys.ARROW_UP).build().perform();
            current_value++;
        }
        int updated_value = Integer.parseInt(driver.findElement(By.id("a")).getAttribute("value"));
        System.out.println("Scroll Bar Position after update :" + updated_value);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("myfile"))));
        driver.findElement(By.id("myfile")).sendKeys("C:\\Users\\ASUS\\OneDrive\\Desktop\\trial.json.csv");
        System.out.println("File Uploaded :" + driver.findElement(By.id("myfile")).getAttribute("value"));
        driver.findElement(By.id("quantity")).click();
        System.out.println("Quantity from Range Before :" +
                driver.findElement(By.id("quantity")).getAttribute("value"));
        int j = 0;
        while (j < 4) {
            act.sendKeys(Keys.ARROW_UP).build().perform();
            j++;
        }
        System.out.println("Quantity from Range After :" +
                driver.findElement(By.id("quantity")).getAttribute("value"));
        WebElement messageBox = driver.findElement(By.name("message"));
        System.out.println("Text in MessageBox before :" + messageBox.getText());
        messageBox.clear();
        String messageToSend = txtReader.readFile("src/test/resources/messageBoxText.txt");
        System.out.println(messageToSend);
        messageBox.sendKeys(messageToSend);
        driver.findElement(By.xpath("//button[text()=' Submit']")).click();
        Set<String> windows = driver.getWindowHandles();
        String parentWindow=windows.iterator().next();
        for (String window : windows) {
            if (driver.switchTo().window(window).getTitle().equalsIgnoreCase("Google")) {
                driver.close();
                driver.switchTo().window(parentWindow);
                break;
            }
        }
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='header'] //h1"))));
        act.moveToElement(driver.findElement(By.xpath("//button[@onclick='alertfunction()']"))).build().perform();
        driver.findElement(By.xpath("//button[@onclick='alertfunction()']")).click();
        System.out.println("Alert! :" + driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
        System.out.println(driver.findElement(By.xpath("//p[contains(text(),'You Pressed the OK Button!')]")).getText());
        WebElement toolTip = driver.findElement(By.cssSelector("div[class='tooltip']"));
        act.moveToElement(toolTip).build().perform();
        wait.until(ExpectedConditions.visibilityOf(toolTip));
        System.out.println(driver.findElement(By.cssSelector("div[class='tooltip'] span")).getText());
        act.moveToLocation(100, 0).sendKeys(Keys.ESCAPE).build().perform();
        driver.findElement(By.cssSelector("div[class='side  ex1']")).click();
        JavascriptExecutor js_Executor = (JavascriptExecutor) driver;
        js_Executor.executeScript("window.scrollBy(0, 500);");
        js_Executor.executeScript("document.querySelector('.ex1').scrollBy(0,500)");
        WebElement doubleClickButton = driver.findElement(By.xpath("//button[contains(text(),'Double-click me')]"));
        act.doubleClick(doubleClickButton).build().perform();
        System.out.println(driver.findElement(By.
                xpath("//p[contains(text(),'Your Sample Double Click worked!')]")).getText());
        WebElement sourceObject = driver.findElement(By.id("drag1"));
        WebElement destinationObject = driver.findElement(By.id("div1"));
        act.dragAndDrop(sourceObject, destinationObject).build().perform();
        String credentials = driver.findElement(By.xpath("//h5"))
                .getText().split("\n", 6)[1].strip()
                .split(":", 2)[1].strip();
        System.out.println("Username & Password is :" + credentials);
        driver.findElement(By.id("uname")).sendKeys(credentials);
        driver.findElement(By.id("pwd")).sendKeys(credentials);
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='header']"))));
        System.out.println(driver.findElement(By.xpath("//h2")).getText());
        driver.findElement(By.linkText("here")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='header']"))));
        System.out.println("Home :" + driver.findElement(By.xpath("//div[@class='header']//h1")).getText());
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='header'] //h1"))));
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("window.scrollBy(0, 1500);");
        List<WebElement> rows = driver.findElements(By.xpath("//tr"));
        List<String> header_names = List.of(rows.get(0).getText().split(" "));
        ArrayList<HashMap<String, String>> table_data = new ArrayList<>();
        for (int i = 1; i < rows.size(); i++) {
            List<String> row = List.of(rows.get(i).getText().split(" "));
            HashMap<String, String> data = new HashMap<>();
            for (int k = 0; k < header_names.size(); k++) {
                if (k < row.size()) {  // Ensure index is within bounds
                    data.put(header_names.get(k), row.get(k));
                } else {
                    data.put(header_names.get(k), ""); // Handle missing data appropriately
                }
            }

            table_data.add(data);
        }
//        System.out.println(table_data);
        for (HashMap<String, String> table_datum : table_data) {
            if (table_datum.get("Firstname").equalsIgnoreCase("Pheobe")) {
                System.out.println("Name :" + table_datum.get("Firstname") + ", Occupation :" + table_datum.get("Occupation"));
            }
        }


        driver.quit();
    }
}
