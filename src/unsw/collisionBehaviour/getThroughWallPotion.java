package unsw.collisionBehaviour;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.Player;

public class getThroughWallPotion implements CollisionBehaviour{

	@Override
	public void interact(Dungeon dungeon, Entity movingEntity, Entity entity) {
		// TODO Auto-generated method stub
		if (movingEntity.isPlayer()) {
			
			Player player = (Player) movingEntity;
			player.setThroughWallTime(10);
			dungeon.removeEntity(entity);
		}
		
	}

}
