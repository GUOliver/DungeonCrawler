package unsw.collisionBehaviour;

import unsw.dungeon.*;
import unsw.playerState.InvincibleState;

public class getInvincibilityPotion implements CollisionBehaviour{

	@Override
	public void interact(Dungeon dungeon, Entity character, Entity potionEntity) {
		// TODO Auto-generated method stub
		if (character.isPlayer()) {
			Player player = (Player) character;
			player.setInvincibleTime(21);
			player.setPlayerState(new InvincibleState());
			dungeon.removeEntity(potionEntity);
		}
	}

}
