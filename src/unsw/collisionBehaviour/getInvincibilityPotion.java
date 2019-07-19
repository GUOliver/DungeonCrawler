package unsw.collisionBehaviour;

import unsw.dungeon.*;

public class getInvincibilityPotion implements CollisionBehaviour{

	@Override
	public void interact(Dungeon dungeon, Entity character, Entity potionEntity) {
		// TODO Auto-generated method stub
		if (character.isPlayer()) {
			Player player = (Player) character;
			player.setInvincibleTime(20);
			dungeon.removeEntity(potionEntity);
		}
	}

}
