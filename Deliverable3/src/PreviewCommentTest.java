import static org.junit.Assert.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * As a user
 * I would like to preview comments and replies on posts
 * so that I may see what how my comment text will be formatted after submission.
 * @author RichardKotermanski
 *
 */

public class PreviewCommentTest {
	private static String username = "cs1632";
	private static String password = "Foobar123!";
	private static WebDriverWait wait;
	private static WebDriver driver;
	private static String postID = "886833";// Anchor Post
	private static String currentTime;

	// SET UP AND TEAR DOWN //

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 10);
		driver.get("http://www.voat.co");

		driver.findElement(By.linkText("login")).click();
		WebElement usernameBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("UserName")));
		usernameBox.sendKeys(username);
		WebElement passwordBox = driver.findElement(By.id("Password"));
		passwordBox.sendKeys(password);
		passwordBox.submit();

		driver.get("https://www.voat.co/v/TestCS1632/comments/" + postID);

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss a");
		currentTime = sdf.format(cal.getTime());
	}

	@AfterClass
	public static void tearDownAfterClass() {
		driver.quit();
	}

	@Before
	public void setUp() throws Exception {
		driver.get("https://www.voat.co/v/TestCS1632/comments/" + postID);
	}

	// TEST //

	@Test
	public void testCommentOnPostPreviewEmpty() {
		WebElement commentBox = driver.findElement(By.xpath("(//textarea[@id='Content'])[2]"));
		commentBox.clear();
		driver.findElement(By.cssSelector("form > #previewButton")).click();
		try {
			WebElement previewArea = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//*[contains(.,'Please enter some text in order to get a preview.')]")));
			assertNotNull(previewArea.getText());
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testCommentOnCommentPreviewEmpty() {
		String commentID = "4422002";// Anchor Comment
		WebElement anchorComment = driver.findElement(By.id(commentID));
		anchorComment.findElement(By.linkText("reply")).click();
		WebElement commentBox = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//textarea[@id='Content'])[4]")));
		commentBox.clear();
		anchorComment.findElement(By.xpath("//form[@id='commentreplyform-" + commentID + "']/input[5]")).click();
		try {
			WebElement previewArea = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//*[contains(.,'Please enter some text in order to get a preview.')]")));
			assertNotNull(previewArea.getText());
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testCommentOnPostPreview() {
		WebElement commentBox = driver.findElement(By.xpath("(//textarea[@id='Content'])[2]"));
		Random r = new Random();
		int id = r.nextInt(Integer.MAX_VALUE);
		commentBox.sendKeys("This is a comment preview for id " + id + " at " + currentTime);
		driver.findElement(By.cssSelector("form > #previewButton")).click();

		try {
			WebElement previewArea = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//*[contains(.,'This is a comment preview for id " + id + " at " + currentTime + "')]")));
			assertNotNull(previewArea.getText());
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testCommentOnCommentPreview() {
		String commentID = "4422002";// Anchor Comment
		WebElement anchorComment = driver.findElement(By.id(commentID));
		anchorComment.findElement(By.linkText("reply")).click();
		WebElement commentBox = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//textarea[@id='Content'])[4]")));
		Random r = new Random();
		int id = r.nextInt(Integer.MAX_VALUE);
		commentBox.sendKeys("This is a reply preview for id " + id + " at " + currentTime);
		anchorComment.findElement(By.xpath("//form[@id='commentreplyform-" + commentID + "']/input[5]")).click();
		try {
			WebElement previewArea = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//*[contains(.,'This is a reply preview for id " + id + " at " + currentTime + "')]")));
			assertNotNull(previewArea.getText());
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testCommentOnCommentCancel() {
		String commentID = "4422002";// Anchor Comment
		WebElement anchorComment = driver.findElement(By.id(commentID));
		anchorComment.findElement(By.linkText("reply")).click();
		WebElement commentBox = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//textarea[@id='Content'])[4]")));
		Random r = new Random();
		int id = r.nextInt(Integer.MAX_VALUE);
		commentBox.sendKeys("This is a reply preview for id " + id + " at " + currentTime);
		anchorComment.findElement(By.xpath("//form[@id='commentreplyform-" + commentID + "']/input[5]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[contains(.,'This is a reply preview for id " + id + " at " + currentTime + "')]")));

		anchorComment.findElement(By.cssSelector("#commentreplyform-" + commentID + " > button.btn-whoaverse-paging"))
				.click();

		try {
			assertFalse(isElementPresent(By.xpath("//form[@id='commentreplyform-" + commentID + "']/div")));
			assertFalse(isElementPresent(
					By.xpath("//*[contains(.,'This is a reply preview for id " + id + " at " + currentTime + "')]")));
		} catch (NoSuchElementException e) {

		}

	}

	// UTILITIES //

	private boolean isElementPresent(By by) {
		int attempts = 0;
		while (attempts < 5) {
			try {
				driver.findElement(by);
				return true;
			} catch (NoSuchElementException e) {
				driver.navigate().refresh();
				attempts++;
			}
		}
		return false;

	}
}
