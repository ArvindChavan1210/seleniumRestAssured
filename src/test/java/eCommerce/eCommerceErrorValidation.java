package eCommerce;

import commons.ListenerUtils;
import commons.Retry;
import commons.baseClass;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ListenerUtils.class)
public class eCommerceErrorValidation extends baseClass {

    @Test(dataProvider = "credentials", retryAnalyzer = Retry.class)
    public void validateErrorMessage(String un, String pw) {
        eCommerceLandingPage.validateErrorMessage(un, pw);
        Assert.assertEquals(eCommerceLandingPage.error_message, "Login Successfully", "Please Check Credentials");
    }



    @DataProvider
    public Object[][] credentials(){
        Object[][] creds=new Object[][] {
                {"arvindchavan7@email.com","test123"}, //valid name invalid password
                {"arvindchavan7@gmail.com","Indore@123"}, //valid name valid password
                {"arvindchavan8@gmail.com","Indore@123"},  //invalid name valid password
                {"arvind.chavan7@email.com","Test123"} //invalid name invalid password
        };
        return creds;
    }
}
