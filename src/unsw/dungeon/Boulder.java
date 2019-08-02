package unsw.dungeon;
import unsw.collisionBehaviour.*;
public class Boulder extends Entity{
	
	/**
	 * Boulder constructor
	 * @param x x coordinate
	 * @param y	y coordinate
	 */
	public Boulder(int x, int y) {
		super(x, y, "boulder");
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
