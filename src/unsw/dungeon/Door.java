package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import unsw.collisionBehaviour.*;

public class Door extends Entity {
	
	private int keyId;
	private BooleanProperty isOpen;
	
	/**
	 * This class is a locked door, which requires a key of matching ID
	 * @param dungeon the dungeon
	 * @param x the x coord
	 * @param y the y coord
	 */
	public Door(Dungeon dungeon, int x, int y) {
		super(x, y, "locked door");
		setKeyId(dungeon.getNumDoors());
		setCollisionBehaviour(new DoorUnlock());
		this.isOpen = new SimpleBooleanProperty(false);
	}
	
	/**
	 * get the key Id
	 * @return the keyId
	 */
	public int getKeyId() {
		return keyId;
	}
	
	/**
	 * set the keyID
	 * @param keyId
	 */
	public void setKeyId(int keyId) {
		this.keyId = keyId;
	}
	
	/**
	 * check if the door is open 
	 * @return true or false
	 */
	public boolean isOpen() {
		return isOpen.get();
	}
	
	/**
	 * check if the door is open 
	 * @return true or false
	 */
	public BooleanProperty isOpenProperty() {
		return isOpen;
	}
	
	/**
	 * set the state of the door
	 * @param isOpen
	 */
	public void setIsOpen(boolean isOpen) {
		this.isOpen.set(isOpen);
	}
	
	/**
	 * open the door
	 * @param player the player
	 */
	public void openDoor(Player player) {
		if (player.findKey(getKeyId())) {
			setIsOpen(true);
			setType("unlocked door");
		} else {
			return;
		}
	}
	
	/**
	 * checking if door can be move into
	 */
	@Override
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
