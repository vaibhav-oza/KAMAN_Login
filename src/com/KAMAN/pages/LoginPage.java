package com.KAMAN.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	WebDriver driver;
	
	By email = By.xpath("//*[@id='atg_store_registerLoginEmailAddress1']");
	By password = By.xpath("//*[@id='atg_store_registerLoginPassword1']");
	By loginButton = By.xpath("//*[@id='atg_store_loginButton1']");

public LoginPage(WebDriver driver) {
	this.driver = driver;
}

public void KAMAN_login(String emailID,String Pass) throws Exception {
	driver.findElement(email).clear();
	driver.findElement(email).sendKeys(emailID);
	driver.findElement(password).clear();
	driver.findElement(password).sendKeys(Pass);
	Thread.sleep(2000);
	driver.findElement(loginButton).click();
	
}
}
