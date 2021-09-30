package com.adp.exam.repository;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/*
 * Created by : Chintan Shah
 * Date : 09/29/2020
 */

public class Heroes {
	// Define WebDriver
	WebDriver driver;

	//Find DashBoard Link And Define WebElement
	@FindBy(partialLinkText = "Dashboard")
	WebElement dashboardLink;
	
	//Find new-hero TextBox And Define WebElement
	@FindBy(id = "new-hero")
	WebElement newHero; 
	
	//Find Add Hero Button And Define WebElement
	@FindBy(xpath = "//button[contains(@class, 'add-button')]")
	WebElement addHero;
	
	//Find List Of Elements And Define WebElement
	@FindBy(xpath = "//ul//li/a")
	List<WebElement> getResultElements;
	
	//Find List Of Element For Delete And Define WebElement
	@FindBy(xpath = "//ul//li/button")
	List<WebElement> getResultElementsForDelete;
		
	//Intialize Constructor
	public Heroes(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//Method : Enter New Hero Name into TextBox
	public void addNewHero(String name) {
		newHero.sendKeys(name);
	}
	
	//Method : Add New Hero into List Of Hero
	public void clickAddHero() {
		addHero.click();
	}
	
	//Method : Navigate To DashBoard Page
	public void clickDashBoard() {
		dashboardLink.click();
	}
	
	//Method : Get List Of Elements 
	public List<WebElement> returnListOfResult() {
		return getResultElements;
	}

	//Method : Delete Hero From List
	public void deleteContent(int i) {
		getResultElementsForDelete.get(i).click();
	}
}
