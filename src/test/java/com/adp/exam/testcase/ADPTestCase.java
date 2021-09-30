package com.adp.exam.testcase;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.adp.exam.repository.DashBoardPage;
import com.adp.exam.repository.DynamicEditPage;
import com.adp.exam.repository.Heroes;

/*
 * Created by : Chintan Shah
 * Date : 09/29/2020
 */

public class ADPTestCase {
	// Chrome Driver Path
	String driverPath = "C:/Drivers/94/chromedriver.exe";

	// Define Variables
	WebDriver driver;
	DashBoardPage objDashBoardPage;
	Heroes objHeros;
	DynamicEditPage objDynamicPage;

	// Load Chrome WebDriver Method
	@BeforeTest
	public void setup() {

		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://localhost:4200");
	}

	// Executes all test cases
	@Test(priority = 0)
	public void test_go_to_HerosPage() {
		
		SoftAssert softAssertion = new SoftAssert();
		
		// Define Javascript object for page scroll up and down
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Define global fluentwait variable
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(500, TimeUnit.MILLISECONDS);

		/* TEST CASE 1 : Go to the Heroes page and add a new hero. */
		// Intialize Page Objcects
		objDashBoardPage = new DashBoardPage(driver);
		objDashBoardPage.clickHeroLink();
		objHeros = new Heroes(driver);
		objHeros.addNewHero("ADPHero");
		objHeros.clickAddHero();
		driver.manage().window().maximize();

		/* TEST CASE 2 :Verify the new hero has been added to the page. */
		driver.findElement(By.tagName("html")).sendKeys(Keys.END);
		List<WebElement> checkResult = objHeros.returnListOfResult();
		WebElement findDynamicElement = driver.findElement(By.xpath("//*[contains(text(), 'ADPHero')]"));
		wait.until(ExpectedConditions.not(ExpectedConditions.stalenessOf(findDynamicElement)));
		softAssertion.assertTrue(checkResult.get(checkResult.size() - 1).getText().contains("ADPHero"));

		/*TEST CASE 3:Go back to the dashboard and search for this new hero and click on their entry.*/
		objHeros.clickDashBoard();
		objDashBoardPage.SearchByKeyWord("ADPHero");
		objDashBoardPage.clickSelectContent();

		/*TEST CASE 4:Change the new hero's name and save it.*/
		objDynamicPage = new DynamicEditPage(driver);
		objDynamicPage.EditText("ADPHero1");
		objDynamicPage.clickUpdate();

		/*TEST CASE 5:Verify the new hero's name has been changed in the hero list.*/
		WebElement findLabel = driver.findElement(By.xpath("//*[contains(text(), 'Top Heroes')]"));

		FluentWait<WebDriver> lbl_Wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(500, TimeUnit.MILLISECONDS);

		lbl_Wait.until(ExpectedConditions.not(ExpectedConditions.stalenessOf(findLabel)));
		objDashBoardPage.clickHeroLink();

		WebElement findUpdatedElement = driver.findElement(By.xpath("//*[contains(text(), 'ADPHero1')]"));
		js.executeScript("arguments[0].scrollIntoView();", findUpdatedElement);
		softAssertion.assertTrue(findUpdatedElement.getText().contains("ADPHero1"));

		/*TEST CASE 6: Delete another hero from the hero list.*/
		objHeros.deleteContent(checkResult.size() - 2);

		/*TEST CASE 7: Verify the new hero's name is now part of the Top Heroes banner on the dashboard.*/
		WebElement findHeader = driver.findElement(By.xpath("//*[contains(text(), 'Tour of Heroes')]"));
		js.executeScript("arguments[0].scrollIntoView();", findHeader);
		objHeros.clickDashBoard();
		boolean checkLabel = objDashBoardPage.verifyDahsboardLabel("ADPHero1");
		softAssertion.assertTrue(checkLabel);
		softAssertion.assertAll();
	}
	
	// Load Chrome WebDriver Method
		@AfterTest
		public void QuitDriver() {
			driver.quit();
		}
}