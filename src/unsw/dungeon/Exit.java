package unsw.dungeon;
import unsw.collisionBehaviour.*;
public class Exit extends Entity{
	
	/**
	 * 
	 * @param x x coord
	 * @param y y coord
	 */
	public Exit(int x, int y) {
		super(x, y, "exit");
		setCollisionBehaviour(new ReachedExit());
	}
	
	
	
	/**
	 * checking if exit can be moved onto or not
	 */
	@Override
	public boolean canMoveOnto(Dungeon dungeon, Entity mover) {
		if (!(mover instanceof Player)) {
			return false;
		}
		
		return true;
	}

}
