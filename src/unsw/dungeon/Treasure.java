package unsw.dungeon;
import unsw.collisionBehaviour.*;
public class Treasure extends Entity {

	/**
	 * Constructor for treasure
	 * @param x x coord
	 * @param y y coord
	 */
	public Treasure(int x, int y) {
		super(x, y, "treasure");
		setCollisionBehaviour(new CollectTreasure());
	}
	
	

	/**
	 * Any entity can move onto treasure block
	 */
	@Override
	public boolean canMoveOnto(Dungeon dungeon, Entity character) {
		return true;
	}


}
