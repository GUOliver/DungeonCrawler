package unsw.collisionBehaviour;

import unsw.dungeon.*;

public class CollectTreasure implements CollisionBehaviour {

	@Override
	public void interact(Dungeon dungeon, Entity character, Entity treasureEntity) {
		if (character instanceof Player && treasureEntity instanceof Treasure) {
			Player player = (Player) character;
			player.setTreasureCollected(player.getTreasureCollected() + 1);
			dungeon.removeEntity(treasureEntity);
			dungeon.setTreasureTotal(dungeon.getTreasureTotal() - 1);
		}

	}

}
