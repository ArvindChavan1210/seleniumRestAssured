package cucumber.stepDefinations;

import commons.baseClass;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import org.testng.Assert;

public class eCommerceStepDefs extends baseClass {


    @Given("user lands on home webPage")
    public void userLandsOnHomeWebPage() {
       launchApplication();
    }


    @Given("user logged in with {string} and password {string}")
    public void user_logged_in_with_and_password(String un, String pw) {
        baseClass.eCommerceLandingPage.validateErrorMessage(un,"Indore@123");
        Assert.assertEquals(eCommerceLandingPage.error_message, "Login Successfully", "Please Check Credentials");
    }

    @After
    public void tearDown(){
        if(driver!=null){
            driver.quit();
        }
    }

}
