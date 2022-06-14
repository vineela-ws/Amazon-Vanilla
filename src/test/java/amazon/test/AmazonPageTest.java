package amazon.test;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.typesafe.config.Config;

import amazon.config.EnvFactory;
import amazon.factories.DriverFactory;
import amazon.pages.AmazonDetailedPage;
import amazon.pages.AmazonPage;
import amazon.pages.SignUpPage;
import amazon.utils.Log;
import amazon.utils.Sync;


public class AmazonPageTest {
    private static Config config = EnvFactory.getInstance().getConfig();
    private static final String HOME_PAGE_URL = config.getString("HOME_PAGE_URL");
    private static final String sortDropdownValue = config.getString("sortDropdownValue");

	SignUpPage signUpPage;
	AmazonDetailedPage amazonGpPage;
	AmazonPage amazonPage;

	public AmazonPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		Log.initialize();
		Log.initializeExtent();
		Log.createExtentTest("Initiate", "SetUp");
		DriverFactory.setLocalWebDriver();
		DriverFactory.getDriver().get(HOME_PAGE_URL);
		DriverFactory.getDriver().manage().window().maximize();
		signUpPage = new SignUpPage(DriverFactory.getDriver());
		amazonGpPage=signUpPage.performActionsOnSigupPage();
		amazonPage =amazonGpPage.clickSamsungCheckbox();
		Sync.waitForSeconds(5);
	}
	
	@Test(priority=1)
	public void verifyAmazonPageTitleTest() throws InterruptedException{
		Log.createExtentTest("AmazonTest001", "AmazonAssignment");
		Log.info("Started Test 001");
		String amazonPageTitle = amazonPage.verifyAmazonPageTitle();
		SoftAssert softAssertion= new SoftAssert();
		softAssertion.assertEquals(amazonPageTitle, "Amazon.in");
		Log.info("amazonPageTitle: " + amazonPageTitle);
		Log.info("Ended Test 001");

	}
	
	@Test(priority=2)
	public void performActionsOnAmazonPage() throws InterruptedException {
		Log.createExtentTest("AmazonTest002", "AmazonAssignment");
		Log.info("Started Test 002");
		amazonPage.performActionsOnAmazonPage("Price: High to Low");
		Log.info("Ended Test 002");
	}
		
		
	@AfterMethod
	public void deinitialize()
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, IOException {
		Log.info("De Initialization Started");
		Log.flushExtent();
		Log.info("De Initialization Ended");
		DriverFactory.getDriver().quit();


	}
}
