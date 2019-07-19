package unsw.dungeon;

import java.util.ArrayList;

public class LitBomb extends Entity{

	private int tick;

	public LitBomb(int x, int y) {
		super(x, y, "lit bomb 1");
		
		setTick(3);
	}
	
	public boolean canMoveOnto(Dungeon dungeon, Entity mover) {
		return true;
	}
	
	
	public void tickTock(Dungeon dungeon) {
		setTick(getTick()-1);
		
		if(getTick() == 0) {
			setType("lit bomb 4");
			explode(dungeon);
		}
		
		if (getTick() == 3) {
			setType("lit bomb 1");
		} else if (getTick() == 2) {
			setType("lit bomb 2");
		} else if (getTick() == 1) {
			setType("lit bomb 3");			
		}
		
		
	}
	
	
	
	private void explode(Dungeon dungeon) {
		// destroy all the entity which 1 grid near the bomb
		ArrayList<Entity> toBeRm = new ArrayList<Entity>();
		toBeRm.addAll(dungeon.findEntity(getX() + 1, getY()));
		toBeRm.addAll(dungeon.findEntity(getX() - 1, getY()));
		toBeRm.addAll(dungeon.findEntity(getX(), getY() + 1));
		toBeRm.addAll(dungeon.findEntity(getX(), getY() - 1));
		toBeRm.addAll(dungeon.findEntity(getX(), getY()));
		
		for (Entity item : toBeRm) {
			if (item instanceof Player) {
				// if the player in invincibility, do nothing
				if (((Player) item).getInvincibleTime() > 0) {
					return;
				}
				// else, normol player is dead, good game (game over)
				dungeon.removeEntity(item);
				dungeon.setGameState(true);
			}
			
			if ((item instanceof Enemy) || (item instanceof Boulder)) {
				dungeon.removeEntity(item);
			}
		}
		
	}


	public void setTick(int num) {
		this.tick = num;
	}
	
	public int getTick() {
		return tick;
	}
}
	



