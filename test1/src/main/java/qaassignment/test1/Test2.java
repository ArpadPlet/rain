package qaassignment.test1;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;

public class Test2 {
	protected static WebDriver driver = null;

	@Before
	public void beforeSuite() {
		
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		driver = new ChromeDriver();	
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://www.google.com/");
		driver.manage().window().maximize();
	}

	@Test
	public void test1() throws Exception {
		// The maximum number of the results
		long maxIt = 1000000000;
		WebElement mail = driver
				.findElement(By.cssSelector("#tsf > div:nth-child(2) > div > div.RNNXgb > div > div.a4bIc > input"));
		Actions action = new Actions(driver);
		action.moveToElement(mail);
		action.click();
		action.perform();
		mail.sendKeys("cheese");

		driver.findElement(By.className("gNO89b")).click();

		// Gets the hole text from the object
		WebElement result = driver.findElement(By.id("resultStats"));
		String value = result.getText();

		// Removes the spaces and the texts
		// I am expecting a number which is bigger than 999 999
		String term = (value.split(" ")[1] + value.split(" ")[2] + value.split(" ")[3]);
		long i = 0;
		for (i = 0; i < maxIt; i++) {

			// Compares the "term" string (which is already contains only the number of
			// Cheese) to "i"
			String str1 = Long.toString(i);
			if (term.equals(str1)) {
				i = maxIt;
			}
			
			// If there are more Cheese on the Internet than 777
			assertTrue("There is too much cheese on the Internet", i < 777);
		}

	}

	@After
	public void afterSuite() {
		driver.close();
		driver.quit();
	}

}
