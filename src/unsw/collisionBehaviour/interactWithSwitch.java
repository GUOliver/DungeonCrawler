package unsw.collisionBehaviour;

import unsw.dungeon.*;


public class interactWithSwitch implements CollisionBehaviour{

	/**
	 * When boulder is moved onto a switch, sets switch state and adds 
	 */
	@Override
	public void interact(Dungeon dungeon, Entity boulderEntity, Entity switchEntity) {
		if (boulderEntity instanceof Boulder) {
			FloorSwitch fs = (FloorSwitch) switchEntity;
			fs.setSwitchState(true);
			dungeon.addBoulderOnSwitch(1);
		}
		
	}

}
