package weather_reporting;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UITemperatureGenerator extends APITemperatureGenerator {

	private WebDriver driver;

	public UITemperatureGenerator() {
		driver = getDriver();
	}
	
	public float getTemperatureFor(String city) throws Exception {
		
		//Initialize driver
		getDriver();
				
		//Triggering game.tv
		lauchWeatherReporting();
		
		//Due to Ads added thread.sleep, Created Explicit wait as well either can be used
		Thread.sleep(10000);
		//waitForElement(driver, 20, driver.findElement(By.xpath("//input[@id='LocationSearch_input']")));				
					
		driver.findElement(By.xpath("//input[@id='LocationSearch_input']")).click();
		driver.findElement(By.xpath("//input[@id='LocationSearch_input']")).sendKeys(city);
		driver.findElement(By.xpath("//div[@id='LocationSearch_listbox']/button[1]")).click();
				
		WebElement tempElement = driver.findElement(By.xpath("//h1[contains(text(),'"+city+"')]//following::div[3]/span[1]"));
		waitForElement(driver, 5, tempElement);
		String temp = tempElement.getText();
		temp = temp.replace("°", "");
		float temperatureValue= Float.parseFloat(temp);
		
		//Code for Test Case : Use the Search City or Postcode section to search the City or Postcode & select the searched city
		/*
	     * driver.findElement(By.xpath("//input[@id='LocationSearch_input']")).click();
		 * driver.findElement(By.xpath("//div[@id='LocationSearch_listbox']/button[1]"))
		 * .click();
		*/
								
				driver.close();
				return temperatureValue;
	}
	
	private WebDriver getDriver() {
		
		System.setProperty("webdriver.chrome.driver", "src\\driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		return driver;
	}
	
	private void lauchWeatherReporting() {
		driver.get("https://weather.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	private void waitForElement(WebDriver driver,int time, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(element));
	}
	
	

}
