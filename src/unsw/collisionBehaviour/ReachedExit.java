package unsw.collisionBehaviour;

import unsw.dungeon.*;

public class ReachedExit implements CollisionBehaviour{

	@Override
	public void interact(Dungeon dungeon, Entity character, Entity exitEntity) {
		if (character instanceof Player) {
			dungeon.setReachExit(true);
		}
		
	}

}
