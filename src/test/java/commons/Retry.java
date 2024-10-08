package commons;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

    int count = 0;
    int repeat = 1;

    @Override
    public boolean retry(ITestResult result) {
        if (count < repeat) {
            count++;
            return true;
        }
        return false;

    }
}
