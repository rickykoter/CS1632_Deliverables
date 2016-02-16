/**
 * Richard Kotermanski 
 * CS1632 Deliverable 2 
 * Map - Maintains the structure of rooms by enforcing linear, 
 * 			bi-directional properties while ensuring no rooms 
 * 			with duplicate room descriptions and/or furniture names may exist.
 */
import java.util.Arrays;

public class Map {
	private Room[] rooms;
	
	
	// Returns true if the given room, rm, does not match any of the other rooms
	//		in the room array ar in terms of set furniture name or set room description.
	//		If any duplicates are found immediately, it returns true; 
	//		otherwise, it returns false.
	private boolean isDuplicate(Room[] ar, Room rm) {
		if (ar == null || ar.length == 0){
			return false;
		}
		String desc = rm.getDescription().toLowerCase();
		String furn = rm.getFurniture().getName().toLowerCase();
		for (Room r : ar) {
			if (r.getDescription().toLowerCase().equals(desc)
					|| r.getFurniture().getName().toLowerCase().equals(furn)) {
				return true;
			}
		}
		return false;
	}

	public Map() {
		rooms = null;
	}
	
	
	// If the Room, adding, is unique in terms of furniture description
	//		and room name, then it is appended to the other rooms in the
	//		Map. It does so by setting the last room in the Map's sequence,
	//		if it exists, to have 'adding' as it's North room. It also sets 
	//		the South room of 'adding' to the same last room in the Map.
	//		The room array is resized to accommodate 'adding', and 'adding' is
	//		is set as the last element in said array.
	//		The function returns false if 'adding' is not null and unique; otherwise,
	//		it returns true, indicating that the room was appended.
	public boolean appendRoom(Room adding) {
		if (adding == null){
			return false;
		}
		if (isDuplicate(rooms, adding)){
			return false;
		}
		
		if (rooms == null || rooms.length == 0) {
			adding.setSouth(null);
		} else {
			adding.setSouth(rooms[rooms.length - 1]);
			rooms[rooms.length - 1].setNorth(adding);
		}
		adding.setNorth(null);
		if (rooms == null) {
			rooms = new Room[1];
		} else {
			rooms = Arrays.copyOf(rooms, rooms.length + 1);
		}
		rooms[rooms.length - 1] = adding;
		
		return true;
	}
	
	
	// Returns the Map's rooms as an array of rooms and null if no
	//		rooms exists.
	public Room[] getRooms() {
		return rooms;
	}
	
	// Returns the Map's first room in the sequence and null if no
	//		rooms exists.
	public Room getStartingRoom() {
		if (rooms == null){
			return null;
		}
		return rooms[0];
	}
}
