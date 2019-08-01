package unsw.dungeon;


import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
    private int bombs;
    private IntegerProperty swordNum;
    private IntegerProperty invincibleTime;
    private ArrayList<Integer> keys;
	private int treasureCollected;
	private ArrayList<Observer> listObservers;
	private PlayerState playerState;

    /**
     * Create a player positioned in square (x,y)
     * @param dungeon
     * @param x the x coord
     * @param y the y coord
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y, "player");
        this.dungeon = dungeon;
        this.bombs = 0;
        this.swordNum = new SimpleIntegerProperty(0);
        this.treasureCollected = 0;
        this.invincibleTime = new SimpleIntegerProperty(0);
        this.keys = new ArrayList<Integer>();
        this.playerState = new NormalState();
        this.listObservers = new ArrayList<Observer>();
        setCollisionBehaviour(new CollisionWithPlayer());
    }
    

    public Bomb dropBomb() {
    	if (getBombNum() > 0) {
    		int x = this.getX();
    		int y = this.getY();
    		Bomb bomb = new Bomb(x,y);
    		bomb.setBombState(true);
    		bomb.setTick(4);
    		bomb.setType("lit bomb 0");
    		// remove from player
    		removeBomb();
    		// add to the map, where the player stands
    		dungeon.addEntity(bomb);
    		return bomb;
    	} else
    		return null;
    }

	/**
	 * List of bombs collected
	 * @return Bombs
	 */
    public int getBombNum() {
		return bombs;
	}
    
    /**
     * add the bomb to player
     * @param bomb 
     */
	public void addBomb() {
		this.bombs = this.bombs+1;
	}
	
	/**
	 * remove bomb from player
	 */
	public void removeBomb() {
		this.bombs = this.bombs-1;
	}
	
	/**
	 * Number of sword uses left
	 * @return swordNum
	 */
	public int getSwordNum() {
		return swordNum.get();
	}
	
	/**
	 * Number of sword uses left
	 * @return swordNum
	 */
	public IntegerProperty getSwordNumProperty() {
		return swordNum;
	}
	
	/**
	 * addSwordNum
	 * @param count
	 */
	public void setSwordNum(int num) {
		this.swordNum.set(num);
	}
	
	/**
	 * getInvincibleTime
	 * @return invincibleTime
	 */
	public int getInvincibleTime() {
		return invincibleTime.get();
	}
	
	/**
	 * getInvincibleTime IntegerProperty wrapper
	 * @return invincibleTime
	 */
	public IntegerProperty getInvincibleTimeProperty() {
		return invincibleTime;
	}
	
	/**
	 * setInvincibleTime
	 * @param invincibleTime
	 */
	public void setInvincibleTime(int invincibleTime) {
		this.invincibleTime.set(invincibleTime);
	}
	
	/**
	 * get Treasure Collected
	 * @return treasureCollected
	 */
	public int getTreasureCollected() {
		return treasureCollected;
	}
	
	/**
	 * set Treasure Collected
	 * @param treasure Collected
	 */
	public void setTreasureCollected(int treasureCollected) {
		this.treasureCollected = treasureCollected;
	}
	
	/**
	 * getKeys
	 * @return keys list
	 */
	public ArrayList<Integer> getKeys() {
		return keys;
	}
	
	/**
	 * find Key or not
	 * @param keyId
	 * @return true or false
	 */
	public boolean findKey(int keyId) {
		if (keys.contains(keyId)) {
			return true;
		} 
		return false;
	}
	
	/**
	 * add Keys to the key list
	 * @param keyId id of key
	 */
	public void addKeys(int keyId) {
		this.keys.add(keyId);
	}
	
	/**
	 * remove Key from list
	 * @param keyId id of key
	 */
	public void removeKeys(int keyId) {
		this.keys.remove(keyId);
	}

	/**
	 * checking if is Invincible
	 * @return true or false
	 */
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

	/**
	 * get Dungeon
	 * @return dungeon
	 */
	public Dungeon getDungeon() {
		return dungeon;
	}

	
	/**
	 * set Dungeon
	 * @param dungeon the dungeon
	 */
	public void setDungeon(Dungeon dungeon) {
		this.dungeon = dungeon;
	}



	/**
	 * checking if it can be moved onto or not
	 */
	@Override
	public boolean canMoveOnto(Dungeon dungeon, Entity character) {
		// cannot move onto another player ?
		return true;
	}
		
	/**
	 * set Player State
	 * @param state player state
	 */
	public void setPlayerState(PlayerState state) {
		this.playerState = state;
	}

	/**
	 * get Player State
	 * @return player State
	 */
	public PlayerState getPlayerState() {
		return playerState;
	}
	
	/**
	 * get Strategy
	 * @return movement strategy
	 */
	public MovementStrategy getStrategy() {
		return this.playerState.enemyStrategy();
	}
	
	/**
	 * get Observers List
	 * @return observers List
	 */
	public ArrayList<Observer> getObserversList(){
		return this.listObservers;
	}

}
