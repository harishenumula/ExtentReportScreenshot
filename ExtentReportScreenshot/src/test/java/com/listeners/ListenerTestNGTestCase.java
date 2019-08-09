package com.listeners;

import java.util.NoSuchElementException;

import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.listeners.*;

@Listeners(com.listeners.MyTestNgListener.class)
public class ListenerTestNGTestCase {
	WebDriver driver;
	SoftAssert softAssert = new SoftAssert();

	@Parameters("Browser")

	// Test to pass as to verify listeners .
	@Test(priority = 1, enabled = true, groups = { "ReTesting" })
	public void TestToPass(String Browser) {
		System.setProperty("webdriver.chrome.driver", "E:\\Selenium\\TestNG_Demo\\chromedriver.exe");
		driver = new ChromeDriver();
		Reporter.log("opening " + Browser + " Browser");
		System.out.println("This method to pass test");
		driver.navigate().to("https://www.softwaretestingmaterial.com/100-software-testing-interview-questions/");
		Reporter.log("Navigating to Softwaretesting");
		driver.getTitle();
		driver.quit();
		Reporter.log("Browser closed");
	}

	// Used skip exception to skip the test
	@Parameters("name")
	@Test(priority = 2, invocationCount = 2, groups = "Regression Testing")
	public void TestToSkip() {
		System.out.println("This method to skip test");
		throw new SkipException("Skipping - This is not ready for testing ");
	}

	// In the above method, we have already closed the browser. So we couldnot get
	// the title here. It is to forcefully fail the test
	@Parameters("name")
	@Test(priority = 3, invocationCount = 2, invocationTimeOut = 3000, enabled = true, expectedExceptions = NoSuchElementException.class, groups = "ReTesting")
	public void TestToFail(String name) {
		System.out.println("This method going to test fail");
		Reporter.log("trying to get title of the page");
		Reporter.log("name= " + name);
		driver.getTitle();
	}// NoSuchSessionException

	@Parameters("name1")
	@Test(priority = 3, invocationCount = 2, invocationTimeOut = 3000, enabled = true, expectedExceptions = NoSuchSessionException.class, groups = {
			"ReTesting", "Regression Testing" })
	public void TestToFail1(String name) {
		System.out.println("This method going to test fail");
		Reporter.log("trying to get title of the page");
		Reporter.log("name= " + name);
		driver.getTitle();
		softAssert.assertEquals(true, false);
		softAssert.assertAll();

	}

	@Test
	public void exclude() {
		System.out.println("exclude");
	}

	@AfterTest
	public void softassert() {
		Reporter.log("Endin--------------");

	}
}
