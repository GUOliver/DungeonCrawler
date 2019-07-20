
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
    private int enemyTotal;
    private int treasureTotal;
    private boolean hasExit;
    private boolean gameState;
    private boolean reachExit;

    public Dungeon(int width, int height) {
    	// start mode is maze 
    	this.name = "maze";
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
        this.switchTotal = 0;
        this.enemyTotal = 0;
        this.treasureTotal = 0;
        this.hasExit = false;
        this.gameState = false;
        this.setReachExit(false);
    }

    /**
     * 
     * @param x
     * @param y
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

	public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    
    
    /**
	 * This method adds the given BoardEntity to the board.
	 * @param entity - the BoardEntity to be added
	 */
    public void addEntity(Entity entity) {
    	
    	if (entity.getType().equals("exit")) {
			setHasExit(true);
		} 
    	
    	else if (entity.isEnemy()) {
			setEnemyTotal(getEnemyTotal() + 1);
		} 
    	
    	else if (entity.getType().equals("floor switch")) {
			setSwitchTotal(getSwitchTotal() + 1);
		} 
    	
    	else if (entity.getType().equals("treasure")) {
			setTreasureTotal(getTreasureTotal() + 1);
		}
    	
        entities.add(entity);
    }
    
    
    
    public void removeEntity(Entity e2) {
		// TODO Auto-generated method stub
		entities.remove(e2);
	}
    
    
// accommodate // adding features
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}

	public int getSwitchTotal() {
		return switchTotal;
	}

	public void setSwitchTotal(int switchTotal) {
		this.switchTotal = switchTotal;
	}

	public int getEnemyTotal() {
		return enemyTotal;
	}

	public void setEnemyTotal(int enemyTotal) {
		this.enemyTotal = enemyTotal;
	}

	public int getTreasureTotal() {
		return treasureTotal;
	}

	public void setTreasureTotal(int treasureTotal) {
		this.treasureTotal = treasureTotal;
	}

	public boolean isHasExit() {
		return hasExit;
	}

	public void setHasExit(boolean hasExit) {
		this.hasExit = hasExit;
	}

	public boolean getGameState() {
		return gameState;
	}

	public void setGameState(boolean gameState) {
		this.gameState = gameState;
	}

	public boolean isReachExit() {
		return reachExit;
	}

	public void setReachExit(boolean reachExit) {
		this.reachExit = reachExit;
	}

	
    
    
    
    
    
}
