package unsw.dungeon;

import unsw.movementStrategy.MovementStrategy;
import unsw.movementStrategy.MovingToPlayer;

public class Enemy extends MovingEntity {
	MovementStrategy howMove;
	
	public Enemy(int x, int y, String name) {
		super(x, y, name);
		this.howMove = new MovingToPlayer();
	}
	
	/**
	 * This method moves the enemy according to the strategy it holds
	 */
	public void moveEnemy(Dungeon dungeon) {
		howMove.moveDirection(dungeon, this);
	}
}
