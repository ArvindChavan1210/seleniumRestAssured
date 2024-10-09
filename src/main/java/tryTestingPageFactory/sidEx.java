package tryTestingPageFactory;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.utilities.AbstractClass;
import org.utilities.javaScriptUtils;

public class sidEx extends AbstractClass {

    WebDriver driver;
    public String message1="";

    public sidEx(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//div[@class='header'] //h1")
    WebElement locator;
    @FindBy(xpath = "//button[@onclick='alertfunction()']")
    WebElement alertButton;
    @FindBy(xpath = "//p[contains(text(),'You Pressed the OK Button!')]")
    WebElement alertMessage;
    @FindBy(css = "div[class='tooltip']")
    WebElement toolTip;
    @FindBy(css = "div[class='tooltip'] span")
    WebElement tipMessage;
    @FindBy(css = "div[class='side  ex1']")
    WebElement sideExBar;
    @FindBy(xpath = "//button[contains(text(),'Double-click me')]")
    WebElement doubleClickButton;
    @FindBy(xpath = "//p[contains(text(),'Your Sample Double Click worked!')]")
    WebElement doubleClickMessage;
    @FindBy(id="drag1")
    WebElement source;
    @FindBy(id="div1")
    WebElement destination;
    @FindBy(xpath = "//h5")
    WebElement creds;
    @FindBy(id="uname")
    WebElement unameField;
    @FindBy(id="pwd")
    WebElement pwdField;
    @FindBy(xpath = "//input[@type='submit']")
    WebElement submitButton;
    @FindBy(xpath = "//div[@class='header']")
    WebElement locator1;
    @FindBy(xpath = "//h2")
    WebElement msgField;
    @FindBy(linkText = "here")
    WebElement nextPage;

    public void getSideData(){
       wait_until_Element_Clickable(locator);
       specific_action().moveToElement(alertButton).click().build().perform();
        System.out.println("Alert! :" + driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
        System.out.println(alertMessage.getText());
        message1=alertMessage.getText();
        specific_action().moveToElement(toolTip).build().perform();
        wait_until_Element_Visible(toolTip);
        System.out.println(tipMessage.getText());
        specific_action().moveToLocation(100, 0).sendKeys(Keys.ESCAPE).build().perform();
        sideExBar.click();
        javaScriptUtils.scrollPage(driver,"0","500");
        javaScriptUtils.scrollSideWindow(driver,".ex1","0","500");
        specific_action().doubleClick(doubleClickButton).build().perform();
        System.out.println(doubleClickMessage.getText());
        specific_action().dragAndDrop(source,destination).build().perform();
        String credentials = creds
                .getText().split("\n", 6)[1].strip()
                .split(":", 2)[1].strip();
        System.out.println("Username & Password is :" + credentials);
        unameField.sendKeys(credentials);
        pwdField.sendKeys(credentials);
        submitButton.click();
        wait_until_Element_Visible(locator1);
        System.out.println(msgField.getText());
        nextPage.click();
    }

    public tablePage callTablePage(){
        return new tablePage(driver);
    }
}

