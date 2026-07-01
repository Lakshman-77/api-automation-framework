package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportListener implements ITestListener {

    private static final ExtentReports extent = createInstance();

    private static final ThreadLocal<ExtentTest> currentTest = new ThreadLocal<>();

    private static ExtentReports createInstance() {

        ExtentSparkReporter spark =
                new ExtentSparkReporter("test-output/api-report.html");

        spark.config().setReportName("API Automation - ReqRes");
        spark.config().setDocumentTitle("API Test Results");

        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(spark);
        extentReports.setSystemInfo("Base URL", "https://reqres.in/api");

        return extentReports;
    }

    @Override
    public void onStart(ITestContext context) {
        // Intentionally left empty.
        // Report is initialized only once.
    }

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest test = extent.createTest(
                result.getMethod().getMethodName(),
                result.getMethod().getDescription());

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