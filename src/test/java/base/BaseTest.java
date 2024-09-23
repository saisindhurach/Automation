package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import services.AuthService;

public class BaseTest {
    protected static ExtentReports extent;
    protected static ExtentTest test;
    protected String authToken;

    @BeforeSuite
    public void setUp() {
        ExtentSparkReporter spark = new ExtentSparkReporter("extent-output/report.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);

        // Fetch auth token at the beginning of the suite
        AuthService authService = new AuthService();
        authToken = authService.getAuthToken();
    }

    @AfterSuite
    public void tearDown() {
        extent.flush(); // Write the report at the end of the test suite
    }
}
