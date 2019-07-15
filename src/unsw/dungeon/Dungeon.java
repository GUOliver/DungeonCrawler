/**
 *
 */
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
    }
    
    //public boolean moveChecking(Entity item, int x, int y) {
    //	Entity itemFound = findEntity(x, y);
    //	if (itemFound.moveChecking(this, item)) {
    //		return true;
    //	}
    //	return false;
    	
    //}

    public Entity findEntity(int x, int y) {
		// TODO Auto-generated method stub
		return null;
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
    

    public void addEntity(Entity entity) {
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

	public boolean isGameState() {
		return gameState;
	}

	public void setGameState(boolean gameState) {
		this.gameState = gameState;
	}

	
    
    
    
    
    
}
