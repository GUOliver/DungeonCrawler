package unsw.dungeon;

import unsw.collisionBehaviour.*;
public class Sword extends Entity {
	/**
	 * Constructor for sword
	 * @param x x coord
	 * @param y y coord
	 */
	public Sword(int x, int y) {
		super(x, y, "sword");
		setCollisionBehaviour(new PickUpSword());
	}
	
	/**
	 * Any entity can move onto sword block
	 */
	@Override
	public boolean canMoveOnto(Dungeon dungeon, Entity mover) {
		return true;
	}

}
