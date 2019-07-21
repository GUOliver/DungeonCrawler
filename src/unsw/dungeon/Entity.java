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
     * @param x x coord
     * @param y y coord
     */
    public Entity(int x, int y, String type) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.type = type;
        
    }
    
    /**
     * check the character can moveonto the entity or not
     * @param dungeon the dungeon
     * @param character the entity
     * @return true or false 
     */
    public abstract boolean canMoveOnto(Dungeon dungeon, Entity character);
   
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
     * @return integer value from IntegerProperty
     */
    public int getY() {
        return y().get();
    }
    
    /**
	 * @param y sets the Y position
	 */
	public void setY(int y) {
		y().set(y);
	}
    
    /**
     * 
     * @return integer value from IntegerProperty
     */
    public int getX() {
        return x().get();
    }
    

	/**
	 * @param x sets the X position
	 */
	public void setX(int x) {
		x().set(x);
	}
	
	/**
	 * 
	 * @return Gets the class of the object as String
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Sets the class of the object as a string
	 * @param type the class name
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * get the collision behaviour
	 * @return the collision behaviour
	 */
	public CollisionBehaviour getCollisionBehaviour() {
		return behavior;
	}
	
	/**
	 * set the collision behaviour
	 * @param behavior the collision behaviour
	 */
	public void setCollisionBehaviour(CollisionBehaviour behavior) {
		this.behavior = behavior;
	}
	
	
	/**
	 * This method calls the collision behaviour of this Entity.
	 * @param dungeon The dungeon
	 * @param mover The Entity moving onto this entity
	 */
	public void interact(Dungeon dungeon, Entity character) {
		behavior.interact(dungeon, character, this);
	}
	
	/**
	 * checking if the entity is player or not
	 * @return true or false
	 */
	public boolean isPlayer() {
		return false;
	}
	
	/**
	 * return if the entity is enemy or not
	 * @return true or false
	 */
	public boolean isEnemy() {
		return false;
	}
}
