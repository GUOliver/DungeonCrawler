package unsw.dungeon;
import unsw.collisionBehaviour.*;
public class Exit extends Entity{

	public Exit(int x, int y) {
		super(x, y, "exit");
		setCollisionBehaviour(new ReachedExit());
	}
	


	public boolean canMoveOnto(Dungeon dungeon, Entity mover) {
		if (!(mover instanceof Player)) {
			return false;
		}
		
		return true;
	}

}
