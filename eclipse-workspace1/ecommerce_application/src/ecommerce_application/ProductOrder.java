package ecommerce_application;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class ProductOrder {
	WebDriver driver;

	@Test(priority = 0)
	public void accountCreation() {
		driver.get("http://automationpractice.com/index.php");
		driver.findElement(By.xpath("(//a[@href='http://automationpractice.com/index.php?controller=my-account'])[1]"))
				.click();
		driver.findElement(By.id("email_create")).sendKeys("jbhutada44@gmail.com");
		driver.findElement(By.xpath("//*[@id='SubmitCreate']")).click();
		driver.findElement(By.id("id_gender1")).click();
		driver.findElement(By.id("customer_firstname")).clear();
		driver.findElement(By.id("customer_firstname")).sendKeys("Mayur");
		driver.findElement(By.id("customer_lastname")).clear();
		driver.findElement(By.id("customer_lastname")).sendKeys("Bhutada");
		driver.findElement(By.id("passwd")).sendKeys("Jaigajanan@44");
		Select s = new Select(driver.findElement(By.id("days")));
		s.selectByIndex(6);
		Select s1 = new Select(driver.findElement(By.id("months")));
		s1.selectByIndex(4);
		Select s2 = new Select(driver.findElement(By.id("years")));
		s2.selectByValue("1995");
		driver.findElement(By.id("firstname")).sendKeys("Mayur");
		driver.findElement(By.id("lastname")).sendKeys("Bhutada");
		driver.findElement(By.id("company")).sendKeys("Tech Mahindra Limited");
		driver.findElement(By.id("address1")).sendKeys("Tech Mahindra Limited, hinjewadi,");
		driver.findElement(By.id("address2")).sendKeys("phase-3, Pune-411057");
		driver.findElement(By.id("city")).sendKeys("Pune");
		Select s3 = new Select(driver.findElement(By.id("id_state")));
		s3.selectByVisibleText("Indiana");
		driver.findElement(By.id("postcode")).sendKeys("46777");
		driver.findElement(By.id("phone_mobile")).sendKeys("9405935072");
		driver.findElement(By.id("alias")).sendKeys("Govinda PG,pune");
		driver.findElement(By.xpath("//*[contains(text(),'Register')]")).click();

	}

	@Test(priority = 1)
	public void loginToAccount() {
		driver.get("http://automationpractice.com/index.php");
		driver.findElement(By.xpath("(//a[@href='http://automationpractice.com/index.php?controller=my-account'])[1]"))
				.click();
		driver.findElement(By.id("email")).sendKeys("jbhutada44@gmail.com");
		driver.findElement(By.id("passwd")).sendKeys("Jaigajanan@44");
		driver.findElement(By.xpath("//*[@id='SubmitLogin']")).click();

		driver.findElement(By.xpath("(//*[@class='sf-with-ul'])[1]")).click();
		driver.findElement(By.xpath("//*[@src='http://automationpractice.com/img/p/7/7-home_default.jpg']")).click();
		
		driver.findElement(By.xpath("//*[@id='quantity_wanted']")).clear();
		driver.findElement(By.xpath("//*[@id='quantity_wanted']")).sendKeys("2");
		// driver.findElement(By.xpath("//*[@id='quantity_wanted_p']/a[2]/span")).click();
		String price = driver.findElement(By.id("our_price_display")).getText();
		String pricewodoller = price.replaceAll("[$,]", "");
		int p = Integer.parseInt(pricewodoller);
		int p2 = p * 2;
		System.out.println("After adding 2 quantities price becomes" + p2);
		driver.findElement(By.xpath("//*[@id='add_to_cart']/button/span")).click();
		String total = driver.findElement(By.id("layer_cart_product_price")).getText();
		String totalwodoller = total.replaceAll("[$,]", "");
		int finaltotal = Integer.parseInt(totalwodoller);
		Assert.assertEquals(p2, finaltotal);
		System.out.println("product quantity total price and Cart value is matching without shipping charges");
		driver.findElement(By.xpath("//*[contains(text(),'Proceed to checkout')]")).click();
		Assert.assertEquals(p2, finaltotal);
		System.out.println("product quantity total price and Cart value is matching without shipping charges");

		driver.findElement(By.xpath("//*[@id='center_column']/p[2]/a[1]/span")).click();
		driver.findElement(By.xpath("//*[@id='center_column']/form/p/button/span")).click();
		driver.findElement(By.id("cgv")).click();
		driver.findElement(By.xpath("//*[@id='form']/p/button/span")).click();
		driver.findElement(By.xpath("//*[@id='HOOK_PAYMENT']/div[2]/div/p/a")).click();
		driver.findElement(By.xpath("//*[contains(text(),'I confirm my order')]")).click();
		driver.findElement(By.xpath("//*[contains(text(),'Back to orders')]")).click();

	}

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\mayur\\eclipse-workspace1\\ecommerce_application\\src\\resources\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void afterMethod() {
		driver.close();
	}

}
