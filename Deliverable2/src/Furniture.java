/**
 * Richard Kotermanski 
 * CS1632 Deliverable 2 
 * Furniture - 	An entity of the quest that has a non-empty, non-null
 * 				name and description.
 */
public class Furniture {
	private String description;
	private String name;

	public Furniture(String desc, String nm) throws Exception {
		if (desc == null || nm == null || desc.isEmpty() || nm.isEmpty()){
			throw new Exception("Name and Description must not be empty.");
		}
		description = desc;
		name = nm;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public boolean setDescription(String desc) {
		if (desc == null || desc.isEmpty()){
			return false;
		}
		description = desc;
		return true;
	}
}
