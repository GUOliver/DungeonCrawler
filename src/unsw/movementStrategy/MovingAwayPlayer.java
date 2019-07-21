package unsw.movementStrategy;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.MovingEntity;

public class MovingAwayPlayer implements MovementStrategy {

	/**
	 * Defining behaviour for an enemy moving away from player position
	 * Extra entities can be added, does not assume entity is enemy
	 */
	@Override
	public void moveDirection(Dungeon dungeon, MovingEntity entity) {
		if (entity.isEnemy()) {
			Enemy enemy = (Enemy)entity;
			
			if (enemy.getX()<enemy.getPlayerX()) {
				enemy.moveLeft(dungeon);
			} else if (enemy.getX()>enemy.getPlayerX()) {
				enemy.moveRight(dungeon);
			} else if (enemy.getY()<enemy.getPlayerY()) {
				enemy.moveUp(dungeon);
			} else if (enemy.getY()>enemy.getPlayerY()) {
				enemy.moveDown(dungeon);
			}
		}
	}

}
