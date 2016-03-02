import static org.junit.Assert.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.After;
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
 * I would like to be able to navigate posts within a subverse,
 * so that I may find posts that I wish to view and share.
 * @author Richard Kotermanski
 *
 */

public class PostNavigationTest {
	private static WebDriver driver;

	// SET UP AND TEAR DOWN //

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		driver = new FirefoxDriver();
	}

	@AfterClass
	public static void tearDownAfterClass() {
		driver.quit();
	}

	@Before
	public void setUp() throws Exception {
		driver.get("http://www.voat.co/v/programming");
	}

	@After
	public void tearDown() throws Exception {
	}

	// TESTS //
	
	// Given that a user is on the front page, page 0, of the /v/programming subverse
	// When the user selects the next link 
	// Then the user will be sent to the next page, page 1.
	@Test
	public void testNextPage() {
		driver.findElement(By.partialLinkText("next ")).click();
		String prevLink = driver.findElement(By.partialLinkText(" prev")).getAttribute("href");
		String current = driver.getCurrentUrl();
		
		// Due to possibility of https versus http, it is best to simply check
		// addresses after "http(s)://".
		assertTrue(prevLink.contains("www.voat.co/v/programming?page=0"));
		assertTrue(current.contains("www.voat.co/v/programming?page=1"));

	}
	
	// Given that a user is on the front page, page 0, of the /v/programming subverse
	// When the user selects the Top and all link
	// Then the user will be directed to a page containing a sorted list of posts descending by number of votes.
	@Test
	public void testTopAllSort() {
		driver.findElement(By.linkText("Top")).click();
		driver.findElement(By.xpath("(//a[contains(text(),'all')])[2]")).click();
		WebElement linkList = driver.findElement(By.className("linklisting"));
		int prevScore = Integer.MAX_VALUE;
		WebElement link = linkList.findElement(By.xpath("div"));
		for (int i = 2; i < 25; i++) {
			int thisScore = Integer.parseInt(link.findElement(By.cssSelector("div.score.unvoted")).getText());
			if (prevScore >= thisScore) {
				prevScore = thisScore;
			} else {
				fail("Failed on index " + i + " - prev:" + prevScore + " - this:" + thisScore);
			}
			link = linkList.findElement(By.xpath("div[" + i + "]"));
		}
	}

	// Given that a user is on the front page, page 0, of the /v/programming subverse
	// When the user selects the New link
	// Then the user will be directed to a page containing a sorted list of posts descending by date.
	@Test
	public void testNewSort() throws ParseException {
		DateFormat df = new SimpleDateFormat("M/d/yyyy h:m:s a");
		Date thisDate;
		Date prevDate = df.parse("1/1/3000 12:00:00 AM");

		driver.findElement(By.linkText("New")).click();
		WebElement linkList = driver.findElement(By.className("linklisting"));
		WebElement link = linkList.findElement(By.xpath("div"));
		for (int i = 2; i < 25; i++) {
			String ago = link.findElement(By.tagName("time")).getAttribute("datetime");
			thisDate = df.parse(ago);
			if (prevDate.after(thisDate)) {
				prevDate = thisDate;
			} else {
				fail("Failed on date: " + thisDate.toString());
			}
			link = linkList.findElement(By.xpath("div[" + i + "]"));
		}
	}

	// Given that a user is on the front page, page 0, of the /v/programming subverse
	// And the user has entered a search term into the search box
	// When the user submits the search term
	// Then the user will be directed to a page containing the results for the search term.
	@Test
	public void testSearchWithin() {
		WebElement searchBox = driver.findElement(By.id("q"));
		WebElement withinSubverse = driver.findElement(By.id("l"));
		withinSubverse.click();
		searchBox.sendKeys("testing");
		searchBox.submit();
		String searchResultsAlert = driver.findElement(By.cssSelector("div.alert.alert-info > p")).getText();
		
		assertEquals("Here are your search results for the term \"testing\":", searchResultsAlert);
	}
	
	// Given that a user requests the page at address http://www.voat.co/v/programming
	// When the page loads
	// Then the page will be contain the default "Hot" sorting of posts for the programming subverse
	@Test
	public void testDefaultToHot() {
		WebElement selected = driver.findElement(By.cssSelector("ul.tabmenu > li.selected > a"));
		String selectedLinkText = selected.getText();
		
		assertEquals("Hot", selectedLinkText);
	}
}
