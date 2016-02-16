/**
 * Richard Kotermanski
 * CS1632 Deliverable 2
 * GameTest - Test functions to test the Game class.
 */
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class GameTest {
	@Mock
	Furniture f = mock(Furniture.class);

	@Mock
	Room rMiddle = mock(Room.class);

	@Mock
	Room rSouth = mock(Room.class);

	@Mock
	Room rNorth = mock(Room.class);

	@Mock
	Item hotCoffeeMock = mock(Item.class);
	
	@Before
	public void setUp() throws Exception {
		// Reusable simple furniture mock that has stubs to 
		//	return a name and description, respectively.
		MockitoAnnotations.initMocks(f);
		when(f.getDescription()).thenReturn("foo");
		when(f.getName()).thenReturn("bar");
		
		// Item mock that is used in the middle room to mock a hot coffee item
		// using stubs.
		MockitoAnnotations.initMocks(hotCoffeeMock);
		when(hotCoffeeMock.getType()).thenReturn(ItemType.COFFEE);
		when(hotCoffeeMock.getDescription()).thenReturn("Hot");
		
		// Set of mock rooms with mock connections to other mock rooms
		// creating the structure to test game progression as shown below:
		// Null <-- rSouth <--> rMiddle <--> rNorth --> Null
		MockitoAnnotations.initMocks(rMiddle);
		MockitoAnnotations.initMocks(rSouth);
		MockitoAnnotations.initMocks(rNorth);
		
		// Middle room
		when(rMiddle.getSouth()).thenReturn(rSouth);
		when(rMiddle.getNorth()).thenReturn(rNorth);
		when(rMiddle.getDescription()).thenReturn("FooMiddle");
		when(rMiddle.getItem()).thenReturn(hotCoffeeMock);
		when(rMiddle.getFurniture()).thenReturn(f);
		
		// South-most room
		when(rSouth.getSouth()).thenReturn(null);
		when(rSouth.getNorth()).thenReturn(rMiddle);
		when(rSouth.getDescription()).thenReturn("FooSouth");
		when(rSouth.getItem()).thenReturn(null);
		when(rSouth.getFurniture()).thenReturn(f);
		
		// North-most room
		when(rNorth.getSouth()).thenReturn(rMiddle);
		when(rNorth.getNorth()).thenReturn(null);
		when(rNorth.getDescription()).thenReturn("FooNorth");
		when(rNorth.getItem()).thenReturn(null);
		when(rNorth.getFurniture()).thenReturn(f);

	}


	// --------------------------------------------------------------
	// MOVING THE PLAYER NORTH FROM THE CURRENT ROOM THROUGH AN EXISTING NORTH DOOR USING THE PROGRESS METHOD
	// --------------------------------------------------------------
	// When the n command is given to the progress function of a game and a room exists to the north,
	// the currentRoom (rMiddle) should change to the room that is set as the North door
	// for the starting, current room. The currentRoom after the 'n' command is equal to
	// rNorth, which is the room north of rMiddle (the starting room).
	@Test
	public void testProgressMoveNorthWithDoor() {
		Player p = mock(Player.class);
		Map m = mock(Map.class);
		when(m.getStartingRoom()).thenReturn(rMiddle);
		
		Game g = new Game(m, p);
		assertEquals(rMiddle, g.getCurrentRoom());
		g.progress("n");
		assertEquals(rNorth, g.getCurrentRoom());
	}

	// --------------------------------------------------------------
	// MOVING THE PLAYER NORTH FROM THE CURRENT ROOM WITH NO EXISTING NORTH DOOR USING THE PROGRESS METHOD
	// --------------------------------------------------------------
	// When the N command is given to progress and a room does not exists to the north,
	// the currentRoom (rNorth) should not change. The currentRoom after the 'N' command is equal to
	// rNorth, which is the original starting room.
	@Test
	public void testProgressMoveNorthWithNoDoor() {
		Player p = mock(Player.class);
		Map m = mock(Map.class);
		when(m.getStartingRoom()).thenReturn(rNorth);

		Game g = new Game(m, p);
		assertEquals(rNorth, g.getCurrentRoom());
		g.progress("N");
		assertEquals(rNorth, g.getCurrentRoom());
	}

	
	// --------------------------------------------------------------
	// MOVING THE PLAYER SOUTH FROM THE CURRENT ROOM THROUGH AN EXISTING SOUTH DOOR USING THE PROGRESS METHOD
	// --------------------------------------------------------------
	// When the s command is given to progress and a room exists to the south,
	// the currentRoom (rMiddle) should change to the room that is set as the South door
	// for the starting, current room. The currentRoom after the 'S' command is equal to
	// rSouth, which is the room south of rMiddle (the starting room).
	@Test
	public void testProgressMoveSouthWithDoor() {
		Player p = mock(Player.class);
		Map m = mock(Map.class);
		when(m.getStartingRoom()).thenReturn(rMiddle);

		Game g = new Game(m,p);
		assertEquals(rMiddle, g.getCurrentRoom());
		g.progress("s");
		assertEquals(rSouth, g.getCurrentRoom());
	}

	
	// --------------------------------------------------------------
	// MOVING THE PLAYER SOUTH FROM THE CURRENT ROOM WITH NO EXISTING SOUTH DOOR USING THE PROGRESS METHOD 
	// --------------------------------------------------------------
	// When the S command is given to progress and a room does not exists to the south,
	// the currentRoom (rSouth) should not change. The currentRoom after the upper case 'S' command is equal to
	// rSouth, which is the original starting room.
	@Test
	public void testProgressMoveSouthWithNoDoor() {
		Player p = mock(Player.class);
		Map m = mock(Map.class);
		when(m.getStartingRoom()).thenReturn(rSouth);

		Game g = new Game(m, p);
		assertEquals(rSouth, g.getCurrentRoom());
		g.progress("S");
		assertEquals(rSouth, g.getCurrentRoom());
	}

	
	// --------------------------------------------------------------
	// WINNING THE GAME AFTER DRINKING WITH A SATISFIED PLAYER INVENTORY USING THE PROGRESS METHOD
	// --------------------------------------------------------------
	// Given that the 'd' command is provided to the progress function and the player's drink
	// method returns true (meaning all items have been acquired to make coffee), the game will
	// go into a winning state. The return string should include a statement saying the player won,
	//the game's status is set to GameStatus.WIN, and the player's drink function is called exactly once.
	@Test
	public void testProgressGameWin() {
		Player p = mock(Player.class);
		Map m = mock(Map.class);
		when(m.getStartingRoom()).thenReturn(rMiddle);

		Game g = new Game(m, p);
		when(p.drink()).thenReturn(true);
		boolean[] outInv = { true, true, true };
		when(p.getInventory()).thenReturn(outInv);
		String res = g.progress("d");
		assertEquals( GameStatus.WIN,g.getStatus());
		assertEquals("win",g.getStatus().toString());
		assertTrue(res.toLowerCase().contains("win"));
		verify(p,times(1)).drink();
	}
	
	
	// --------------------------------------------------------------
	// LOSING THE GAME AFTER DRINKING WITH AN UNSATISFIED PLAYER INVENTORY USING THE PROGRESS METHOD
	// --------------------------------------------------------------
	// Given that the 'D' command is provided to the progress function and the player's drink
	// method returns false (meaning that not all items have been acquired to make coffee), the game will
	// go into a losing state. The return string should include a statement saying the player lost,
	//the game's status is set to GameStatus.LOSE, and the player's drink function is called exactly once.
	@Test
	public void testProgressGameLose() {
		boolean[] outInv = { true, true, false };
		Player p = mock(Player.class);
		when(p.drink()).thenReturn(false);
		when(p.getInventory()).thenReturn(outInv);
		Map m = mock(Map.class);
		when(m.getStartingRoom()).thenReturn(rMiddle);
	
		Game g = new Game(m, p);
		String res = g.progress("D");
		assertEquals( GameStatus.LOSE,g.getStatus());
		assertEquals("lose",g.getStatus().toString());
		assertTrue(res.toLowerCase().contains("lose"));
	}

	
	// --------------------------------------------------------------
	// GETTING A FORMATTED FULL INVENTORY WITH THE INVENTORY COMMAND FOR THE PROGRESS METHOD
	// --------------------------------------------------------------
	// Calling the progress command with an 'i' value for the command argument 
	// while the current player has an full inventory (coffee, cream, and sugar) will
	// produce a string for a return value containing the messages for each item that 
	// the user has said item (i.e. You have <item>!). This should require exactly one call
	// to the player's getInventory method.
	@Test
	public void testProgressInventoryCheckFullCommand() {
		Player p = mock(Player.class);
		Map m = mock(Map.class);
		boolean[] inv = { true, true, true };
		when(m.getStartingRoom()).thenReturn(rMiddle);
		when(p.getInventory()).thenReturn(inv);

		Game g = new Game(m, p);
		String s = g.progress("i");
		verify(p, times(1)).getInventory();
		assertTrue(s.contains("You have coffee!") && s.contains("You have cream!") && s.contains("You have sugar!"));
	}
	
	
	// --------------------------------------------------------------
	// GETTING A FORMATTED EMPTY INVENTORY WITH THE INVENTORY COMMAND FOR THE PROGRESS METHOD
	// --------------------------------------------------------------
	// Calling the progress command with an "i" value for the command argument 
	// while the current player has an empty inventory (no coffee, no cream, and no sugar) will
	// produce a string for a return value containing the messages for each item that 
	// the user does not have said item (i.e. You do not have <item>.). This should require exactly one call
	// to the player's getInventory method.
	@Test
	public void testProgressInventoryCheckEmptyCommand() {
		Player p = mock(Player.class);
		Map m = mock(Map.class);
		boolean[] inv = { false, false, false };
		when(m.getStartingRoom()).thenReturn(rMiddle);
		when(p.getInventory()).thenReturn(inv);

		Game g = new Game(m, p);
		String s = g.progress("i");
		verify(p, times(1)).getInventory();
		assertTrue(s.contains("You do not have coffee.") && s.contains("You do not have cream.") && s.contains("You do not have sugar."));
	}
	
	
	// --------------------------------------------------------------
	// THE PROGRESS METHOD ACCEPTING THE HELP COMMAND TO DISPLAY ALL COMMANDS AND THEIR DESCRIPTIONS 
	// --------------------------------------------------------------
	// Calling the progress command with an "H" value for the command argument 
	// will receive a return value of a string containing a listing of possible 
	// commands and what their effects are. This test ensure the command is recognized
	// and that the return value contains mention of every command possible.
	@Test
	public void testProgressHelpCommand() {
		Player p = mock(Player.class);
		Map m = mock(Map.class);
		boolean[] inv = { true, true, true };
		when(m.getStartingRoom()).thenReturn(rMiddle);
		when(p.getInventory()).thenReturn(inv);

		Game g = new Game(m, p);
		String s = g.progress("H");
		assertFalse(s.contains("What?"));
		assertTrue(s.contains("n,N"));
		assertTrue(s.contains("s,S"));
		assertTrue(s.contains("h,H"));
		assertTrue(s.contains("i,I"));
		assertTrue(s.contains("l,L"));
		assertTrue(s.contains("d,D"));
	}
	

	// --------------------------------------------------------------
	// LOOKING AND ACQUIRING ITEMS USING THE LOOK COMMAND FOR THE PROGRESS METHOD
	// --------------------------------------------------------------
	// Calling the progress command with an "l" value for the command argument 
	// while the currentRoom is one in which an item is located (not null) will
	// produce a string for a return value that contains the message in the format:
	// You see a <adjective> <item>. Also, the player's acquireItem method with 
	// the currentRoom's item's type as the argument will be called exactly once.
	@Test
	public void testProgressLookCommandWithItem(){
		Map m = mock(Map.class);
		Player p = mock(Player.class);
		when(m.getStartingRoom()).thenReturn(rMiddle);
		when(p.acquireItem(ItemType.COFFEE)).thenReturn(true);
		
		Game g = new Game(m, p);
		String res = g.progress("l");
		assertTrue(res.contains("You see a Hot coffee"));
		verify(p, times(1)).acquireItem(ItemType.COFFEE);
	}
	
	
	// --------------------------------------------------------------
	// LOOKING BUT NOT ACQUIRING ITEMS USING THE LOOK COMMAND FOR THE PROGRESS METHOD
	// --------------------------------------------------------------
	// Calling the progress command with an "l" value for the command argument 
	// while the currentRoom is one in which no items are located (null) will
	// produce a string for a return value that contains the message in the format:
	// You see nothing. Also, the player's acquireItem method with 
	// the currentRoom's item's type as the argument will never be called.
	@Test
	public void testProgressLookCommandWithNoItem(){
		Map m = mock(Map.class);
		Player p = mock(Player.class);
		when(m.getStartingRoom()).thenReturn(rNorth);
		
		Game g = new Game(m, p);
		String res = g.progress("l");
		assertTrue(res.contains("You see nothing"));
		verify(p, times(0)).acquireItem(any());
	}
	
	
	// --------------------------------------------------------------
	// USING AN INVALID, NON-EMPTY STRING COMMAND WITH THE PROGRESS METHOD
	// --------------------------------------------------------------
	// Calling the progress command with a non-empty string value that is not a 
	// valid command for the command argument (nNiIlLnNsShHdD123456789) will produce a
	// return value string that contains the text: What?.
	@Test
	public void testProgressInvalidCommandString() {
		Player p = mock(Player.class);
		Map m = mock(Map.class);
		boolean[] inv = { true, true, true };
		when(m.getStartingRoom()).thenReturn(rMiddle);
		when(p.getInventory()).thenReturn(inv);

		Game g = new Game(m, p);
		String s = g.progress("nNiIlLnNsShHdD123456789");
		assertTrue(s.contains("What?"));
	}
	
	
	// --------------------------------------------------------------
	// USING AN INVALID, NULL ARGUMENT AS A COMMAND WITH THE PROGRESS METHOD
	// --------------------------------------------------------------
	// Calling the progress command with a null value for the command argument
	// will produce a return value string that contains the text: What?.
	@Test
	public void testProgressInvalidCommandNull() {
		Player p = mock(Player.class);
		Map m = mock(Map.class);
		boolean[] inv = { true, true, true };
		when(m.getStartingRoom()).thenReturn(rMiddle);
		when(p.getInventory()).thenReturn(inv);

		Game g = new Game(m, p);
		String s = g.progress(null);
		assertTrue(s.contains("What?"));
	}
	
	
	// --------------------------------------------------------------
	// USING AN INVALID, EMPTY-STRING ARGUMENT AS A COMMAND WITH THE PROGRESS METHOD
	// --------------------------------------------------------------
	// Calling the progress command with an empty-string ("") value for the command argument
	// will produce a return value string that contains the text: What?.
	@Test
	public void testProgressInvalidCommandEmpty() {
		Player p = mock(Player.class);
		Map m = mock(Map.class);
		boolean[] inv = { true, true, true };
		when(m.getStartingRoom()).thenReturn(rMiddle);
		when(p.getInventory()).thenReturn(inv);

		Game g = new Game(m, p);
		String s = g.progress("");
		assertTrue(s.contains("What?"));
	}
	
	
	// --------------------------------------------------------------
	// GETTING THE CURRENT ROOM FROM A GAME IN PROGRESS
	// --------------------------------------------------------------
	// Given that a game is initialized with a map that has a starting room
	// (rMiddle) the getCurrentRoom method should return that same (mock) object
	// when called.
	@Test
	public void testGetCurrentRoom(){
		Map m = mock(Map.class);
		Player p = mock(Player.class);
		when(m.getStartingRoom()).thenReturn(rMiddle);
		
		Game g = new Game(m, p);
		assertSame(rMiddle, g.getCurrentRoom());
	}
	
	
	// --------------------------------------------------------------
	// GETTING THE CURRENT STATUS FROM A GAME IN PROGRESS
	// --------------------------------------------------------------
	// Given that a game is initialized the getStatus method should return 
	// a default status of IN_PROGRESS.
	@Test
	public void testGetStatus(){
		Map m = mock(Map.class);
		Player p = mock(Player.class);
		when(m.getStartingRoom()).thenReturn(rMiddle);
		
		Game g = new Game(m, p);
		assertEquals(GameStatus.IN_PROGRESS, g.getStatus());
	}
	
	
	// --------------------------------------------------------------
	// GAME CONSTRUCTOR SETTING VALUES CORRECTLY
	// --------------------------------------------------------------
	// Given that a game is initialized status and currentRoom (based off of the input map) should
	// be set appropriately and are checked using their respective getters. 
	@Test
	public void testGameConstructor(){
		Map m = mock(Map.class);
		Player p = mock(Player.class);
		when(m.getStartingRoom()).thenReturn(rMiddle);
		
		Game g = new Game(m, p);
		assertSame(rMiddle, g.getCurrentRoom());
		assertEquals("in progress",g.getStatus().toString());
		
	}

}
