package unsw.dungeon;

import java.util.ArrayList;

import unsw.collisionBehaviour.*;
import unsw.playerObserve.Observer;
import unsw.playerObserve.Subject;

import java.util.List;
/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends MovingEntity implements Subject {

	private Dungeon dungeon;
    private int bombNum;
    private int swordNum;
    private int invincibleTime;
    private ArrayList<Integer> keys;
	private int treasureCollected;
	ArrayList<Observer> listObservers = new ArrayList<Observer>();
	//Placeholder attribute for state pattern implementation
	private State playerState;

    /**
     * Create a player positioned in square (x,y)
     * @param dungeon
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y, "player");
        this.dungeon = dungeon;
        this.bombNum = 0;
        this.swordNum = 0;
        this.treasureCollected = 0;
        this.invincibleTime = 0;
        this.keys = new ArrayList<Integer>();
        // setCollisionBehaviour is implemented in super class MovingEntity
        setCollisionBehaviour(new CollisionWithPlayer());
    }

	
    public int getBombNum() {
		return bombNum;
	}

	public void addBombNum(int bombNum) {
		this.bombNum += bombNum;
	}

	public int getSwordNum() {
		return swordNum;
	}

	public void addSwordNum(int count) {
		this.swordNum += count;
	}

	public int getInvincibleTime() {
		return invincibleTime;
	}

	public void setInvincibleTime(int invincibleTime) {
		this.invincibleTime = invincibleTime;
	}
	
	
	public int getTreasureCollected() {
		return treasureCollected;
	}

	public void setTreasureCollected(int treasureCollected) {
		this.treasureCollected = treasureCollected;
	}

	public ArrayList<Integer> getKeys() {
		return keys;
	}

	public void addKeys(int keyId) {
		this.keys.add(keyId);
	}

	public boolean isInvincible() {
		if (this.getInvincibleTime() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean isPlayer() {
		return true;
	}
	
	/**
	 * Placeholder function to update state and notify observers
	 */
	public void changeState() {
		this.playerState.changeState();
		notifyObservers();
	}

	/**
	 * Adds all enemies in the dungeon as an observer of the player
	 * Also provides enemies with the initial position of the player
	 */
	@Override
	public void registerObservers() {
		for (Entity entity : this.dungeon.getEntities()) {
			if (entity.getClass()==Enemy.class) {
				this.listObservers.add((Observer)entity);
				Enemy enemy = (Enemy)entity;
				enemy.setPlayerPos(this.getX(), this.getY());
			}
		}
		
	}

	
	/**
	 * Removes a specific enemy from the observed list, e.g. when they die
	 */
	@Override
	public void removeObserver(Observer o) {
		this.listObservers.remove(o);
	}

	
	/**
	 * Provides enemies a constant update of where the player is, also
	 * notifies the enemies when the player becomes invincible, or returns to normal
	 */
	@Override
	public void notifyObservers() {
		for( Observer obs : listObservers) {
			obs.update(this);
		}
	}

}
