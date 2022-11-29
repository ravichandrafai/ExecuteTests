package org.fai.ExtractAutomationTests;

import org.fai.driver.Driver;
import org.fai.driver.DriverManager;
import org.fai.enums.PropertyEnums;
import org.fai.reports.FrameworkLogger;
import org.fai.utils.ReadProperties;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

/**
 * Acts as a parent class for all the test classes in this framework.
 * All the test classes needs to extend this class. This class is responsible for invoking and terminating
 * browser under test.
 * 
 *
 */
public class BaseTest {

	
	WebDriver driver;
	protected BaseTest() {

	}
	/**
	 * Invokes a new browser instance and loads the respective URL.
	 * 
	 * 
	 * @param data Have all the data fed to the corresponding test method from data provider.In our case,it is
	 * a hashmap containing all the values from the excel sheet.
	 */
	@BeforeClass(groups={"prerequisites"})
	protected void init() {
		driver=Driver.initDriver("chrome");
		driver.get(ReadProperties.get(PropertyEnums.URL));
	}
	
	@BeforeMethod(groups={"prerequisites"})
	protected void setUp(Object[] data){
		FrameworkLogger.initiLogger();
		//Map<String,String> map = (Map<String, String>) data[0];
		DriverManager.setDriver(driver);
		//Driver.initDriver("chrome");
		
		}
	
	/**
	 * Terminates the browser instance
	 * 
	 */
	@AfterSuite(groups= {"smoke"})
	protected void tearDown() {
		Driver.quitDriver();
	}

}
