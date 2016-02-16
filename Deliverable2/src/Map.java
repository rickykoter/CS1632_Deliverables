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

	public Room[] getRooms() {
		return rooms;
	}

	public Room getStartingRoom() {
		if (rooms == null){
			return null;
		}
		return rooms[0];
	}
}
