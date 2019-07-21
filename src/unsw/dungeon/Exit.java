package unsw.dungeon;
import unsw.collisionBehaviour.*;
public class Exit extends Entity{
	
	/**
	 * Constructor for an exit
	 * @param x x coord
	 * @param y y coord
	 */
	public Exit(int x, int y) {
		super(x, y, "exit");
		setCollisionBehaviour(new ReachedExit());
	}
	
	
	
	/**
	 * Only players can move onto and interact with the exit
	 */
	@Override
	public boolean canMoveOnto(Dungeon dungeon, Entity mover) {
		if (!(mover instanceof Player)) {
			return false;
		}
		
		return true;
	}

}
