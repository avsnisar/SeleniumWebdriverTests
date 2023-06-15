package Examples;

//import java.awt.List;
import java.util.concurrent.TimeUnit;

//import org.openqa.selenium.By;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//comment the above line and uncomment below line to use Chrome

public class Test {

	public static void main(String[] args) {
		String driverPath = "D:\\Install\\BrowserDrivers\\";
		// IE Capabilities
		System.setProperty("webdriver.ie.driver", driverPath + "IEDriverServer.exe");
		DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
		cap.setCapability("browserName", "internet explorer");
		InternetExplorerDriver driver = new InternetExplorerDriver(cap);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//		WebDriverWait wait = new WebDriverWait(driver, 3);
//		wait.until(d => {
//			List<String> elements = driver.findElements(By.className("k1"));
//			if (elements.countItems()>0)
//				return elements[0];
//			return null;
//		});
		driver.navigate().to("https://github.com/");
		driver.close();
	}
}