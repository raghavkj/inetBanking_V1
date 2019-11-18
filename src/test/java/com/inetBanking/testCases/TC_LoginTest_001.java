package com.inetBanking.testCases;

import org.testng.annotations.Test;

import java.io.IOException;

import org.testng.AssertJUnit;
import com.inetBanking.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass {

	@Test
	public void logiDDT() throws IOException {

		logger.info("URL is loaded");
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		logger.info("Entered User Name");

		lp.setPassword(password);
		logger.info("Entered Password");

		lp.clickSubmit();
		logger.info("Clicked on Submit");

		if (driver.getTitle().equals("Guru99 Bank Manager HomePage")) {
			AssertJUnit.assertTrue(true);
			logger.info("Login test passed");

		} else {
			captureScreen(driver, "loginTest");
			AssertJUnit.assertTrue(false);
			logger.info("Login test failed");
		}
	}

}
