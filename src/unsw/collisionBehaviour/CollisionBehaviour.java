package unsw.collisionBehaviour;
import unsw.dungeon.*;

public interface CollisionBehaviour {
	/**
	 * Defines behaviour when an Entity collides with another entity
	 * @param dungeon the dungeon
	 * @param movingEntity the entity which is moving into another entity
	 * @param entity the entity which is being interacted with, and holds the behaviour
	 */
	public void interact(Dungeon dungeon, Entity movingEntity, Entity entity);
	
}
