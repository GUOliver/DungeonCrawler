package unsw.collisionBehaviour;

import unsw.dungeon.*;

public class MovingBoulder implements CollisionBehaviour {

	/**
	 * Moves boulder if player, but ensure boulder can move to where its directed to
	 */
	@Override
	public void interact(Dungeon dungeon, Entity character, Entity boulderEntity) {
		// if not a player, no collision happens
		if (! (character instanceof Player)) {
			return; 
		}
		
		Player player = (Player) character;
		
		// get the moving boulder coordinates
		int boulderX = 0;
		int boulderY = 0;
		if (player.getDirection() == Direction.Up) {
			boulderX = character.getX();
			boulderY = character.getY() - 1;
			
		} else if (player.getDirection() == Direction.Down) {
			boulderX = character.getX();
			boulderY = character.getY() + 1;
			
		} else if (player.getDirection() == Direction.Left) {
			boulderX = character.getX() - 1;
			boulderY = character.getY();
			
		} else if (player.getDirection() == Direction.Right) {
			boulderX = character.getX() + 1;
			boulderY = character.getY();
			
		} else {
			return;
		}
		
		// the boulder can not be out of the range of the dungeon
		if (isOutOfRange(boulderX, boulderY, dungeon)) {
			return;
		}
		
		
		// set the old position boulder to new place
		Boulder boulder = (Boulder) boulderEntity;
		boulder.setX(boulderX);
		boulder.setY(boulderY);
		
	}
	
	public boolean isOutOfRange(int x, int y, Dungeon dungeon) {
		if (x < 0 || x > dungeon.getWidth() || y < 0 || y > dungeon.getHeight()) {
			return true;
		}
		return false;
	}

}
