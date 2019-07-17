package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import unsw.collisionBehaviour.CollisionBehaviour;


/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */

public abstract class Entity {

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private IntegerProperty x, y;
    private String type;
    private CollisionBehaviour behavior;
    
    /**
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     */
    public Entity(int x, int y, String type) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.type = type;
    }
    
    
    /**
     * 
     * @return the x integer
     */
    public IntegerProperty x() {
        return x;
    }
    
    /**
     * 
     * @return the y integer
     */
    public IntegerProperty y() {
        return y;
    }
    
    
    /**
     * 
     * @return the y
     */
    public int getY() {
        return y().get();
    }
    
    /**
	 * @param y 
	 */
	public void setY(int y) {
		y().set(y);
	}
    
    /**
     * 
     * @return the x
     */
    public int getX() {
        return x().get();
    }
    

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		x().set(x);
	}
	
	/**
	 * 
	 * @return
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * 
	 * @param name
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * 
	 * @return
	 */
	public CollisionBehaviour getCollisionBehaviour() {
		return behavior;
	}
	
	public void setCollisionBehaviour(CollisionBehaviour behavior) {
		this.behavior = behavior;
	}
	
	
	/**
	 * This method calls the collision behaviour of this Entity.
	 * @param dungeon The dungeon
	 * @param mover The Entity moving onto this entity
	 */
	public void interact(Dungeon dungeon, Entity mover) {
		behavior.interact(dungeon, mover, this);
	}
	
	public boolean isPlayer() {
		return false;
	}

	public boolean isEnemy() {
		// TODO Auto-generated method stub
		return false;
	}
}
