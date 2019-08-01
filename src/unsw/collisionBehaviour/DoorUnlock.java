package unsw.collisionBehaviour;

import unsw.dungeon.*;

public class DoorUnlock implements CollisionBehaviour {

	/**
	 * This interaction is called after already checking player has correct key
	 * This interact sets door to open and removes key from player
	 */
	@Override
	public void interact(Dungeon dungeon, Entity player, Entity door) {
		if (player instanceof Player && door instanceof Door) {
			Player p = (Player)player;
			Door d = (Door)door;
			if (!d.isOpen()) {
				p.removeKeys(d.getKeyId());
				d.setIsOpen(true);
			}
		}
	}

}
