package unsw.dungeon;
import unsw.collisionBehaviour.*;
public class Key extends Entity{
	
	private int keyID;
	private int counter;
	
	public Key(int x, int y) {
		super(x, y, "key");
		setKeyID(counter++);
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
