package unsw.dungeon;
import unsw.collisionBehaviour.*;
public class FloorSwitch extends Entity{
	
	private boolean switchState;
	
	/**
	 * Constructor for a boulder activated floor switch
	 * @param x coord
	 * @param y coord
	 */
	public FloorSwitch(int x, int y) {
		super(x, y, "switch");
		this.switchState = false;
		setCollisionBehaviour(new interactWithSwitch());
	}
	
	/**
	 * checking if switch can be moved onto or not
	 */
	@Override
	public boolean canMoveOnto(Dungeon dungeon, Entity mover) {
		return true;
	}
	

	/**
	 * Checks if switch is already activated
	 * @return boolean true or false
	 */
	public boolean isActivated() {
		return switchState;
	}
	
	
	/**
	 * Sets the state of the switch
	 * @param state boolean true or false
	 */
	public void setSwitchState(boolean state) {
		this.switchState = state;
	}


}
	