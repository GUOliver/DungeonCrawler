package unsw.dungeon;
import unsw.collisionBehaviour.*;
public class Treasure extends Entity {

	/**
	 * 
	 * @param x x coord
	 * @param y y coord
	 */
	public Treasure(int x, int y) {
		super(x, y, "treasure");
		setCollisionBehaviour(new CollectTreasure());
	}
	
	

	/**
	 * checking if it can be moved onto or not
	 */
	@Override
	public boolean canMoveOnto(Dungeon dungeon, Entity character) {
		return true;
	}


}
