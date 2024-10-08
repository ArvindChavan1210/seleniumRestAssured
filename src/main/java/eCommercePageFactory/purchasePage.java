package eCommercePageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.utilities.AbstractClass;
import org.utilities.YamlReader;

import java.util.List;

public class purchasePage extends AbstractClass {

    WebDriver driver;
    public orderConfirmationPage orderConfirmationPage;

    public purchasePage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//a[contains(text(),'Place Order')]")
    WebElement placeOrderButton;
    @FindBy(xpath = "//div[contains(text(),'Credit Card Number ')]//following-sibling::input")
    WebElement creditCardField;
    @FindBy(xpath = "//div[contains(text(),'Expiry Date ')]//following-sibling::select")
    WebElement month;
    @FindBy(xpath = "//div[contains(text(),'Expiry Date ')]//following-sibling::select[2]")
    WebElement date;
    @FindBy(xpath = "//div[contains(text(),'CVV Code ')]//following-sibling::input")
    WebElement cvvField;
    @FindBy(xpath = "//div[contains(text(),'Name on Card ')]//following-sibling::input")
    WebElement nameField;
    @FindBy(xpath = "//div[@class='form-group'] //input")
    WebElement countriesField;
    @FindBy(css = ".list-group .ta-item")
    WebElement countriesLocator;
    @FindBy(css = ".list-group .ta-item span")
    List<WebElement> countries;
    @FindBy(xpath = "//div[@id='toast-container']")
    WebElement toastContainer;
    @FindBy(xpath = "//div[@id='toast-container'] //div //div")
    WebElement message;

    public void paymentDetails(){
        wait_until_Element_Clickable(placeOrderButton);
        creditCardField.clear();
        creditCardField.sendKeys(YamlReader.readYamlData("credit_card"));
        Select_Action(month).selectByVisibleText("10");
        Select_Action(date).selectByIndex(6);
        cvvField.sendKeys(YamlReader.readYamlData("cvv"));
        nameField.sendKeys(YamlReader.readYamlData("name"));

    }

    public void shipmentDetails(){
        specific_action().moveToElement(countriesField).click().sendKeys("Ind").build().perform();
        wait_until_Element_Clickable(countriesLocator);
        for (WebElement country : countries) {
            if (country.getText().strip().equalsIgnoreCase("India")) {
                specific_action().moveToElement(country).click().build().perform();
                break;
            }
        }
        try{
            Thread.sleep(200);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public eCommercePageFactory.orderConfirmationPage placeOrder(){
        placeOrderButton.click();
        wait_until_Element_Visible(toastContainer);
        System.out.println(message.getText());
        wait_until_Element_Invisible(toastContainer);
        return orderConfirmationPage=new orderConfirmationPage(driver);
    }
}
