/**
 * Richard Kotermanski 
 * CS1632 Deliverable 2 
 * Room  - 	The entity of the quest
 * 			that has a unique piece of furniture, a unique description, 
 * 			an item, and doors (links) to other rooms.
 */

public class Room {
	private Room north;
	private Room south;
	private Furniture furniture;
	private String description;
	private Item item;

	public Room(Room northRoom, Room southRoom, String desc, Furniture f, Item i) throws Exception {
		if (f == null){
			throw new Exception("Room must have furniture.");
		}
		if (desc == null || desc.isEmpty()){
			throw new Exception("Room must have a description.");
		}
		south = southRoom;
		north = northRoom;
		description = desc;
		furniture = f;
		item = i;
	}

	public Room(String desc, Furniture f) throws Exception {
		if (f == null){
			throw new Exception("Room must have furniture.");
		}
		if (desc == null || desc.isEmpty()){
			throw new Exception("Room must have a description.");
		}
		description = desc;
		furniture = f;
	}

	public Room getNorth() {
		return north;
	}

	public Room getSouth() {
		return south;
	}

	public String getDescription() {
		return description;
	}

	public Item getItem() {
		return item;
	}

	public Furniture getFurniture() {
		return furniture;
	}

	public void setNorth(Room rm) {
		north = rm;
	}

	public void setSouth(Room rm) {
		south = rm;
	}

	public boolean setDescription(String desc) {
		if (desc == null || desc.isEmpty()){
			return false;
		}
		description = desc;
		return true;
	}

	public void setItem(Item itm) {
		item = itm;
	}

	public boolean setFurniture(Furniture fntr) {
		if (fntr == null){
			return false;
		}
		furniture = fntr;
		return true;
	}

}
