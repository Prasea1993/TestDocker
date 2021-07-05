package testDocker;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class ChromeStandAloneTest2 {
	
	@Test
	public void chromeTest2() throws MalformedURLException {
		
		URL url=new URL("http://localhost:4444/wd/hub");
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		WebDriver driver=new RemoteWebDriver(url, cap);
		driver.get("https://www.toolsqa.com");
		System.out.println(driver.getTitle());
		
	}

}
