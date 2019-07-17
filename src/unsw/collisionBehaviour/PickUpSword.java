package unsw.collisionBehaviour;
import unsw.dungeon.*;

public class PickUpSword implements CollisionBehaviour{
	
	
	@Override  
	// apply strategy pattern (or state pattern ?) 
	public void interact(Dungeon dungeon, Entity character, Entity sword) {
		// player picked up the sword
		// Only one sword can be carried at once. 
		// Each sword is only capable of 5 hits 
		// and disappears after that. One hit of 
		// the sword is sufficient to destroy any enemy.
		if (character instanceof Player && sword instanceof Sword) {
			Player player = (Player) character;
			
			// means five hits not five swords!
			player.addSwordNum(5);
			
			// if e2 is of type sword
			// remove this instance from the dungeon
			dungeon.removeEntity(sword);
			
		}
		
		
	}
	

}
