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

	private boolean moveNorth() {
		if (currentRoom != null && currentRoom.getNorth() != null) {
			currentRoom = currentRoom.getNorth();
			return true;
		}
		return false;
	}

	private boolean moveSouth() {
		if (currentRoom != null && currentRoom.getSouth() != null) {
			currentRoom = currentRoom.getSouth();
			return true;
		}
		return false;
	}
	
	private boolean drink(){
		if (player.drink()) {
			status = GameStatus.WIN;
			return true;
		} else {
			status = GameStatus.LOSE;
			return false;
		}
	}
	
	private boolean look() {
		Item i = currentRoom.getItem();
		if (i == null)
			return false;
		return player.acquireItem(i.getType());
	}

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
