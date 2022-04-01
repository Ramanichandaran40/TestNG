package com.flipkart;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Tset1 {
	static WebDriver driver;
	static long startTime;
	static String name1;
@BeforeClass(groups = "default")
public static void beforeClass() {
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	driver.get("https://www.flipkart.com/");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
}
@AfterClass(groups = "default")
public static void afterClass() {
	driver.quit();
}
@BeforeMethod(groups = "default")
public void beforScript() {
	startTime = System.currentTimeMillis();
}
@AfterMethod(groups = "default")
public void afterScript() {
	long endTime = System.currentTimeMillis();
	System.out.println("Time taken is" + (endTime - startTime));
}
@Test(priority = 1, groups = "sanity")
public void login() {
	WebElement btn = driver.findElement(By.xpath("//button[text() ='✕']"));
	btn.click();
}
@Test(priority = 2, groups = "sanity")
public void search() {
	WebElement search = driver.findElement(By.name("q"));
	search.sendKeys("Redmi Mobile",Keys.ENTER);
}
@Test(priority = 3, invocationCount = 5)
public void mobileSelect() {
	WebElement mobileName1 = driver.findElement(By.xpath("(//div[@class ='_4rR01T'])[1]"));
	name1 = mobileName1.getText();
	System.out.println(name1);
	mobileName1.click();
}
@Test(priority = 4)
public void window() {
	String pWin = driver.getWindowHandle();
	Set <String> cWin = driver.getWindowHandles();
	for(String x:cWin) {
		if(!pWin.equals(x)) {
			driver.switchTo().window(x);
		}
	}
}
@Test(priority = 5, enabled = false)
public void validation() {
	WebElement mobileName2 = driver.findElement(By.xpath("//span[@class ='B_NuCI']"));
	String name2 = mobileName2.getText();
			System.out.println(name2);
			Assert.assertEquals(name1, name2);
}


}
