package com.inetBanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetBanking.pageObjects.AddCutomerPage;
import com.inetBanking.pageObjects.LoginPage;

public class TC_AddCustomerTest_003 extends BaseClass {

	@Test
	public void addNewCutomer() throws InterruptedException, IOException {
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		lp.setPassword(password);
		lp.clickSubmit();
		Thread.sleep(2000);

		AddCutomerPage addcust = new AddCutomerPage(driver);
		addcust.clickAddNewCustomer();

		addcust.custName("Pavan");
		addcust.custgender("male");
		addcust.custdob("10", "15", "1985");		
		addcust.custaddress("INDIA");
		addcust.custcity("HYD");
		addcust.custstate("AP");
		addcust.custpinno("5000074");
		addcust.custtelephoneno("987890091");
		String email = randomString() + "@gmail.com";
		addcust.custemailid(email);
		addcust.custpassword("abcdef");
		addcust.custsubmit();

		logger.info("validation started....");
		Thread.sleep(3000);

		boolean res = driver.getPageSource().contains("Customer Registered Successfully!!!");
		if (res == true) {
			Assert.assertTrue(true);
			logger.info("test case passed....");
		} else {
			logger.info("test case failed....");
			captureScreen(driver, "addNewCutomer");
			Assert.assertTrue(false);
		}
	}

}
