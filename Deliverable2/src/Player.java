/**
 * Richard Kotermanski 
 * CS1632 Deliverable 2 
 * Player - The entity of the quest
 * 			that has an inventory, acquires items for said inventory, and attempts to
 * 			drink from said inventory
 */

public class Player {
	private boolean hasCream;
	private boolean hasSugar;
	private boolean hasCoffee;

	public Player() {
		hasCream = false;
		hasSugar = false;
		hasCoffee = false;
	}

	public boolean drink() {
		if (hasCoffee && hasCream && hasSugar) {
			return true;
		}
		return false;
	}

	public boolean[] getInventory() {
		boolean[] it = { hasCoffee, hasCream, hasSugar };
		return it;
	}

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
