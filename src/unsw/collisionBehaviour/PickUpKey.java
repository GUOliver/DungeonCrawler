package unsw.collisionBehaviour;

import unsw.dungeon.*;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;

public class PickUpKey implements CollisionBehaviour {

	@Override
	public void interact(Dungeon dungeon, Entity character, Entity keyEntity) {
		if (character instanceof Player && keyEntity instanceof Key) {
			Player player = (Player) character;
			Key key = (Key) keyEntity;
			
			dungeon.removeEntity(keyEntity);
			player.addKeys(key.getKeyID());
		}

	}

}
