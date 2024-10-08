package commons;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Objects;

public class ListenerUtils implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports reports;
    public ExtentTest test;
    ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>(); //prevents from overloading the test results into another

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
        sparkReporter=new ExtentSparkReporter("target/ExtentReports/executionReport.html");
        sparkReporter.config().setDocumentTitle("Automation Testing");
        sparkReporter.config().setReportName("Automation Testing");
        sparkReporter.config().setTheme(Theme.STANDARD);
        reports=new ExtentReports();
        reports.attachReporter(sparkReporter);
        reports.setSystemInfo("Environment","QA");
       // reports.setSystemInfo("Date & Time of Execution", org.utilities.dateUtility.currentDateTime());
    }

    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        test= reports.createTest(result.getName()).log(Status.PASS,result.getName());
        extentTest.set(test);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        test=reports.createTest(result.getName());
        test.log(Status.FAIL,MediaEntityBuilder.createScreenCaptureFromBase64String(Objects.requireNonNull(baseClass.getScreenShot_asBase64(result.getName()))).build());
        test.log(Status.INFO, "Reason for failure is: " +result.getThrowable());
        extentTest.set(test);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
        test=reports.createTest(result.getName());
        test.log(Status.SKIP,"Was the test retried -"+result.wasRetried());
        extentTest.set(test);
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
        reports.flush();
    }
}
