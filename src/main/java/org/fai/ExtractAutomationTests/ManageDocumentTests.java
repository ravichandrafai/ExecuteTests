package org.fai.ExtractAutomationTests;

import java.awt.AWTException;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.fai.driver.DriverManager;
import org.fai.pages.ManageDocuments.ManageDocuments;
import org.fai.pages.extactions.Extraction;
import org.fai.pages.navigationbar.NavigationBar;
import org.fai.pages.worklist.UploadFiles;
import org.fai.pages.worklist.WorkList;
import org.fai.reports.ExtentLogger;
import org.testng.annotations.Test;

public class ManageDocumentTests extends BaseTest{
	
	private ManageDocumentTests() {
		
	}
	@Test(priority=5,groups={"smoke"},dependsOnGroups = {"prerequisites"})
	public void editDocumentandSave(Map<String, String> input) throws InterruptedException, AWTException {

			
		new NavigationBar()
		.gotoWorkList();
		String fileNames= new UploadFiles()
		.clickOnUploadFiles()
		.selectQueue(input.get("selectQueue"))
		.browseFiles(input.get("uploadfile"))
		.getUploadedFileName("NONE");
		new UploadFiles().clickUpload();
		new WorkList().waitForFilestatusChange(fileNames, input.get("exp status"));
		
		new WorkList()
		.clickValidate(input.get("searchText"));

		
		String currentURL = DriverManager.getDriver().getCurrentUrl();
		String docFileName = new ManageDocuments()
				.getDocFileName();

		Assertions.assertThat(input.get("searchText")).contains(docFileName);

		if(currentURL.contains("categorization")) {

			String pageHeading = new ManageDocuments()
					.getPageHeading();
			Assertions
			.assertThat(pageHeading)
			.isEqualTo("Manage Documents");

		}else if(currentURL.contains("extractions")) {

			new Extraction().retrunToManageDocuments();

		}

		String mgDocFileName = new ManageDocuments()
				.getFileName();

		Assertions.assertThat(input.get("searchText"))
		.contains(mgDocFileName);

		new ManageDocuments()
					.changeTheCategoryName("1", input.get("new category name"))
					.saveChanges()				
					.selectPage("8")
					.rotateLeft()
					.rotateLeft()
					.rotateRight()
					.zoominPage()
					.zoominPage()
					.zoominPage()
					.zoomOutPage();
					new ManageDocuments()
					.selectPageToMove("5")
					.selectCategoryTOMovePage("2")
					.selectPositionToMove("Beginning")
					.clickOnCopy()
					.saveChanges();
					
	}
	@Test(priority=6,groups={"smoke"},dependsOnGroups = {"prerequisites"})
	public void splitDocuments(Map<String, String> input) throws InterruptedException, AWTException {

		new NavigationBar()
		.gotoWorkList();
		String fileNames= new UploadFiles()
		.clickOnUploadFiles()
		.selectQueue(input.get("selectQueue"))
		.browseFiles(input.get("uploadfile"))
		.getUploadedFileName("NONE");
		new UploadFiles().clickUpload();
		new WorkList().waitForFilestatusChange(fileNames, input.get("exp status"));

		new WorkList()
		.clickValidate(input.get("searchText"));
		String currentURL = DriverManager.getDriver().getCurrentUrl();
		String docFileName = new ManageDocuments()
				.getDocFileName();

		Assertions.assertThat(input.get("searchText")).contains(docFileName);

		if(currentURL.contains("categorization")) {

			String pageHeading = new ManageDocuments()
					.getPageHeading();
			Assertions
			.assertThat(pageHeading)
			.isEqualTo("Manage Documents");

		}else if(currentURL.contains("extractions")) {

			new Extraction().retrunToManageDocuments();

		}

		String mgDocFileName = new ManageDocuments()
				.getFileName();

		Assertions.assertThat(input.get("searchText"))
		.contains(mgDocFileName);

		String newCatgrName= new ManageDocuments().splitDocument(input.get("split threshhold")).getNewDocCategoryName();
		ExtentLogger.pass("New catgory is created with no and Name as "+newCatgrName);
		
	}
	@Test(priority=16,groups={"smoke"},dependsOnGroups = {"prerequisites"})
	public void verifyLabelsandNotes(Map<String, String> input) throws InterruptedException, AWTException {
		
		new NavigationBar()
		.gotoWorkList();
		String fileNames= new UploadFiles()
		.clickOnUploadFiles()
		.selectQueue(input.get("selectQueue"))
		.browseFiles(input.get("uploadfile"))
		.getUploadedFileName("NONE");
		
		new UploadFiles().clickUpload();
		new WorkList().waitForFilestatusChange(fileNames, input.get("exp status"));
		
		new WorkList()
		.clickValidate(input.get("searchText"));

		String currentURL = DriverManager.getDriver().getCurrentUrl();
		String docFileName = new ManageDocuments()
				.getDocFileName();

		Assertions.assertThat(input.get("searchText")).contains(docFileName);

		if(currentURL.contains("categorization")) {

			String pageHeading = new ManageDocuments()
					.getPageHeading();
			Assertions
			.assertThat(pageHeading)
			.isEqualTo("Manage Documents");
		}else if(currentURL.contains("extractions")) {

			new Extraction().retrunToManageDocuments();

		}
		String mgDocFileName = new ManageDocuments()
				.getFileName();

		Assertions.assertThat(input.get("searchText"))
		.contains(mgDocFileName);

		new ManageDocuments()
					.clickNotes()
					.enterNotes("Notes")
					.addLabel("Test");
		new ManageDocuments()
					.changeTheCategoryName("1", "WCAB")
					.saveChanges()
					.gotoExtractions();
		
		new NavigationBar().gotoWorkList().sort().clickValidate(input.get("searchText"));
		
		new Extraction().retrunToManageDocuments();
		String notes= new ManageDocuments()
				.clickNotes()
		.getNotes();
		
		Assertions.assertThat(notes).isEqualTo("Notes");
		
		String label= new ManageDocuments()
				.clickNotes()
		.getLabel();
		Assertions.assertThat(label).isEqualTo("Test");
	}
	@Test(priority=17,groups={"smoke"},dependsOnGroups = {"prerequisites"})
	public void dragnDrop(Map<String, String> input) throws InterruptedException, AWTException {
		
		
		
		new NavigationBar()
		.gotoWorkList().searchFiles(input.get("searchText"));
		
		
		new WorkList()
		.clickValidate(input.get("searchText"));

		
		String currentURL = DriverManager.getDriver().getCurrentUrl();
		String docFileName = new ManageDocuments()
				.getDocFileName();

		Assertions.assertThat(input.get("searchText")).contains(docFileName);

		if(currentURL.contains("categorization")) {

			String pageHeading = new ManageDocuments()
					.getPageHeading();
			Assertions
			.assertThat(pageHeading)
			.isEqualTo("Manage Documents");

		}else if(currentURL.contains("extractions")) {

			new Extraction().retrunToManageDocuments();

		}

		String mgDocFileName = new ManageDocuments()
				.getFileName();

		Assertions.assertThat(input.get("searchText"))
		.contains(mgDocFileName);

		new ManageDocuments()
					.selectPage(input.get("gotopage"))
					.dragAndDrop(input.get("move to"));
	
					
	}

}
