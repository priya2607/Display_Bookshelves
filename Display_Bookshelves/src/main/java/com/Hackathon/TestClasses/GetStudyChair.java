package com.Hackathon.TestClasses;
import java.util.List;

import com.Hackathon.Base.*;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.Hackathon.output.ShowOutputInConsole;
import com.Hackathon.output.ShowOutputInExcel;
public class GetStudyChair extends BaseUI
{
	@Test
	public void testForStudyChairs() throws Exception 
	{
		//driver.close();
	   	logger = report.createTest("Test 3 :: Study Chairs");
		invokeBrowser();											// Invoke the browser
		openURL("websiteURL");											// Open Application
		
		enterText("searchbox_Id","Study Chair");						// Search Bookshelves on searchbox
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

		Thread.sleep(2000);				
		hitEnter("searchbox_Id");										// Hit enter to go on next search page
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

		elementClick("exclude_btn_click_Id");
		Thread.sleep(2000);		
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

																			
		List<WebElement> chair_name= findSetOfElements("chair_name_Xpath");		
		List<WebElement> chair_color= findSetOfElements("chair_color_Xpath");
		List<WebElement> chair_price= findSetOfElements("chair_price_Xpath");


		ShowOutputInConsole obj = new ShowOutputInConsole();
		obj.printBookShelvesOutput(chair_name,chair_color,chair_price);
		
		Thread.sleep(2000);		
		
		ShowOutputInExcel soie = new ShowOutputInExcel();
		soie.showStudyChairDetailsInExcelFile(chair_name,chair_color,chair_price);

		quitBrowser();
		
	}
	
}
