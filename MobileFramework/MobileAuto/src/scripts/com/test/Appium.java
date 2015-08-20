package scripts.com.test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

public class Appium {

	@Test
	public void Appium_SetUp() throws MalformedURLException {

		AppiumDriver d;
		// Android Capabilities
		DesiredCapabilities cap = new DesiredCapabilities();
		// cap.setCapability("deviceName", "emulator-5554");
		cap.setCapability("deviceName", "0123456789ABCDEF");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "5.1");
		cap.setCapability("appPackage", "com.vmware.vApprove");
		cap.setCapability("appActivity", ".VapproveActivity");
		d = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		d.manage().timeouts().implicitlyWait(1500, TimeUnit.SECONDS);

		/*
		 * DesiredCapabilities cap = new DesiredCapabilities();
		 * cap.setCapability("deviceName", "emulator-5554");
		 * cap.setCapability("platformName", "Android");
		 * cap.setCapability("platformVersion", "5.1");
		 * cap.setCapability("appPackage", "net.one97.paytm");
		 * cap.setCapability("appActivity", ".SplashActivity"); d = new
		 * AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),cap);
		 * d.manage().timeouts().implicitlyWait(1500,TimeUnit.SECONDS);
		 */

		/*WebElement we = d
				.findElement(By
						.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.view.View[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.view.View[2]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.TextView[1]"));*/
		
		WebElement we = d
				.findElementByXPath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.view.View[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.view.View[2]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.TextView[1]");
									 //android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.view.View[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.view.View[2]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.TextView[1]
		System.out.println("=================>>>>>>       " + we);

		we.click();
		System.out.println(d + "    ---------------------------");

	}

}
