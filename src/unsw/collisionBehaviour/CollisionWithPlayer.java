package unsw.collisionBehaviour;

import unsw.dungeon.*;

public class CollisionWithPlayer implements CollisionBehaviour{

	/**
	 * If enemy collides with player, check if player has sword or is invincible
	 * If not, game ends.
	 */
	@Override
	public void interact(Dungeon dungeon, Entity character, Entity e2) {
		if (character.isPlayer()) {
			Player player = (Player) character;

			if (e2 instanceof Enemy) {
				Enemy enemy = (Enemy)e2;
				if (player.isInvincible()) {
					dungeon.removeEntity(e2);
					dungeon.setEnemyTotal(dungeon.getEnemyTotal() - 1);
					player.removeObserver(enemy);
				} else if (player.getSwordNum() > 0) {
					dungeon.removeEntity(e2);
					player.addSwordNum(-1);
					dungeon.setEnemyTotal(dungeon.getEnemyTotal() - 1);
					player.removeObserver(enemy);
				} else {
					dungeon.setGameState(true);
				}
			}
		}
	}

}
