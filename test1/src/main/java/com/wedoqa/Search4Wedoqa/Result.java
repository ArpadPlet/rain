package com.wedoqa.Search4Wedoqa;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Result extends PageObject {


	@FindBy(id="resultStats")
    private WebElement result;
	
	
	public Result(WebDriver driver) {
		super(driver);
	}
	
	public Integer compareResult() {
		String value = result.getText();
		
		// Removes the spaces and the texts
		// I am expecting a number which is bigger than 999 999 999
		String term = (value.split(" ")[1] + value.split(" ")[2] + value.split(" ")[3]);
			
		return Integer.parseInt(term);
	}
}
