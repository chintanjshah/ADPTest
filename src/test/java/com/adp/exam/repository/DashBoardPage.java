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

public class DashBoardPage {

	// Define Webdriver 
	WebDriver driver;

	// Find Heroes link And Define WebElement
	@FindBy(partialLinkText = "Heroes")
	WebElement heroLink; 
	
	// Find SearchBox And Define WebElement
	@FindBy(id = "search-box")
	WebElement searchBox;
	
	// Find New Element into list And Define WebElement
	@FindBy(xpath = "//*[contains(text(), 'ADPHero')]")
	WebElement selectContent;
	
	// Find Top Heores Header and Define WebElement
	@FindBy(xpath = "//*[contains(text(), 'Top Heroes')]")
	WebElement selectTopHeroes;
	
	// Find List And Define WebElement
	@FindBy(xpath = "//div/a")
	List<WebElement> selectUpdatedDashboardList;
	

	 // Intialize Constructor
	public DashBoardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Method : Click On Hero Link On Dashboard page
	public void clickHeroLink() {
		heroLink.click();
	}
	
	// Method : Enter search item into search box at Dashboard page
	public void SearchByKeyWord(String enterString) {
		searchBox.sendKeys(enterString);
		
	}
	
	// Method : Click on Search item into list
	public void clickSelectContent() {
		selectContent.click();
	}

	// Method : Verify Updated Label into DashBoard Page
	public boolean verifyDahsboardLabel(String validateContent) {
		boolean value = false;
		for(WebElement element : selectUpdatedDashboardList) {
			if(element.getText().equals(validateContent))
				value = true;
		}
		
		return value;
	}
}