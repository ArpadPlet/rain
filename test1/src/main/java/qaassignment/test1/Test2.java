package qaassignment.test1;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.wedoqa.Search4Wedoqa.Search;

public class Test2 {
	protected static WebDriver driver = null;

	@BeforeAll
	public static void beforeSuite() {
		
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		driver = new ChromeDriver();	
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://www.google.com/");
		driver.manage().window().maximize();
	}

	@Test
	public void test1() throws Exception {
		Search search = new Search(driver);
		search.Type("cheese");
		search.ClickSearch();
		search.CompareResult();
	}

	@AfterAll
	public static void afterSuite() {
		driver.close();
		driver.quit();
	}

}
