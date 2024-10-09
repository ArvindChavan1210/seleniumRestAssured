package tryTestingPageFactory;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.utilities.AbstractClass;
import org.utilities.YamlReader;
import org.utilities.txtReader;

import java.util.Set;

public class tryTestLandingPage extends AbstractClass {

    WebDriver driver;
    String firstName= YamlReader.readYamlData("src/test/resources/config.yaml","name")
            .strip().split(" ",2)[0].strip();
    String lastName= YamlReader.readYamlData("src/test/resources/config.yaml","name")
            .strip().split(" ",2)[1].strip();
    public boolean isSelected;
    public String DropdownSelected;
    public String ListSelected;
    public boolean isCheckBoxSelected;
    int current_value;
    int upadated_value;
    txtReader txtReader=new txtReader();
    public sidEx sidEx;

    public tryTestLandingPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//button[@class='btn btn-success']")
    WebElement submitButton;
    @FindBy(id="fname")
    WebElement f_Name;
    @FindBy(id="lname")
    WebElement l_Name;
    @FindBy(xpath = "//label[text()='Male']//preceding-sibling::input[@id='male']")
    WebElement radioButton;
    @FindBy(name = "option")
    WebElement dropDown;
    @FindBy(id="owc")
    WebElement listOptions;
    @FindBy(name = "option3")
    WebElement checkBoxOption;
    @FindBy(xpath = "//input[@list='datalists']")
    WebElement element1;
    @FindBy(xpath = "//option[@value='Vanilla']")
    WebElement value;
    @FindBy(id="favcolor")
    WebElement favColor;
    @FindBy(id="day")
    WebElement calender;
    @FindBy(id="a")
    WebElement scrollBar;
    @FindBy(id="myfile")
    WebElement file;
    @FindBy(id="quantity")
    WebElement quantityByRange;
    @FindBy(name="message")
    WebElement messageBox;
    @FindBy(xpath="//div[@class='FPdoLc lJ9FBc']//input[@name='btnK']")
    WebElement locator;

    public void getData(){
        wait_until_Element_Clickable(submitButton);
        System.out.println("First Name :"+firstName+", Last Name :"+lastName);
        f_Name.sendKeys(firstName);
        l_Name.sendKeys(lastName);
        radioButton.click();
        isSelected= radioButton.isSelected();
        Select_Action(dropDown).selectByIndex(1);
        System.out.println(dropDown.getAttribute("value"));
        DropdownSelected=dropDown.getAttribute("value");
        Select_Action(listOptions).selectByVisibleText("Option 2");
        System.out.println(listOptions.getAttribute("value"));
        ListSelected=listOptions.getAttribute("value");
        checkBoxOption.click();
        System.out.println(checkBoxOption.getAttribute("value"));
        isCheckBoxSelected=checkBoxOption.isSelected();
        element1.sendKeys("Vanilla");
        element1.click();
        specific_action().moveToLocation(value.getLocation().x, value.getLocation().y).
                click().build().perform();
        specific_action().click().build().perform();
        wait_until_Element_Visible(favColor);
        System.out.println("Option Visible :" + element1.getAttribute("value"));
        System.out.println("Initial Value of Color: " + favColor.getAttribute("value"));
        favColor.sendKeys("#FF8000");
        wait_until_Element_Visible(favColor);
        System.out.println("Current Value of Color: " + favColor.getAttribute("value"));
        calender.sendKeys("26/09/2024");
        System.out.println("Date Displayed: " +calender.getAttribute("value"));
        current_value = Integer.parseInt(scrollBar.getAttribute("value"));
        System.out.println("Scroll Bar Current Position :" + current_value);
        scrollBar.click();
        while (current_value < 60) {
            specific_action().sendKeys(Keys.ARROW_UP).build().perform();
            current_value++;
        }
        upadated_value= Integer.parseInt(scrollBar.getAttribute("value"));
        System.out.println("Scroll Bar Position after update :" + upadated_value);
        wait_until_Element_Visible(file);
        file.sendKeys("C:\\Users\\ASUS\\OneDrive\\Desktop\\trial.json.csv");
        System.out.println("File Uploaded :" + file.getAttribute("value"));
        System.out.println("Quantity from Range Before :" +
                quantityByRange.getAttribute("value"));
        int j = 0;
        while (j < 4) {
            specific_action().sendKeys(Keys.ARROW_UP).build().perform();
            j++;
        }
        System.out.println("Quantity from Range After :" +
                quantityByRange.getAttribute("value"));
        System.out.println("Text in MessageBox before :" + messageBox.getText());
        messageBox.clear();
        messageBox.sendKeys(txtReader.readFile("src/test/resources/messageBoxText.txt"));
        submitButton.click();
        Set<String> windows = driver.getWindowHandles();
        String parentWindow=windows.iterator().next();
        for (String window : windows) {
            if (driver.switchTo().window(window).getTitle().equalsIgnoreCase("Google")) {
                driver.close();
                driver.switchTo().window(parentWindow);
                break;
            }
        }
    }

    public tryTestingPageFactory.sidEx callSidEx(){
        return sidEx=new sidEx(driver);
    }


    public void goTO(){
        driver.get("https://trytestingthis.netlify.app/");
    }
}
