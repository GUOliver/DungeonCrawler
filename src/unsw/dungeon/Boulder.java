package unsw.dungeon;
import unsw.collisionBehaviour.*;
public class Boulder extends Entity{

	public Boulder(int x, int y) {
		super(x, y, "boulder");
		// TODO Auto-generated constructor stub
		setCollisionBehaviour(new MovingBoulder());
	}

	

	

}
