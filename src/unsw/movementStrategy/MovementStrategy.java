package unsw.movementStrategy;
import unsw.dungeon.*;
public interface MovementStrategy {
	/*
	 * Interface which defines different movement strategies for the enemy entity
	 */
	public void moveDirection(Dungeon dungeon, MovingEntity entity);
}
