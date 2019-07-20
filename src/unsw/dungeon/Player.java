package unsw.dungeon;

import java.util.ArrayList;

import unsw.collisionBehaviour.*;
//import java.util.List;
/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends MovingEntity {

	private Dungeon dungeon;
    private ArrayList<Bomb> bombs;
    private int swordNum;
    private int invincibleTime;
    private ArrayList<Integer> keys;
	private int treasureCollected;

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

}
