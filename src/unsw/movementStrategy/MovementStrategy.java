package unsw.movementStrategy;
import unsw.dungeon.*;
public interface MovementStrategy {
	
	public void moveDirection(Dungeon dungeon, MovingEntity enemyEntity);
}
