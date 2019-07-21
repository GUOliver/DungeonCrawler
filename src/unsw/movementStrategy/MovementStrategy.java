package unsw.movementStrategy;
import unsw.dungeon.*;
public interface MovementStrategy {

	/**
	 * Interface which defines different movement strategies for the enemy entity
	 * @param dungeon The current dungeon
	 * @param entity The entity which is trying to move according to the strategy
	 */
	public void moveDirection(Dungeon dungeon, MovingEntity entity);
}
