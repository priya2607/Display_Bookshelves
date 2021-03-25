package com.Hackatone.TestClasses;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.Hackatone.Base.BaseUI;
import com.Hackatone.output.ShowOutputInConsole;
import com.Hackatone.output.ShowOutputInExcel;
public class GetBookshelves extends BaseUI
{
	@Test
	public void testOneForBookshelves() throws Exception					// Test Case to be passed		
	{
	   	logger = report.createTest("Test 1 :: Bookshelves");
		
		invokeBrowser();													// Invoke the browser
		openURL("websiteURL");													// Open Application
		
		//Thread.sleep(2000);				
		
		enterText("searchbox_Id","Bookshelves");								// Search Bookshelves on searchbox
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

					
		hitEnter("searchbox_Id");												// Hit enter to go on next search page
		
		waitLoad(2);	
		//waitLoad(2);
		//chooseManualPriceWithSlider("price_element_Xpath","price_drage_Xpath");
		//driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		Thread.sleep(3000);
		clickAndSelectWebElement("storage_type_Xpath","openchekbox_Id");		// Select storage type from list
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		Thread.sleep(3000);
		//waitLoad(2);
		//chooseManualPriceWithSlider("price_element_Xpath","price_drage_Xpath");	 // Update price range using slider
		waitLoad(2);													// wait for two seconds
		excludeChekBox("exclude_stock_chekbox_Id");
		waitLoad(1);															// wait for two seconds
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		
		
		List<WebElement> BookshelvesName = findSetOfElements("product_name_Xpath");		// List of Bookshelves name
		List<WebElement> Bookshelvesspecifications = findSetOfElements("product_specification_Xpath");	// list of Bookshelves specifications
		List<WebElement> BookshelvesDetails = findSetOfElements("product_price_Xpath");					// list of bookshelves price
		
		ShowOutputInConsole obj = new ShowOutputInConsole();									
		obj.printBookShelvesOutput(BookshelvesName,Bookshelvesspecifications,BookshelvesDetails); // show output in console
		
		waitLoad(1);																		// wait for two second
		
		ShowOutputInExcel soie2 = new ShowOutputInExcel();
		soie2.showBookshelvesInExcelFile(BookshelvesName,Bookshelvesspecifications,BookshelvesDetails);	// Show output Excels
		
		quitBrowser();
	}
	
	@AfterTest
	public void endReport() {
	report.flush();

	}
	
}
