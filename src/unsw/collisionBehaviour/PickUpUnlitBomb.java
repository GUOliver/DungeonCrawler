package unsw.collisionBehaviour;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.*;

public class PickUpUnlitBomb implements CollisionBehaviour {

	@Override
	public void interact(Dungeon dungeon, Entity character, Entity it) {
		if (character instanceof Player) {
			Player player = (Player) character;
			player.addBombNum(1);
			dungeon.removeEntity(it);
		}
		
	}

}
