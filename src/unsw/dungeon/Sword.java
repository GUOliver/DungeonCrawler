package unsw.dungeon;

import unsw.collisionBehaviour.*;
public class Sword extends Entity {
	/**
	 * 
	 * @param x x coord
	 * @param y y coord
	 */
	public Sword(int x, int y) {
		super(x, y, "sword");
		setCollisionBehaviour(new PickUpSword());
		//setCanMoveOnto(true); // anything can move onto the sword
	}
	
	/**
	 * checking if it can be moved onto or not
	 */
	@Override
	public boolean canMoveOnto(Dungeon dungeon, Entity mover) {
		return true;
	}

}
