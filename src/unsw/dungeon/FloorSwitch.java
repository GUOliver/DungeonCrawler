package unsw.dungeon;

public class FloorSwitch extends Entity{
	
	private boolean switchState;
	
	public FloorSwitch(int x, int y) {
		super(x, y, "floor switch");
		this.switchState = false;
		// should every entity has its own collision behaviour ?
		setCollisionBehaviour(new CollideWithFloorSwitch());
		
	}
	
	public boolean isActivated() {
		return switchState;
	}
	
	
	public void setSwitchState(boolean state) {
		this.switchState = state;
	}

}
	