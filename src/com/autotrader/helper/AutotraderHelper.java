package com.autotrader.helper;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;

import com.autotrader.pages.HomePage;

public class AutotraderHelper {
	private static WebDriver driver;
	private static Select select;

	static {
		driver = null;
	}

	public static void selectByIndex(WebElement element, int index) throws Exception {
		select = new Select(element);
		select.selectByIndex(index);
	}

	public static void selectByValue(WebElement element, String value) throws Exception {
		select = new Select(element);
		select.selectByValue(value);
		
	}

	public static void selectByText(WebElement element, String text) throws Exception {
		select = new Select(element);
		select.selectByVisibleText(text);
	}

	public static void launchBrowser(String browser) throws Exception {
		switch (browser.toLowerCase()) {
		case "chrome":
			driver = initialiseChrome();
			break;
		case "firefox":
			driver = initialiseFirefox();
			break;
		case "internet explorer":
			driver = initialiseInternetExplorer();
			break;

		default:
			System.out.println(browser + " is not recognised. So Firefox is launched instead");
			driver = initialiseFirefox();

		}

		driver.manage().window().maximize();
	}

	public static void closeBrowser() throws Exception {
		driver.manage().deleteAllCookies();
		driver.close();
		driver.quit();
	}

	public static void launchUrl(String Url) {
		driver.navigate().to(Url);
	}

	private static WebDriver initialiseChrome() throws Exception {
		System.setProperty("webdriver.chrome.driver", "./lib/chromedriver.exe");
		driver = new ChromeDriver();

		return driver;
	}

	private static WebDriver initialiseInternetExplorer() throws Exception {
		System.setProperty("webdriver.ie.driver", "./lib/IEDriverServer.exe");
		driver = new InternetExplorerDriver();

		return driver;
	}

	private static WebDriver initialiseFirefox() throws Exception {
		driver = new FirefoxDriver();

		return driver;
	}

	public static File takeScreenShot() throws Exception {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

	}

	public static void saveScreenshot() throws Exception {

		String dateNow = new SimpleDateFormat("yyMMdd").format(new GregorianCalendar().getTime());
		String timeNow = new SimpleDateFormat("hhmmss").format(new GregorianCalendar().getTime());

		String fileName = String.format(".\\Screenshots\\%s\\screenshot_%s.png", dateNow, timeNow);

		File screeShot = takeScreenShot();

		FileUtils.copyFile(screeShot, new File(fileName));

	}

	public static WebElement getElementById(String id) throws Exception {
		By locator = By.id(id);
		return getElement(locator);
	}

	public static WebElement getElementByClassName(String className) throws Exception {
		By locator = By.className(className);
		return getElement(locator);
	}

	public static WebElement getElementByName(String name) throws Exception {
		By locator = By.name(name);
		return getElement(locator);
	}

	public static WebElement getElementByCssSelector(String cssSelector) throws Exception {
		By locator = By.cssSelector(cssSelector);
		return getElement(locator);
	}

	public static WebElement getElementByXpath(String xpath) throws Exception {
		By locator = By.xpath(xpath);
		return getElement(locator);
	}

	private static WebElement getElement(By locator) throws Exception {

		WebElement element = null;
		int tryCount = 0;

		while (element == null) {
			try {

				element = driver.findElement(locator);

			} catch (Exception e) {
				if (tryCount == 3) {
					saveScreenshot();
					throw e;
				}

				Thread.sleep(2000);

				tryCount++;
			}
		}

		System.out.println(element.toString() + " is now retrieved");
		return element;

	}

	private static List<WebElement> getElements(By locator) throws Exception {
		List<WebElement> element = null;
		int tryCount = 0;

		while (element == null) {
			try {
				element = driver.findElements(locator);

			} catch (Exception e) {
				if (tryCount == 3) {
					saveScreenshot();
					throw e;
				}

				Thread.sleep(2000);

				tryCount++;
			}
		}

		return element;

	}

	public static List<WebElement> getElementsById(String id) throws Exception {
		By locator = By.id(id);
		return getElements(locator);
	}

	public static List<WebElement> getElementsByClassName(String className) throws Exception {
		By locator = By.className(className);
		return getElements(locator);
	}

	public static List<WebElement> getElementsByName(String name) throws Exception {
		By locator = By.name(name);
		return getElements(locator);
	}

	public static List<WebElement> getElementsByCssSelector(String cssSelector) throws Exception {
		By locator = By.cssSelector(cssSelector);
		return getElements(locator);
	}

	public static List<WebElement> getElementsByXpath(String xpath) throws Exception {
		By locator = By.xpath(xpath);
		return getElements(locator);
	}

	public HomePage GivenINavigateAutotraderHomepage() throws Exception {
		launchUrl("http://www.autotrader.co.uk/");
		return new HomePage();
	}
}
