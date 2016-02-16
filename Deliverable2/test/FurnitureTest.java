
/**
 * Richard Kotermanski 
 * CS1632 Deliverable 2 
 * FurnitureTest - A set of tests that test the Furniture class.
 */
import static org.junit.Assert.*;

import org.junit.Test;

public class FurnitureTest {

	// --------------------------------------------------------------
	// SETTING THE VALUE FOR THE DESCRIPTION TO A VALID STRING USING setDescription
	// --------------------------------------------------------------
	// Passing a non-empty string into the setDescription method will
	// change the furniture's description, and the change can be checked 
	// using the getDescription method.
	@Test
	public void testSetDescriptionValid() {
		Furniture f = null;
		try {
			f = new Furniture("foo", "bar");
		} catch (Exception e) {
			fail("Method should allow any non-empty string as description and name input upon initialization.");
			e.printStackTrace();
			return;
		}
		assertTrue(f.setDescription("FOOBAR"));
		assertEquals("FOOBAR", f.getDescription());
	}
	
	// --------------------------------------------------------------
	// SETTING THE VALUE FOR THE DESCRIPTION TO AN INVALID, EMPTY-STRING USING setDescription
	// --------------------------------------------------------------
	// Passing an empty string ("") into the setDescription method will not
	// change the furniture's description, and be checked 
	// using the getDescription method. Also, setDescription will return false.
	@Test
	public void testSetDescriptionInvalidEmpty() {
		Furniture f = null;
		try {
			f = new Furniture("foo", "bar");
		} catch (Exception e) {
			fail("Method should allow any non-empty string as description and name input upon initialization.");
			e.printStackTrace();
			return;
		}
		assertFalse(f.setDescription(""));
		assertEquals("foo", f.getDescription());
	}
	
	
	// --------------------------------------------------------------
	// SETTING THE VALUE FOR THE DESCRIPTION TO AN INVALID, NULL VALUE USING setDescription
	// --------------------------------------------------------------
	// Passing null into the setDescription method will not
	// change the furniture's description, and be checked 
	// using the getDescription method. Also, setDescription will return false.
	@Test
	public void testSetDescriptionInvalidNull() {
		Furniture f = null;
		try {
			f = new Furniture("foo", "bar");
		} catch (Exception e) {
			fail("Method should allow any non-empty string as description and name input upon initialization.");
			e.printStackTrace();
			return;
		}
		assertFalse(f.setDescription(null));
		assertEquals("foo", f.getDescription());
	}
	

	// --------------------------------------------------------------
	// GETTING THE NAME OF THE FURNITURE THAT WAS GIVEN THROUGH THE CONSTRUCTOR USING getName
	// --------------------------------------------------------------
	// Calling getName will return a string value that is equal to the name set when initializing the
	// furniture object and is checked using string comparison with the name string: "bar".
	@Test
	public void testGetName() {
		Furniture f = null;
		try {
			f = new Furniture("foo", "bar");
		} catch (Exception e) {
			fail("Method should allow any non-empty string as description and name input upon initialization.");
			e.printStackTrace();
			return;
		}
		assertEquals("bar", f.getName());
	}

	
	// --------------------------------------------------------------
	// GETTING THE DESCRIPTION OF THE FURNITURE THAT WAS GIVEN THROUGH THE CONSTRUCTOR USING getDescription
	// --------------------------------------------------------------
	// Calling getDescription will return a string value that is equal to the description set when initializing the
	// furniture object and is checked using string comparison with the description string: "foo".
	@Test
	public void testGetDescription() {
		Furniture f = null;
		try {
			f = new Furniture("foo", "bar");
		} catch (Exception e) {
			fail("Method should allow any non-empty string as description and name input upon initialization.");
			e.printStackTrace();
			return;
		}
		assertEquals("foo", f.getDescription());
	}
	
	
	// --------------------------------------------------------------
	// CREATING A NEW FURNITURE OBJECT USING VALID, NON-EMPTY, NON-NULL STRING ARGUMENTS
	// --------------------------------------------------------------
	// Initializing a new furniture object with both valid name and description will not
	// cause any exception and the values' permanence can be checked using getDescription and getName.
	@Test
	public void testConstructor() {
		Furniture f = null;
		try {
			f = new Furniture("foo", "bar");
		} catch (Exception e) {
			fail("Method should allow any non-empty string as description and name input upon initialization.");
			e.printStackTrace();
			return;
		}
		assertEquals("foo", f.getDescription());
		assertEquals("bar", f.getName());
	}

	
	// --------------------------------------------------------------
	// CREATING A NEW FURNITURE OBJECT USING INVALID, EMPTY-STRING ARGUMENTS
	// --------------------------------------------------------------
	// Initializing a new furniture object with both invalid name and description ("") will
	// cause an exception and can be checked by catching said exception and returning instead of 
	// continuing to fail.
	@Test
	public void testInvalidConstructor() {
		Furniture f = null;
		try {
			f = new Furniture("", "");
		} catch (Exception e) {
			return;
		}
		fail("Method should not allow any non-empty string as description and name input upon initialization.");
	}
}
