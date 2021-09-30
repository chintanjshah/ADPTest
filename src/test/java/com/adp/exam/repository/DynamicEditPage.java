package com.adp.exam.repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/*
 * Created by : Chintan Shah
 * Date : 09/29/2020
 */

public class DynamicEditPage {

	//Define Variable
	WebDriver driver;

	// Find TextBox And Define WebElement
	@FindBy(id = "hero-name")
	WebElement editBox;
	
	// Find Save Button And Define WebElement
	@FindBy(xpath = "//*[contains(text(), 'save')]")
	WebElement saveButton;

	//Intialize Constructor
	public DynamicEditPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// Method : Enter Updated Text
	public void EditText(String enterNewString) {		
		editBox.clear();
		editBox.sendKeys(enterNewString);		
	}
	
	// Save Updated Text
	public void clickUpdate() {
		saveButton.click();
	}
}