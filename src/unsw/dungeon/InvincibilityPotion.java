package unsw.dungeon;
import unsw.collisionBehaviour.*;

public class InvincibilityPotion extends Entity{

	public InvincibilityPotion(int x, int y) {
		super(x, y, "Invincibility Potion");
		
		setCollisionBehaviour(new getInvincibilityPotion());
	}
	
	public boolean canMoveOnto(Dungeon dungeon, Entity mover) {
		return true;
	}
	
	



}
