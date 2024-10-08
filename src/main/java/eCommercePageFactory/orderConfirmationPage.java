package eCommercePageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.utilities.AbstractClass;

import java.util.List;

public class orderConfirmationPage extends AbstractClass {

    WebDriver driver;
    public String order_id_on_final_page="";
    public String order_id_On_Summary_pag="";

    public orderConfirmationPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(className = "img-fluid")
    WebElement locator;
    @FindBy(xpath = "//tbody //tr //td  //h1")
    WebElement message;
    @FindBy(xpath = "//tbody //tr //td  //label[@class='ng-star-inserted']")
    WebElement getOrderID;


    public void orderConfirmation(){
        wait_until_Element_Visible(locator);
        System.out.println("Order Confirmation Message :"+message.getText());
        order_id_on_final_page=getOrderID.getText().split("\\|",3)[1].strip();
        System.out.println(" Final Page Order ID :"+order_id_on_final_page);
    }

    @FindBy(xpath = "//label[contains(text(),'Orders History')]")
    WebElement orderList;
    @FindBy(xpath = "//tbody //tr //th")
    List<WebElement> orderIds;
    @FindBy(xpath = "//button[text()='View']")
    List<WebElement> viewButtons;
    @FindBy(className = "artwork-card-image")
    WebElement locator1;



    public void orderList(){
        orderList.click();
        wait_until_Element_Visible(locator);
        for(int i=0; i<orderIds.size(); i++){
            if(orderIds.get(i).getText().equalsIgnoreCase(order_id_on_final_page)){
                viewButtons.get(i).click();
                break;
            }

        }
        wait_until_Element_Visible(locator1);
    }

    @FindBy(xpath = "//p[contains(text(),'Thank you')]")
    WebElement receiptPageMessage;
    @FindBy(xpath = "//div[@class='col-md-6'] //small//following-sibling::div")
    WebElement orderIDonSummaryPage;

    public void orderReceipt(){
        System.out.println("Receipt Page Message :"+receiptPageMessage.getText());
        order_id_On_Summary_pag=orderIDonSummaryPage.getText();
        System.out.println("Summary Page OrderID :"+orderIDonSummaryPage.getText());
    }
}
