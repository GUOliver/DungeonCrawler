package unsw.dungeon;

import unsw.collisionBehaviour.*;
public class Sword extends Entity {

	public Sword(int x, int y) {
		super(x, y, "sword");
		setCollisionBehaviour(new PickUpSword());
	}
	
}
