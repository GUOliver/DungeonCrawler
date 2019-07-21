package unsw.collisionBehaviour;

import unsw.dungeon.*;

public class ReachedExit implements CollisionBehaviour{

	/**
	 * When playe reaches exit, the reachexit goal is complete
	 */
	@Override
	public void interact(Dungeon dungeon, Entity character, Entity exitEntity) {
		if (character instanceof Player) {
			dungeon.setReachExit(true);
		}
		
	}

}
