package unsw.collisionBehaviour;

import unsw.dungeon.*;


public class interactWithSwitch implements CollisionBehaviour{

	@Override
	public void interact(Dungeon dungeon, Entity boulderEntity, Entity switchEntity) {
		if (boulderEntity instanceof Boulder) {
			FloorSwitch fs = (FloorSwitch) switchEntity;
			fs.setSwitchState(true);
			dungeon.setSwitchTotal(dungeon.getSwitchTotal() - 1);
		}
		
	}

}