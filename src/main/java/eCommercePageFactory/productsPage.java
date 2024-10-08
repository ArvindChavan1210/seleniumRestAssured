package eCommercePageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.utilities.AbstractClass;

import java.util.List;

public class productsPage extends AbstractClass {

    WebDriver driver;
    public String product_Selected="";
    public String price_of_product_selected="";
    public cartPage cartPage;

    public productsPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(className = "card")
    WebElement locator;
    @FindBy(xpath = "//div[@class='card-body'] //h5 //b")
    List<WebElement> products;
    @FindBy(xpath = "//button[text()=' View']")
    List<WebElement> viewButton;

    public void get_Single_Product(String productRequired){
        wait_until_Element_Visible(locator);
        for(int i=0; i< products.size(); i++){
            if(products.get(i).getText().equalsIgnoreCase(productRequired)){
                viewButton.get(i).click();
                break;
            }
        }
    }

    @FindBy(className = "img-fluid")
    WebElement locator1;
    @FindBy(css = "div h2")
    WebElement product;
    @FindBy(xpath = "//div[@class='col-lg-6 rtl-text'] //div //h3")
    WebElement price;
    @FindBy(css = ".btn-primary")
    WebElement add_to_CartButton;
    @FindBy(xpath = "//div[@id='toast-container']")
    WebElement toastContainer;
    @FindBy(xpath = "//div[@id='toast-container'] //div //div")
    WebElement message;

    public void getProductDetails(){
        wait_until_Element_Visible(locator1);
        product_Selected=product.getText();
        System.out.println("Product Selected :"+product_Selected);
        price_of_product_selected=price.getText().split(" ",2)[1].strip();
        System.out.println("Price of Product Selected :"+price_of_product_selected);
        add_to_CartButton.click();
        wait_until_Element_Visible(toastContainer);
        System.out.println(message.getText());

    }

    @FindBy(xpath = "//ul //li //button")
    List<WebElement> navigation_icons;

    public eCommercePageFactory.cartPage clickOnCartPage(){
        wait_until_Element_Invisible(toastContainer);
        for (WebElement navigationIcon : navigation_icons) {
            String icon_name = navigationIcon.getText().strip().split(" ", 2)[0].strip();
            if (icon_name.equalsIgnoreCase("cart")) {
                navigationIcon.click();
                break;
            }
        }
        return cartPage=new cartPage(driver);
    }
}
