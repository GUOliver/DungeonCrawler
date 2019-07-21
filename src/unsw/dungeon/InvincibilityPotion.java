package unsw.dungeon;
import unsw.collisionBehaviour.*;

public class InvincibilityPotion extends Entity{
	
	/**
	 * Constructor for invincibility potion
	 * @param x coord
	 * @param y coord
	 */
	public InvincibilityPotion(int x, int y) {
		super(x, y, "Invincibility Potion");
		
		setCollisionBehaviour(new getInvincibilityPotion());
	}
	/**
	 * Any moving entity can move onto same position as potion
	 */
	@Override
	public boolean canMoveOnto(Dungeon dungeon, Entity mover) {
		return true;
	}
	
	



}
