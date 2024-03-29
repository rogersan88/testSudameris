package utils;

import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.android.AndroidDriver;
import tests.testBase;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListenersAndroid extends tests.test implements ITestListener {

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("TESTNG_LISTENERS_ANDROID : RUNNING: " + getTestMethodName(result));
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("TESTNG_LISTENERS_ANDROID : THE TEST : " + getTestMethodName(result) + " -> SUCCEED");
		// ExtentReports log operation for passed tests.
		ExtentTestManagerAndroid.getTest().log(LogStatus.PASS, "Test passed");
		ExtentTestManagerAndroid.endTest();
		ExtentManagerAndroid.getReporter().flush();

	}

	@Override
	public void onTestFailure(ITestResult result) {
		Object testClass = result.getInstance();
		AndroidDriver<?> webDriver = testBase.driver;

		// ExtentReports log and screenshot operations for failed tests.
		// Take base64Screenshot screenshot.
		String base64Screenshot = "data:image/png;base64,"
				+ ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BASE64);

		System.out.println("TESTNG_LISTENERS_ANDROID : THE TEST : " + getTestMethodName(result) + " -> FAILED");
		ExtentTestManagerAndroid.getTest().log(LogStatus.FAIL, "Test Failed " + result.getThrowable().toString(),
				ExtentTestManagerAndroid.getTest().addBase64ScreenShot(base64Screenshot));
		ExtentTestManagerAndroid.endTest();
		ExtentManagerAndroid.getReporter().flush();
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("TESTNG_LISTENERS_ANDROID : THE TEST : " + getTestMethodName(result) + " -> SKIPPED");
		// ExtentReports log operation for skipped tests.

		// Get driver from BaseTest and assign to local webDriver variable.
		Object testClass = result.getInstance();
		AndroidDriver webDriver = testBase.driver;

		// Take base64Screenshot screenshot.
		String base64Screenshot = "data:image/png;base64,"
				+ ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BASE64);

		ExtentTestManagerAndroid.getTest().log(LogStatus.SKIP, "Test Skipped: " + result.getThrowable().toString(),
				ExtentTestManagerAndroid.getTest().addBase64ScreenShot(base64Screenshot));
		ExtentTestManagerAndroid.endTest();
		ExtentManagerAndroid.getReporter().flush();

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("TESTNG_LISTENERS_ANDROID : TEST FAILED BUT IT IS DEFINED SUCCESS RATIO : "
				+ getTestMethodName(result));
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("TESTNG_LISTENERS_ANDROID : STARTING : " + context.getName());
		context.setAttribute("WebDriver", testBase.driver);
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("TESTNG_LISTENERS_ANDROID : FINISH : " + context.getName());
		// Do tier down operations for extentreports reporting!
		ExtentTestManagerAndroid.endTest();
		ExtentManagerAndroid.getReporter().flush();
	}
}
