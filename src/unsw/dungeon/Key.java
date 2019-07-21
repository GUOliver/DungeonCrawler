package unsw.dungeon;
import unsw.collisionBehaviour.*;
public class Key extends Entity{
	
	private int keyID;
	
	 /* 
	 * @param x coord
	 * @param y coord
	 */
	public Key(Dungeon dungeon, int x, int y) {
		super(x, y, "key");
		setKeyID(dungeon.getNumKeys());
		setCollisionBehaviour(new PickUpKey());
	}
	
	/**
	 * checking if key can be moved onto or not
	 */
	@Override
	public boolean canMoveOnto(Dungeon dungeon, Entity mover) {
		return true;
	}
	
	/**
	 * set the key id 
	 * @param keyID the id 
	 */
	public void setKeyID(int keyID) {
		this.keyID = keyID;
	}
	
	/**
	 * get the key id
	 * @return the key id
	 */
	public int getKeyID() {
		return keyID;
	}

}
