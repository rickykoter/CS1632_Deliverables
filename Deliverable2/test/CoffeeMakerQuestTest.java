/**
 * Richard Kotermanski 
 * CS1632 Deliverable 2 
 * CoffeeMakerQuestTest - Test functions to test the CoffeeMakerQuest class.
 * 							Note: This class contains the main method which
 * 							is not subject to tests.	
 */
import static org.junit.Assert.*;

import org.junit.Test;

public class CoffeeMakerQuestTest {
	
	// --------------------------------------------------------------
	// CREATING A HARDCODED MAP FOR GAME USE USING initializeMap
	// --------------------------------------------------------------
	// The method initializeMap returns a Map object that is constructed using
	// hard coded room descriptions, furniture names, furniture descriptions, and item locations.
	// It can be tested be see if the map has 5 rooms and is not null to determine that rooms 
	// were successfully appended. This test requires Map, Room, and Furniture classes to be 
	// implemented due to internal creation of Map, Room, and Furniture objects required. 
	@Test
	public void testIntializeMap() throws Exception {
		CoffeeMakerQuest cm = new CoffeeMakerQuest();
		Map m = CoffeeMakerQuest.initializeMap();
		assertNotNull(m);
		assertEquals(m.getRooms().length, 5);
	}

}
