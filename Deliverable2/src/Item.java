/**
 * Richard Kotermanski 
 * CS1632 Deliverable 2 
 * Item - an element of the game that has a type 
 * 			defined by the ItemType enum and an optional description.
 */

public class Item {
	private String description;
	private ItemType type;
	
	public Item(String desc, ItemType typ){
		description = desc;
		type = typ;
	}
	public ItemType getType(){
		return type;
	}
	
	public String getDescription(){
		return description;
	}
}


