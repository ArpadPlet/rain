package com.wedoqa.Search4Wedoqa;

import org.openqa.selenium.WebDriver;

public class Initialization extends PageObject{

	public Initialization(WebDriver driver) {
		super(driver);
		driver.get("https://www.google.com/");
		driver.manage().window().maximize();
	}
	
}
