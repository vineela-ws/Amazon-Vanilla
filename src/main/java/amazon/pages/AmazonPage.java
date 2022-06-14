package amazon.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Iterator;
import java.util.Set;

import amazon.factories.DriverFactory;
import amazon.utils.Log;
import amazon.utils.Sync;

public class AmazonPage {

	@FindBy(xpath = "//select[@id='s-result-sort-select']")
	WebElement sortDropdown;

	@FindBy(xpath = "//div[@cel_widget_id='MAIN-SEARCH_RESULTS-8']")
	WebElement secondHighestProduct;

	@FindBy(xpath = "//*[contains(text(),' About this item ')]")
	WebElement aboutThisItem;

	@FindBy(xpath = "//*[@class='a-unordered-list a-vertical a-spacing-mini']")
	WebElement aboutThisItemDescription;

	// Initializing the Page Objects:
	public AmazonPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Actions:
	public String verifyAmazonPageTitle() {
		return DriverFactory.getDriver().getTitle();
	}

	public void performActionsOnAmazonPage(String sortDropdownValue) throws InterruptedException {
		selectValueFromFeatureDropdown(sortDropdownValue);
		clickSecondHighestProduct();
		verifyAboutThisItem();
		printAboutThisItemDescription();
	}

	private void selectValueFromFeatureDropdown(String sortDropdownValue) throws InterruptedException {
		Log.info("Select Value From Feature Dropdown");
		Sync.waitForSeconds(5);
		WebElement ele = sortDropdown;
		Select select = new Select(ele);
		select.selectByVisibleText(sortDropdownValue);
	}

	private void clickSecondHighestProduct() throws InterruptedException {
		Log.info("Click Second Highest Product");
		Sync.waitForSeconds(5);
		Sync.clickOn(DriverFactory.getDriver(), secondHighestProduct,20);
		
		// Window Handling and switching to child window
		String parent = DriverFactory.getDriver().getWindowHandle();
		Set<String> s = DriverFactory.getDriver().getWindowHandles();

		// Now iterate using Iterator
		Iterator<String> I1 = s.iterator();

		while (I1.hasNext()) {
			String child_window = I1.next();
			if (!parent.equals(child_window)) {
				DriverFactory.getDriver().switchTo().window(child_window);
			}
		}
	}

	private void verifyAboutThisItem() {
		Log.info("Verify About This Item");
		WebElement ele = aboutThisItem;
		String aboutThisItem = ele.getText();
		Assert.assertEquals(aboutThisItem, "About this item");
	}

	private void printAboutThisItemDescription() {
		Log.info("Print About This Item Description");
		WebElement ele = aboutThisItemDescription;
		String aboutThisItemDescription = ele.getText();
		System.out.println("aboutThisItemDescription" + aboutThisItemDescription);
	}
}
