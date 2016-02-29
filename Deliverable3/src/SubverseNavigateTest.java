import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SubverseNavigateTest {
	private static String username = "cs1632";
	private static String password = "Foobar123!";
	private static WebDriverWait wait;
	private static WebDriver driver;
	
	// SET AND TEAR DOWN //
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 10);

	}

	@AfterClass
	public static void tearDownAfterClass() {
		driver.quit();
	}

	@Before
	public void setUp() throws Exception {
		driver.get("http://www.voat.co");
	}

	// TESTS //
	
	@Test
	public void testExploreVoatLink() {
		driver.findElement(By.linkText("Explore Voat")).click();
		List<WebElement> subverseEntries = driver.findElements(By.className("entry"));
		assertEquals(25, subverseEntries.size());
	}

	@Test
	public void testRandomSubverse() {
		driver.findElement(By.linkText("Random Subverse")).click();
		assertTrue(isElementPresent(By.cssSelector("span.hover.pagename")));
	}

	@Test
	public void testHomeImgLink() {
		driver.get("http://www.voat.co/v/programming");
		driver.findElement(By.id("header-img-a")).click();
		assertEquals("Voat - have your say", driver.getTitle());
	}

	@Test
	public void testTechnologySubverseFromHeader() {
		driver.findElement(By.linkText("technology")).click();
		assertEquals("News stories related to technology", driver.getTitle());
		assertTrue(driver.getCurrentUrl().contains("www.voat.co/v/technology/"));
	}

	// UTILITIES //
	
	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
}
