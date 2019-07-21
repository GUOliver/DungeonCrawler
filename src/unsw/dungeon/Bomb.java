package unsw.dungeon;
//import java.util.ArrayList;
//import java.util.Timer;
//import java.util.TimerTask;

import java.util.ArrayList;

import unsw.collisionBehaviour.*;
public class Bomb extends Entity{

	//private boolean isLitten;
	//private boolean isExploded;
	//private Timer timer;
	

	private boolean bombState;
	private int tick;
	private boolean isExploded;
	/**
	 * 
	 * @param x x coordinate
	 * @param y y coordinate
	 */ 
	public Bomb(int x, int y) {
	
		super(x, y, "unlit bomb");
		setCollisionBehaviour(new PickUpUnlitBomb());
		setBombState(false);
		this.setExploded(false);
		setTick(3);
	}
	
	/**
	 * set the bomb state
	 * @param bombState the bomb state
	 */
	public void setBombState(boolean bombState) {
		this.bombState = bombState;
	}
	
	/**
	 * get the bomb state
	 * @return bomb state
	 */
	public boolean getBombState() {
		return bombState;
	}
	
	/**
	 * mimic what happens before the bomb explosion
	 * @param dungeon the dungeon 
	 */
	public void tickTock(Dungeon dungeon) {
		if (getBombState() == true) {
			setTick(getTick() - 1);
			
			if(getTick() == 0) {
				setType("lit bomb 4");
				explode(dungeon);
			}
			
			else if (getTick() == 3) {
				setType("lit bomb 1");
			} 
			
			else if (getTick() == 2) {
				setType("lit bomb 2");
			} 
			
			else if (getTick() == 1) {
				setType("lit bomb 3");			
			}
		}
		
	}
	
	
	/**
	 * explode and destroy things around
	 * @param dungeon the dungeon
	 */
	private void explode(Dungeon dungeon) {
		// destroy all the entity which 1 grid near the bomb
		setExploded(true);
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
		
		dungeon.removeEntity(this);
		
	}

	/**
	 * set the tick
	 * @param num
	 */
	public void setTick(int num) {
		this.tick = num;
	}
	
	/**
	 * get the tick 
	 * @return the tick number
	 */
	public int getTick() {
		return tick;
	}
	
	
	/**
	 * checking if it can be moved onto 
	 */
	@Override
	public boolean canMoveOnto(Dungeon dungeon, Entity mover) {
		return true;
	}
	
	/**
	 * is exploded checking 
	 * @return true or false
	 */
	public boolean isExploded() {
		return isExploded;
	}
	
	/**
	 * set the exploded state
	 * @param isExploded
	 */
	public void setExploded(boolean isExploded) {
		this.isExploded = isExploded;
	}
}