package qaassignment.test1;

import static org.junit.Assert.assertFalse;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class Test3 {
	
	protected static WebDriver driver = null;

	@BeforeAll
	public static void beforeSuite() {
		System.setProperty("webdriver.gecko.driver", ".\\driver\\geckodriver.exe");
		driver = new FirefoxDriver();	
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://orangehrm-demo-6x.orangehrmlive.com");
		driver.manage().window().maximize();
	}
	
	@Test
	public void test1() throws Exception {
		//Log in
		driver.findElement(By.cssSelector("#btnLogin")).click();
		//Click on the Recruitment
		driver.findElement(By.cssSelector("#menu_recruitment_viewRecruitmentModule > span.left-menu-title")).click();
		//At this point i got the exception: "Exception in thread "main" org.openqa.selenium.NoSuchElementException: no such element:". 
		//It seems to me that I have no control above this site. I can't click on any of the elements.
		//I couldn't manage this out why does it happens.
		//So I am just try to finish my test case without testing it.
		WebElement tab = driver.findElement(By.cssSelector("#vacancyTableContainer > div.nova-content-wrap > table > tbody > tr:nth-child(1) > td:nth-child(2)"));
		
		Actions action = new Actions(driver);
		action.moveToElement(tab);
		action.click();
		action.perform();
		//Click on Candidates
		driver.findElement(By.cssSelector("#slide-out > li:nth-child(7) > a")).click();
		
		//Scroll down to the bottom of the page
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		//Print out the last part of the content, what is the number of candidates.
		String str = driver.findElement(By.cssSelector("#fromToOf > div")).getText();
		System.out.println(str.split(" ")[4]);
		//Click on the green Add button
		driver.findElement(By.cssSelector("#addItemBtn > i")).click();
		//Fill the required fields
		driver.findElement(By.cssSelector("#addCandidate_firstName")).sendKeys("QA");
		driver.findElement(By.cssSelector("#addCandidate_lastName")).sendKeys("Assignment");
		driver.findElement(By.cssSelector("#addCandidate_email")).sendKeys("qaassignment@gmail.com");
		driver.findElement(By.cssSelector("#saveCandidateButton")).click();
		//Get the number of candidates
		String str1 = driver.findElement(By.cssSelector("#fromToOf > div")).getText();
		System.out.println(str1.split(" ")[4]);
		
		assertFalse("Number of candidates have changed!", str.equals(str1));
		//Check the checkbox at the new candidate
		driver.findElement(By.cssSelector("#content > div:nth-child(10) > div > div.nova-content-wrap > table > tbody > tr:nth-child(1) > td.material-checkbox")).click();
		//Delete the candidate
		driver.findElement(By.cssSelector("#ohrmList_Menu > i")).click();
		driver.findElement(By.cssSelector("#deleteItemBtn")).click();
		str = driver.findElement(By.cssSelector("#fromToOf > div")).getText();
		assertFalse("Number of candidates have changed!", str.equals(str1));
		
		//Log out
		driver.findElement(By.cssSelector("#content > div.navbar-fixed > nav > div > a.button-collapse.show-on-large > i")).click();
		driver.findElement(By.cssSelector("#welcome > span:nth-child(1)")).click();
		driver.findElement(By.cssSelector("#novaLogoutPanel > div:nth-child(3) > a")).click();
		
	}
	
	@AfterAll
	public static void afterSuite() {
		driver.close();
		driver.quit();
	}
	
}
