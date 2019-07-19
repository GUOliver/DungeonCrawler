package unsw.dungeon;
//import java.util.ArrayList;
//import java.util.Timer;
//import java.util.TimerTask;

import unsw.collisionBehaviour.*;
public class Bomb extends Entity{

	//private boolean isLitten;
	//private boolean isExploded;
	//private Timer timer;

	public Bomb(int x, int y, String type) {
		super(x, y, "bomb");
		setCollisionBehaviour(new PickUpUnlitBomb());
		
		//this.isLitten = false;
		//this.isExploded = false;
	}
	
	public boolean canMoveOnto(Dungeon dungeon, Entity mover) {
		
		return true;
	}
	
	/**
	 * Lit of the bomb when called, and keep counting down until it explodes
	 * which destroys objects near it (passed in by Maze
	 */
	
	/*
	public void litBomb(Dungeon dungeon) {
		if (! this.isLitten) {
			this.isLitten = true;
			this.timer = new Timer();
			// this may be replaced by setting the bomb jpg here
			this.setType("litten bomb 1");
			timer.schedule(new Tick2(), 1000);
		}
		
		else if (this.isExploded) {
			// cancel all the timer task
			this.timer.purge();
			
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
	}
	

	
	class Tick2 extends TimerTask {
		public void run() {
			
			timer.schedule(new Tick3(), 1000);
		}
	}
	
	class Tick3 extends TimerTask {
		public void run() {
			
			timer.schedule(new Tick4(), 1000);
		}
	}
	
	class Tick4 extends TimerTask {
		public void run() {
			timer.schedule(new Explode(), 100);
		}
	}
	
	class Explode extends TimerTask {
		public void run() {
			isExploded = true;
			timer.cancel();
        }
	}
	*/
	
	/**
	 * Check if the bomb is currently lit/used
	 * @return true if bomb has been lit
	 */
	
	/*
	public boolean isLitten() {
		return isLitten;
	}

	public void setLitten(boolean isLitten) {
		this.isLitten = isLitten;
	}
	*/
	
	
	/**
	 * Check if the bomb is already exploded
	 * @return true if bomb has been lit
	 */
	/*
	public boolean isExploded() {
		return isExploded;
	}

	public void setExploded(boolean isExploded) {
		this.isExploded = isExploded;
	}*/
	
	
	
	
	

	
}
