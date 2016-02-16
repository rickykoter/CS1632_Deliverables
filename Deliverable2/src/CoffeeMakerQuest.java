/**
 * Richard Kotermanski 
 * CS1632 Deliverable 2 
 * CoffeeMakerQuest - A game that is command driven allowing a player
 * 			to progress through linear rooms using doors
 * 			while collecting items that go into making 
 * 			coffee. To win, all items must be collected
 * 			before attempting to drink the coffee; otherwise,
 * 			you lose and the game still ends.
 */
import java.util.Scanner;

public class CoffeeMakerQuest {

	public static void main(String[] args) throws Exception {
		Player player = new Player();
		Map map = null;
		try {
			map = initializeMap();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		Game g = new Game(map, player);
		Scanner s = new Scanner(System.in);
		
		System.out.println(g.getInformation());
		
		// Main Game loop that finishes when game goes into
		// 		a winning or losing state
		while (g.getStatus() == GameStatus.IN_PROGRESS) {
			System.out.print("INSTRUCTIONS (N,S,L,I,D) >> ");
			String d = s.nextLine();
			System.out.println(g.progress(d));
		}
	}

	// Initializes and creates a map with predefined rooms and furniture that
	// 	has 5 rooms each with unique room names and furniture descriptions.
	// 	Items are dispersed at predefined room indexes throughout the map
	// 	that the player may acquire.
	public static Map initializeMap() throws Exception {
		Map m = new Map();
		// Set of unique roomAdjectives (room descriptions), furniture names, and furniture 
		// 	adjectives that are associated with one another by index.
		String[] rmAdj = { "Small", "Funny", "Refinanced", "Dumb", "Bloodthirsty", "Rough" };
		String[] furNm = { "sofa", "record player", "bed", "couch", "futon", "air hockey table" };
		String[] furAdj = { "Quaint", "Sad", "Bare", "Comfy", "Futuristic", "Perfect" };
		
		// Item locations by index: 
		//	{ coffee location index, cream location index, sugar location index }
		int[] itmLoc = { 0, 1, 3 }; 
		
		//Append each new room and its new furniture using the predefined attributes above
		int numRooms = rmAdj.length;
		for (int i = 0; i < numRooms; i++) {
			Furniture f = new Furniture(furAdj[i], furNm[i]);
			if (!m.appendRoom(new Room(rmAdj[i], f))) {
				System.out.println("Invalid room");
			}
		}
		
		// Set the rooms items to a new item based on the predefined location
		// 	and type given by itmLoc.
		int i = 0;
		Room[] rooms = m.getRooms();
		for (int r : itmLoc) {
			Room tmp = rooms[r];
			tmp.setItem(new Item("nifty", ItemType.values()[i % 3]));
			i++;
		}
		return m;
	}

}
