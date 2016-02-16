/**
 * Richard Kotermanski 
 * CS1632 Deliverable 2 
 * MapTest - Tests to test the Map class.
 */
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MapTest {
	@Mock
	Room r1f1 = mock(Room.class);
	@Mock
	Room r2f2 = mock(Room.class);
	@Mock
	Room r2f1 = mock(Room.class);
	@Mock
	Room r1f3 = mock(Room.class);
	@Mock
	Furniture f1 = mock(Furniture.class);
	@Mock
	Furniture f2 = mock(Furniture.class);
	@Mock
	Furniture f3 = mock(Furniture.class);

	@Before
	public void setUp() throws Exception {
		//Reusable Room mock objects that contain various furniture names
		// and descriptions that may overlap with each other.
		MockitoAnnotations.initMocks(r1f1);
		MockitoAnnotations.initMocks(r2f2);
		MockitoAnnotations.initMocks(r2f1);
		MockitoAnnotations.initMocks(r1f3);
		//Reusable Furniture mock objects
		MockitoAnnotations.initMocks(f1);
		MockitoAnnotations.initMocks(f2);
		MockitoAnnotations.initMocks(f3);
		
		//Each furniture mock object has a unique name
		when(f1.getName()).thenReturn("f1");
		when(f2.getName()).thenReturn("f2");
		when(f3.getName()).thenReturn("f3");

		//Room conflicting with r2f1 and r1f3
		when(r1f1.getDescription()).thenReturn("r1");
		when(r1f1.getItem()).thenReturn(null);
		when(r1f1.getFurniture()).thenReturn(f1);
		
		//Room conflicting with r2f2
		when(r2f2.getDescription()).thenReturn("r2");
		when(r2f2.getItem()).thenReturn(null);
		when(r2f2.getFurniture()).thenReturn(f2);

		//Room conflicting with r1f1
		when(r2f1.getDescription()).thenReturn("r2");
		when(r2f1.getItem()).thenReturn(null);
		when(r2f1.getFurniture()).thenReturn(f1);

		//Room conflicting with r1f1
		when(r1f3.getDescription()).thenReturn("r1");
		when(r1f3.getItem()).thenReturn(null);
		when(r1f3.getFurniture()).thenReturn(f3);
	}

	
	// --------------------------------------------------------------
	// APPENDING A ROOM WITH A DUPLICATE ROOM DESCRIPTION AND FURNITURE NAME USING appendRoom 
	// --------------------------------------------------------------
	// When a room is appended to the map that has a duplicate room description and a duplicate furniture name
	// as one of the other rooms already in the map, then the room shall not be appended. This can be checked by appending a
	// room object (r1f1), ensuring that it added, and then appending the same room object (r1f1) again. 
	// This time ensure that the appendRoom function returns false and the array of rooms remains unaffected.
	@Test
	public void testAppendRoomNonUniqueRoomAndFurniture() {
		Map m = new Map();
		assertTrue(m.appendRoom(r1f1));
		Room[] r = m.getRooms();
		assertFalse(m.appendRoom(r1f1));
		assertArrayEquals(r, m.getRooms());
	}
	

	// --------------------------------------------------------------
	// APPENDING A ROOM WITH A UNIQUE ROOM DESCRIPTION AND FURNITURE NAME USING appendRoom 
	// --------------------------------------------------------------
	// When a room is appended to the map that has a unique room description and a unique furniture name
	// as one of the other rooms already in the map, then the room shall be appended to the north-most/last room. This can be checked by appending a
	// room object (r1f1), ensuring that it added, and then appending another room object (r2f2) that has a unique name and furniture description. 
	// This time ensure that the appendRoom function again returns true and that the array of rooms contains r2f2 at the last index and the number of
	// rooms increased by one using the getRooms function.
	@Test
	public void testAppendRoomUniqueRoomAndFurniture() {
		Map m = new Map();
		assertTrue(m.appendRoom(r1f1));
		Room[] r = m.getRooms();
		assertTrue(m.appendRoom(r2f2));
		assertTrue(r.length + 1 == m.getRooms().length);
		assertSame(r2f2, m.getRooms()[r.length]);
	}

	
	// --------------------------------------------------------------
	// APPENDING A ROOM WITH A DUPLICATE ROOM DESCRIPTION USING appendRoom 
	// --------------------------------------------------------------
	// When a room is appended to the map that has a duplicate room description but a unique furniture name
	// as one of the other rooms already in the map, then the room shall not be appended. This can be checked by appending a
	// room object (r1f1), ensuring that it added, and then appending the another room object that has the same room description but
	// different furniture name (r1f3). This time ensure that the appendRoom function returns false and the array of rooms remains unaffected.
	@Test
	public void testAppendRoomNonUniqueRoomDescription() {
		Map m = new Map();
		assertTrue(m.appendRoom(r1f1));
		Room[] r = m.getRooms();
		assertFalse(m.appendRoom(r1f3));
		assertArrayEquals(r, m.getRooms());
	}

	
	// --------------------------------------------------------------
	// APPENDING A ROOM WITH A DUPLICATE FURNITURE NAME USING appendRoom 
	// --------------------------------------------------------------
	// When a room is appended to the map that has a unique room description but a duplicate furniture name
	// as one of the other rooms already in the map, then the room shall not be appended. This can be checked by appending a
	// room object (r1f1), ensuring that it added, and then appending the another room object that has a different room description but
	// the same furniture name (r2f1). This time ensure that the appendRoom function returns false and the array of rooms remains unaffected.
	@Test
	public void testAppendRoomNonUniqueFurnitureName() {
		Map m = new Map();
		assertTrue(m.appendRoom(r1f1));
		Room[] r = m.getRooms();
		assertFalse(m.appendRoom(r2f1));
		assertArrayEquals(r, m.getRooms());
	}

	
	// --------------------------------------------------------------
	// APPENDING NULL TO THE MAP USING appendRoom 
	// --------------------------------------------------------------
	// When null is used as an argument for appendRoom, ensure that the appendRoom function 
	// returns false and the array of rooms remains unaffected as null. Nothing should be appended to the
	// map, and the state shall remain the same.
	@Test
	public void testAppendRoomNull() {
		Map m = new Map();
		assertFalse(m.appendRoom(null));
		assertNull(m.getRooms());
	}
	
	
	// --------------------------------------------------------------
	// GETTING THE STARTING ROOMv AFTER APPENDS USING getStartingRoom
	// --------------------------------------------------------------
	// After appending multiple unique mock rooms, ensure that getStartingRoom returns the same
	// mock room as the first room appended (r1f1).
	public void testGetStartingRoom() {
		Map m = new Map();
		assertTrue(m.appendRoom(r1f1));
		assertTrue(m.appendRoom(r2f2));
		assertEquals(r1f1, m.getStartingRoom());
	}
	
	
	// --------------------------------------------------------------
	// GETTING THE STARTING ROOM INITIALLY USING getStartingRoom
	// --------------------------------------------------------------
	// Without appending any rooms, ensure that getStartingRoom returns null.
	@Test
	public void testGetStartingRoomNull() {
		Map m = new Map();
		assertNull(m.getStartingRoom());
	}
	
	
	// --------------------------------------------------------------
	// GETTING ROOMS AS NULL INITIALLY USING getRooms
	// --------------------------------------------------------------
	// Without appending any rooms, ensure that getRooms returns null.
	@Test
	public void testGetRoomsNull() {
		Map m = new Map();
		assertNull(m.getRooms());
	}

	
	// --------------------------------------------------------------
	// GETTING THE ARRAY OF ROOMS AFTER APPENDING USING getRooms
	// --------------------------------------------------------------
	// After appending two unique mock rooms, ensure that getRooms returns 
	// a list of rooms that exactly contains the mock room objects passed in
	// and in the correct order while taking size into consideration: [r1f1, r2f2]
	@Test
	public void testGetRoomsTwo() {
		Map m = new Map();
		assertTrue(m.appendRoom(r1f1));
		assertTrue(m.appendRoom(r2f2));
		assertTrue(m.getRooms().length == 2);
		assertEquals(r1f1, m.getRooms()[0]);
		assertEquals(r2f2, m.getRooms()[1]);
	}
}
