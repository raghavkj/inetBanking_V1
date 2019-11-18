package com.inetBanking.testCases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import org.testng.Assert;
import com.inetBanking.pageObjects.LoginPage;
import com.inetBanking.utilities.XLUtils;

public class TC_LoginDD_002 extends BaseClass {

	@Test(dataProvider = "LoginData")
	public void loginDD(String user, String pwd) throws IOException, InterruptedException {

		LoginPage lp = new LoginPage(driver);
		lp.setUserName(user);
		logger.info("Username provided--" + user);
		lp.setPassword(pwd);
		logger.info("Password provided--" + pwd);
		lp.clickSubmit();

		if (isAlertPresent() == true) {
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			logger.warn("Login Failed");
			Thread.sleep(3000);
		} else {
			Assert.assertTrue(true);
			logger.warn("Login Passed");
			lp.clickLogout();
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			Thread.sleep(3000);
		}

	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@DataProvider(name = "LoginData")
	String[][] getData() throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/com/inetbanking/testData/LoginData.xlsx";
		int rownum = XLUtils.getRowCount(path, "Sheet1");
		int colcount = XLUtils.getCellCount(path, "Sheet1", 1);

		String logindata[][] = new String[rownum][colcount];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				logindata[i - 1][j] = XLUtils.getCellData(path, "Sheet1", i, j);// 1 0
			}

		}
		return logindata;
	}

}
