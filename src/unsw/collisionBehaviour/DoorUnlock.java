package unsw.collisionBehaviour;

import unsw.dungeon.*;

public class DoorUnlock implements CollisionBehaviour {

	@Override
	public void interact(Dungeon dungeon, Entity player, Entity door) {
		if (player instanceof Player && door instanceof Door) {
			Player p = (Player)player;
			Door d = (Door)door;
			p.removeKeys(d.getKeyId());
			d.setIsOpen(true);
		}
	}

}
