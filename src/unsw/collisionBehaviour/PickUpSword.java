package unsw.collisionBehaviour;
import unsw.dungeon.*;

public class PickUpSword implements CollisionBehaviour{
	
	
	/**
	 * Player picks up sword by adding sword uses to player, and removes sword from dungeon
	 */
	@Override  
	public void interact(Dungeon dungeon, Entity character, Entity sword) {
		// 
		if (character instanceof Player && sword instanceof Sword) {
			Player player = (Player) character;
			player.setSwordNum(5);
			dungeon.removeEntity(sword);	
		}
		
		
	}
	

}
