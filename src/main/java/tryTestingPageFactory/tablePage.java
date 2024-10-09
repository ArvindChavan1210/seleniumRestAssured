package tryTestingPageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.utilities.AbstractClass;
import org.utilities.javaScriptUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class tablePage extends AbstractClass {

    WebDriver driver;

    public tablePage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//div[@class='header'] //h1")
    WebElement locator;
    @FindBy(xpath = "//tr")
    List<WebElement> rows;
    List<String> header_names;
    ArrayList<HashMap<String, String>> table_data = new ArrayList<>();

    public void gettableData(){
        wait_until_Element_Visible(locator);
        javaScriptUtils.scrollPage(driver,"0","1500");
        header_names=List.of(rows.get(0).getText().split(" "));
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

        for (HashMap<String, String> table_datum : table_data) {
            if (table_datum.get("Firstname").equalsIgnoreCase("Pheobe")) {
                System.out.println("Name :" + table_datum.get("Firstname") + ", Occupation :" + table_datum.get("Occupation"));
            }
        }
    }

}
