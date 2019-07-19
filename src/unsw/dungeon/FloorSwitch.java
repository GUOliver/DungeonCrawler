package unsw.dungeon;
import unsw.collisionBehaviour.*;
public class FloorSwitch extends Entity{
	
	private boolean switchState;
	
	public FloorSwitch(int x, int y) {
		super(x, y, "floor switch");
		this.switchState = false;
		setCollisionBehaviour(new interactWithSwitch());
	}
	
	public boolean canMoveOnto(Dungeon dungeon, Entity mover) {
		return true;
	}
	
	
	public boolean isActivated() {
		return switchState;
	}
	
	// true means activated
	// false means not-activated
	public void setSwitchState(boolean state) {
		this.switchState = state;
	}

}
	