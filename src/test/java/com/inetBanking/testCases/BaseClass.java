package com.inetBanking.testCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetBanking.utilities.ReadConfig;

public class BaseClass {

	ReadConfig readConfig = new ReadConfig();

	public String baseURL = readConfig.getApplicationURL();
	public String username = readConfig.getUserName();
	public String password = readConfig.getPassword();

	public static WebDriver driver;
	public static Logger logger;

	@Parameters("browser")
	@BeforeClass
	public void setup(String br) {

		logger = Logger.getLogger("ebanking");
		PropertyConfigurator.configure("log4j.properties");

		if (br.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + readConfig.getChromePath());
			driver = new ChromeDriver();
		} else if (br.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + readConfig.getFirFoxPath());
			driver = new FirefoxDriver();
		}
		driver.get(baseURL);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		logger.info("URL is loaded");
	}

	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	public String randomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(8);
		return generatedString;
	}

	public String randomNumber() {
		String generatedNum = RandomStringUtils.randomNumeric(4);
		return generatedNum;
	}
}
