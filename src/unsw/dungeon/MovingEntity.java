package unsw.dungeon;

import java.util.List;
import unsw.movementStrategy.*;
public abstract class MovingEntity extends Entity {
	
	
	private MovementStrategy strategy;
	
	public MovingEntity(int x, int y, String name) {
		super(x, y, name);
		// TODO Auto-generated constructor stub
	}
	
	
	public MovementStrategy getMovementStrategy() {
		return strategy;
	}
	
	
	public void setMovementStrategy(MovementStrategy s) {
		this.strategy = s;
	}
	
	// put movement function in this class so that 
	// both enemy and player can use the method in the class
	public boolean moveUp(Dungeon dungeon) {
        if (getY() > 0)
            y().set(getY() - 1);
        
        return moveTo(dungeon, this.getX(), this.getY());
    }

    public boolean moveDown(Dungeon dungeon) {
        if (getY() < dungeon.getHeight() - 1)
            y().set(getY() + 1);
        return moveTo(dungeon, this.getX(), this.getY());
    }

    public boolean moveLeft(Dungeon dungeon) {
        if (getX() > 0)
            x().set(getX() - 1);
        return moveTo(dungeon, this.getX(), this.getY());
    }

    public boolean moveRight(Dungeon dungeon) {
        if (getX() < dungeon.getWidth() - 1)
            x().set(getX() + 1);
        return moveTo(dungeon, this.getX(), this.getY());
    }
    
    
    
    
	public boolean moveTo(Dungeon dungeon, int x, int y) {
		// checking if it can be move to this place or not
		
		List<Entity> items = dungeon.findEntity(x, y);
		
		// checking first
		for (Entity item : items) {
			//if the player can not move onto this item 
			// return false
		}
		
		
		// player can move onto the spot
		setX(x);
		setY(y);
		// call the collision for this colliding with the entities on the tile
		for (Entity entity : items) {
			entity.interact(dungeon, this);
		}
		// make the function boolean ??
		return true;
	}
	
	
	
}
