package commons;

import eCommercePageFactory.eCommerceLandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.utilities.PropertiesReader;

import java.io.File;
import java.time.Duration;

public class baseClass {
    private String browser;
    public static WebDriver driver;
    public static eCommerceLandingPage eCommerceLandingPage;
    private String filePath = "src/test/resources/config.properties";

    @BeforeMethod
    public void launchApplication() {
        driver = getDriver();
        eCommerceLandingPage=new eCommerceLandingPage(driver);
        eCommerceLandingPage.goTO();

    }

    public WebDriver getDriver() {
        if (System.getProperty("browser") != null) {
            browser = System.getProperty("browser");
        } else {
            browser = PropertiesReader.readProperties(filePath, "driver");
        }

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("start-maximized", "disable-popup-blocking");
                driver = new ChromeDriver(options);
                break;
            case "chromeheadless":
                WebDriverManager.chromedriver().setup();
                ChromeOptions options1 = new ChromeOptions();
                options1.addArguments("start-maximized", "disable-popup-blocking", "headless");
                driver = new ChromeDriver(options1);
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions options2 = new EdgeOptions();
                options2.addArguments("start-maximized", "disable-popup-blocking");
                driver = new EdgeDriver(options2);
            default:
                throw new IllegalArgumentException("Invalid Browser Requested :" + browser);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        return driver;
    }

    public static void getScreenShotAsFile(String fileName) {
        try {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File src = takesScreenshot.getScreenshotAs(OutputType.FILE);
            File dst = new File("src/main/java/screenshots" + fileName + ".png");
            FileUtils.copyFile(src, dst);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static String getScreenShot_asBase64(String fileName){
        try {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            return takesScreenshot.getScreenshotAs(OutputType.BASE64);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @AfterMethod
    public void tearDown(){
        if(driver != null)
        {
            driver.quit();
        }
    }

}
