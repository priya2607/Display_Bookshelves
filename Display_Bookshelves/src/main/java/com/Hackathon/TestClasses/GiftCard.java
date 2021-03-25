package com.Hackathon.TestClasses;
import java.util.concurrent.TimeUnit;

//import org.openqa.selenium.Alert;
//import org.openqa.selenium.JavascriptExecutor;
//import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Hackathon.Base.*;

public class GiftCard extends BaseUI
{
	
	@Test
	public void testForGiftCards() throws InterruptedException
	{
	   	logger = report.createTest("Test 4 ::Gift Cards");
	   	//count++;

		invokeBrowser();						// Invoke the browser
		openURL("websiteURL");							// Open Application
		
		//driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		Thread.sleep(3000);
		elementClick("gift_card_link_Xpath");
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		elementClick("gift_for_aniversaryorbirthday_Xpath");
		
		Thread.sleep(2000);
		enterText("amount_Xpath","2000");
		
		Thread.sleep(2000);
		mouseHoverAndSelect("month_drop_down_Xpath","Apr(2021)");
		
		Thread.sleep(2000);
		mouseHoverAndSelect("date_drop_down_Xpath","3");
		
		Thread.sleep(2000);
		elementClick("click_on_next_Xpath");
		
		Thread.sleep(1000);
		enterText("recipient_name_Xpath","Nagapriyadharsini");
		
		Thread.sleep(1000);
		enterText("recipient_email_Xpath","");
		
		Thread.sleep(1000);
		enterText("sender_name_Xpath","Gowtham");

		Thread.sleep(1000);
		enterText("sender_email_Xpath","priyadharshini@gmail.com");
		
		Thread.sleep(1000);
		enterText("sender_phone_Xpath","9876543210");
		
		Thread.sleep(1000);
		enterText("message_Xpath","Hello");
		
		Thread.sleep(2000);
		elementClick("confirm_Xpath");
		//Thread.sleep(2000);

	    validateResult("validate_payment_Xpath");
		
		//System.out.println("Gift Card Created Successfully ::)) ");
		
		quitBrowser();
	}
		
	
}
