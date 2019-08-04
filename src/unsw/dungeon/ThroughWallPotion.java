package unsw.dungeon;
import unsw.collisionBehaviour.*;

public class ThroughWallPotion extends Entity{
	
	/**
	 * Constructor for invincibility potion
	 * @param x coord
	 * @param y coord
	 */
	public ThroughWallPotion(int x, int y) {
		super(x, y, "hover potion");
		
		setCollisionBehaviour(new getThroughWallPotion());
	}
	/**
	 * Any moving entity can move onto same position as potion
	 */
	@Override
	public boolean canMoveOnto(Dungeon dungeon, Entity mover) {
		return true;
	}
	
	



}