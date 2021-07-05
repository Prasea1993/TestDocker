package testDocker;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class FirefoxStandAloneTest2 {
	
	@Test
	public void firefoxTest2() throws MalformedURLException {
		
		URL url=new URL("http://localhost:4444/wd/hub");
		DesiredCapabilities cap = DesiredCapabilities.firefox();
		WebDriver driver=new RemoteWebDriver(url, cap);
		driver.get("http://www.linkedin.com");
		System.out.println(driver.getTitle());
		
	}

}
