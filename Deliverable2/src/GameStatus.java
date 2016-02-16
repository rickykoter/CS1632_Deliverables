/**
 * Richard Kotermanski 
 * CS1632 Deliverable 2 
 * GameStatus - an enum containing values describing the current game state
 */
public enum GameStatus {
	IN_PROGRESS,
	WIN,
	LOSE;
    public String toString() {
    	String res = new String();
    	for(String s : this.name().toLowerCase().split("_")){
    		res += s + " ";
    	}
    	return res.trim();
	}
}
