/**
 * Richard Kotermanski 
 * CS1632 Deliverable 2 
 * Player - The entity of the quest
 * 		that has an inventory, acquires items for said inventory, and attempts to
 * 		drink from said inventory
 */

public class Player {
	private boolean hasCream; // inventory state for whether
				  // 	or not the player has acquired cream
	
	private boolean hasSugar; // inventory state for whether
				  // 	or not the player has acquired sugar
	
	private boolean hasCoffee; // inventory state for whether
				   // 	or not the player has acquired coffee

	public Player() {
		hasCream = false;
		hasSugar = false;
		hasCoffee = false;
	}
	
	
	// Given that the inventory state for each item type (cream, sugar, and coffee)
	//	is true and that the player has acquired them, then return true.
	//	 Otherwise, it returns false.
	public boolean drink() {
		if (hasCoffee && hasCream && hasSugar) {
			return true;
		}
		return false;
	}

	
	// Creates and returns a boolean array where the indexes describe the inventory
	//	state for each item in the format: 
	//	{ coffee's inventory state, cream's inventory state, sugar's inventory state }
	public boolean[] getInventory() {
		boolean[] it = { hasCoffee, hasCream, hasSugar };
		return it;
	}
	
	
	// Given an item type, itm, the player will acquire the item
	//	by setting the inventory state for the given item type
	//	value to true. If the type, itm, is of type COFFEE,
	//	CREAM, or SUGAR, then the function will return true;
	//	otherwise, it will return false.
	public boolean acquireItem(ItemType itm) {
		if (itm == null)
			return false;
		switch (itm) {
		case COFFEE:
			hasCoffee = true;
			break;
		case CREAM:
			hasCream = true;
			break;
		case SUGAR:
			hasSugar = true;
			break;
		default:
			return false;
		}
		return true;
	}

}
