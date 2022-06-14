package amazon.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import amazon.factories.DriverFactory;
import amazon.utils.Log;
import amazon.utils.Sync;


public class AmazonDetailedPage {
	
	//Page Factory - OR:
	@FindBy(xpath="//span[text()='Samsung']")
	WebElement samsungCheckbox;
	
	
	//Initializing the Page Objects:
	public AmazonDetailedPage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	//Actions:
	public String validateAmazonGpPageTitle(){
		return DriverFactory.getDriver().getTitle();
	}
	
	public AmazonPage clickSamsungCheckbox() throws InterruptedException {
		Log.info("Click Samsung Check box");
		Sync.waitForSeconds(2);
		samsungCheckbox.click();
		return new AmazonPage(DriverFactory.getDriver());	
	}	
	
}
