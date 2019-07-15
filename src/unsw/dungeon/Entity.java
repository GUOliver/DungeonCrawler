package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import unsw.collisionBehaviour.CollisionBehaviour;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */

// do i have to make it abstract ??
public abstract class Entity {

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private IntegerProperty x, y;
    private String type;
    // private CollisionBehaviour behaviour;
    
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
    
    // public void interact(Dungeon dungeon, Entity other) {
    	
	// 	behaviour.interact(dungeon, other, this);
    // }
    
    
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
	
	public boolean isPlayer() {
		return false;
	}
}
