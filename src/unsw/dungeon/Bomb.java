package unsw.dungeon;
import unsw.collisionBehaviour.*;
public class Bomb extends Entity{

	public Bomb(int x, int y, String type) {
		super(x, y, "bomb");
		setCollisionBehaviour(new PickUpUnlitBomb());
	}
	
}
