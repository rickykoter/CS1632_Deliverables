/**
 * Richard Kotermanski 
 * CS1632 Deliverable 2 
 * PlayerTest - Test functions to test the Player class.	
 */
import static org.junit.Assert.*;
import org.junit.Test;

public class PlayerTest {
	

	// --------------------------------------------------------------
	// GETTING THE INVENTORY OF A PLAYER USING getInventory
	// --------------------------------------------------------------
	// Calling the getInventory method will return the given player's inventory as a boolean array, 
	// This can be tested by seeing if the returned inventory is not null.
	@Test
	public void testGetInventory(){
		Player p = new Player();
		boolean[] inv = p.getInventory();
		assertNotNull(inv);
	}
	
	
	// --------------------------------------------------------------
	// ACQUIRING COFFEE FOR A PLAYER USING acquireItem
	// --------------------------------------------------------------
	// Calling the acquireItem method will change the player to having the passed in ItemType (true) regardless 
	// if they already have it. This can be tested by seeing if the returned boolean value for acquireItem is true, and the index
	// associated with coffee (0) in the inventory boolean array returned by getInventory is now true.
	@Test
	public void testAquireItemCoffee(){
		ItemType coffee = ItemType.COFFEE;
		Player p = new Player();
		assertTrue(p.acquireItem(coffee));
		assertTrue(p.getInventory()[0]);
	}
	
	
	// --------------------------------------------------------------
	// ACQUIRING CREAM FOR A PLAYER USING acquireItem
	// --------------------------------------------------------------
	// Calling the acquireItem method will change the player to having the passed in ItemType (true) regardless 
	// if they already have it. This can be tested by seeing if the returned boolean value for acquireItem is true, and the index
	// associated with cream (1) in the inventory boolean array returned by getInventory is now true.
	@Test
	public void testAquireItemCream(){
		ItemType cream = ItemType.CREAM;
		Player p = new Player();
		assertTrue(p.acquireItem(cream));
		assertTrue(p.getInventory()[1]);
	}
	
	
	// --------------------------------------------------------------
	// ACQUIRING SUGAR FOR A PLAYER USING acquireItem
	// --------------------------------------------------------------
	// Calling the acquireItem method will change the player to having the passed in ItemType (true) regardless 
	// if they already have it. This can be tested by seeing if the returned boolean value for acquireItem is true, and the index
	// associated with sugar (2) in the inventory boolean array returned by getInventory is now true.
	@Test
	public void testAquireItemSugar(){
		ItemType sugar = ItemType.SUGAR;
		Player p = new Player();
		assertTrue(p.acquireItem(sugar));
		assertTrue(p.getInventory()[2]);
	}
	
	
	// --------------------------------------------------------------
	// ACQUIRING ALL ITEMTYPES FOR A PLAYER USING acquireItem
	// --------------------------------------------------------------
	// Calling the acquireItem method will change the player to having the passed in ItemType (true) regardless 
	// if they already have it. If done for all ItemTypes available, the inventory will be true for all indexes. 
	// This can be tested by seeing if the returned boolean value for acquireItem is true, and the index
	// associated with coffee(0), cream(1), and sugar (2) in the inventory boolean array returned by getInventory are all now true.
	@Test
	public void testAquireAllItems(){
		Player p = new Player();
		for(ItemType i : ItemType.values()){
			assertTrue(p.acquireItem(i));
		}
		boolean[] inv = p.getInventory();
		assertTrue(inv[0] && inv[1] && inv[2]);
	}
	
	
	// --------------------------------------------------------------
	// ACQUIRING NULL FOR A PLAYER USING acquireItem
	// --------------------------------------------------------------
	// Calling the acquireItem method with null will not change any element of the inventory nor state of the player.
	// This can be tested by seeing if the returned boolean value for acquireItem is false, and the index
	// associated with coffee(0), cream(1), and sugar (2) in the inventory boolean array returned by getInventory 
	// are all still false as they were initially.
	@Test
	public void testAquireNullItem(){
		Player p = new Player();
		boolean isNullAquired = p.acquireItem(null);
		boolean[] inv = p.getInventory();
		assertFalse(isNullAquired);
		assertFalse(inv[0] && inv[1] && inv[2]);
	}
	
	
	// --------------------------------------------------------------
	// DRINKING WITH SUGAR, CREAM, AND COFFEE NOT YET ACQUIRED
	// --------------------------------------------------------------
	// Calling the drink function will return false if aquiredItem(<item type>) has
	// yet to be called for a given player for all item types applicable.
	@Test
	public void testDrinkNotComplete(){
		Player p = new Player();
		assertFalse(p.drink());
	}
	
	// --------------------------------------------------------------
	// DRINKING WITH SUGAR NOT YET ACQUIRED
	// --------------------------------------------------------------
	// Calling the drink function will return false if aquiredItem(<item type>) has
	// yet to be called for a given player for all item types applicable.
	@Test
	public void testDrinkNotCompletePartial(){
		ItemType coffee = ItemType.COFFEE;
		ItemType cream = ItemType.CREAM;
		
		Player p = new Player();
		p.acquireItem(coffee);
		p.acquireItem(cream);
		assertFalse(p.drink());
	}

	// --------------------------------------------------------------
	// DRINKING WITH SUGAR, CREAM, AND COFFEE ALREADY ACQUIRED
	// --------------------------------------------------------------
	// Calling the drink function will return true if aquiredItem(<item type>) has
	// been called for a given player for all item types applicable.
	@Test
	public void testDrinkComplete(){
		ItemType coffee = ItemType.COFFEE;
		ItemType cream = ItemType.CREAM;
		ItemType sugar = ItemType.SUGAR;
		
		Player p = new Player();
		p.acquireItem(coffee);
		p.acquireItem(cream);
		p.acquireItem(sugar);
		assertTrue(p.drink());
	}
	
	// --------------------------------------------------------------
	// INITIAL INVENTORY BEING EMPTY
	// --------------------------------------------------------------
	// Upon initializing a Player object, all boolean values in the inventory
	// array denoting whether or not they have been acquired should be false.
	// This can be tested by iterating through all elements returned by the
	// getInventory method directly after initializing a player; the test should 
	// fail if any are true and pass if all are false.
	@Test
	public void testConstructorInventoryInitial() {
		Player p = new Player();
		boolean[] inv = p.getInventory();
		for(int i = 0; i < inv.length; i++){
			if(inv[i]) fail("Inventory is not initialized empty");
		}
	}

}
