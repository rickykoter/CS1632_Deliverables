/**
 * Richard Kotermanski 
 * CS1632 Deliverable 2 
 * RoomTest - Test functions to test the Room class.	
 */

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class RoomTest {
	@Mock
	Furniture f = mock(Furniture.class);
	@Mock
	Furniture f2 = mock(Furniture.class);
	
	@Before
	public void setUp() throws Exception {
		//Reusable Furniture mocks that each return different names
		// using stubs for getName method calls.
		MockitoAnnotations.initMocks(f);
		when(f.getName()).thenReturn("ZOO");
		MockitoAnnotations.initMocks(f2);
		when(f2.getName()).thenReturn("BAR");
	}
	
	
	// --------------------------------------------------------------
	// SETTING THE NORTH ROOM OF A ROOM USING setNorth
	// --------------------------------------------------------------
	// Calling the setNorth method with a Room (r2) as an argument will change the given 
	// room's north room to said Room (r2), thus meaning a door exists north.
	// This can be tested by seeing if the room set to be north (r2) is the same as what is
	// returned by getNorth.
	@Test
	public void testSetNorth() throws Exception {
		Room r = new Room("FOO", f);
		Room r2 = new Room("BAR",f2);
		r.setNorth(r2);
		assertSame(r2, r.getNorth());
	}

	
	// --------------------------------------------------------------
	// SETTING THE SOUTH ROOM OF A ROOM USING setSouth
	// --------------------------------------------------------------
	// Calling the setSouth method with a Room (r2) as an argument will change the given 
	// room's south room to said Room (r2), thus meaning a door exists south.
	// This can be tested by seeing if the room set to be south (r2) is the same as what is
	// returned by getSouth.
	@Test
	public void testSetSouth() throws Exception {
		Room r = new Room("FOO", f);
		Room r2 = new Room("BAR",f2);
		r.setSouth(r2);
		assertSame(r2, r.getSouth());
	}
	
	
	// --------------------------------------------------------------
	// GETTING THE NORTH ROOM OF A ROOM USING getNorth
	// --------------------------------------------------------------
	// Calling the getNorth method will return the given room's north room (rN), 
	// thus meaning a door exists north since it is not null. This can be tested by seeing 
	// if the room set to be north (rN) during initialization is the same as what is 
	// returned by getNorth.
	@Test
	public void testGetNorth() throws Exception {
		Room rN = new Room("FOO", f);
		Room r = new Room(rN, null, "FOOBAR", f, null);
		assertSame(rN, r.getNorth());
	}
	
	
	// --------------------------------------------------------------
	// GETTING THE SOUTH ROOM OF A ROOM USING getSouth
	// --------------------------------------------------------------
	// Calling the getSouth method will return the given room's south room (rS), 
	// thus meaning a door exists south since it is not null. This can be tested by seeing 
	// if the room set to be south (rS) during initialization is the same as what is 
	// returned by getSouth.
	@Test
	public void testGetSouth() throws Exception {
		Room rS = new Room("FOO", f);
		Room r = new Room(null, rS, "FOOBAR", f, null);
		assertSame(rS, r.getSouth());
	}
	
	
	// --------------------------------------------------------------
	// GETTING THE ITEM OF A ROOM USING getItem
	// --------------------------------------------------------------
	// Calling the getItem method will return the given room's item (i), 
	// which may be null. This can be tested by seeing 
	// if the item (i) is the same as the item the getItem returns.
	@Test
	public void testGetItem() throws Exception {
		Item i = mock(Item.class);
		Room r = new Room(null, null, "FOOBAR", f, i);
		assertSame(i, r.getItem());
	}
	
	
	// --------------------------------------------------------------
	// SETTING THE ITEM OF A ROOM USING setItem
	// --------------------------------------------------------------
	// Calling the setItem method with an Item object (i) as the argument will change the room's item to
	// said item (i). This can be tested by seeing 
	// if the item (i) is the same as the item the getItem returns and not null.
	@Test
	public void testSetItem() {
		Item i = mock(Item.class);
		when(i.getType()).thenReturn(ItemType.COFFEE);
		when(i.getDescription()).thenReturn("Test");

		Furniture f = mock(Furniture.class);

		Room r = null;
		try {
			r = new Room("FOO", f);
		} catch (Exception e) {
			fail("Room could not be created.");
			e.printStackTrace();
		}
		r.setItem(i);
		assertNotNull(r.getItem());
		assertSame(i, r.getItem());
	}

	
	// --------------------------------------------------------------
	// SETTING THE FURNITURE OF A ROOM USING setFurniture
	// --------------------------------------------------------------
	// Calling the setFurniture method with a valid Furniture object (f2) as the argument will change 
	// the room's original furniture (f) to said furniture (f2). This can be tested by seeing 
	// if the furniture (f2) is the same as the furniture the getFurniture returns and the setFurniture returns true.
	@Test
	public void testSetFunitureValid() {
		Room r = null;
		try {
			r = new Room("FOO", f);
		} catch (Exception e) {
			fail("Room could not be created.");
			e.printStackTrace();
		}
		Furniture f2 = mock(Furniture.class);
		assertTrue(r.setFurniture(f2));
		assertSame(f2, r.getFurniture());
	}
	
	
	// --------------------------------------------------------------
	// SETTING THE FURNITURE INVALIDLY FOR A ROOM USING setFurniture
	// --------------------------------------------------------------
	// Calling the setFurniture method with a null value as the argument will not change 
	// the room's original furniture (f) to null. This can be tested by seeing 
	// if the furniture (f) is the same as the furniture the getFurniture returns and the setFurniture returns false.
	@Test
	public void testSetFunitureInvalid() {
		Room r = null;
		try {
			r = new Room("FOO", f);
		} catch (Exception e) {
			fail("Room could not be created.");
			e.printStackTrace();
		}
		assertFalse(r.setFurniture(null));
		assertSame(f, r.getFurniture());
	}


	// --------------------------------------------------------------
	// SETTING THE DESCRIPTION OF A ROOM USING setDescription
	// --------------------------------------------------------------
	// Calling the setDescription method with a valid, non-empty, and non-null string ("BAR") as the argument will change 
	// the room's original description ("FOO") to said description ("BAR"). This can be tested by seeing 
	// if the new description ("BAR") is equal to the description the getDescription returns and the setDescription returns true.
	@Test
	public void testSetDescriptionValid() {
		Room r = null;
		try {
			r = new Room("FOO", f);
		} catch (Exception e) {
			fail("Room could not be created.");
			e.printStackTrace();
		}

		assertTrue(r.setDescription("BAR"));
		assertEquals("BAR", r.getDescription());
	}

	
	// --------------------------------------------------------------
	// SETTING THE DESCRIPTION TO AN INVALID, EMPTY-STRING FOR A ROOM USING setDescription
	// --------------------------------------------------------------
	// Calling the setDescription method with an invalid, empty string ("") as the argument will not change 
	// the room's original description ("FOO") to said description (""). This can be tested by seeing 
	// if the original description ("FOO") is equal to the description the getDescription returns and the setDescription returns false.
	@Test
	public void testSetDescriptionInvalidEmpty() {
		Room r = null;
		try {
			r = new Room("FOO", f);
		} catch (Exception e) {
			fail("Room could not be created.");
			e.printStackTrace();
		}

		assertFalse(r.setDescription(""));
		assertEquals("FOO", r.getDescription());
	}
	
	
	// --------------------------------------------------------------
	// SETTING THE DESCRIPTION TO AN INVALID, NULL VALUE FOR A ROOM USING setDescription
	// --------------------------------------------------------------
	// Calling the setDescription method with an invalid, null value as the argument will not change 
	// the room's original description ("FOO") to null. This can be tested by seeing 
	// if the original description ("FOO") is equal to the description the getDescription returns and the setDescription returns false.
	@Test
	public void testSetDescriptionInvalidNull() {
		Room r = null;
		try {
			r = new Room("FOO", f);
		} catch (Exception e) {
			fail("Room could not be created.");
			e.printStackTrace();
		}

		assertFalse(r.setDescription(null));
		assertEquals("FOO", r.getDescription());
	}
	
	
	// --------------------------------------------------------------
	// CREATING A NEW ROOM OBJECT WITH VALID PARAMETERS
	// --------------------------------------------------------------
	// Initializing a room with a north room, south room, description, furniture, and item as arguments
	// will produce a room object with those attributes set and correct. This can be checked by using the
	// respective getters for the attributes to check if they are the same/equal to the arguments, respectively.
	@Test
	public void testValidConstructor() throws Exception {
		Room rN = new Room("FOO", f);
		Room rS = new Room("BAR", f);
		Item i = mock(Item.class);
		Room r = new Room(rN, rS, "FOOBAR", f, i);

		assertSame(rN, r.getNorth());
		assertSame(rS, r.getSouth());
		assertEquals("FOOBAR", r.getDescription());
		assertSame(i, r.getItem());
	}

	
	// --------------------------------------------------------------
	// CREATING A NEW ROOM OBJECT WITH VALID PARAMETERS
	// --------------------------------------------------------------
	// Initializing a room with an empty string and no furniture (null) as invalid arguments
	// will cause an exception to be thrown. To test this, catching the exception and matching the message,
	// returning if correct, will prevent the method from reaching the fail function call.
	@Test
	public void testInvalidConstructor() {
		Item i = mock(Item.class);
		Room r = null;
		try {
			r = new Room(null, null, "", null, i);
		} catch (Exception e) {
			if(e.getMessage().contains("Room must have")){
				return;
			}
		}
		fail("Room must have furniture and name to exist.");
	}
}
