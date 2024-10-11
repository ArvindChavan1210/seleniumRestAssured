package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/cucumber/featurefiles"
        , monochrome = true,
        glue = "cucumber/stepDefinations", plugin = {
        "html:target/cucumberReports/CucumberEx.html"
},tags = "@regression")


public class cucumberTestNGRunner extends AbstractTestNGCucumberTests {

}
