package eCommercePageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.utilities.AbstractClass;
import org.utilities.PropertiesReader;
import org.utilities.javaScriptUtils;

public class eCommerceLandingPage extends AbstractClass {

    WebDriver driver;
    public String error_message;
    public productsPage productsPage;

    public eCommerceLandingPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void goTO(){
        driver.get(PropertiesReader.readProperties("src/test/resources/config.properties","ecommerceurl"));
    }

    @FindBy(className = "banner")
    WebElement locator;
    @FindBy(id="userEmail")
    WebElement userName;
    @FindBy(id="userPassword")
    WebElement password;
    @FindBy(id="login")
    WebElement loginButton;
    @FindBy(xpath = "//div[@id='toast-container']")
    WebElement toastContainer;
    @FindBy(xpath = "//div[@id='toast-container'] //div //div")
    WebElement message;

    public eCommercePageFactory.productsPage validateErrorMessage(String un, String pw){
        wait_until_Element_Visible(locator);
        userName.sendKeys(un);
        javaScriptUtils.drawBorderAroundElement(userName,driver);
        password.sendKeys(pw);
        loginButton.click();
        wait_until_Element_Visible(toastContainer);
        error_message=message.getText();
        System.out.println("Message Validation at Login :"+error_message);
        wait_until_Element_Invisible(toastContainer);
        return productsPage=new productsPage(driver);
    }
}
