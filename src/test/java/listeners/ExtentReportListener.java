package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportListener implements ITestListener {
    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> currentTest = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        ExtentSparkReporter spark = new ExtentSparkReporter("test-output/api-report.html");
        spark.config().setReportName("API Automation - ReqRes");
        spark.config().setDocumentTitle("API Test Results");
        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Base URL", "https://reqres.in/api");
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(
            result.getMethod().getMethodName(),
            result.getMethod().getDescription()
        );
        currentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        currentTest.get().pass("Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        currentTest.get().fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        currentTest.get().skip("Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
