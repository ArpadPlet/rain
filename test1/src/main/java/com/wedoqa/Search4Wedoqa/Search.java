package com.wedoqa.Search4Wedoqa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Search extends PageObject {

	@FindBy(xpath="//*[@id=\"tsf\"]/div[2]/div[1]/div[1]/div/div[2]/input")
    private WebElement search;
	
	@FindBy(xpath="/html/body/div/div[4]/form/div[2]/div[1]/div[3]/center/input[1]")
    private WebElement searchButton;
	
		
	public Search(WebDriver driver) {
		super(driver);
	}
	
	public void type(String string) {
		//Type the String into google search
		this.search.sendKeys(string);;
	}
	
	public Result clickSearch() {
		//Click on search button
		this.searchButton.click();
		return new Result(driver);
	}
	
}