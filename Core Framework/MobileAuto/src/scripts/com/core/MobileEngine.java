package scripts.com.core;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.prolifics.MobileAppDriver;

public class MobileEngine {

	public MobileAppDriver mobileAppDriver = null;
	Hashtable<String, String> mobileCapabilities = null;
	
	String projectName = "MyVMWareAutomation";
	String moduleName = "IOS" + "_" + "I_PHONE";
	//String appPath = "sauce-storage:vApprove30_07_2015.zip";
	String appPath = "sauce-storage:vApprove.zip";
	//String appPath = "D:\\VMWare\\Apps\\vApprove30_07_2015.apk";//for IOS
	String appPackage = "com.vmware.vApprove";
	String appActivity = ".VapproveActivity";
	String userName = "";
	String accessKey = "e60cb4d8-ad86-415c-ac32-9fc5d9d82b68";
	
	public MobileEngine() {
		
	}

	@BeforeSuite
	@Parameters("env")
	public void setUp(boolean env) {

		System.out.println("from Framework Setup Sauce Labs Environments -->>    "+env);
		if(env)
			this.userName = "SureshCh";
		else{
			this.userName = "Local";
		}
		
		//working settings for Android
		mobileCapabilities = new Hashtable<String, String>();
		mobileCapabilities.put("appiumVersion", "1.3.7");
		mobileCapabilities.put("deviceName", "Android Emulator");
		mobileCapabilities.put("deviceOrientation", "portrait");
		mobileCapabilities.put("browserName", "");
		mobileCapabilities.put("platformVersion", "4.4");
		mobileCapabilities.put("platformName", "Android");
		mobileCapabilities.put("appPackage", "com.vmware.vApprove");
		mobileCapabilities.put("appActivity", ".VapproveActivity");
		
		//IOS settings
		/*mobileCapabilities = new Hashtable<String, String>();
		mobileCapabilities.put("appiumVersion", "1.3.7");
		mobileCapabilities.put("deviceName", "iPhone Simulator");
		mobileCapabilities.put("deviceOrientation", "portrait");
		mobileCapabilities.put("browserName", "");
		mobileCapabilities.put("platformVersion", "7.1");
		mobileCapabilities.put("platformName", "ios");
		mobileCapabilities.put("appPackage", "com.vmware.vApprove");
		mobileCapabilities.put("appActivity", ".VapproveActivity");*/
		
		try {
			mobileAppDriver = new MobileAppDriver(projectName, moduleName,"TestClass", "TestClass", userName, accessKey, mobileCapabilities,appPath, appPackage, appActivity);
			Thread.sleep(3000);
			
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
