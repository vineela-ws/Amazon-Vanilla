import amazon.config.EnvFactory;
import amazon.factories.DriverFactory;
import com.typesafe.config.Config;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class TestSandbox {
    private static Config config = EnvFactory.getInstance().getConfig();
    private static final String HOME_PAGE_URL = config.getString("HOME_PAGE_URL");
    private WebDriver driver = DriverFactory.getDriver();

    
    //@Test
    public void assertThatHomePageTitleIsCorrect() {
    	System.out.println("---" + HOME_PAGE_URL);
        driver.get(HOME_PAGE_URL);
       //assertEquals("Amazon.com. Spend less. Smile more.", driver.getTitle());
    }
}
