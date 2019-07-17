package unsw.collisionBehaviour;

import unsw.dungeon.*;

public class CollisionWithPlayer implements CollisionBehaviour{

	@Override
	public void interact(Dungeon dungeon, Entity character, Entity e2) {
		// TODO Auto-generated method stub
		if (!character.isPlayer()) {
			return;
		}
		
		Player player = (Player) character;
		
		if (e2 instanceof Enemy) {
			if (player.isInvincible()) {
				dungeon.removeEntity(e2);
				dungeon.setEnemyTotal(dungeon.getEnemyTotal() - 1);
			} else if (player.getSwordNum() > 0) {
				dungeon.removeEntity(e2);
				player.addSwordNum(-1);
				dungeon.setEnemyTotal(dungeon.getEnemyTotal() - 1);
			} 
			
			// what else
		}
	}

}
