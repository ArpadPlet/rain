package com.wedoqa.Search4Wedoqa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Search4Wedoqa {

	WebDriver driver;
	By search=By.cssSelector("#tsf > div:nth-child(2) > div > div.RNNXgb > div > div.a4bIc > input");
	By searchButton=By.cssSelector("#tsf > div:nth-child(2) > div > div.FPdoLc.VlcLAe > center > input.gNO89b");
	
	public Search4Wedoqa(WebDriver driver) {
		this.driver=driver;
	}
	
	public void TypeWedpqa() {
		driver.findElement(search).sendKeys("wedoqa");
	}
	
	public void ClickSearch() {
		driver.findElement(searchButton).click();
	}
	
}
