package ExtentDemo;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.restassured.response.Response;
import org.utilities.dateUtility;

import static io.restassured.RestAssured.given;

public class extentDemo {

    public static void main(String[] args) {
        //Select Reporter and define path where you want to store your report
        ExtentSparkReporter sparkReporter=new ExtentSparkReporter("target/ExtentReports/DemoReport/DemoReport.html");
        dateUtility dU=new dateUtility();
        //call ExtentReports class and attach reporter selected
        ExtentReports reports=new ExtentReports();
        reports.attachReporter(sparkReporter);

        //Configure the reporter
        sparkReporter.config().setDocumentTitle("Demo Automation Test Report");
        sparkReporter.config().setTheme(Theme.STANDARD);

        //Configure the report
        reports.setSystemInfo("Environment","QA");
        reports.setSystemInfo("Tester","Automation");
        reports.setSystemInfo("Date & Time", dU.currentDateTime());

        //Extent Test Class
        ExtentTest test= reports.createTest("Test Case 1");

        // Simple Pass Test
        test.pass("This is simple pass test");

        //Simple Fail Test
        test.fail("This is simple fail test");

        test= reports.createTest("Test Case 2");

        //Simple pass log
        test.log(Status.PASS,"This is Simple pass log");

        //Simple Fail log
        test.log(Status.FAIL,"This is Simple Fail log");

        /* log sequence :
        To decide step outcome such as Pass/Fail/Skip in case of a test with multiple logs should follow following sequence
        -info
        -pass
        -warning
        -skip
        -fail
        * */

        test= reports.createTest("Test Case 3");

        //simple warning test with multiple log events
        test.createNode("NODE 1").
                log(Status.WARNING,"This is warning log")
                .log(Status.SKIP,"Warning log should usually followed by SKIP Status")
                        .log(Status.FAIL,"SKIP should be followed by FAIL");

        test.createNode("NODE 2").log(Status.INFO,"This is just to test what is node")
                .log(Status.PASS,"This case follows sequence").assignAuthor("Arvind Chavan");

        //Handle text of log / test
        test= reports.createTest("Test Case 4","Handle Text of Test-Case 4");

        test.pass("This is how you can mark <b>text as bold</b> of test")
                .info("This is how you can mark <i>text as italic</i> of test").
                warning("This is how you can mark <b><i>text as bold as well as italic</i></b>").
                skip("This is how <mark>certain text in the test can be highlighted</mark>").assignAuthor("Arvind Chavan");


        //Handle exception
        test= reports.createTest("Test Case 5", "Demonstration of How to Handle Exception");
        try{
            int i=1/0;

        } catch (Exception e)
        {
            e.printStackTrace();
            test.fail("Failed to read properties: " + e.getMessage()).assignAuthor("Arvind Chavan");

        }

        //json data handling
        test= reports.createTest("Test Case 6","Json Data Handling");

        String jsonData="{\"fruit\": \"Apple\",\n" +
                "    \"size\": \"Large\",\n" +
                "    \"color\": \"Red\"\n" +
                "}";


        test.assignAuthor("Arvind Chavan").log(Status.PASS, MarkupHelper.createCodeBlock(jsonData, CodeLanguage.JSON));


        //handle xml data
        test= reports.createTest("Test Case 7","Handle XML Data");
        String xmlData="<note>\n" +
                "<to>Tove</to>\n" +
                "<from>Jani</from>\n" +
                "<heading>Reminder</heading>\n" +
                "<body>Don't forget me this weekend!</body>\n" +
                "</note>";

        test.assignAuthor("Arvind Chavan").log(Status.WARNING,MarkupHelper.createCodeBlock(xmlData, CodeLanguage.XML));


        //runtime application for API
        test=reports.createTest("Test Case 8","This is realtime application for API");
        Response response=given().when().get("https://reqres.in/api/users/2");
        //get Status Code
        test.log(Status.PASS,"Status Code :"+String.valueOf(response.getStatusCode())).assignAuthor("Arvind Chavan");
        //result contains certain Value
        boolean containsJanet = response.getBody().asPrettyString().contains("Arvind");
        test.log(Status.INFO, String.valueOf(containsJanet));

       // Conditionally generate a log message
        if (containsJanet) {
            test.log(Status.INFO, "The response contains 'Janet'.").generateLog(Status.PASS,"Pass");
        } else {
            test.log(Status.WARNING, "The response does not contain 'Janet'.").generateLog(Status.FAIL,"Fail");
        }
        //flush ExtentReports to store reports on designated path
        reports.flush();


    }
}
