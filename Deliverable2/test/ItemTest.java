/**
 * Richard Kotermanski 
 * CS1632 Deliverable 2 
 * ItemTest - Tests to test the Item class.
 */
import static org.junit.Assert.*;
import org.junit.Test;

public class ItemTest {
	
	// --------------------------------------------------------------
	// GETTING THE DESCRIPTION OF AN ITEM USING getDescription
	// --------------------------------------------------------------
	// Calling the getDescription method will return the given item's description as a string. 
	// This can be tested by seeing if the returned string from getDescription is equal to the value
	// passed in as an argument for the constructor ("Foo").
	@Test
	public void testGetDescription() {
		Item i = new Item("Foo", ItemType.COFFEE);
		assertEquals("Foo", i.getDescription());
	}
	
	
	// --------------------------------------------------------------
	// GETTING THE TYPE OF A COFFEE ITEM USING getType
	// --------------------------------------------------------------
	// Calling the getType method will return the given item's description as a string.
	// This can be tested by seeing if the returned string from getType.toString() is equal to the corresponding 
	// value passed in as an argument for the constructor ("coffee") and the ItemType value returned from getType
	// is equal to the equal to the value passed in as an argument for the constructor (ItemType.COFFEE).
	@Test
	public void testGetTypeCoffee() {
		Item i = new Item("Foo", ItemType.COFFEE);
		assertEquals("coffee", i.getType().toString());
		assertEquals(ItemType.COFFEE, i.getType());
	}
	
	
	// --------------------------------------------------------------
	// GETTING THE TYPE OF A SUGAR ITEM USING getType
	// --------------------------------------------------------------
	// Calling the getType method will return the given item's description as a string.
	// This can be tested by seeing if the returned string from getType.toString() is equal to the corresponding 
	// value passed in as an argument for the constructor ("sugar") and the ItemType value returned from getType
	// is equal to the equal to the value passed in as an argument for the constructor (ItemType.SUGAR).
	@Test
	public void testGetTypeSugar() {
		Item i = new Item("Foo", ItemType.SUGAR);
		assertEquals("sugar", i.getType().toString());
		assertEquals(ItemType.SUGAR, i.getType());
	}
	
	
	// --------------------------------------------------------------
	// GETTING THE TYPE OF A CREAM ITEM USING getType
	// --------------------------------------------------------------
	// Calling the getType method will return the given item's description as a string, 
	// This can be tested by seeing if the returned string from getType.toString() is equal to the corresponding 
	// value passed in as an argument for the constructor ("cream") and the ItemType value returned from getType
	// is equal to the equal to the value passed in as an argument for the constructor (ItemType.CREAM).
	@Test
	public void testGetTypeCream() {
		Item i = new Item("Foo", ItemType.CREAM);
		assertEquals("cream", i.getType().toString());
		assertEquals(ItemType.CREAM, i.getType());
	}
}
