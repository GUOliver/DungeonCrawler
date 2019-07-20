package unsw.dungeon;
import unsw.collisionBehaviour.*;
public class Key extends Entity{
	
	private int keyID;
	
	public Key(Dungeon dungeon, int x, int y) {
		super(x, y, "key");
		setKeyID(dungeon.getNumKeys());
		setCollisionBehaviour(new PickUpKey());
	}
	
	public boolean canMoveOnto(Dungeon dungeon, Entity mover) {
		return true;
	}
	
	public void setKeyID(int keyID) {
		this.keyID = keyID;
	}
	
	
	public int getKeyID() {
		return keyID;
	}

}
