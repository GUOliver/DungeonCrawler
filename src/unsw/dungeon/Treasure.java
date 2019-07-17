package unsw.dungeon;
import unsw.collisionBehaviour.*;
public class Treasure extends Entity {

	
	public Treasure(int x, int y, String type) {
		super(x, y, "treasure");
		setCollisionBehaviour(new CollectTreasure());
	}

}
