package com.inetBanking.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	Properties prop;

	public ReadConfig() {
		File src = new File("./Configuration/Config.properties");
		try {
			FileInputStream fis = new FileInputStream(src);
			prop = new Properties();
			prop.load(fis);
		} catch (Exception e) {
			System.out.println("Exception is :" + e.getMessage());
		}
	}

	public String getApplicationURL() {
		String url = prop.getProperty("baseURL");
		return url;
	}

	public String getUserName() {
		String username = prop.getProperty("username");
		return username;
	}

	public String getPassword() {
		String chromeBrowser = prop.getProperty("password");
		return chromeBrowser;
	}

	public String getFirFoxPath() {
		String fireFoxBrowser = prop.getProperty("fireFoxpath");
		return fireFoxBrowser;
	}

	public String getChromePath() {
		String chromeBrowser = prop.getProperty("chromepath");
		return chromeBrowser;
	}

}
