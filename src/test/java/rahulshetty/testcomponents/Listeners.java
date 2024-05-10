package rahulshetty.testcomponents;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulshetty.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {

	ExtentReports extent = ExtentReporterNG.getReportObject();
	ExtentTest test;
    WebDriver driver;
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
    
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("Method failed with certain success percentage" + result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
        test.fail(result.getThrowable());
     try {
		driver=  (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
	} catch (Exception e) {
				e.printStackTrace();
	}
        String filepath = null;
      try {
		 filepath=  getScreenShot((result.getMethod().getMethodName()),driver);
			} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      test.addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("Method skipped" + result.getName());
	}

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}
	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}
	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Method passed" + result.getName());
        test.log(Status.PASS, "Test Passed");
	}

}
