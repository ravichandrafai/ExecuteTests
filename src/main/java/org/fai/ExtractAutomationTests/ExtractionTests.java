package org.fai.ExtractAutomationTests;

import java.awt.AWTException;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.fai.driver.DriverManager;
import org.fai.localUtil.ReadTaxanomyDetails;
import org.fai.pages.ManageDocuments.ManageDocuments;
import org.fai.pages.download.Download;
import org.fai.pages.extactions.ExtractedFields;
import org.fai.pages.extactions.Extraction;
import org.fai.pages.navigationbar.NavigationBar;
import org.fai.pages.worklist.UploadFiles;
import org.fai.pages.worklist.WorkList;
import org.fai.reports.ExtentLogger;
import org.testng.annotations.Test;

public class ExtractionTests extends BaseTest{

	private ExtractionTests() {
		
	}
	
	@Test(priority=11,groups={"smoke"},dependsOnGroups = {"prerequisites"})
	public void docViewerZoom(Map<String, String> input) throws AWTException, InterruptedException {
		
			
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

		String mgDocFileName = new ManageDocuments()
				.getFileName();
		Assertions
		.assertThat(input.get("searchText"))
		.contains(mgDocFileName);

		String currentURL= DriverManager.getDriver().getCurrentUrl();

		if(currentURL.contains("categorization")) {
			new ManageDocuments().gotoExtractions();
			
		}
			new Extraction().zoomin().zoomin().zoomin().zoomin()
			.zoomout()
			.reset();
	
	}
	
	@Test(priority=12,groups={"smoke"},dependsOnGroups = {"prerequisites"})
	public void validatedDocNameAndDownload(Map<String, String> input) throws InterruptedException, AWTException {


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
		.saveChanges();

		new ManageDocuments()
		.gotoExtractions();
		
		String nameBeforeValidation= new ExtractedFields()
		.getDocumentName();
		
		ExtentLogger.pass("Doc Name before validation "+nameBeforeValidation);
		new ExtractedFields() 
		.setetMatterId(input.get("matter id"))
		.setDocDate(input.get("doc date"))
		.verifyCaseName(input.get("case name"))
		.setDocType(input.get("doc cat"),input.get("doc sub cat"))
		.setTags(input.get("tags"))
		.clickOnValidate()
		.goBackToExtractions();
		String nameAfterValidation = new ExtractedFields().getDocumentName();
		new Extraction()
		.clickDownload();
		String downloadDocName = new Download().verifyTheDocName("1");
		Assertions.assertThat(nameAfterValidation).isEqualTo(downloadDocName);
	}

	@Test(priority=17,groups={"smoke"},dependsOnGroups = {"prerequisites"})
	public void uploadAndValidateFiles(Map<String, String> input) throws AWTException, InterruptedException {
	
		Thread.sleep(5000);
		
		new NavigationBar().gotoDashBorad();
		
		new NavigationBar().gotoWorkList();
//		String fileNames= new UploadFiles()
//		.clickOnUploadFiles()
//		.selectQueue(input.get("selectQueue"))
//		.browseFiles(input.get("uploadfile"))
//		.getUploadedFileName("NONE");
//		new UploadFiles().clickUpload();
//		new WorkList().waitForFilestatusChange(fileNames, input.get("exp status"));
		new WorkList().searchFiles(input.get("searchText"))
		.clickValidate(input.get("searchText"));
		String docFileName = new ManageDocuments()
				.getDocFileName();
		Thread.sleep(5000);
		String currentURL = DriverManager.getDriver().getCurrentUrl();
		
		Assertions.assertThat(input.get("searchText")).contains(docFileName);

		if(currentURL.contains("categorization")) {

		new ManageDocuments().gotoExtractions();

		}

		
		Assertions.assertThat(input.get("searchText")).contains(docFileName);
		String catName= new ExtractedFields().getCategoryName();
		String docType = new ExtractedFields().getDocType();
		Map<String,String> expExtractDetails = ReadTaxanomyDetails.getExtractiondetails(catName, docType);
		
		new ExtractedFields().verifyFieldOrder(expExtractDetails.get("field order"));
		new ExtractedFields().verifyCaseName(expExtractDetails.get("Case Name"))
							.verifyApplicantFirstName(expExtractDetails.get("Applicant First Name"))
							.verifyApplicantLastName(expExtractDetails.get("Applicant Last Name"))
							.verifyMatterID(expExtractDetails.get("Matter ID"))
							.verifyDocDate(expExtractDetails.get("Document Date"))
							.verifyDocType(expExtractDetails.get("Document Type"))
							.verifySender(expExtractDetails.get("Sender"))
							.verifyTags(expExtractDetails.get("External Tags"))
							.verifyDocName(expExtractDetails.get("Document Name"));
//							.clickOnValidate()
//							.goBackToExtractions();
//							

		
		
	}
}
