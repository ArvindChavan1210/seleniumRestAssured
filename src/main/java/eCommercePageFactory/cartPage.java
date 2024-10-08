package eCommercePageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.utilities.AbstractClass;

public class cartPage extends AbstractClass {

    WebDriver driver;
    public String item_in_Cart="";
    public String price_of_item_in_Cart="";
    public purchasePage purchasePage;

    public cartPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }


    @FindBy(className = "itemImg")
    WebElement locator;
    @FindBy(xpath = "//div[@class='cartSection'] //h3")
    WebElement cartItem;
    @FindBy(xpath = "//div[@class='cartSection']//p[contains(text(),'MRP')]")
    WebElement itemPrice;
    @FindBy(xpath = "//button[contains(text(),'Checkout')]")
    WebElement checkoutButton;

    public eCommercePageFactory.purchasePage getCartData(){
    wait_until_Element_Visible(locator);
    item_in_Cart=cartItem.getText();
        System.out.println("Item in Cart :"+item_in_Cart);
    price_of_item_in_Cart=itemPrice.getText().strip().split("\\$",2)[1].strip();
        System.out.println("Price of Item in Cart :"+price_of_item_in_Cart);
    checkoutButton.click();
    return purchasePage=new purchasePage(driver);
    }

}
