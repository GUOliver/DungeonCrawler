package unsw.dungeon;
import unsw.collisionBehaviour.*;

public class InvincibilityPotion extends Entity{
	
	/**
	 * 
	 * @param x coord
	 * @param y coord
	 */
	public InvincibilityPotion(int x, int y) {
		super(x, y, "Invincibility Potion");
		
		setCollisionBehaviour(new getInvincibilityPotion());
	}
	/**
	 * checking if potion can be moved onto or not
	 */
	@Override
	public boolean canMoveOnto(Dungeon dungeon, Entity mover) {
		return true;
	}
	
	



}
