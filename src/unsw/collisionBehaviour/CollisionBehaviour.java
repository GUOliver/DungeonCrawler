package unsw.collisionBehaviour;
import unsw.dungeon.*;

public interface CollisionBehaviour {
	/**
	 * wheh an Entity collide with another entity
	 * @param dungeon
	 * @param e1
	 * @param e2
	 */
	public void interact(Dungeon dungeon, Entity e1, Entity e2);
	
}
