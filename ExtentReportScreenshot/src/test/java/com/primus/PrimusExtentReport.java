package com.primus;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class PrimusExtentReport {
	static WebDriver driver;
	ExtentReports extent;
	ExtentTest logger;
	String screenshotName;

	@BeforeTest
	public void setExtent() {
		extent = new ExtentReports(System.getProperty("user.dir") + "\\test-output\\ExtentReport.html", true);
		extent.addSystemInfo("username", "Harish");
		extent.addSystemInfo("OS", "Windows");
		extent.addSystemInfo("env", "testing");

	}

	@AfterTest
	public void endreport() {
		extent.flush();
		extent.close();
		driver.close();

	}

	@BeforeMethod
	public void setup() throws InterruptedException {
		// TestNG Related.
		System.setProperty("org.uncommons.reportng.escape-output", "false");

		// start extent test
		logger = extent.startTest(this.getClass().getName());

		System.setProperty("webdriver.chrome.driver", "E:\\selenium_jars\\chromedriver_win32\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.get("http://primusbank.qedgetech.com");
		Thread.sleep(5000);
	}

	@Test
	public void titleVerification() {
		// extent.startTest("titleVerification");
		String actTitle = driver.getTitle();
		Reporter.log("checking");

		Assert.assertEquals("Primus BanK", actTitle);
		// SoftAssert softAssert = new SoftAssert();
		// softAssert.assertEquals(actTitle, "Primus BanK");
		// softAssert.assertAll();

	}

	@Test
	public void t1() throws IOException {
		String screenShotPath = capture(driver, this.getClass().getSimpleName());

		Reporter.log("checking");

		// String path = "<img src=file://" + screenShotPath + " height='1000'
		// width='1000' />";
		// Reporter.log(path);

	}

	public static String capture(WebDriver driver, String screenShotName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String dest = System.getProperty("user.dir") + "\\ErrorScreenshots\\" + screenShotName + ".png";
		File destination = new File(dest);
		FileUtils.copyFile(source, destination);

		return dest;
	}

	@AfterMethod
	public void teardown(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			System.out.println("test Failed-----------------");
			logger.log(LogStatus.FAIL, "Test case Failed is" + result.getName());
			logger.log(LogStatus.FAIL, "Test case Failed is" + result.getThrowable());
			String screenShotPath = capture(driver, result.getMethod().getMethodName());

			// Adding screenshots to the Extent report
			logger.log(LogStatus.FAIL, "Snapshot below: " + logger.addScreenCapture(screenShotPath));
			System.out.println(screenShotPath);

			// String path = "<br><img src=file:\\" + screenshotName + "height='1000'
			// width='1000' /><br>";
			// Reporter.log(path);

		}
		logger.log(LogStatus.PASS, result.getMethod().getMethodName() + "test Pass");
		extent.endTest(logger);
	}
}
