/**
 * Richard Kotermanski 
 * CS1632 Deliverable 2 
 * Game - 	The entity of the quest
 * 			that maintains the player position on the map, controls the game status,
 * 			and progresses (moving, looking, drinking, and helping) 
 * 			the player given a set of valid commands while providing meaningful
 * 			and formatted text. 
 */
public class Game {

	private Map map;
	private Room currentRoom;
	private GameStatus status;
	private Player player;

	public Game(Map m, Player p) {
		map = m;
		currentRoom = map.getStartingRoom();
		status = GameStatus.IN_PROGRESS;
		player = p;
	}
	
	
	// Given a command string, cmd, the game will maintain state of the game
	// 		for the appropriate command (n, N, s, S, l, L, d, D, h, H)
	//		and build a response string that describes the updated state
	//		including the necessary information on the player, map, 
	//		current room, game status, and room attributes such as items. 
	public String progress(String cmd) {
		StringBuilder result = new StringBuilder("\n----------\n");
		if (cmd != null) {
			cmd = cmd.toLowerCase();
			if (cmd.equals("n")) {
				if (!moveNorth()) {
					result.append("You cannot go that way!\n");
				}
			} else if (cmd.equals("s")) {
				if (!moveSouth()) {
					result.append("You cannot go that way!\n");
				}
			} else if (cmd.equals("l")) {
				if (look()) {
					result.append("You see a "+ currentRoom.getItem().getDescription() + " "
							+ currentRoom.getItem().getType().toString() + "!\n");
				} else {
					result.append("You see nothing.\n");
				}
			} else if (cmd.equals("i")) {
				result.append(getInventory());
			} else if (cmd.equals("h")) {
				result.append(getHelpInfo());
			} else if (cmd.equals("d")) {
				result.append(getInventory());
				if(drink()){
					result.append("You Win!\n");
				} else {
					result.append("You Lose.\n");
				}
				return result.toString();
			} else {
				result.append("What?\n");
			}
		} else {
			result.append("What?\n");
		}
		result.append(getInformation());

		return result.toString();
	}

	
	// If a room exists to the North of the currentRoom, then
	// 		a door exists, and the currentRoom will be changed to
	//		said, north room and true will be returned. 
	//		Otherwise, return false.
	private boolean moveNorth() {
		if (currentRoom != null && currentRoom.getNorth() != null) {
			currentRoom = currentRoom.getNorth();
			return true;
		}
		return false;
	}

	
	// If a room exists to the South of the currentRoom, then
	// 		a door exists, and the currentRoom will be changed to
	//		said, South room and true will be returned. 
	//		Otherwise, return false.
	private boolean moveSouth() {
		if (currentRoom != null && currentRoom.getSouth() != null) {
			currentRoom = currentRoom.getSouth();
			return true;
		}
		return false;
	}
	
	
	// Depending on the player's drink's return,
	//		the status changes to GameStatus.WIN and returns
	//		true if drink is true and status changes to 
	//		GameStatus.LOSE and returns false if drink is false
	private boolean drink(){
		if (player.drink()) {
			status = GameStatus.WIN;
			return true;
		} else {
			status = GameStatus.LOSE;
			return false;
		}
	}
	
	
	// If an item exists in the currentRoom (not null)
	//		then have the player acquire the item given
	//		said item's type and return that functions
	//		boolean return value upon success/failure. 
	//		Otherwise, it simply returns false.
	private boolean look() {
		Item i = currentRoom.getItem();
		if (i == null)
			return false;
		return player.acquireItem(i.getType());
	}

	
	// Given the inventory returned for the player,
	//		a result string is built that is 
	//		properly formatted for display and
	//		includes whether or not the player has
	//		each item: 
	//		You have <item type>! or You do not have <item type>.
	private String getInventory() {
		StringBuilder result = new StringBuilder();
		boolean[] inv = player.getInventory();

		if (inv[0]) {
			result.append("You have coffee!\n");
		} else {
			result.append("You do not have coffee.\n");
		}

		if (inv[1]) {
			result.append("You have cream!\n");
		} else {
			result.append("You do not have cream.\n");
		}

		if (inv[2]) {
			result.append("You have sugar!\n");
		} else {
			result.append("You do not have sugar.\n");
		}

		return result.append("\n").toString();
	}

	
	//	Builds and returns a formatted string that contains each command and
	//		its description for use.
	public String getHelpInfo() {
		StringBuilder result = new StringBuilder();
		result.append("To play the game, enter one of the following commands each iteration: \n");
		result.append("\td,D\t-- Drink the combination of the items that the player currently has in their inventory.\n\t\t   "
						+ "This command will end the game, and the player will either win (has coffee, cream, and sugar) or lose.\n\n");
		result.append("\tn,N\t-- Attempts to move the player north, through a door, to another room.\n\n");
		result.append("\ts,S\t-- Attempts to move the player south, through a door, to another room.\n\n");
		result.append("\tl,L\t-- Looks around the room the player is currently in, and adds any found items to the player's inventory.\n\n");
		result.append("\ti,I\t-- Displays the contents/status of the player's current inventory.\n\n");
		result.append("\th,H\t-- Shows a listing of possible commands and what their effects are (what you are looking at now).\n\n");
		return result.toString();
	}

	public Room getCurrentRoom() {
		return currentRoom;
	}

	public GameStatus getStatus() {
		return status;
	}

	// Builds and returns a formatted string describing the current state
	//		of the game involving the Player, the currentRoom, and its furniture.
	public String getInformation() {
		StringBuilder result = new StringBuilder();
		result.append("\nYou are in the " + currentRoom.getDescription() + " room.\n");
		result.append("\nThere is a " + currentRoom.getFurniture().getDescription() + " "
				+ currentRoom.getFurniture().getName() + " in the room.\n");
		if (currentRoom.getNorth() != null)
			result.append("There is a " + currentRoom.getNorth().getDescription() + " door to the North.\n");
		if (currentRoom.getSouth() != null)
			result.append("There is a " + currentRoom.getSouth().getDescription() + " door to the South.\n");
		return result.toString();
	}
}
