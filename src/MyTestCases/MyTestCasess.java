package MyTestCases;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class MyTestCasess {
	DesiredCapabilities caps = new DesiredCapabilities();

	AndroidDriver driver;

	Assertion myassert = new Assertion();
	

	@BeforeTest()

	public void MySetup() {

		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "abc");

		File myApplicationCalcuator = new File("src/MyApplications/calculator.apk");

		caps.setCapability(MobileCapabilityType.APP, myApplicationCalcuator.getAbsolutePath());

	}

	@Test(priority = 1)

	public void multiplyTwoNumber() throws IOException, InterruptedException {

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
		WebElement number9Caps = driver.findElement(By.id("com.google.android.calculator:id/digit_9"));
		WebElement Number5Caps = driver.findElement(By.id("com.google.android.calculator:id/digit_5"));
		WebElement multiplyCaps = driver.findElement(By.id("com.google.android.calculator:id/op_mul"));
		number9Caps.click();
		multiplyCaps.click();
		Number5Caps.click();
		WebElement equalcaps = driver.findElement(By.id("com.google.android.calculator:id/eq"));
		equalcaps.click();

		WebElement Results = driver.findElement(By.id("com.google.android.calculator:id/result_final"));

		myassert.assertEquals(Results.getText(), "45", "this is my assert");
		

	}

	@Test(priority = 2)

	public void PressEvenNumbers() throws IOException, InterruptedException {

		List<WebElement> allNumbers = driver.findElements(By.className("android.widget.ImageButton"));
		for (int i = 0; i < allNumbers.size(); i++) {
			
			if (allNumbers.get(i).getAttribute("resource-id").contains("digit")) {
				int Numbers =	Integer.parseInt(allNumbers.get(i).getAttribute("content-desc")) ;
				if (Numbers % 2 == 0) {
					allNumbers.get(i).click();
					Date date = new Date();
					String FileName = date.toString().replace(":", "-");
					TakesScreenshot ts = (TakesScreenshot) driver ; 
					Thread.sleep(2000);
					File file = ts.getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile( file , new File("./src/picturesForEvenNumber/"+FileName+".jpg"));

				}

			}

		}

	}

}
