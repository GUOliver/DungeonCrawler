package unsw.collisionBehaviour;
import unsw.dungeon.*;

public interface CollisionBehaviour {
	/**
	 * wheh an Entity collides with another entity
	 * @param dungeon the dungeon
	 * @param movingEntity the moving entity
	 * @param entity the item
	 */
	public void interact(Dungeon dungeon, Entity movingEntity, Entity entity);
	
}
