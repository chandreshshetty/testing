package config;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import base.BaseTest;

public class BrowserSetup extends BaseTest {
	
	RemoteWebDriver driver=null;

	public enum BrowserType {
		Chrome, Firefox, IE, Safari
	}

	@SuppressWarnings("static-access")
	public void InitializeBrowser(BrowserType type) throws MalformedURLException {
		DesiredCapabilities cap = null;
		switch (type) {
		case Chrome:
			System.out.println("-----browser invoking-----");
			System.setProperty("webdriver.chrome.silentOutput", "true");
			cap = new DesiredCapabilities().chrome();
			break;
		case Firefox:
			cap = new DesiredCapabilities().firefox();
			break;
		default:
			break;
		}
		URL url = new URL("http://"+ConfigReader.grid_url+":4444/wd/hub");
		driver = new RemoteWebDriver(url, cap);
		BaseTest.setRemoteWebDriverLocal(driver);
	}

	@BeforeMethod
	public void browserSetup() throws Exception {
		ConfigReader.readConfigData();
		InitializeBrowser(ConfigReader.browser);
		System.out.println("-----maximizing the browser window-----");
		BaseTest.getRemoteWebDriver().manage().window().maximize();
		BaseTest.getRemoteWebDriver().get(ConfigReader.URL);
	}

	@AfterMethod
	public void tearDown() {
		System.out.println("-----closing down browser window-----");
		BaseTest.getRemoteWebDriver().quit();
	}

}
