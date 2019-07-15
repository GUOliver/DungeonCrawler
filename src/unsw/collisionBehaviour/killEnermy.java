package unsw.collisionBehaviour;

import unsw.dungeon.*;

public class killEnermy implements CollisionBehaviour{

	@Override
	public void interact(Dungeon dungeon, Entity character, Entity enermy) {
		if (character instanceof Player) {
			Player player = (Player) character;
			
			if (player.isInvincible()) {
				dungeon.removeEntity(enermy);
				dungeon.setEnermyTotal(dungeon.getEnermyTotal() - 1);
			}
			else if (player.getSwordNum() > 0) {
				dungeon.removeEntity(enermy);
				dungeon.setEnermyTotal(dungeon.getEnermyTotal() - 1);
				player.addSwordNum(-1);
			} 
			
			// where if the enermy meet the bombs
			else {
				// the game finished
				dungeon.setGameState(true);
			}
		}
		
	}
	
}
