package org.fai.pages.extactions;

import java.util.Arrays;
import java.util.List;

import org.fai.driver.DriverManager;
import org.fai.enums.ExplicitWaitExpextecConditions;
import org.fai.exceptions.FrameworkException;
import org.fai.generics.ExplicitWaitConditions;
import org.fai.pages.base.BasePage;
import org.fai.reports.ExtentLogger;
import org.fai.utils.ActionsUtil;
import org.fai.utils.JSExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;

public class ExtractedFields extends BasePage{

	private By fieldsDisplayed = By.xpath("//div[@class='PR_keyValuesGrid_body']//div//div[contains(@class,'PR_keyValGrid_item_1')]");
	
	private By catName = By.cssSelector("div.fai__flexbox3col_main>h5");
		
	private By caseNameLoc = By.xpath("//font[text()='Case Name']/parent::div/following-sibling::div/font");
	
	private By applicantFirstNameLoc = By.xpath("//font[text()='Applicant First Name']/parent::div/following-sibling::div/font");
	
	private By applicantLastNameLoc = By.xpath("//font[text()='Applicant Last Name']/parent::div/following-sibling::div/font");
	
	private By matterIDLoc = By.xpath("//font[text()='Matter ID']/parent::div/following-sibling::div/font//input");
	
	private By matterIDSearchInput = By.xpath("//input[@placeholder='Search']");
	
	private By matterIDSearch =By.xpath("//input[@placeholder='Search']/following-sibling::button[1]");
	
	private By initMatterIDSearch = By.xpath("//div[contains(@class,'MatterIDSearch')]");
	
	private By selectMatterID =By.xpath("//th[text()='Matter Id']/ancestor::thead/following-sibling::tbody/tr/td[1]");
	
	private By sender = By.xpath("//font[text()='Sender']/parent::div/following-sibling::div/font//input");
	
	private By doctor = By.xpath("//font[text()='Doctor']/parent::div/following-sibling::div/font");
	
	private By docDateLoc =By.xpath("//font[text()='Document Date']/parent::div/following-sibling::div/font");
	
	private By errorMsgLoc = By.xpath("//div[@class='PR_keyValGrid_item_errorhead']");
	
	private By docTypeDropDownLoc = By.xpath("//font[text()='Document Type']/parent::div/following-sibling::div//div");
	
	private By selectDocCatType;
	
	private By selectDocSubCatType;
	
	private By getTags = By.cssSelector("div.css-12jo7m5");
	private By tagsDropDownLoc = By.xpath("//font[text()='Tags']/parent::div/following-sibling::div/div[contains(@class,'zindex')]/child::div/child::Div[2]");
	
	private By notesLoc = By.xpath("//font[text()='Notes']/parent::div/following-sibling::div/font");
	
	private By docNameLoc = By.xpath("//div[text()='Document Name']/following-sibling::div");
	
	
	private By validate= By.xpath("//button[text()='Validate']");
	
	private By confirmationWindow = By.xpath("//h2[text()='Document Validation is Complete']");
	
	private By confirmValidation = By.xpath("//h2[text()='Document Validation is Complete']/parent::div/following-sibling::div/button[text()='Confirm']");
	
	private By goBackToExtraction = By.xpath("//h2[text()='Document Validation is Complete']/parent::div/following-sibling::div/button[text()='Go Back']");
	
	private By reviewOutput = By.xpath("//h2[text()='Document Validation is Complete']/parent::div/following-sibling::div/button[text()='Review File Output']");
	
	
	
	
	public ExtractedFields verifyCaseName(String expCaseName) {
		
		String actualCaseName = getText(caseNameLoc, ExplicitWaitExpextecConditions.PRESENSCE);
		if(actualCaseName.equals(expCaseName))
			ExtentLogger.pass("Actual case name "+actualCaseName+" is same as epected case name "+expCaseName,true);
		else {
			ExtentLogger.fail("Actual case name "+actualCaseName+ "different from expected case name"+expCaseName,true);
			throw new FrameworkException("Extracted details are not as expected");
		}
		return this;
		
	}
	public ExtractedFields verifyApplicantFirstName(String expFirstName) {
		String actualFirstName = getText(applicantFirstNameLoc, ExplicitWaitExpextecConditions.PRESENSCE);
		if(actualFirstName.equals(expFirstName))
			ExtentLogger.pass("Actual first name "+actualFirstName+" is same as epected first name "+expFirstName,true);
		else {
			ExtentLogger.fail("Actual first name "+actualFirstName+ "different from expected first name"+actualFirstName,true);
			throw new FrameworkException("Extracted details are not as expected");
		}
		return this;
	}
	public ExtractedFields verifyApplicantLastName(String expLastName) {
		String actualLastName = getText(applicantLastNameLoc, ExplicitWaitExpextecConditions.PRESENSCE);
		if(actualLastName.equals(expLastName))
			ExtentLogger.pass("Actual last name "+actualLastName+" is same as epected last name "+expLastName,true);
		else {
			ExtentLogger.fail("Actual last name "+actualLastName+ "different from expected last name"+expLastName,true);
			throw new FrameworkException("Extracted details are not as expected");
		}
		return this;
	}
	
	public ExtractedFields verifyMatterID(String expMatterID) {
		String actualMatterId = getAttribute(matterIDLoc, ExplicitWaitExpextecConditions.PRESENSCE,"value");
		if(actualMatterId.isEmpty()||!(actualMatterId.equals(expMatterID))) {
			ExtentLogger.fail("Actual Matter Id is different from expected MatterID",true);
			throw new FrameworkException("Extracted details are not as expected");
		}else {
			ExtentLogger.pass("Actual Matter Id is same as expected MatterID",true);
		}
		return this;
	}
	
	public ExtractedFields setetMatterId(String expMatterID) throws InterruptedException {
		
		String actualMatterId = getText(matterIDLoc, ExplicitWaitExpextecConditions.PRESENSCE);
		String matterIDSearchResLoc = "//th[text()='Matter Id']/ancestor::thead/following-sibling::tbody/tr/td[text()='"+expMatterID+"']";
		selectMatterID = By.xpath(matterIDSearchResLoc);
		if(actualMatterId.isEmpty()||!(actualMatterId.equals(expMatterID))) {
		JSExecutor.scrollByPixel("0", "300");
		JSExecutor.scrolltoView(matterIDLoc);
		clickUsinBy(initMatterIDSearch, ExplicitWaitExpextecConditions.PRESENSCE);
		enterText(matterIDSearchInput,expMatterID,ExplicitWaitExpextecConditions.PRESENSCE);
		clickUsinBy(matterIDSearch, ExplicitWaitExpextecConditions.PRESENSCE);
		
		ExplicitWaitConditions.performExplicitWait(selectMatterID, ExplicitWaitExpextecConditions.PRESENSCE);
		clickUsinBy(selectMatterID, ExplicitWaitExpextecConditions.CLICKABLE);
		ExplicitWaitConditions.invisbileOfElement(selectMatterID, ExplicitWaitExpextecConditions.NONE);
		String selectedMatterID = getAttribute(matterIDLoc, ExplicitWaitExpextecConditions.PRESENSCE,"value");
		ExtentLogger.pass("Selected the matter id "+selectedMatterID,true);
		
		}else {
			ExtentLogger.pass("Actual matter id "+actualMatterId+" is same as expected "+expMatterID+"matter id",true);
		}
		return this;
	}
	public ExtractedFields setDocDate(String expDocDate) {
		String actualDocDate = getText(docDateLoc, ExplicitWaitExpextecConditions.PRESENSCE);
		if(actualDocDate.isEmpty()||!(actualDocDate.equals(expDocDate)))
			enterText(docDateLoc,expDocDate, ExplicitWaitExpextecConditions.PRESENSCE);
		return this;
	}
	
	public ExtractedFields verifyDocDate(String expDocDate) {
		String actualDocDate = getText(docDateLoc, ExplicitWaitExpextecConditions.PRESENSCE);
		if(actualDocDate.isEmpty()||!(actualDocDate.equals(expDocDate))) {
			ExtentLogger.fail("Actual doc date "+actualDocDate+" is same as epected doc date "+expDocDate,true);
			throw new FrameworkException("Extracted details are not as expected");
		}
			else {
			ExtentLogger.pass("Actual doc date "+actualDocDate+ "different from expected doc date "+expDocDate,true);
			
		}
		
		return this;
	}
	
	public ExtractedFields verifyDocType(String expDocType) {
		String actualDocType = getText(docTypeDropDownLoc, ExplicitWaitExpextecConditions.PRESENSCE);
		if(actualDocType.isEmpty()||!(actualDocType.equals(expDocType))) {
			ExtentLogger.fail("Actual doc type "+actualDocType+ "different from expected doc type "+expDocType,true);
			throw new FrameworkException("Extracted details are not as expected");
		}
			
		else {
			ExtentLogger.pass("Actual doc type "+actualDocType+" is same as epected doc type "+expDocType,true);
		}
		return this;
	}
	public String getDocType() {
		
		String actualDocType = getText(docTypeDropDownLoc, ExplicitWaitExpextecConditions.PRESENSCE);
		return actualDocType;
	}
	public ExtractedFields setDocType(String expDocType, String expDocSubcat) throws InterruptedException {
		
		String specificCategory = "//div[@class='main_container']//ul//li[contains(@class,'main-items')]/button/div[text()='"+expDocType+"']";
		
		selectDocCatType= By.xpath(specificCategory);
		
		String specificSubCategory = "//div[@class='main_container']//div[@class='left_side']/ul/li/button[text()='"+expDocSubcat+"']";
		selectDocSubCatType = By.xpath(specificSubCategory);
		
		String actualDocType = getText(docTypeDropDownLoc, ExplicitWaitExpextecConditions.PRESENSCE);
		if(actualDocType.isEmpty()||!(actualDocType.equals(expDocType))) {
		WebElement e=DriverManager.getDriver().findElement(By.xpath("//font[text()='Notes']/parent::div/following-sibling::div/font"));
		Coordinates cor=((Locatable)e).getCoordinates();
		cor.inViewPort();
		ActionsUtil.scrollDown(docTypeDropDownLoc);
		ActionsUtil.moveToEleAndClick(docTypeDropDownLoc);
		JSExecutor.scrolltoView(selectDocCatType);
		JSExecutor.mouseHover(selectDocCatType);
		JSExecutor.scrolltoView(selectDocSubCatType);
		clickUsingJS(selectDocSubCatType, ExplicitWaitExpextecConditions.PRESENSCE, false);
		JSExecutor.scrolltoView(docNameLoc);
		}
		return this;
		
	}
	
	public ExtractedFields verifyTags(String expTags) {
		String[] expTagsArray = expTags.split(",");
		String[] actualsTags = new String[expTagsArray.length];
		List<WebElement> tagsList = findElements(getTags);
		int i=0;
		for(WebElement el:tagsList) {
			
			actualsTags[i]=el.getText();
			i++;
		}
		if(Arrays.equals(expTagsArray, actualsTags)) {
			ExtentLogger.pass("Actual tags "+actualsTags+" is same as expected tags "+expTagsArray,true);
			
			
		}else {
			ExtentLogger.fail("Actual tags "+actualsTags+ "different from  epected tags "+expTagsArray,true);
			throw new FrameworkException("Extracted details are not as expected");
		}
		return this;
	}
	
	public ExtractedFields setTags(String expTag) throws InterruptedException {
		
		String[] tags =expTag.split(",");
		if(tags.length>0) {
		for(String tag:tags) {
			JSExecutor.scrolltoView(tagsDropDownLoc);
			ActionsUtil.moveToEleAndClick(tagsDropDownLoc);
			ActionsUtil.sendText(tagsDropDownLoc, tag);
			ActionsUtil.clickEnter();
			
		}
		
	}
		return this;
	}
	
	public ExtractedFields verifyNotes(String expNotes) {
		
		String actualNotes = getText(notesLoc, ExplicitWaitExpextecConditions.PRESENSCE);
		if(actualNotes.isEmpty()||!(actualNotes.equals(expNotes)))
		{
			ExtentLogger.pass("Actual notes "+actualNotes+" is same as expected notes "+expNotes,true);
		}else {
			ExtentLogger.fail("Actual notes "+actualNotes+ "different from  expected notes "+expNotes,true);
			throw new FrameworkException("Extracted details are not as expected");
		}
		return this;
	}
	
	public ExtractedFields setNotes(String expNotes) {
		String actualNotes = getText(notesLoc, ExplicitWaitExpextecConditions.PRESENSCE);
		if(actualNotes.isEmpty()||!(actualNotes.equals(expNotes)))
			enterText(notesLoc,expNotes, ExplicitWaitExpextecConditions.PRESENSCE);
		return this;
	}
	
	public String getDocumentName() throws InterruptedException {
		JSExecutor.scrolltoView(docNameLoc);
		String docName = getText(docNameLoc, ExplicitWaitExpextecConditions.PRESENSCE);
		return docName;
	}
	public ExtractedFields verifyDocName(String expDocName) throws InterruptedException {
		
		String actualDocName = getDocumentName();
		if(actualDocName.isEmpty()||(!actualDocName.equals(expDocName)))
		{
			ExtentLogger.fail("Actual doc name "+actualDocName+ "different from expected doc name "+expDocName,true);
			throw new FrameworkException("Extracted details are not as expected");
			
		}else {
			ExtentLogger.pass("Actual doc name "+actualDocName+" is same as expected doc name "+expDocName,true);
		}
		return this;
	}
	public String getdateErrorMsg() {
		String dtError = getText(errorMsgLoc, ExplicitWaitExpextecConditions.PRESENSCE);
		return dtError;
	}
	public ExtractedFields clickOnValidate() {
		clickUsinBy(validate, ExplicitWaitExpextecConditions.CLICKABLE);
		ExplicitWaitConditions.performExplicitWait(confirmationWindow, ExplicitWaitExpextecConditions.PRESENSCE);
		return this;
	}
	
	
	public ExtractedFields verifySender(String expSender) {
		
		String actualSender = getAttribute(sender, ExplicitWaitExpextecConditions.PRESENSCE,"value");
		if(actualSender.isEmpty()||!(actualSender.equals(expSender)))
		{
			ExtentLogger.fail("Actual Sender  "+actualSender+ "different from expected Sender  "+expSender,true);
			//throw new FrameworkException("Extracted details are not as expected");
			
		}else {
			ExtentLogger.pass("Actual Sender  "+actualSender+" is same as expected Sender  "+expSender,true);
		}
		return this;
	}
	public ExtractedFields setSender(String expSender) {
		String actualSender = getText(sender, ExplicitWaitExpextecConditions.PRESENSCE);
		if(actualSender.isEmpty()||!(actualSender.equals(expSender)))
			ActionsUtil.sendText(sender, expSender);
			//enterText(sender,expSender, ExplicitWaitExpextecConditions.PRESENSCE);
		return this;
		
	}
	public ExtractedFields confirmValidations() {
		clickUsinBy(confirmValidation, ExplicitWaitExpextecConditions.CLICKABLE);
		return this;
	}
	public ExtractedFields goBackToExtractions() {
		
		clickUsinBy(goBackToExtraction, ExplicitWaitExpextecConditions.CLICKABLE);
		return this;
	}
	public ExtractedFields reviewOutputFile() {
		
		clickUsinBy(reviewOutput, ExplicitWaitExpextecConditions.CLICKABLE);
		return this;
	}
	public ExtractedFields verifyDoctor(String expDoctor) {
		String actualDoctor = getText(doctor, ExplicitWaitExpextecConditions.PRESENSCE);
		if(actualDoctor.isEmpty()||!(actualDoctor.equals(expDoctor)))
		{
			ExtentLogger.pass("Actual Doctor  "+actualDoctor+" is same as expected Doctor  "+expDoctor,true);
		}else {
			ExtentLogger.fail("Actual Doctor  "+actualDoctor+ "different from  expected Doctor  "+expDoctor,true);
			throw new FrameworkException("Extracted details are not as expected");
		}
		return this;
	}
	
	public ExtractedFields verifyFieldOrder(String expFieldOrder) {
		String[] expFldArray = expFieldOrder.split(",");
		String[] actualFldArray = new String[expFldArray.length];
		int i=0;
		List<WebElement> actualFlds = findElements(fieldsDisplayed);
		for(WebElement fld:actualFlds) {
			actualFldArray[i] = fld.getText();
			i++;
		}
		if(Arrays.equals(expFldArray, actualFldArray)) {
			ExtentLogger.pass("Actual fields  "+actualFldArray+" are same as expected fields  "+expFldArray,true);
		}else {
			ExtentLogger.fail("Actual fields  "+actualFldArray+ " are different from  expected fields  "+expFldArray,true);
			throw new FrameworkException("Extracted details are not as expected");
		
		}
		return this;
	}
	public String getCategoryName() {
		String categoryName = getText(catName, ExplicitWaitExpextecConditions.PRESENSCE);
		
		return categoryName;
	}
}
