package unsw.dungeon;
import unsw.collisionBehaviour.*;
public class Boulder extends Entity{
	
	/**
	 * 
	 * @param x x coord
	 * @param y	y coord
	 */
	public Boulder(int x, int y) {
		super(x, y, "boulder");
		// TODO Auto-generated constructor stub
		setCollisionBehaviour(new MovingBoulder());
	}
	
	/**check if player can move onto or not
	 * dungeon, moving entity
	 */
	@Override
	public boolean canMoveOnto(Dungeon dungeon, Entity mover) {
		// nobody can move onto the boulder except when the player
		// pushing the boulder
		if (mover instanceof Player) {
			return true;
		}
		return false;
	}
}
