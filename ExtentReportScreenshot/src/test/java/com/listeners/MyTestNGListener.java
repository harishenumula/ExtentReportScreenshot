package com.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.primus.TestNGDefaultReport;

public class MyTestNGListener implements ITestListener {

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("Test Success : " + result.getMethod().getMethodName()+ " is " + result.getStatus());
		Reporter.log("Test Status : " + result.getMethod().getMethodName() + " is " + result.getStatus());
		

	}

	public void onTestFailure(ITestResult result) {
		System.out.println("Test Failed : " + result.getMethod().getMethodName()+ " is " + result.getStatus());
		Reporter.log("Test Failed : " + result.getMethod().getMethodName()+ " is " + result.getStatus());
		try {
			String path = TestNGDefaultReport.takeScreenshot();
			Reporter.log(path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

}
