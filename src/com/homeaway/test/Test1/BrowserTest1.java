package com.homeaway.test.Test1;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.Selenium;

public class BrowserTest1{
	WebDriver driver = new FirefoxDriver();
//@BeforeMethod
//public void launchBrowzer(){
//	WebDriver driver = new FirefoxDriver();
//	driver.get("http://store.demoqa.com");
//}

	
@Test 
public void test1(){
	driver.get("http://store.demoqa.com");

	
	Select droplist = new Select(driver.findElement(By.id("Product Category")));   
	droplist.selectByVisibleText("iPhones");
	
	driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[title='Fill Quote']")));
	driver.findElement(By.id("Add to Cart")).click();
	
	Alert alert = driver.switchTo().alert();
	
	//if alert text needs to be tested
	//String textAlert = "You just added 'Apple iPhone 4S 16GB SIM-Free - Black' to your cart.";
	//textAlert.toLowerCase().replace("'", "");
	//Assert.assertVerify(textAlert, alert.getText().toLowerCase().replace("'", ""));
	
	alert.sendKeys("Go to Checkout"); 
	driver.switchTo().alert().accept();

	// Check for Price
	String totalPrice = driver.findElement(By.cssSelector("div[id='checkout_page_container'] span[class='pricedisplay']")).getText();
	Assert.assertEquals(totalPrice, "$270.00");
	driver.findElement(By.id("Continue")).click();
	
	Select dropdownCountry  = new Select(driver.findElement(By.id("Please select a country")));
	WebElement option = dropdownCountry.getFirstSelectedOption();
	String content = option.getText();
	// It is already pre-selected for country instead of "please select a country"
	Assert.assertEquals(content,"Please select a country");
	
	// Checking if pre-selcted country is "USA"
	Assert.assertEquals(content,"USA");
	
	// Choose a different country
	dropdownCountry.selectByVisibleText("India");
	Assert.assertEquals(dropdownCountry.getFirstSelectedOption().getText(),"India");
	
	
	// State drop down is not working. Checking if the drop down has anything selected	
	Select dropdownState  = new Select(driver.findElement(By.id("Please select a state")));
	WebElement optionState = dropdownCountry.getFirstSelectedOption();
	// State drop down does not list anything and so nothing gets selected
	Assert.assertTrue(optionState.isSelected());

	// Uncomment to test the  the details of buyer
	
	
//	WebElement elementEmail = driver.findElement(By.id("Enter your email address"));
//	elementEmail.sendKeys("test-account1-homeaway@gmail.com");
//    
//    WebElement elFirstName  = driver.findElement(By.id("First Name"));
//    elFirstName.sendKeys("text");
//    WebElement elLastName = driver.findElement(By.id("Last Name"));
//    elLastName.sendKeys("text");
//    WebElement elAddress = driver.findElement(By.id("Address"));
//    elAddress.sendKeys("text");
//    WebElement elCity = driver.findElement(By.id("City"));
//    elCity.sendKeys("text");
//    
//    
//    WebElement elState = driver.findElement(By.id("State"));
//    Assert.assertTrue(elState.isDisplayed());
//    WebElement elState2 = driver.findElement(By.id("undefined"));
//    elState2.sendKeys("text");
//    
//    WebElement elCountry = driver.findElement(By.id("Country"));
//    elCountry.sendKeys("text");
//    
//    
//    WebElement elZip = driver.findElement(By.id("Postal Code"));
//    elCountry.sendKeys("text");
//    
//    driver.findElement(By.id("shippingSameBilling")).click();
//	driver.findElement(By.id("Purchase")).click();
    
//	driver.close();
//	driver.quit();

	}

@Test
public void test2(){
	//register into the website. Assumption username test-account-email1 is already registered
	 driver.get("http://store.demoqa.com/tools-qa/");
	 WebElement elUserName = driver.findElement(By.id("Username"));
	 elUserName.sendKeys("test-account-email1");
	 WebElement elPassword = driver.findElement(By.id("Password"));
	 elPassword.sendKeys("dtmAXIPOTl2j");
	
	 driver.findElement(By.id("Login")).click();
	
	
	 driver.findElement(By.id("First Name")).sendKeys("TestUser1");;
	 driver.findElement(By.id("Last Name")).sendKeys("TestUserLastName"); 
	 driver.findElement(By.id("Update Profile")).click();
	 
	 Select droplist = new Select(driver.findElement(By.id("Howdy,test-account-email1")));   
	 droplist.selectByVisibleText("Logout");
	 
	 // Relogin to check 
	 driver.findElement(By.id("Username")).sendKeys("test-account-email1");
	 driver.findElement(By.id("Password")).sendKeys("dtmAXIPOTl2j");
	 driver.findElement(By.id("Login")).click();
	 

	 Assert.assertEquals(driver.findElement(By.id("First Name")).getText(), "TestUser1");
	 Assert.assertEquals(driver.findElement(By.id("Last Name")).getText(), "TestUserLastName");
	 
	 
}


@Test(dependsOnMethods={"test1"})
public void test3(){
	
	driver.get("http://store.demoqa.com");
	Select droplist = new Select(driver.findElement(By.id("Product Category")));   
	droplist.selectByVisibleText("iPhones");
	
	driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[title='Fill Quote']")));
	driver.findElement(By.id("Add to Cart")).click();
	
	Alert alert = driver.switchTo().alert();
	

	
	alert.sendKeys("Go to Checkout"); 
	driver.switchTo().alert().accept();
	
	
	WebElement htmltable=driver.findElement(By.xpath("//*[@id='checkout_cart']/table[1]/tbody"));
	List<WebElement> rows=htmltable.findElements(By.tagName("tr"));
	Assert.assertNotEquals(rows, 0);
	for(int rnum=0;rnum<rows.size();rnum++)
	{
		  WebElement elRemove = driver.findElement(By.id("Remove"));
		  elRemove.click();

	}

	htmltable=driver.findElement(By.xpath("//*[@id='checkout_cart']/table[1]/tbody"));
	rows=htmltable.findElements(By.tagName("tr"));
	Assert.assertEquals(rows, 0);	
	
}




@AfterMethod
public void closerDriver(){
	driver.close();
	driver.quit();
}



}
