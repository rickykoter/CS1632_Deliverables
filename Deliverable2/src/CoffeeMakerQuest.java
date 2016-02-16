/**
 * Richard Kotermanski 
 * CS1632 Deliverable 2 
 * CoffeeMakerQuest - A game that is command driven allowing a player
 * 						to progress through linear rooms using doors
 * 						while collecting items that go into making 
 * 						coffee. To win, all items must be collected
 * 						before attempting to drink the coffee; otherwise,
 * 						you lose and the game still ends.
 */
import java.util.Scanner;

public class CoffeeMakerQuest {

	public static void main(String[] args) {
		Player player = new Player();
		Map map = null;
		try {
			map = initializeMap();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Game g = new Game(map, player);
		Scanner s = new Scanner(System.in);
		System.out.println(g.getInformation());
		while (g.getStatus() == GameStatus.IN_PROGRESS) {
			System.out.print(">> ");
			String d = s.nextLine();
			System.out.println(g.progress(d));
		}
	}

	public static Map initializeMap() throws Exception {
		Map m = new Map();
		String[] rmAdj = { "Small", "Funny", "Rigid", "Big", "Northern" };
		String[] furNm = { "desk", "armoire", "bed", "couch", "futon" };
		String[] furAdj = { "decrepit", "Armenian", "bare", "comfy", "futuristic" };
		int[] itmLoc = { 0, 1, 3 };
		int numRooms = rmAdj.length;
		for (int i = 0; i < numRooms; i++) {
			Furniture f = new Furniture(furAdj[i], furNm[i]);
			if (!m.appendRoom(new Room(rmAdj[i], f))) {
				System.out.println("Invalid room");
			}
		}
		int i = 0;
		Room[] rooms = m.getRooms();
		for (int r : itmLoc) {
			Room tmp = rooms[r];
			tmp.setItem(new Item("", ItemType.values()[i % 3]));
			i++;
		}
		return m;
	}

}
