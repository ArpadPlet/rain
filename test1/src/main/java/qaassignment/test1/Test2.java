package qaassignment.test1;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.wedoqa.Search4Wedoqa.Search;

public class Test2 {
	protected static WebDriver driver = null;

	@BeforeAll
	public static void beforeSuite() {
		
		System.setProperty("webdriver.gecko.driver", ".\\driver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://www.google.com/");
		driver.manage().window().maximize();
	}

	@Test
	public void test1() throws Exception {
		Search search = new Search(driver);
		search.type("cheese");
		// If there are more Cheese on the Internet than 777
		assertTrue("There is too much cheese on the Internet", search.clickSearch().compareResult() < 777);
	}

	@AfterAll
	public static void afterSuite() {
		driver.close();
		driver.quit();
	}

}
