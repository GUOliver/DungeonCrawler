package unsw.collisionBehaviour;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.*;

public class PickUpUnlitBomb implements CollisionBehaviour {

	/**
	 * Player picks up an unlit bomb, which is removed from dungeon
	 */
	@Override
	public void interact(Dungeon dungeon, Entity character, Entity it) {
		if (character instanceof Player) {
			Player player = (Player) character;
			Bomb bomb = (Bomb) it;
			player.addBomb(bomb);
			dungeon.removeEntity(it);
		}
		
	}

}
