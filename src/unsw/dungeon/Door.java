package unsw.dungeon;

public class Door extends Entity {
	
	private int keyId;
	private int doorId;
	private static int counter;
	private boolean isOpen;
	
	public Door(int x, int y) {
		super(x, y, "door");
		setDoorId(++counter);
		// there will be no collision behaviour here 
		//setCanMoveOnto(false);
		this.isOpen = false;
	}

	public int getKeyId() {
		return keyId;
	}

	public void setKeyId(int keyId) {
		this.keyId = keyId;
	}

	public int getDoorId() {
		return doorId;
	}

	public void setDoorId(int doorId) {
		this.doorId = doorId;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void setIsOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	
	
	public void openDoor(Player player) {
		if (player.findKey(getKeyId())) {
			setIsOpen(true);
		} else {
			return;
		}
	}
	
	public boolean canMoveOnto(Dungeon dungeon, Entity mover) {
		// nobody can move onto the boulder except when the player
		// pushing the boulder
		if (!(mover instanceof Player)) {
			return false;
		}
		
		
		// if the door is closed, player can not walk through
		// if the door is open, player can wall through
		
		if (this.isOpen) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	
	 

}
