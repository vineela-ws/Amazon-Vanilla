package amazon.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import amazon.factories.DriverFactory;
import amazon.utils.Log;
import amazon.utils.Sync;

public class SignUpPage {
	
	//Page Factory - OR:
		@FindBy(xpath="//*[@id='nav-hamburger-menu']")
		WebElement hamburgerMenu;
		
		@FindBy(xpath="//*[contains(text(),'TV, Appliances, Electronics')]")
		WebElement elctronicsLink;
		
		@FindBy(xpath="//a[contains(text(),'Televisions')]")
		WebElement televisionsLink;
		
		@FindBy(xpath="//a[//*[@class='hmenu hmenu-visible']")
		WebElement scroll;
		
		
		//Initializing the Page Objects:
		public SignUpPage(WebDriver driver){
			PageFactory.initElements(driver, this);
		}
		
		//Actions:
		public String validateSignUpPagePageTitle(){
			return DriverFactory.getDriver().getTitle();
		}
		
		public AmazonDetailedPage performActionsOnSigupPage() throws InterruptedException {
			clickHamburgerMenu();
			Sync.waitForSeconds(2);
			clickElectronicsLink();
			Sync.waitForSeconds(2);
			clickTelevisionsLink();
			Sync.waitForSeconds(2);
			return new AmazonDetailedPage(DriverFactory.getDriver());			
		}
		
		private void clickHamburgerMenu() {
			Log.info("Click Hamburger Menu");
			hamburgerMenu.click();
		}
			  
		private void clickElectronicsLink(){ 
			Log.info("Click Electronics Link");
			elctronicsLink.click(); 
		}
		  
		private void clickTelevisionsLink() { 
			Log.info("Click Televisions Link");
			televisionsLink.click(); 
		}	 
		
}
