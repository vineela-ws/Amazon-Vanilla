package amazon.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Sync {
	
	// Method 1 - Wait for particular seconds
	public static void waitForSeconds(int strWaitTime) throws InterruptedException {
		Thread.sleep(strWaitTime*1000);
		Log.info("Waited for <<" + strWaitTime + ">> seconds");
	}
	
	public static void clickOn(WebDriver driver,WebElement element,int timeout) {
	    new WebDriverWait(driver, timeout)
		.until(ExpectedConditions.elementToBeClickable((element)));
	    element.click();
	}
}
