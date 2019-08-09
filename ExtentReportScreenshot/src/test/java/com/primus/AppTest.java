package com.primus;

import org.testng.annotations.Test;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

	@Test
	public void kickStart() throws Throwable {
		PrimusExtentReport ds = new PrimusExtentReport();
		try {
			ds.titleVerification();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
