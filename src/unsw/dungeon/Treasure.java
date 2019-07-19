package unsw.dungeon;
import unsw.collisionBehaviour.*;
public class Treasure extends Entity {

	
	public Treasure(int x, int y) {
		super(x, y, "treasure");
		setCollisionBehaviour(new CollectTreasure());
	}

	@Override
	public boolean canMoveOnto(Dungeon dungeon, Entity character) {
		return true;
	}


}
