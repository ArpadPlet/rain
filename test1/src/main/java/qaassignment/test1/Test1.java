package qaassignment.test1;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import qaassignment.test1.Test1;

import com.wedoqa.Search4Wedoqa.Search;

public class Test1 {
	protected static WebDriver driver = null;
	Logger logger = LogManager.getLogger(Test1.class);

	@BeforeAll
	public static void beforeSuite() {
		int help = 0;
		Scanner choose = new Scanner(System.in);
		System.out.println("Which browser do you want to use: (Input your answer)");
		System.out.println("Chrome = 1");
		System.out.println("Internet Explorer = 2");
		System.out.println("Mozilla Firefox = 3");

		help = choose.nextInt();

		if (help == 1) {
			System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (help == 2) {
			System.setProperty("webdriver.ie.driver", ".\\driver\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		} else if (help == 3) {
			/*System.setProperty("webdriver.edge.driver", ".\\driver\\MicrosoftWebDriver.exe");
			driver = new EdgeDriver();*/
			System.setProperty("webdriver.gecko.driver", ".\\driver\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://www.google.com/");
		driver.manage().window().maximize();

	}

	@Test
	public void test1() throws Exception {

		logger.info("Start search!");
		Search search = new Search(driver);
		search.Type("wedoqa");
		search.ClickSearch();

		// Click on the first result
		driver.findElement(By.partialLinkText("wedoqa")).click();

		// These function are needed the number of testimonails. I got this from the
		// site.
		findElement(6);
		// By sending a String ("simplymap") we are choosing the name of the image file
		screenShot("simplymap");

		findElement(20);

		screenShot("eversave");

		findElement(10);

		screenShot("pattern");
		// counts the number of the letters "T" and "t" in the names of the team members
		WebElement team = driver.findElement(By.cssSelector("#team"));

		int sum = 0;
		boolean isExist = false;
		String str = " ";
		for (int pom = 0; pom < 53; pom++) {
			// Try to find does this element exists or not
			try {
				team.findElement(By.id("illdy_person-" + pom + ""));
				isExist = true;
			} catch (NoSuchElementException e) {
				System.out.println("No scuh element found!");
				isExist = false;
			}
			if (isExist) {
				str = team.findElement(By.id("illdy_person-" + pom + ""))
						.findElement(By.cssSelector("#illdy_person-" + pom + " > div > div.person-content > h6"))
						.getText();
				for (int i = 0; i < str.length(); i++) {
					if (str.toCharArray()[i] == 'T' || str.toCharArray()[i] == 't') {
						sum++;
					}
				}
			}
		}
		System.out.println("The sum of the letters -T- and -t- is: " + sum);
		// Go to Blog
		driver.findElement(By.id("menu-item-3127")).click();
		// Search for the "test"
		driver.findElement(By.cssSelector("#s")).sendKeys("test");

		String str1 = driver.findElement(By.className("post-meta-time")).getText();
		// I doesn't had time to finish the date comparation
	}

	@AfterAll
	public static void afterSuite() {
		driver.close();
		driver.quit();
	}

	public static void screenShot(String name) throws Exception {
		// This function takes a Screenshot and stores in the folder of the project in a
		// folder named Screenshot
		TakesScreenshot ts1 = (TakesScreenshot) driver;

		File source1 = ts1.getScreenshotAs(OutputType.FILE);

		FileUtils.copyFile(source1, new File("./Screenshot/" + name + ".png"));
	}

	public static void findElement(int number) throws Exception {
		// This function finds the correct Testimonial by the number of the testimonial.
		WebElement element = driver
				.findElement(By.cssSelector("#illdy_testimonial-" + Integer.toString(number) + " > div"));

		Actions action = new Actions(driver);
		action.moveToElement(element);
		action.perform();

		// Wait 1000 milliseconds
		Thread.sleep(1000);

		System.out.println("-----------------");
		System.out.println(element.findElement(By.className("testimonial-content")).getText());
	}
}
