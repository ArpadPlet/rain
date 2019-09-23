package com.wedoqa.Search4Wedoqa;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Search extends PageObject {

	@FindBy(xpath="//*[@id=\"tsf\"]/div[2]/div[1]/div[1]/div/div[2]/input")
    private WebElement search;
	
	@FindBy(xpath="//*[@id=\"tsf\"]/div[2]/div[1]/div[2]/div[2]/div[2]/center/input[1]")
    private WebElement searchButton;
	
	@FindBy(id="resultStats")
    private WebElement result;
		
	public Search(WebDriver driver) {
		super(driver);
	}
	
	public void Type(String string) {
		//Type the String into google search
		this.search.sendKeys(string);;
	}
	
	public void ClickSearch() {
		//Click on search button
		this.searchButton.click();
	}
	
	public void CompareResult() {
		//Max number of results
		long maxIt = 1000000000;
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
	
}