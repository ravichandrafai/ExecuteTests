package org.fai.pages.ManageDocuments;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.fai.driver.DriverManager;
import org.fai.enums.ExplicitWaitExpextecConditions;
import org.fai.exceptions.FrameworkException;
import org.fai.generics.ExplicitWaitConditions;
import org.fai.pages.base.BasePage;
import org.fai.pages.extactions.Extraction;
import org.fai.reports.ExtentLogger;
import org.fai.utils.ActionsUtil;
import org.fai.utils.JSExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ManageDocuments extends BasePage{


	private By title = By.xpath("//h1[contains(@class,'main-head mb-30')]");
	private By mngDocFileName = By.className("mngdocsfile_title");
	private By notes = By.xpath("//button[@class='fai__buttons faibtn__plain pr-3 b-r']//img[@class='img-responsive mr-2']");
	private By enterNotes = By.xpath("//textarea");
	private By closeNotes = By.xpath("//button[text()='X']");
	private By save = By.xpath("//button[@class='fai__buttons faibtn__plain pr-4']");
	private By splitDoc = By.xpath("//button[text()='Split Document']");
	private By getNewDocCateName = By.xpath("//span[@class='splittedDoc_new']/following-sibling::div");
	private By getNewCatNumber = By.xpath("//span[@class='splittedDoc_new']/ancestor::div//a[@class='mdcn-active']//div[@class='navdocsstatus_num_icon navdoc_notvalidated']");
	private By goBackToPrevCatgr = By.xpath("//div[text()=' Go back to the previous category']");
	private By gotoExtractions = By.xpath("//button[normalize-space()='Go to Extractions']");
	private By trash = By.xpath("//a[normalize-space()='Trash']");
	private By selectSpecificPage;
	private By pageCategory = By.xpath("//div[@class='docviewer_doc_name_txt']");
	private By zoomin = By.xpath("//button[@title='Zoom In']//img");
	private By zoomOut = By.xpath("//button[@title='Zoom Out']//img");
	private By reset = By.xpath("//button[@title='Reset']//img");
	private By rotateLeft = By.xpath("//a[@id='previewimage_RotateLeft']//img");
	private By rotateRight = By.xpath("//a[@id='previewimage_RotateRight']//img");
	private By moveOrCopyFromCategory;
	private By chkBoxForCatgory = By.xpath("//div[contains(text(), '. Claims')]/ancestor::div[@class='mngDocsCats__selectbox_search']/following-sibling::label/input");
	private By closePage = By.xpath("//button[@class='sidebarCollapse fai__buttons faibtn__dgrey']");
	private By renameFile = By.xpath("//button/img[@class='img-responsive']");
	private By renameInput = By.xpath("//input[@id='file_name']");
	private By moveToCategoryChkBox = By.xpath("//div[@class='col-md-6 col-sm-12 form-group']//span[@class='text']/parent::span/parent::label/input");
	private By moveat ;
	private By movePage = By.xpath("//button[@id='submit-button-create_user']");
	private By copyPage = By.xpath("//button[@id='submit-button-copy']");
	private By cancel = By.xpath("//button[@id='addcatCollapse']");
	private By lastPageNo= By.xpath("//div[contains(text(),'Trash')]/preceding::ul[contains(@class,'cf-manage-docslist mt-2 fbox')]/ul/li[last()]");

	private By categoryLeftPane = By.xpath("//ul[@class='section-nav']/li/a/div");
	private By categoryNoLeftPane = By.xpath("//ul[@class='section-nav']/li/a//div[contains(@class,'navdocsstatus_num_icon navdoc')]");
	private By selectCategoryName; //div[@class='managedocscat_nav']/parent::div/following-sibling::div//div[@class='mb-4 cf-manage-docs selectedCategory']//div[@class=' css-2b097c-container']//div[contains(text(),'1')];
	private By categorywisePages = By.xpath("//div[@class='managedocscat_nav']/parent::div/following-sibling::div//div[@class='mb-4 cf-manage-docs selectedCategory']//ul/ul/li");
	private By selectCategoryToMove = By.xpath("//div[@class='col-md-6 col-sm-12 form-group']/label//span[@class='text']") ;
	private By extracteFields = By.xpath("//div[@class='extractresults_wrap']");
	private By closeDocViewer = By.xpath("//div[contains(@class,'pagenclose')]//button");
	private By addLabel = By.xpath("//div[@class='mdcl__addlabel_wrap']");
	private By enterLabel = By.xpath("//div[@class='mdcl__addlabel_wrap']//div//input");
	private By saveLabel = By.xpath("//div[@class='mdcl__addlabel_wrap']//div/following-sibling::button[contains(@class,'blue')]");
	private By getLabel = By.xpath("//div[@class='mdcl__addlabel_wrap']//h5");
	private By dragAndDropTo;

 	public String getDocFileName() {

		String docFileName =getText(mngDocFileName, ExplicitWaitExpextecConditions.VISIBILE);
		return docFileName;

	}
	public String getPageHeading() {

		String pageHeading = getText(title, ExplicitWaitExpextecConditions.PRESENSCE);
		return pageHeading;
	}
	public String getFileName() {
		String fileName= getText(mngDocFileName, ExplicitWaitExpextecConditions.PRESENSCE);
		return fileName;

	}
	public void waitForFileName() {

		ExplicitWaitConditions.performExplicitWait(mngDocFileName, ExplicitWaitExpextecConditions.VISIBILE);

	}
	public ManageDocuments clickNotes() {
		clickUsinBy(notes, ExplicitWaitExpextecConditions.CLICKABLE);
		return this;
	}
	public ManageDocuments enterNotes(String notes) {
		enterText(enterNotes, notes, ExplicitWaitExpextecConditions.VISIBILE);
		return this;
	}
	public ManageDocuments closeNotes() {
		clickUsinBy(closeNotes, ExplicitWaitExpextecConditions.PRESENSCE);
		return this;
	}
	public ManageDocuments saveChanges() {
		clickUsinBy(save, ExplicitWaitExpextecConditions.PRESENSCE);
		waitForPageload();
		return this;
	}
	public ManageDocuments clickSplitDocument() {
		clickUsinBy(splitDoc,ExplicitWaitExpextecConditions.CLICKABLE);
		return this;
	}
	public Extraction gotoExtractions() {
		waitForPageload();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clickUsingJS(gotoExtractions,ExplicitWaitExpextecConditions.CLICKABLE,true);
		ExplicitWaitConditions.performExplicitWait(extracteFields, ExplicitWaitExpextecConditions.PRESENSCE);
		return new Extraction();
	}
	public ManageDocuments renameFile(String fileName) {
		clickUsinBy(renameFile, ExplicitWaitExpextecConditions.PRESENSCE);
		enterText(renameInput, fileName, ExplicitWaitExpextecConditions.PRESENSCE);
		return this;
	}
	public ManageDocuments gotoTrash() {

		clickUsinBy(trash,ExplicitWaitExpextecConditions.PRESENSCE);
		return this;
	}
	public ManageDocuments selectPage(String pageNumber) {
		ExplicitWaitConditions.performExplicitWait(save, ExplicitWaitExpextecConditions.CLICKABLE);
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(),Duration.ofSeconds(20));
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(categoryLeftPane)));
		List<WebElement> categoryList = DriverManager.getDriver().findElements(categoryLeftPane);
		boolean isSelected = false;
		for(WebElement ele:categoryList) {
			try {
				JSExecutor.click(ele);
				Thread.sleep(200);
				wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(categoryLeftPane)));
				List<WebElement> categoryPagesList = DriverManager.getDriver().findElements(categorywisePages);
				for(WebElement page:categoryPagesList) {
					Thread.sleep(200);
					String attValue = page.getAttribute("data-id");
					if(attValue.equals(pageNumber)){
						String pageLoc= "//div[@class='docImg' and @id='"+pageNumber+"']";
						selectSpecificPage = By.xpath(pageLoc);
						clickUsinBy(selectSpecificPage, ExplicitWaitExpextecConditions.CLICKABLE);
						isSelected=true;
					}
					if(isSelected)
						break;

				}
			}catch(StaleElementReferenceException ex) {

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(isSelected)
				break;
		}

		return this;

	}
	public String getPageCategory() {

		String pageCategoryName = getTextJS(pageCategory,ExplicitWaitExpextecConditions.PRESENSCE);
		return pageCategoryName;
	}
	public ManageDocuments zoominPage() {

		clickUsinBy(zoomin,ExplicitWaitExpextecConditions.PRESENSCE);
		return this;
	}
	public ManageDocuments zoomOutPage() {

		clickUsinBy(zoomOut, ExplicitWaitExpextecConditions.PRESENSCE);
		return this;
	}
	public ManageDocuments resetChanges() {
		clickUsinBy(reset,ExplicitWaitExpextecConditions.PRESENSCE);
		return this;
	}
	public ManageDocuments rotateLeft() {
		String beforeRotationAngle = getRotationAngle(selectSpecificPage);
		ExtentLogger.pass("Rotations angle before rotate left "+beforeRotationAngle);
		clickUsinBy(rotateLeft, ExplicitWaitExpextecConditions.PRESENSCE);
		String afterRotationAngle = getRotationAngle(selectSpecificPage);
		ExtentLogger.pass("Rotations angle before rotate left "+afterRotationAngle);
		return this;
	}
	public ManageDocuments rotateRight() {
		String beforeRotationAngle = getRotationAngle(selectSpecificPage);
		ExtentLogger.pass("Rotations angle before rotate left "+beforeRotationAngle);
		clickUsinBy(rotateRight,ExplicitWaitExpextecConditions.PRESENSCE);
		String afterRotationAngle = getRotationAngle(selectSpecificPage);
		ExtentLogger.pass("Rotations angle before rotate left "+afterRotationAngle);
		return this;
	}
	public ManageDocuments selectPageToMove(String pageNumber) throws InterruptedException {

		String[] pagenos= pageNumber.split(",");
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(),Duration.ofSeconds(20));
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(categoryLeftPane)));
		List<WebElement> categoryList = DriverManager.getDriver().findElements(categoryLeftPane);
		
		for(String pageno:pagenos) {
			boolean isSelected = false;
			String selectToMoveLoc = "//div[@class='docImg' and @id='"+pageno+"']/ancestor::div[@class='docImg']/following-sibling::div/child::div[2]/img";
			moveOrCopyFromCategory= By.xpath(selectToMoveLoc);
			for(WebElement ele:categoryList) {
				try {
					JSExecutor.click(ele);
					Thread.sleep(1200);
					wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(categoryLeftPane)));
					List<WebElement> categoryPagesList = DriverManager.getDriver().findElements(categorywisePages);
					for(WebElement page:categoryPagesList) {
						Thread.sleep(1500);
						String attValue = page.getAttribute("data-id");
						System.out.println("page number is "+attValue);
						if(attValue.equals(pageno)){
							clickUsinBy(moveOrCopyFromCategory, ExplicitWaitExpextecConditions.CLICKABLE);
							isSelected=true;
							By selectedCat= By.xpath("//div[@class='item selected']");
							ExplicitWaitConditions.performExplicitWait(selectedCat, ExplicitWaitExpextecConditions.PRESENSCE);
							break;
						}
						if(isSelected)
							break;

					}
				}catch(StaleElementReferenceException ex) {

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(isSelected)
					break;
			}
		}
		Thread.sleep(2000);
		return this;

	}
	public ManageDocuments clicktoMove() {
		clickUsinBy(movePage,ExplicitWaitExpextecConditions.PRESENSCE);
		return this;
	}
	public ManageDocuments selctCategory() {
		clickUsinBy(chkBoxForCatgory, ExplicitWaitExpextecConditions.NONE);
		return this;
	}
	public ManageDocuments selectCategoryToMoveORCopy() {

		clickUsingJS(moveToCategoryChkBox, ExplicitWaitExpextecConditions.NONE,true);
		return this;
	}
	public ManageDocuments selectPositionToMove(String positionToMove) {

		String moveToPosition = "//form//span[contains(text(),'"+positionToMove+"')]/parent::label/input";
		moveat = By.xpath(moveToPosition);
		clickUsingJS(moveat,ExplicitWaitExpextecConditions.NONE,true);
		return this;
	}
	public ManageDocuments clickOnMove() {
		clickUsinBy(movePage,ExplicitWaitExpextecConditions.NONE);
		return this;
	}
	public ManageDocuments clickOnCopy() {
		clickUsinBy(copyPage, ExplicitWaitExpextecConditions.NONE);
		return this;
	}
	public ManageDocuments clickOnClose() {
		clickUsinBy(closePage,ExplicitWaitExpextecConditions.NONE);
		return this;
	}
	public ManageDocuments cancelSelection() {
		clickUsinBy(cancel,ExplicitWaitExpextecConditions.NONE);
		return this;
	}
	public String getCountOfPages() throws InterruptedException {
		List<WebElement> lastPage= findElements(lastPageNo);
		int elemntCount=lastPage.size();
		while(elemntCount==0) {
			JSExecutor.scrollPage();
			lastPage= findElements(lastPageNo);
			elemntCount=lastPage.size();
		}

		String no = getAttribute(lastPageNo, ExplicitWaitExpextecConditions.VISIBILE,"data-id");
		return no;
	}
	public ManageDocuments splitDocument(String splitThreshold) {

		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(),Duration.ofSeconds(20));
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(categoryLeftPane)));
		List<WebElement> categoryList = DriverManager.getDriver().findElements(categoryLeftPane);
		boolean isSelected = false;
		for(WebElement ele:categoryList) {
			try {

				JSExecutor.click(ele);
				Thread.sleep(1500);
				wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(categoryLeftPane)));
				List<WebElement> categoryPagesList = DriverManager.getDriver().findElements(categorywisePages);
				int noOfPages = categoryPagesList.size();
				
				if(noOfPages>Integer.parseInt(splitThreshold)) {
					int count =0;
					for(WebElement individualpages:categoryPagesList) {
						WebElement page=individualpages;
						Thread.sleep(2000);
						count++;
						if(count>=Integer.parseInt(splitThreshold)){
						
							((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].scrollIntoView(true);", page);
							String text= page.getText();
							System.out.println(text);
							Thread.sleep(2000);
							ActionsUtil.scrollUP(page);
							Thread.sleep(2000);
							page.click();
							isSelected=true;
						}
						
						if(isSelected)
							break;

					}
				}
			}catch(StaleElementReferenceException ex) {

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(isSelected)
				break;
		}

		clickUsingJS(splitDoc,ExplicitWaitExpextecConditions.PRESENSCE,true);
		ExplicitWaitConditions.performExplicitWait(getNewDocCateName, ExplicitWaitExpextecConditions.PRESENSCE);
		return this;

	}
	public String getNewDocCategoryName() {
		String newDocCatNumber = getText(getNewCatNumber, ExplicitWaitExpextecConditions.VISIBILE);
		String newDocCateoryName = getText(getNewDocCateName, ExplicitWaitExpextecConditions.VISIBILE);
		return newDocCatNumber+"."+newDocCateoryName;
	}
	public ManageDocuments goBackToPreviousCategory() {
		clickUsinBy(goBackToPrevCatgr, ExplicitWaitExpextecConditions.VISIBILE);
		return this;
	}
	public ManageDocuments selectCategoryTOMovePage(String categoryNo) throws InterruptedException {

		String[] categories =categoryNo.split(",");
		for(String catno:categories) {
			List<WebElement> list = findElements(selectCategoryToMove);
			int totalCategories = list.size();
			if(totalCategories>=Integer.parseInt(catno)) {
				for(WebElement el:list) {
					try {
						String getCatNo = el.getText();
						if(getCatNo.startsWith(catno)) {
							JSExecutor.scrolltoView(selectCategoryToMove);
							el.click();
							Thread.sleep(1000);
							break;
						}
					}catch(NoSuchElementException ne) {

					}
				}
			}else {
				throw new FrameworkException("Category Number provided is more than the categories available");
			}
		}
		return this;
	}
	public ManageDocuments changeTheCategoryName(String categoryno ,String categoryName) throws AWTException, InterruptedException {

		String catNameLoc = "//div[contains(text(),'"+categoryno+".')]";
		selectCategoryName= By.xpath(catNameLoc);
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(),Duration.ofSeconds(20));
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(categoryNoLeftPane)));
		List<WebElement> categoryList = DriverManager.getDriver().findElements(categoryNoLeftPane);
		boolean isSelected =false;
		for(WebElement category:categoryList) {
			category.click();
			String changeNameOfCategoryNo = category.getText();
			if(changeNameOfCategoryNo.equals(categoryno)) {
				ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(selectCategoryName));
				ExplicitWaitConditions.performExplicitWait(selectCategoryName, ExplicitWaitExpextecConditions.CLICKABLE);
				JSExecutor.scrolltoView(selectCategoryName);
				Thread.sleep(1000);
				ActionsUtil.scrollUP(selectCategoryName);
				Thread.sleep(2000);
				ActionsUtil.moveToEleAndClick(selectCategoryName);
				Thread.sleep(2000);
				ActionsUtil.sendText(selectCategoryName, categoryName);
				ActionsUtil.clickEnter();
				isSelected=true;
			}
			if(isSelected)
				break;
		}

		return this;
	}
	public String getRotationAngle(By by) {
		String styleAttribute= getAttribute(by, ExplicitWaitExpextecConditions.PRESENSCE, "style");
		String[] styleAtts = styleAttribute.split(";");
		String rotationAngle= styleAtts[1];
		return rotationAngle;
	}
	
	public ManageDocuments addLabel(String label) {
		clickUsinBy(addLabel, ExplicitWaitExpextecConditions.CLICKABLE);
		enterText(enterLabel, label, ExplicitWaitExpextecConditions.CLICKABLE);
		clickUsinBy(saveLabel, ExplicitWaitExpextecConditions.CLICKABLE);
		
		return this;
	}
	public String getLabel() {
		String label = getText(getLabel, ExplicitWaitExpextecConditions.VISIBILE);
		
		return label;
	}
	public String getNotes() {
		String notes = getText(enterNotes, ExplicitWaitExpextecConditions.VISIBILE);
		
		return notes;
	}
	
	public ManageDocuments dragAndDrop(String moveTo) throws InterruptedException, AWTException {
		Thread.sleep(3000);
		By lastPageofSelCategory = By.xpath("//div[contains(@class,'selectedCategory')]//ul/li[last()]");
		By firstPageOfSelectedCategory = By.xpath("//div[contains(@class,'selectedCategory')]//ul/li[1]");
		Actions builder = new Actions(DriverManager.getDriver());
		WebElement from = DriverManager.getDriver().findElement(selectSpecificPage);
		
		String toLoc= "ul[class='cf-manage-docslist mt-2 fbox'] ul li:nth-child("+moveTo+") > div";
		dragAndDropTo = By.cssSelector(toLoc);
		WebElement to = DriverManager.getDriver().findElement(dragAndDropTo);
		//builder.moveToElement(from);
		//builder.clickAndHold().perform();
		System.out.println("0");
		if(moveTo.equals("Begining")) {
			System.out.println("1");
			to = DriverManager.getDriver().findElement(firstPageOfSelectedCategory);
			Point point = to.getLocation();
			int xcord = point.getX();
			System.out.println("Position of the webelement from left side is "+xcord +" pixels");
			int ycord = point.getY();
			builder.dragAndDropBy(from, xcord, ycord).perform();
			
		}else if(moveTo.equals("End")) {
			System.out.println("2");
			to = DriverManager.getDriver().findElement(lastPageofSelCategory);
			Point point = to.getLocation();
			int xcord = point.getX();
			System.out.println("Position of the webelement from left side is "+xcord +" pixels");
			int ycord = point.getY();
			builder.dragAndDropBy(from, xcord, ycord).perform();
		}else {
			System.out.println("3");
			dragAndDropTo = By.cssSelector(toLoc);
			
			
			System.out.println("Div size is "+DriverManager.getDriver().findElement(By.xpath("//div[@class='fai_wrapper']")).getLocation());
			ActionsUtil.moveToEleAndClick(selectSpecificPage);
//			Point sourcept = from.getLocation();
//			
//						
//		    int NumberX=sourcept.getX();
//		    int NumberY=sourcept.getY();
//		    
//		    Point destpt = to.getLocation();
//			
//			
//		    int DNumberX=destpt.getX();
//		    int DNumberY=destpt.getY();
//		    
//		    System.out.println("Source position is "+NumberX+""+NumberY);
//		    System.out.println("Dest position is "+DNumberX+""+DNumberY);
//		    
//		    builder.dragAndDropBy(from, DNumberX+1, DNumberY+1).perform();
//		    builder.moveToElement(from).perform();
//		    builder.clickAndHold(from).perform();
//		    
////			builder.clickAndHold(from);
////			Thread.sleep(2000);
////			builder.moveByOffset(NumberX+100, NumberY+50).release().perform();
////			Thread.sleep(5000);
			
			
			
			Rectangle start = from.getRect();
			Rectangle finish = to.getRect();
			System.out.println("from dimensions "+start.getX()+" y is"+start.getY());
			System.out.println("from dimensions "+finish.getX()+" y is"+finish.getY());
			builder.dragAndDropBy(from, finish.getX() - start.getX(), finish.getY() - start.getY()).perform();
			Thread.sleep(4000);
			
			builder.dragAndDrop(from, to)
            .perform();
			
			Robot robot = new Robot ();

		    robot.mouseMove(finish.getX(), finish.getY());
		    robot.mousePress(InputEvent.BUTTON1_MASK);
		    robot.mouseMove(finish.getX(), finish.getY());
		    robot.mouseRelease(InputEvent.BUTTON1_MASK);
		    Thread.sleep(3000);
		}
		
		
		return this;
	}
	
	public void closeViewer() {
		clickUsinBy(closeDocViewer, ExplicitWaitExpextecConditions.CLICKABLE);
		
	}
}
