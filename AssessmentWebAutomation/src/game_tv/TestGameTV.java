package game_tv;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class TestGameTV {
	
	private WebDriver driver;

	public TestGameTV() {
		driver = getDriver();
	}
	
	public void run() {
		
		//Triggering game.tv
		lauchGameTV();
		
		//Execute validation points
		validate();

	}
	
	private WebDriver getDriver() {
		
		System.setProperty("webdriver.chrome.driver", "src\\driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		return driver;
	}
	
	private void lauchGameTV() {
		
		driver.get("https://www.game.tv/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	private void validate() {
		
		//Test Case 1
		validateApplicationLaunch();
		
		//Test Case 2
		validateTwitterOnLoginScreen();
		
		//Test Case 3
		validateTwitterLoginPage();
		
		//Test Case 4
		validateGameTVHomePage();		
	}
	
	private void validateApplicationLaunch() {
		if(driver.findElement(By.xpath("(//a[text()='Launch App'])[2]")).isDisplayed()) {
			System.out.println("Verify that the user is able to launch the application - Passed");
		}else {
			System.out.println("Verify that the user is able to launch the application - Failed");
		}
	}
	
	private void validateTwitterOnLoginScreen() {
		driver.findElement(By.xpath("(//a[text()='Launch App'])[2]")).click();
		
		String parent=driver.getWindowHandle();

		Set<String>s=driver.getWindowHandles();

		// Now iterate using Iterator
		Iterator<String> I1= s.iterator();
		while(I1.hasNext())
		{
			String child_window=I1.next();
			if(!parent.equals(child_window))
			{
			driver.switchTo().window(child_window);
			}
		}
		
		if(driver.findElement(By.xpath("(//*[local-name()='svg']/*[local-name()='path'])[6]")).isDisplayed()) {
			System.out.println("Verify that the user is able see Login Screen & Twitter is visible - Passed");
		}else {
			System.out.println("Verify that the user is able see Login Screen & Twitter is visible  - Failed");
		}	
	}
	
	private void validateTwitterLoginPage() {
		driver.findElement(By.xpath("(//*[local-name()='svg']/*[local-name()='path'])[6]")).click();

		if(driver.findElement(By.xpath("//a[text()='Twitter']")).isDisplayed()) {
		System.out.println("Verify that the user is able navigate to Twitter For login - Passed");
		}else {
		System.out.println("Verify that the user is able navigate to Twitter For login  - Failed");
		}	
	}
	
	private void validateGameTVHomePage() {
		driver.findElement(By.id("username_or_email")).sendKeys("tes1.auto1@gmail.com");
		driver.findElement(By.id("password")).sendKeys("game@twitter");
		
		driver.findElement(By.xpath("//input[@id='allow']")).click();
	}
}
