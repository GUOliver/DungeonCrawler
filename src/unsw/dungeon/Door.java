package unsw.dungeon;

import unsw.collisionBehaviour.DoorUnlock;

public class Door extends Entity {
	
	private int keyId;
	private boolean isOpen;
	
	public Door(Dungeon dungeon, int x, int y) {
		super(x, y, "locked door");
		setKeyId(dungeon.getNumDoors());
		setCollisionBehaviour(new DoorUnlock());
		this.isOpen = false;
	}

	public int getKeyId() {
		return keyId;
	}

	public void setKeyId(int keyId) {
		this.keyId = keyId;
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
			setType("unlocked door");
		} else {
			return;
		}
	}
	
	public boolean canMoveOnto(Dungeon dungeon, Entity mover) {
		// nobody can move onto the boulder except when the player
		// pushing the boulder
		if (this.isOpen()) {
			return true;
		} else if (mover instanceof Player) {
			Player player = (Player)mover;
			if (player.findKey(this.getKeyId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	
	
	 

}
