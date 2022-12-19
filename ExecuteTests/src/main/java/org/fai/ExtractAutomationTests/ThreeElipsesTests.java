package org.fai.ExtractAutomationTests;

import java.util.Map;

import org.fai.driver.DriverManager;
import org.fai.pages.navigationbar.NavigationBar;
import org.fai.pages.worklist.AuditTrail;
import org.fai.pages.worklist.ThreeElipses;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;



public class ThreeElipsesTests extends BaseTest {

	private ThreeElipsesTests() {
		
	}
	@Test(priority=12,groups={"smoke"},dependsOnGroups = {"prerequisites"})
	public void auditTrail(Map<String, String> input) {
		
		
		new NavigationBar().gotoWorkList()
		.searchFiles(input.get("searchText"));
		
		ThreeElipses threeElipses = PageFactory.initElements(DriverManager.getDriver(),ThreeElipses.class);
		threeElipses
		.clickOnThreeElipses()
		.gotAuditTrail();
		new AuditTrail().closeAuditWindow();
					
		
		
		
	}
}
