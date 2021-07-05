package testDocker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class ChromeStandAloneTest {

	@BeforeSuite
	public void beforeSuite() throws IOException, InterruptedException {
		Calendar cal = Calendar.getInstance();
		cal.add(cal.SECOND, 60);
		long threshold = cal.getTimeInMillis();
		boolean flag = false;
		Runtime.getRuntime().exec("cmd.exe /c start  dockerUp.bat", null, new File(System.getProperty("user.dir")));
        Thread.sleep(3000);
		while (System.currentTimeMillis() < threshold) {

			if (flag) {

				break;
			}

			BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/dockerUp_logs.txt"));
			String lineContent = reader.readLine();

			while (lineContent != null && !flag) {
				if (lineContent.contains("The node is registered to the hub and ready to use")) {

					System.out.println("Searched keyword found in the file dockerUp_logs.txt");
					flag = true;
					break;
				}

				lineContent = reader.readLine();
			}

			reader.close();

		}
		Thread.sleep(10000);
		Assert.assertTrue(flag);
		

	}

	@AfterSuite
	public void afterSuite() throws IOException, InterruptedException {
		boolean flag = false;
		Calendar cal = Calendar.getInstance();
		cal.add(cal.SECOND, 60);
		long threshold = cal.getTimeInMillis();
		Runtime.getRuntime().exec("cmd.exe /c start  dockerDown.bat", null, new File(System.getProperty("user.dir")));

		while (System.currentTimeMillis() < threshold) {

			if (flag) {

				break;
			}
			BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/dockerUp_logs.txt"));
			String lineContent = reader.readLine();

			while (lineContent != null && !flag) {
				if (lineContent.contains("Shutdown complete")) {

					System.out.println("Searched keyword found in the file dockerUp_logs.txt");
					flag = true;
					break;
				}

				lineContent = reader.readLine();
			}

			reader.close();

		}
		Thread.sleep(10000);
		Assert.assertTrue(flag);
		
		File file=null;
        try {
		file=new File(System.getProperty("user.dir") + "/dockerUp_logs.txt");
		}
        catch(Exception e) {
        	e.printStackTrace();
        }
		
		if(file.exists()) {
			file.delete();
			Thread.sleep(3000);
			System.out.println("File deleted succesfully");
		}
		
		Runtime.getRuntime().exec("cmd.exe /c start Taskkill /IM cmd.exe /f", null,new File(System.getProperty("user.dir")));
		
	}

	@Test
	public void chromeTest1() throws MalformedURLException {

		URL url = new URL("http://localhost:4444/wd/hub");
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		WebDriver driver = new RemoteWebDriver(url, cap);
		driver.get("http://www.google.com");
		System.out.println(driver.getTitle());

	}

}
