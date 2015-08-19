package scripts.com.test;
import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.prolifics.MobileAppDriver;

public class MRT_STG {

	WebDriverWait wait;

	String projectName = "MyVMWareAutomation";
	String moduleName = "IOS" + "_" + "I_PHONE";
	String appPath = "sauce-storage:Paytm.zip";
	//String appPath = "D:\\VMWare\\Apps\\vApprove30_07_2015.apk";
	String appPackage = "net.one97.paytm";
	String appActivity = ".SplashActivity";
	String userName = "";
	String accessKey = "e60cb4d8-ad86-415c-ac32-9fc5d9d82b68";

	Hashtable<String, String> mobileCapabilities;

	MobileAppDriver mobileAppDriver = null;

	@BeforeClass
	public void launchSauceLabApp() throws Exception {
		
		try {
			mobileCapabilities = new Hashtable<String, String>();
			mobileCapabilities.put("appiumVersion", "1.3.7");
			mobileCapabilities.put("deviceName", "Android Emulator");
			mobileCapabilities.put("deviceOrientation", "portrait");
			mobileCapabilities.put("browserName", "");
			mobileCapabilities.put("platformVersion", "4.4");
			mobileCapabilities.put("platformName", "Android");
			mobileCapabilities.put("appPackage", "net.one97.paytm");
			mobileCapabilities.put("appActivity", ".SplashActivity");
			//mobileCapabilities.put("app","sauce-storage:Paytm.zip");
			
			/*mobileCapabilities=new Hashtable<String, String>();
            mobileCapabilities.put("appiumVersion", "1.3.7");
            mobileCapabilities.put("deviceName","iPhone Simulator");
            mobileCapabilities.put("deviceOrientation", "portrait");
            mobileCapabilities.put("browserName","");
            mobileCapabilities.put("app","sauce-storage:MyCloud_Prod-appfile.app");
            mobileCapabilities.put("platformVersion","8.1");
            mobileCapabilities.put("platformName", "ios");*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Parameters("env")
	//false 	-	Local,	true	-	Sauce Labs
	public void login(boolean env) {
		System.out.println("Sauce Labs Environments -->>    "+env);
		if(env)
			this.userName = "SureshCh";
		else{
			this.userName = "Local";
		}	
		try {
			mobileAppDriver = new MobileAppDriver(projectName, moduleName,"Trial", "TrialOne", userName, accessKey, mobileCapabilities,appPath, appPackage, appActivity);
			//Thread.sleep(10000);
			mobileAppDriver.effecta("sendReport", "Verifying Supplier User Ability to save FTA document4 " ,"Supplier User Unable to save the FTA document", "Pass");
			String str = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.view.View[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.view.View[2]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.TextView[1]";
			mobileAppDriver.effecta("click", str, "Login Button");
			mobileAppDriver.effecta("sendReport", "Click Search Button" ,"Successfully clicked Search Button", "Pass");
			
			//mobileAppDriver.effecta("click");
			//mobileAppDriver.effecta("click", "id=login", "submit");
			//mobileAppDriver.driver.findElementByClassName("android.view.View[1]").click();
			
			//-DprjName="Demo" -DtestEnvironment="{Test1=Windows 7||Chrome}" -DinstanceName="" -DsauceUser="Local" -DmoduleName="test.demo" -DtestSetName="TS_1"
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@AfterClass
	public void oneTearDown() throws IOException {

		// This method is used to quit the driver and generate the html report
		//if(mobileAppDriver != null)
			mobileAppDriver.stopSelenium();
	}

}
