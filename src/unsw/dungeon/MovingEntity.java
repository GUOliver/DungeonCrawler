package unsw.dungeon;

import java.util.List;

import unsw.movementStrategy.*;
public abstract class MovingEntity extends Entity {
	
	
	private MovementStrategy strategy;
	private Direction direction;
	
	public MovingEntity(int x, int y, String name) {
		super(x, y, name);
		setDirection(Direction.Resting);   // set the initail direction to null ?
	}
	
	/**
	 * 
	 * @return the curr direction
	 */
	public Direction getDirection() {
		return direction;
	}
	
	/**
	 * 
	 * @param direction set the direction
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	/**
	 * 
	 * @return the movement strategy
	 */
	public MovementStrategy getMovementStrategy() {
		return strategy;
	}
	
	/**
	 * 
	 * @param s set the movement strategey s
	 */
	public void setMovementStrategy(MovementStrategy s) {
		this.strategy = s;
	}
	
	// put movement function in this class so that 
	// both enemy and player can use the method in the class
	public boolean moveUp(Dungeon dungeon) {
		setDirection(Direction.Up);
        if (getY() > 0) {
        	
        	int currY = getY() - 1;
        	int currX = getX();
            //y().set(getY() - 1);
          
        	return moveTo(dungeon, currX, currY);
        }
        
        return false;
    }

    public boolean moveDown(Dungeon dungeon) {
    	setDirection(Direction.Down);
        if (getY() < dungeon.getHeight() - 1) {
        	int currY = getY() + 1;
        	int currX = getX();
            //y().set(getY() - 1);
          
        	return moveTo(dungeon, currX, currY);
        }
            // y().set(getY() + 1);
        return false;
    }

    public boolean moveLeft(Dungeon dungeon) {
    	setDirection(Direction.Left);
        if (getX() > 0) {
        	int currY = getY();
        	int currX = getX() - 1;
            //y().set(getY() - 1);
          
        	return moveTo(dungeon, currX, currY);
        }
            //x().set(getX() - 1);
        return false;
    }

    public boolean moveRight(Dungeon dungeon) {
    	setDirection(Direction.Right);
        if (getX() < dungeon.getWidth() - 1) {
        	int currY = getY();
        	int currX = getX() + 1;
            //y().set(getY() - 1);
          
        	return moveTo(dungeon, currX, currY);
        }
            //x().set(getX() + 1);
        return false;
    }
    
    
    
    
	public boolean moveTo(Dungeon dungeon, int x, int y) {
		// checking if it can be move to this place or not
		
		List<Entity> items = dungeon.findEntity(x, y);
		
		
		// checking first
		for (Entity item : items) {
			//if the player can not move onto this item 
			//return false
			if (! item.canMoveOnto(dungeon, this)) {
				System.out.println("Cannot move to " + item.getType());
				return false;
			}
			
			// when the boulder in the list, to be careful if there is a wall in front
			// a little hard coding here, when a special case like this " p->b->w"
			if (item instanceof Boulder) {
				if (isBarriarInFront(x, y,dungeon)) {
					return false;
				}
			}	
			
			
			
		}
		// move ! And use x() and y() integerProperty to make it consistent
		x().set(x);
		y().set(y);
		
		// call the collision for this colliding with the entities on the tile
		for (Entity entity : items) {
			entity.interact(dungeon, this);
			
			// if the boulder pushed into switch, two interaction occurred here, so make 
			// the interaction betweem boulder and switch mannualy 
			if (entity instanceof Boulder) {
				FloorSwitch sw = (FloorSwitch) dungeon.findSpecificEntity(entity.getX(), entity.getY(), "switch");
				// if not a switch,  do nothing
				if (sw != null) {
					sw.interact(dungeon, entity);
				}
			}
			
		}
		return true;
		
	}
	
	/**
	 * 
	 * @param x the given x
	 * @param y the given y
	 * @param dungeon the given dungeon
	 * @return if there is a barrar or not
	 */
	public boolean isBarriarInFront(int x, int y, Dungeon dungeon) {
		
		int newX = 0, newY = 0;
		
		switch (this.getDirection()){
			case Up:
				newX = x;
				newY = y - 1;
				
				if (isBarriar(newX, newY, dungeon)) {
					return true;
				}
				return false;
				
			case Down:
				newX = x;
				newY = y + 1;
				if (isBarriar(newX, newY, dungeon)) {
					return true;
				}
				return false;
				
			case Left:
				newX = x - 1;
				newY = y;
				if (isBarriar(newX, newY, dungeon)) {
					return true;
				}
				return false;
			case Right:
				newX = x + 1;
				newY = y;
				if (isBarriar(newX, newY, dungeon)) {
					return true;
				}
				return false;
			case Resting:
				break;
			default:
				break;
		}
		return false;
	}
	
	public boolean isBarriar(int x, int y, Dungeon dungeon) {
		List<Entity> items = dungeon.findEntity(x, y);
		for (Entity item : items) {
			if (item.getType().equals("wall") || item.getType().equals("locked door")) {
				return true;
			} 
		}
		return false;
		
	}
	

	
	
	
	
	
	
	
}
