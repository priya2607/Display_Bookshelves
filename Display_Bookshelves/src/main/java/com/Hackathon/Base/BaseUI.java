package com.Hackathon.Base;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.opera.OperaDriver;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.remote.RemoteWebDriver;
//import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.asserts.SoftAssert;

import com.Hackathon.utils.ExtentReportManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
//import com.datadriven.framework.utils.DateUtils;

public class BaseUI {
	public WebDriver driver;
	public Properties prop;
	public ExtentReports report = ExtentReportManager.getReportInstance();
	public ExtentTest logger;
	SoftAssert softAssert = new SoftAssert();

	public void invokeBrowser() {
		try {
			String propPath = "./src/test/resources/ObjectRepository/projectConfig.properties";
			FileInputStream file = new FileInputStream(new File(propPath));
			Properties prop = new Properties();
			prop.load(file);
			file.close();

			String browserName = prop.getProperty("browserName");

			if (browserName.equalsIgnoreCase("Chrome")) {

				System.setProperty("webdriver.chrome.driver","./src/test/resources/drivers/chromedriver.exe");
				driver = new ChromeDriver();
			} else  {
				
				System.setProperty("webdriver.gecko.driver","./src/test/resources/drivers/geckodriver.exe");
				driver = new FirefoxDriver();
			}/* else if (browserName.equalsIgnoreCase("Opera")) {

				System.setProperty("webdriver.opera.driver","./src/test/resources/drivers/operadriver.exe");
				driver = new OperaDriver();
			} else if (browserName.equalsIgnoreCase("remote")) {
				String Node = "http:192.168.1.100:5566/wd/hub";
				DesiredCapabilities cap = DesiredCapabilities.chrome();
				cap.setBrowserName("chrome");

				driver = new RemoteWebDriver(new URL(Node), cap);
			} else {
				driver = new SafariDriver();
			}*/
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
        
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
	

		if (prop == null) {
			prop = new Properties();
			try {
				FileInputStream file = new FileInputStream("./src/test/resources/ObjectRepository/projectConfig.properties");
				prop.load(file);

			} catch (Exception e) {
				reportFail(e.getMessage());
				e.printStackTrace();
			}

		}
	}

	public void openURL(String websiteURLKey) {
		try {
			driver.get(prop.getProperty(websiteURLKey));
			reportPass(websiteURLKey + " Identified Successfully");
			WebDriverWait wait=new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='close-reveal-modal hide-mobile']")));
			driver.findElement(By.xpath("//a[@class='close-reveal-modal hide-mobile']")).click();//closing the pop-up
			
			//a[@class='close-reveal-modal hide-mobile']
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

	}

	/****************** Close Browser ***********************/
	public void tearDown() {
		driver.close();

	}

	/****************** Quit Browser ***********************/
	public void quitBrowser() {
		driver.quit();

	}

	/****************** Enter Text ***********************/
	public void enterText(String xpathKey, String data) {
		try {
			getElement(xpathKey).sendKeys(data);
			reportPass(data + " - Entered successfully in locator Element : " + xpathKey);

		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	/****************** Hit Enter ***************************/
	public void hitEnter(String xpathKey) {
		try {
			getElement(xpathKey).sendKeys(Keys.ENTER);
			reportPass("Enter Button hitted successfully ::");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

	}

	public void clickAndSelectWebElement(String xpathKey, String idKey) {
		try {
			getElement(xpathKey).click();
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//input[@id='filters_storage_type_Open']")).click();
			//getElement(idKey).click();
			reportPass("Element selected successfully from dropdown :: " + idKey);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	/****************** Click Element ***********************/
	public void elementClick(String xpathKey) {
		try {
			getElement(xpathKey).click();
			reportPass(xpathKey + " :: Element Clicked Successfully");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	public void chooseManualPriceWithSlider(String xpathKey, String sliderXpath) throws InterruptedException {
		try {
			getElement(xpathKey).click();
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			
			Actions act = new Actions(driver);
			WebElement slider =driver.findElement(By.xpath("//div[@class='noUi-handle noUi-handle-upper']"));//
			//Action action = (Action) act.dragAndDropBy(slider, -150, 0).build();
		    //((Actions) action).perform();
			//act.dragAndDropBy(slider,-100,0).build().perform();
			act.clickAndHold(slider);
			Thread.sleep(2000);
			act.moveByOffset(-207, 0);
			act.release().build().perform();
			slider.click();
			Thread.sleep(2000);
			reportPass("Prices Choosen Successfully using slider");

		} catch (Exception e) {
			reportFail(e.getMessage());
		}

	}

	public void excludeChekBox(String idKey) {
		try {
			elementClick(idKey);
			reportPass("Element has excluded successfully:" + idKey);
		} catch (Exception e) {
			reportFail(e.getMessage());

		}

	}

	public List<WebElement> getProductDetails(String classKey) {
		List<WebElement> product_details = driver.findElements(By.className(prop.getProperty(classKey)));
		return product_details;
	}

	public List<WebElement> findSetOfElements(String xpathKey) {
		List<WebElement> element = driver.findElements(By.xpath(prop.getProperty(xpathKey)));
		return element;
	}

	public void mouseHoverAndSelect(String xpathKey, String visibleData) {
		try {
			WebElement choose = getElement(xpathKey);
			Select select = new Select(choose);
			driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			select.selectByIndex(2);
			reportPass(xpathKey + " : Mouse Hover and Click Successfully");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	public List<String> mouseclickAndSelectElements(String menuxpathKey, String xpathKey) {
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		elementClick(menuxpathKey);
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<WebElement> element = driver.findElements(By.xpath(prop.getProperty(xpathKey)));
		List<String> str = new ArrayList<String>();
		for (int i = 0; i < element.size(); i++)
			str.add(element.get(i).getText());
		System.out.println(str.size());
		return str;
	}

	public void validateResult(String validate_Xpath) {
		try {
			if (!getElement(validate_Xpath).isDisplayed()) {
			/*//	 String error_text = driver.findElement(By.xpath("")).getText();
				reportPass("Gift has been created successfully :: Verify the Details in attached ScreenShot");

			} else {*/
				//Thread.sleep(1000);
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				takeScreenShotForValidation();
				System.out.println("Screenshot taken");
				//reportFail("Wrong information Fed-Up :: Verify The Details in attached ScreenShot");
				
			}

		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	/****************** Identify Element ***********************/
	public WebElement getElement(String locatorKey) {
		WebElement element = null;

		try {
			if (locatorKey.endsWith("_Id")) {
				element = driver.findElement(By.id(prop.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identidied : " + locatorKey);
			} else if (locatorKey.endsWith("_Xpath")) {
				element = driver.findElement(By.xpath(prop.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identidied : " + locatorKey);
			} else if (locatorKey.endsWith("_ClassName")) {
				System.out.println("vikash");
				element = driver.findElement(By.className(prop.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identidied : " + locatorKey);
			} else if (locatorKey.endsWith("_CSS")) {
				element = driver.findElement(By.cssSelector(prop.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identidied : " + locatorKey);
			} else if (locatorKey.endsWith("_LinkText")) {
				element = driver.findElement(By.linkText(prop.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identidied : " + locatorKey);
			} else if (locatorKey.endsWith("_PartialLinkText")) {
				element = driver.findElement(By.partialLinkText(prop.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identidied : " + locatorKey);
			} else if (locatorKey.endsWith("_Name")) {
				element = driver.findElement(By.name(prop.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identidied : " + locatorKey);
			} else {
				reportFail("Failing the Testcase, Invalid Locator " + locatorKey);
				// Assert.fail("Failing The Test Cases: " + locatorKey);
			}
		} catch (Exception e) {

			// Fail the TestCase and Report the error
			reportFail(e.getMessage());
			e.printStackTrace();
			Assert.fail("Failing The Test Cases: " + e.getMessage());
		}
		return element;
	}

	/****************** Verify Element ***********************/

	public void verifyPageTitle(String pageTitle) {
		try {
			String actualTite = driver.getTitle();
			logger.log(Status.INFO, "Actual Title is : " + actualTite);
			logger.log(Status.INFO, "Expected Title is : " + pageTitle);
			Assert.assertEquals(actualTite, pageTitle);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	/****************** Assertion Functions ***********************/
	public void assertTrue(boolean flag) {
		softAssert.assertTrue(flag);
	}

	public void assertfalse(boolean flag) {
		softAssert.assertFalse(flag);
	}

	public void assertequals(String actual, String expected) {
		try {
			logger.log(Status.INFO, "Assertion : Actual is -" + actual + " And Expacted is - " + expected);
			softAssert.assertEquals(actual, expected);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

	}

	public void waitLoad(int i) {
		try {
			Thread.sleep(i * 1000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	/****************** Reporting Functions ***********************/
	public void reportFail(String reportString) {
		logger.log(Status.FAIL, reportString);
		takeScreenShotForValidation();
		Assert.fail(reportString);
	}

	public void reportPass(String reportString) {
		logger.log(Status.PASS, reportString);
	}

	@AfterMethod
	public void afterTest() {
		softAssert.assertAll();
		driver.quit();
	}

	/****************** Capture Screen Shot ***********************/
	public void takeScreenShotForValidation() {
		TakesScreenshot takeScreenShot = (TakesScreenshot) driver;
		//Thread.sleep(2000);
		File sourceFile = takeScreenShot.getScreenshotAs(OutputType.FILE);
		String capture_img = com.Hackathon.utils.DateUtils.getTimeStamp();
		File destFile = new File(System.getProperty("user.dir") + "//ScreenShot//" + capture_img + ".png");

		try {
			FileUtils.copyFile(sourceFile, destFile);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			logger.addScreenCaptureFromPath(System.getProperty("user.dir") + "/ScreenShot/" + capture_img + ".png");

		} catch (IOException e) {
			e.printStackTrace();
		}
		// logger.addScreenCaptureFromPath(destFile);

	}

	@AfterTest
	public void endReport() {
		report.flush();

	}

}
