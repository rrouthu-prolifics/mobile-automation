package scripts.com.test;

import org.testng.annotations.Test;

import scripts.com.core.MobileEngine;

public class TestClass extends MobileEngine{

	public TestClass() {
		System.out.println("Construcotr");
	}

	@Test
	public void doSauceAction() {
		String xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.view.View[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.view.View[2]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.TextView[1]";
		
		try {
			mobileAppDriver.effecta("sendReport", "Verifying User Ability to Launch vApprove App " ,"User able to Launch vApprove App", "Pass");
			Thread.sleep(5000);
			mobileAppDriver.effecta("sendReport", "Verifying User Ability to view vApprove App for 5 secs" ,"User able to view vApprove App for 5 secs", "Pass");
			mobileAppDriver.effecta("click", xpath, "Login Button");
			mobileAppDriver.effecta("sendReport", "Verifying User Ability to click initial Login button " ,"User able to click initial Login button", "Pass");
			mobileAppDriver.effecta("sendReport", "Verifying User Ability to see Login screen " ,"User able to see Login button", "Pass");
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*@AfterClass
	public void oneTearDown() throws IOException {

		// This method is used to quit the driver and generate the html report
		mobileAppDriver.stopSelenium();
	}*/
}
