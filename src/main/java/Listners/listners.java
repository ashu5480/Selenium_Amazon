package Listners;

import org.testng.ITestResult;
import org.testng.ITestListener;
import org.testng.ITestContext;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.ExtentTest;

import Base.BaseClass;

public class listners extends BaseClass implements ITestListener {
    private static ExtentTest extentTest;
    @Override
    public void onStart(ITestContext context) {
        // no-op
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extentReport.createTest(result.getMethod().getMethodName());
        extentTest = test;
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.skip("Test skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReport.flush();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // no-op
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        // no-op
    }
}
