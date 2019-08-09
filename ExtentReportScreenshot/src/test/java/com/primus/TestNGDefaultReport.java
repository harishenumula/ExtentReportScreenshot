package com.primus;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;

@Listeners(com.listeners.MyTestNGListener.class)

public class TestNGDefaultReport {
	static WebDriver driver;
	static String path;

	@BeforeSuite
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "E:\\selenium_jars\\chromedriver_win32\\chromedriver.exe");
//New
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		driver = new ChromeDriver();
	}

	@BeforeMethod
	public void beforeEachMethod() {
		driver.get("http://google.com");
	}

//Test case 1
	@Test
	public void cars() throws Exception {
		System.out.println("I am Test method and I am searching for cars");
		driver.findElement(By.name("q")).sendKeys("Cars");
		Thread.sleep(3000);

		driver.findElement(By.name("btnK")).click();

//Wait for the results to appear
		Thread.sleep(2000);
		takeScreenshot();
		if (driver.findElement(By.partialLinkText("car")).isDisplayed()) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	}

//Test case 2
	@Test
	public void bikes() throws Exception {
		System.out.println("I am Test method and I am searching for bikes");
		driver.findElement(By.name("q")).sendKeys("Bikes");
		driver.findElement(By.name("btnK")).click();
//Wait for the results to appear
		Thread.sleep(2000);
		takeScreenshot();
		if (driver.findElement(By.partialLinkText("bike")).isDisplayed()) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	}

//Test case 3
	@Test
	public void moon() throws Exception {
		System.out.println("I am Test method and I am searching for moon");
		driver.findElement(By.name("q")).sendKeys("TestingDen");
		driver.findElement(By.name("btnK")).click();
//Wait for the results to appear
		Thread.sleep(2000);
		takeScreenshot();
		if (driver.findElement(By.partialLinkText("moon")).isDisplayed()) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	}

	@AfterTest
	public void reporterTest() {
		Reporter.log("test-----------------");
		Reporter.log(path);
	}

	@AfterSuite
	public void endOfSuite() {
		System.out.println("I am the end of suite");
		driver.quit();
	}

	public static String takeScreenshot() throws Exception {
		String timeStamp;
		File screenShotName;
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//The below method will save the screen shot in d drive with name "screenshot.png"
		timeStamp = new SimpleDateFormat("yyyyMMdd_HHmm").format(Calendar.getInstance().getTime());
		screenShotName = new File("E:\\Screenshots\\" + timeStamp + ".png");
		FileUtils.copyFile(scrFile, screenShotName);

		String filePath = screenShotName.toString();
		path = "<br><img src=File:\\" + filePath + " height='500' width='500'/><br>";
		return path;

	}

}