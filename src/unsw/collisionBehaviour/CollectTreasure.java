package unsw.collisionBehaviour;

import unsw.dungeon.*;

public class CollectTreasure implements CollisionBehaviour {
	
	/**
	 * Checks that what is trying to interact with Treasure is a Player
	 * If so, add treasure to player and remove from dungeon
	 */
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
