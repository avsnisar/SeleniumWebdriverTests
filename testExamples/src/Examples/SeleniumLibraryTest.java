package Examples;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runners.model.TestTimedOutException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumLibraryTest {

	@Test
	public void test01_Basic_Setup() {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("https://www.amazon.com/");
		driver.close();
	}

	/**
	 * 
	 * @throws TestTimedOutException
	 */
	@Test
	public void test02_Google_Search() throws TestTimedOutException {
//		WebDriver driver = new FirefoxDriver();
//		driver.get("https://www.udemy.com/");
		try {
			WebDriver driver = new ChromeDriver();
			driver.get("https://google.com.ua/");
			WebElement query = driver.findElement(By.name("q"));
			query.sendKeys("testing it");
			query = driver.findElement(By.cssSelector("input[type='submit'][name='btnK']"));
			query.submit();
			driver.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception here" + e.getMessage().toString());
		}
//		WebElement query = driver.findElement(By.cssSelector("a[href='//google.com/search/howsearchworks/?fg=1']"));
//		query.click();
	}

	/**
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void test03_Seleca_and_Alert_checks() throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.goindigo.in/");
		driver.findElement(By.cssSelector("#multiCity")).click();
		Thread.sleep(1000L);
		WebElement selectfield = driver.findElement(By.id("sd"));
		Select select = new Select(selectfield);
		select.deselectByIndex(3);
		Alert alert = driver.switchTo().alert();
		System.out.println(alert.getText());
		alert.accept();
		driver.close();
//		WebElement query = driver.findElement(By.name("q"));

	}

	@Test
	public void test04_Hover_Block_displayed() {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.com/");
		Actions move = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//*[@data-nav-ref='nav_ya_signin']"));
		move.moveToElement(element).build().perform();
	}

	@Test
	public void test05_Type_into_searchable_field_with_SHIFT() {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.com/");
		Actions move = new Actions(driver);
		WebElement element = driver.findElement(By.id("twotabsearchtextbox"));
		element.click();
		move.keyDown(Keys.SHIFT).moveToElement(element).sendKeys("Tesla car").build().perform();

	}

	@Test
	public void test06_display_context_menu() {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.com/");
		Actions move = new Actions(driver);
		WebElement element = driver.findElement(By.id("twotabsearchtextbox"));
		element.click();
		move.contextClick(element).build().perform();

	}

	@Test
	public void test07_Framesets() {
		WebDriver driver = new ChromeDriver();
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		driver.switchTo().frame(0);
		WebElement element = driver.findElement(By.name("fldLoginUserId"));
		element.click();
		element.sendKeys("100000");
	}

	@Test
	public void test08_Counting_Limited_Scope_Printing_Anchors() {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.ebay.com/");
		System.out.println("Links in entire page");
		System.out.println(driver.findElements(By.tagName("a")).size());

		System.out.println("Links in footer only");
		WebElement footer = driver.findElement(By.id("hlGlobalFooter"));
		System.out.println(footer.findElements(By.tagName("a")).size());

		System.out.println("Links in second column");
		WebElement column2 = driver.findElement(By.xpath("//*[@id='gf-BIG']/table/tbody/tr/td[2]/ul"));
		System.out.println(column2.findElements(By.tagName("a")).size());

		for (int i = 0; i < column2.findElements(By.tagName("a")).size(); i++) {
			System.out.println(column2.findElements(By.tagName("a")).get(i).getText());
		}

	}

	@SuppressWarnings("deprecation")
	@Test
	public void test09_Profile_Settings() {
		FirefoxProfile profile = new FirefoxProfile();
		profile.setAcceptUntrustedCertificates(false);
		WebDriver driver = new FirefoxDriver(profile);
		driver.get("https://google.com/");
		Set<Cookie> abd = driver.manage().getCookies();
		System.out.println(abd.size());
	}

	@SuppressWarnings("deprecation")
	@Test
	public void test10_Firefox_Troubleshooting() {
		File pathToFirefoxBinary = new File("C://Program Files (x86)//Mozilla Firefox 42//firefox.exe");
		FirefoxBinary ffbinary = new FirefoxBinary(pathToFirefoxBinary);
		FirefoxProfile ffprofile = new FirefoxProfile();
		WebDriver driver = new FirefoxDriver(ffbinary, ffprofile);
		driver.get("https://google.com/");
		driver.close();
	}

	@Test (expected = (Error.class))
	public void test11_TakeScreenshotThenCloseDesktopProgram() throws IOException {

		WebDriver driver = new ChromeDriver();
		driver.get("https://google.com");
		File scrfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrfile, new File("C:\\Java\\scr.png"));
		WindowsUtils.killByName("Totalcmd.exe");
		driver.close();
	}

	@Test
	public void test12_Firefox_Troubleshooting() throws MalformedURLException {
		DesiredCapabilities capability = DesiredCapabilities.firefox();
		capability.setCapability(FirefoxDriver.BINARY,
				new File("C://Program Files (x86)//Mozilla Firefox 42//firefox.exe").getAbsolutePath());
//		File pathToFirefoxBinary = new File("C://Program Files (x86)//Mozilla Firefox 42//firefox.exe");
//		FirefoxBinary ffbinary = new FirefoxBinary(pathToFirefoxBinary);
//		FirefoxProfile ffprofile = new FirefoxProfile();
//		WebDriver driver = new FirefoxDriver(ffbinary, ffprofile);
		WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
		driver.get("https://google.com/");
		driver.close();
	}

	@Test
	public void test13_WindowHandlers() {

		WebDriver driver = new ChromeDriver();
		driver.get("https://www.eurointegration.com.ua/");
		String initialWindow = driver.getWindowHandle();
		System.out.println(initialWindow);
		driver.findElement(By.cssSelector(
				"body > header > div > div.header__content > div.layout__inner_head > div.top_all_sections > a:nth-child(3)"))
				.click();

		Set<String> existingWindows = driver.getWindowHandles();
		System.out.println(existingWindows);

		// or

		Object[] allWindows = driver.getWindowHandles().toArray();

		for (int i = 0; i < allWindows.length; i++) {
			System.out.println(allWindows[i]);
		}

		driver.switchTo().window(initialWindow);
		System.out.println(initialWindow);
		WebElement copyright = driver.findElement(By.xpath("//*[@id=\"footer\"]/div/div/div[2]/p[1]/b"));
		assertEquals("© 2014 - 2019, Європейська правда, eurointegration.com.ua", copyright.getText());
		System.out.println(copyright.getText());
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
		System.out.println(driver.getWindowHandle());
		String location = driver.getCurrentUrl();
		assertEquals("https://twitter.com/EuropeanPravda", location);
		System.out.println(location);
	}

}
