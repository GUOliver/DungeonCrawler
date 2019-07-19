package unsw.movementStrategy;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.MovingEntity;

public class MovingAwayPlayer implements MovementStrategy {

	@Override
	public void moveDirection(Dungeon dungeon, MovingEntity entity) {
		if (entity.isEnemy()) {
			Enemy enemy = (Enemy)entity;
			System.out.println("Player is at: " + enemy.getPlayerX() + ", " + enemy.getPlayerY());
			System.out.println("Enemy is at: " + enemy.getX() + ", " + enemy.getY());
			
			if (enemy.getX()<enemy.getPlayerX()) {
				enemy.moveLeft(dungeon);
				System.out.println("Enemy moves right");
			} else if (enemy.getX()>enemy.getPlayerX()) {
				enemy.moveRight(dungeon);
				System.out.println("Enemy moves left");
			} else if (enemy.getY()<enemy.getPlayerY()) {
				enemy.moveUp(dungeon);
				System.out.println("Enemy moves down");
			} else if (enemy.getY()>enemy.getPlayerY()) {
				enemy.moveDown(dungeon);
				System.out.println("Enemy moves up");
			}
		}
	}

}
