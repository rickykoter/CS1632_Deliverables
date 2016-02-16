/**
 * Richard Kotermanski 
 * CS1632 Deliverable 2 
 * ItemType - an enum containing values describing the 
 * 				possible types of items seen in the game
 */
public enum ItemType {
	COFFEE,
	CREAM,
	SUGAR;
	
    public String toString() {
    	return this.name().toLowerCase();
	}
}
