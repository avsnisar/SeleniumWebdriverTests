package Examples;

import static org.testng.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RemoteWebdriverTest {
	public static void main(String[] args) throws MalformedURLException {
		WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),
				DesiredCapabilities.internetExplorer());
		driver.get("http://www.google.com");
		// driver.navigate().to("http://www.google.com");

		System.out.println("Page title is: " + driver.getTitle());

		WebElement element = driver.findElement(By.name("q"));

		element.sendKeys("Cheese!");

		element.submit();

		// Wait for the content dynamically generated with JavaScript using old
		// FluentWait
		Boolean foo = (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.getTitle().toLowerCase().startsWith("cheese!");
			}
		});
		
		/*
		 * or use FluentWait from selenium-java-4.9.0.jar Wait<WebDriver> wait = new
		 * FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
		 * .pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
		 * 
		 * Boolean foo = wait.until(new Function<WebDriver, Boolean>() { public Boolean
		 * apply(WebDriver d) { return d.getTitle().toLowerCase().startsWith("cheese!");
		 * } });
		 */
		System.out.println("Page title is: " + driver.getTitle());

		try {
			assertTrue(foo, "SERP tile does not start with 'cheese!'");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}
}
