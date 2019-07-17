package unsw.movementStrategy;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.MovingEntity;

public class MovingToPlayer implements MovementStrategy{
	
	/**
	 * Does not assume that what is moving to player is entity to allow future additions
	 */
	@Override
	public void moveDirection(Dungeon dungeon, MovingEntity entity) {
		if (entity.getClass()==Enemy.class) {
			Enemy enemy = (Enemy)entity;
			if (enemy.getX()<enemy.getPlayerX()) {
				enemy.moveRight(dungeon);
			} else if (enemy.getX()>enemy.getPlayerX()) {
				enemy.moveLeft(dungeon);
			} else if (enemy.getY()>enemy.getPlayerY()) {
				enemy.moveDown(dungeon);
			} else if (enemy.getY()>enemy.getPlayerY()) {
				enemy.moveUp(dungeon);
			}
		}
	}
	
}
