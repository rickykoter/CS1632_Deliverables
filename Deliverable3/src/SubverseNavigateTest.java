import static org.junit.Assert.*;
import java.util.List;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * As a user,
 * I would like to be able to navigate between subverses
 * So that I may find the subverse that best fit my current interests.
 * @author Richard Kotermanski
 *
 */

public class SubverseNavigateTest {
	private static WebDriver driver;
	
	// SET AND TEAR DOWN //
	
	// Reuse the same driver for each test
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		driver = new FirefoxDriver();
	}

	@AfterClass
	public static void tearDownAfterClass() {
		driver.quit();
	}

	//Before each test, reset to the landing page for voat.
	@Before
	public void setUp() throws Exception {
		driver.get("http://www.voat.co");
	}

	// TESTS //
	// Given that a user is on the landing page for Voat
	// And the user is not logged in
	// When the user clicks on the "Explore Voat" link
	// Then the user is directed to a page titled "Most popular subverses" 
	//	that contains 25 of the most popular subverses.
	@Test
	public void testExploreVoatLink() {
		driver.findElement(By.linkText("Explore Voat")).click();
		List<WebElement> subverseEntries = driver.findElements(By.className("entry"));
		
		assertEquals(25, subverseEntries.size());
		assertEquals("Most popular subverses", driver.getTitle());
	}
	
	// TESTS //
	// Given that a user is on the landing page for Voat
	// And the user is not logged in
	// When the user clicks on the "Explore Voat" link
	// Then the user is directed to a subverse page 
	//	which has includes "/v/<subverse name>" in its address.
	@Test
	public void testRandomSubverse() {
		driver.findElement(By.linkText("Random Subverse")).click();
		
		assertTrue(driver.getCurrentUrl().contains("www.voat.co/v/"));
	}

	// Given that a user is on the front page, page 0, of the /v/programming subverse
	// And the user is not logged in
	// When the user clicks on the "Voat" image link
	// Then the user is directed to to the landing page for Voat which contains the
	// 	title "Voat - have your say".
	@Test
	public void testHomeImgLink() {
		driver.get("http://www.voat.co/v/programming");
		driver.findElement(By.id("header-img-a")).click();
		
		assertEquals("Voat - have your say", driver.getTitle());
	}
	
	// Given that a user is on the landing page for Voat
	// And the user is not logged in
	// When the user clicks on the "technology" link in the header
	// Then the user is directed to the technology subverse at address "/v/technology/".
	@Test
	public void testTechnologySubverseLinkFromHeader() {
		driver.findElement(By.linkText("technology")).click();
		
		assertEquals("News stories related to technology", driver.getTitle());
		assertTrue(driver.getCurrentUrl().contains("www.voat.co/v/technology/"));
	}
	
	// Given that a user is on the landing page for Voat
	// And the user is not logged in
	// When the user clicks on the subverse link for the first post
	// Then the user should be redirected to that subverse.
	@Test
	public void testSubverseLinkFromPost() {
		WebElement firstPost = driver.findElement(By.className("submission"));
		WebElement subverseLink = firstPost.findElement(By.xpath("//div[3]/p[2]/a[2]"));
		String pageLink = subverseLink.getAttribute("href");
		subverseLink.click();
		
		assertEquals(pageLink, driver.getCurrentUrl());
	}
}
