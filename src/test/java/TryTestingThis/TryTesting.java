package TryTestingThis;

import commons.baseClass;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TryTesting extends baseClass {


    @Test
    public void form1() {
        tryTestingLandingPage.goTO();
        tryTestingLandingPage.getData();
        Assert.assertTrue(tryTestingLandingPage.isSelected,"Button Not Selected");
        Assert.assertEquals(tryTestingLandingPage.DropdownSelected,"option 1","Different Option Selected");
        Assert.assertEquals(tryTestingLandingPage.ListSelected,"option 2","Different Option Selected");
        Assert.assertTrue(tryTestingLandingPage.isCheckBoxSelected,"CheckBox not Selected");
    }

}
