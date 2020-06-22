package config;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import config.BrowserSetup.BrowserType;

public class ConfigReader {
	
	public static String URL;
	public static BrowserType browser;
	public static String grid_url;
	
	public static void readConfigData() throws Exception {
		ConfigReader conf = new ConfigReader();
		conf.readConfigFile();
	}
	
	public void readConfigFile() throws Exception {
		Properties prop = new Properties();
		File f = new File("C:\\Users\\Chandresh Shetty\\Workspace\\GridAutomationFRMK\\src\\main\\java\\config\\globalConfig.properties");
		FileInputStream fis = new FileInputStream(f);
		prop.load(fis);
		//prop.load(getClass().getResourceAsStream("globalConfig.properties"));
		URL=prop.getProperty("url");
		browser= BrowserType.valueOf(prop.getProperty("browser"));
		grid_url=prop.getProperty("selenium_host");
	}

}
