
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {
	
	private String name;
    private int width, height;
    private List<Entity> entities;
    private Player player;
    private int switchTotal;
    private int enemyTotal;		 // win condition
    private int treasureTotal;	 // win condition
    private boolean hasExit;
    private boolean gameState;
    private boolean reachExit;	 // win condition
    private int boulderOnSwitch; // win condition
    
    /**
     * 
     * @param width the width of the dungeon
     * @param height the hight of the dungeon
     */
    public Dungeon(int width, int height) {
    	// start mode is maze 
    	this.name = "maze";
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<Entity>();
        this.player = null;
        this.switchTotal = 0;
        this.enemyTotal = 0;
        this.treasureTotal = 0;
        this.hasExit = false;
        this.gameState = false;
        this.setReachExit(false);
        this.boulderOnSwitch = 0;
    }
    
    /**
     * add boulder number
     * @param i the number needed
     */
    public void addBoulderOnSwitch(int i) {
		this.boulderOnSwitch += i;
		
	}
    /**
     * get the boulders on switch number
     * @return the number of boulders on switch
     */
    public int getBoulderOnSwitch() {
		return boulderOnSwitch;
		
	}
    
    /**
     * set the game progress, checking if the game is completed or not
     */
	public void checkSetGameComplete() {
		if (this.gameState != true) {
			if (this.switchTotal == this.getBoulderOnSwitch() && this.enemyTotal == 0 
					&& this.treasureTotal == 0) {
				if (this.hasExit == true) {
					if (this.reachExit == true) {
						this.gameState = true;
					}
				} else {
					this.gameState = true;
				}
			}
		}
    }

    /**
     * 
     * @param x the x coord
     * @param y the y coord
     * @return the list of entities on a given spot
     */
    public List<Entity> findEntity(int x, int y) {
    	// Since ArrayList is a kind of List
    	// we can easily upcast it:
		List<Entity> items = new ArrayList<Entity>();
		for (Entity e : getEntities()) {
			if (x == e.getX() && y == e.getY()) {
				items.add(e);
			}
		}
		return items;
	}
    
    /**
     * 
     * @param x the x coordinnate
     * @param y the y coordinnate
     * @param type the given type of the entity
     * @return the item 
     */
    public Entity findSpecificEntity(int x, int y, String type) {
    	List<Entity> items = findEntity(x, y);
    	for (Entity item : items) {
    		if (item.getType().equals(type)) {
    			return item;
    		}
    	}
    	return null;
    }
    
    public List<String> findEntityType(int x, int y) {
		List<String> entityTypes = new ArrayList<String>();
		List<Entity> entities = findEntity(x, y);
		
		for (Entity entity : entities) {
			entityTypes.add(entity.getType());
		}
		
		return entityTypes;
	}
    
    /**
     * get the width
     * @return the width
     */
	public int getWidth() {
        return width;
    }
	
	/**
	 * get the hight
	 * @return the hight
	 */
    public int getHeight() {
        return height;
    }

    /**
     * get the player in the dungeon
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }
    
    /**
     * set the player in the dungeon
     * @param player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }
    
    
    /**
	 * This method adds the given BoardEntity to the board.
	 * @param entity - the BoardEntity to be added
	 */
    public void addEntity(Entity entity) {
    	
    	if (entity.getType().equals("exit")) {
    		if (this.isHasExit() == true ) {
    			return;
    		}
			setHasExit(true);
		} 
    	
    	else if (entity.isEnemy()) {
			setEnemyTotal(getEnemyTotal() + 1);
		} 
    	
    	else if (entity.getType().equals("switch")) {
			setSwitchTotal(getSwitchTotal() + 1);
		} 
    	
    	else if (entity.getType().equals("treasure")) {
			setTreasureTotal(getTreasureTotal() + 1);
		}
    	
        entities.add(entity);
    }
    
    
    /**
     * remove the entity from the dungeon
     * @param e2 the entity want to remove
     */
    public void removeEntity(Entity e2) {
		// TODO Auto-generated method stub
		entities.remove(e2);
	}
    
    
    /**
     * get the name of the dungeon
     * @return the name
     */
	public String getName() {
		return name;
	}
	
	/**
	 * set the name of the dungeon
	 * @param name the name of the dungeon
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * get all entities of the dungeon
	 * @return the list of entities
	 */
	public List<Entity> getEntities() {
		return entities;
	}
	
	/**
	 * set the entities
	 * @param entities the entities list we want to add
	 */
	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}
	
	/**
	 * get the switch total num
	 * @return the num
	 */
	public int getSwitchTotal() {
		return switchTotal;
	}
	
	/**
	 * set the switch total number
	 * @param switchTotal the number
	 */
	public void setSwitchTotal(int switchTotal) {
		this.switchTotal = switchTotal;
	}
	
	/**
	 * get the enemy number
	 * @return the num of enemy
	 */
	public int getEnemyTotal() {
		return enemyTotal;
	}
	
	/**
	 * set the num of enemy
	 * @param enemyTotal the total num of enmey 
	 */
	public void setEnemyTotal(int enemyTotal) {
		this.enemyTotal = enemyTotal;
	}
	
	/**
	 * get the treasure num
	 * @return the treasure num
	 */
	public int getTreasureTotal() {
		return treasureTotal;
	}
	
	/**
	 * set the treasure number
	 * @param treasureTotal the num of treasure in the dungeon
	 */
	public void setTreasureTotal(int treasureTotal) {
		this.treasureTotal = treasureTotal;
	}
	
	/**
	 * 
	 * @return if the dungeon has an exit or not
	 */
	public boolean isHasExit() {
		return hasExit;
	}

	/**
	 * set the hasExit 
	 * @param hasExit if the dungeon has exit or not
	 */
	public void setHasExit(boolean hasExit) {
		this.hasExit = hasExit;
	}
	
	/**
	 * get the game state
	 * @return the game state
	 */
	public boolean getGameState() {
		return gameState;
	}
	
	/**
	 * set the game state
	 * @param gameState the game state
	 */
	public void setGameState(boolean gameState) {
		this.gameState = gameState;
	}
	
	/**
	 * checing if the exit is reached or not
	 * @return
	 */
	public boolean isReachExit() {
		return reachExit;
	}
	
	/**
	 * set if the exit is reached or not
	 * @param reachExit
	 */
	public void setReachExit(boolean reachExit) {
		this.reachExit = reachExit;
	}
	
	/**
	 * get the num of door
	 * @return the num of door
	 */
	public int getNumKeys() {
		int count = 0;
		for (Entity entity : this.entities) {
			if (entity.getType()=="key")
				count++;
		}
		return count;
	}
	
	/**
	 * get the numof door
	 * @return the num of door
	 */
	public int getNumDoors() {
		int count = 0;
		for (Entity entity : this.entities) {
			if (entity.getType()=="locked door")
				count++;
		}
		return count;
	}
    
    
}
