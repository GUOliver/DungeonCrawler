package unsw.dungeon;


import java.util.ArrayList;

import unsw.collisionBehaviour.*;
import unsw.movementStrategy.MovementStrategy;
import unsw.playerObserve.Observer;
import unsw.playerObserve.Subject;
import unsw.playerState.InvincibleState;
import unsw.playerState.NormalState;
import unsw.playerState.PlayerState;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends MovingEntity implements Subject {

	private Dungeon dungeon;
    private ArrayList<Bomb> bombs;
    private int swordNum;
    private int invincibleTime;
    private ArrayList<Integer> keys;
	private int treasureCollected;
	private ArrayList<Observer> listObservers;
	//Placeholder attribute for state pattern implementation
	private PlayerState playerState;

    /**
     * Create a player positioned in square (x,y)
     * @param dungeon
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y, "player");
        this.dungeon = dungeon;
        this.bombs = new ArrayList<Bomb>();
        this.swordNum = 0;
        this.treasureCollected = 0;
        this.invincibleTime = 0;
        this.keys = new ArrayList<Integer>();
        this.playerState = new NormalState();
        this.listObservers = new ArrayList<Observer>();
        // setCollisionBehaviour is implemented in super class MovingEntity
        setCollisionBehaviour(new CollisionWithPlayer());
    }
    

    public void dropBomb() {
    	if (getBombs().size() > 0) {
    		int x = this.getX();
    		int y = this.getY();
    		Bomb bomb = this.bombs.get(this.getBombs().size() - 1);
    		bomb.setX(x);
    		bomb.setY(y);
    		
    		// add to the map, where the player stands
    		dungeon.addEntity(bomb);
    		// remove from player
    		removeBombFromPlayer();
    		bomb.setBombState(true);
    	}
    }

	
    public ArrayList<Bomb> getBombs() {
		return bombs;
	}

	public void addBomb(Bomb bomb) {
		this.bombs.add(bomb);
	}
	
	public void removeBombFromPlayer() {
		this.bombs.remove(this.getBombs().size() - 1);
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
	
	public boolean findKey(int keyId) {
		if (keys.contains(keyId)) {
			return true;
		} 
		return false;
	}

	public void addKeys(int keyId) {
		this.keys.add(keyId);
	}

	public boolean isInvincible() {
		if (this.playerState instanceof InvincibleState) {
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
	 * Adds all enemies in the dungeon as an observer of the player
	 * Also provides enemies with the initial position of the player
	 */
	@Override
	public void registerObservers() {
		for (Entity entity : this.dungeon.getEntities()) {
			if (entity.isEnemy()) {
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


	public Dungeon getDungeon() {
		return dungeon;
	}


	public void setDungeon(Dungeon dungeon) {
		this.dungeon = dungeon;
	}


	@Override
	public boolean canMoveOnto(Dungeon dungeon, Entity character) {
		// cannot move onto another player ?
		return true;
	}
	
	public void setPlayerState(PlayerState state) {
		this.playerState = state;
	}


	public PlayerState getPlayerState() {
		return playerState;
	}
	
	public MovementStrategy getStrategy() {
		return this.playerState.enemyStrategy();
	}
	
	public ArrayList<Observer> getObserversList(){
		return this.listObservers;
	}

}
