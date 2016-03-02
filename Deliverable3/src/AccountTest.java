import static org.junit.Assert.*;
import java.util.Random;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * As a user,
 * I would like to have and manage an account,
 * so that I may customize and save my Voat experience and configurations.
 * 
 * @author Richard Kotermanski
 *
 */

public class AccountTest {
	private StringBuffer verificationErrors = new StringBuffer();
	private WebDriver driver;
	private String username;
	private String password;
	private WebDriverWait wait;

	// SET UP AND TEAR DOWN //

	// Before each test, set the page to the home page
	// and with a new driver to ensure logins/sessions to not carry
	// over between tests.
	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.get("https://www.voat.co");
		username = "cs1632";
		password = "Foobar123!";
		wait = new WebDriverWait(driver, 10);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	// TESTS //
	
	// Given that a user is logged in
	// And on the front page
	// When the user selects the logout link
	// Then the user will be logged out of the site.
	@Test
	public void testLogout() {
		login();
		WebElement logoff = driver.findElement(By.linkText("Log off"));
		logoff.click();
		String userBar = driver.findElement(By.cssSelector("span.user")).getText();
		assertEquals("want to join? login or register in seconds", userBar);
	}

	// Given the user is logging in
	// And the user provides a correct user name and password
	// When the user selects to submit his/her login credentials
	// Then the user is successfully logged in
	@Test
	public void testSuccessfulLogin() {
		assertTrue(login());
	}
	
	// Given the user is logged in
	// When the user selects the night mode toggle link
	// Then the page will assume the opposite theme as what they had.
	@Test
	public void testToggleDarkMode() throws InterruptedException {
		login();
		WebElement body = driver.findElement(By.tagName("body"));
		String className = body.getAttribute("class");
		String colorOriginal = body.getCssValue("background-color");
		String expect;
		if (className.equals("dark")) {
			expect = "light";
		} else {
			expect = "dark";
		}
		WebElement toggle = driver.findElement(By.id("nightmodetoggle"));
		toggle.click();
		try {
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className(expect)));
		} catch (TimeoutException e) {
			fail(e.getMessage());
		}
		WebElement body2 = driver.findElement(By.tagName("body"));
		String colorToggled = body2.getCssValue("background-color");

		assertNotEquals(colorOriginal, colorToggled);
	}
	
	// Given that a user is logging in
	// And the user does not provide a correct username and password
	// When the user submits his/her login credentials
	// Then a message will display noting the incorrect credentials.
	@Test
	public void testIncorrectLogin() {
		Random r = new Random();

		driver.findElement(By.linkText("login")).click();
		WebElement usernameBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("UserName")));
		usernameBox.sendKeys("invalid" + r.nextInt(10000));
		WebElement passwordBox = driver.findElement(By.id("Password"));
		passwordBox.sendKeys("notavalidpassword");
		passwordBox.submit();

		String error = driver.findElement(By.cssSelector("div.validation-summary-errors")).getText();
		assertEquals("Invalid username or password.", error);
	}

	// Given that a user is logged in
	// And on the account manage page
	// When when the user enters and saves a bio for his/her account
	// Then the bio will be set for his/her account.  
	@Test
	public void testSetBio() {
		login();
		driver.get("https://www.voat.co/account/manage");
		Random r = new Random();
		int expectedInt = r.nextInt(10000);
		WebElement bioBox = driver.findElement(By.id("Bio"));
		bioBox.clear();
		bioBox.sendKeys("Test Bio: " + expectedInt);
		driver.findElement(By.cssSelector("input.btn-whoaverse")).click();

		String bioTextResult = driver.findElement(By.cssSelector("span.user-quote")).getText();
		assertEquals("Test Bio: " + expectedInt, bioTextResult);
	}

	// UTILITIES //
	// Attempts to login with user name and password.
	// Return true is successful and false if not
	private boolean login() {
		driver.findElement(By.linkText("login")).click();
		WebElement usernameBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("UserName")));
		usernameBox.sendKeys(username);
		WebElement passwordBox = driver.findElement(By.id("Password"));
		passwordBox.sendKeys(password);
		passwordBox.submit();
		return isElementPresent(By.linkText("cs1632"));
	}
	
	// Returns true if an element exists on the page
	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
}
