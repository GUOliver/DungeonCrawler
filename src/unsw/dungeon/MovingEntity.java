package unsw.dungeon;

import java.util.List;

import unsw.playerState.NormalState;

public abstract class MovingEntity extends Entity {

	private Direction direction;

	/**
	 * Constructor for a moving entity, takes in position and type
	 * @param x X pos
	 * @param y Y pos
	 * @param name String class name
	 */
	public MovingEntity(int x, int y, String name) {
		super(x, y, name);
		setDirection(Direction.Resting);   // set the initail direction to null ?
	}

	/**
	 * Gets direction of moving entity
	 * @return the curr direction
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * sets direction of moving entity
	 * @param direction new direction
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}


	/**
	 * moveUp
	 * @param dungeon
	 * @return whether or not the movement occurs
	 */
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
	
	/**
	 * moveDown
	 * @param dungeon
	 * @return whether or not the movement occurs
	 */
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
	
	/**
	 * moveLeft
	 * @param dungeon
	 * @return whether or not the movement occurs
	 */
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
	
	/**
	 * move Right
	 * @param dungeon
	 * @return whether or not the movement occurs
	 */
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



	/**
	 * checking if the entity can move to that place or not. If they can, perform the move.
	 * If the moving entity is a player, allow them to interact with any objects they 
	 * move into. Checks if invincibility wears off and if all goals are completed at 
	 * end of player turn.
	 * @param dungeon the dungeon
	 * @param x x coord entity is trying to move to
	 * @param y y coord entity is trying to move to
	 * @return whether or not the movement occurs
	 */ 
	public boolean moveTo(Dungeon dungeon, int x, int y) {
		// checking if it can be move to this place or not

		List<Entity> items = dungeon.findEntity(x, y);


		// checking first
		for (Entity item : items) {
			//if the player can not move onto this item 
			//return false
			if (! item.canMoveOnto(dungeon, this)) {
				return false;
			}
			
			// when the boulder in the list, to be careful if there is a wall in front
			// a little hard coding here, when a special case like this " p->b->w"
			if (item instanceof Boulder) {
				if (isBarrierInFront(x, y,dungeon)) {
					return false;
				}
			}	
		}
		
		
		// move ! And use x() and y() integerProperty to make it consistent
		x().set(x);
		y().set(y);
		
		// call the collision for this colliding with the entities on the tile, unless moving entity is enemy
		if (this instanceof Player) {
			
			Player player = (Player) this;
			for (Entity entity : items) {
				// call the collision for this colliding with the entities on the tile
				entity.interact(dungeon, player);
				
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
			
			player.notifyObservers();
			// If the entity moving is player, after player moves make enemies move
			Enemy[] tempArray = dungeon.getEnemyArray();
			for(int i = 0; i < tempArray.length;i++) {
				if (tempArray[i]!=null)
					tempArray[i].moveEnemy(dungeon);
			}
			
			// Checks if invincibility ran out
			if (player.getInvincibleTime()==0 && player.isInvincible()) {
				player.setPlayerState(new NormalState());
			} else if (player.getInvincibleTime()>0) {
				player.setInvincibleTime(player.getInvincibleTime()-1);
			}

		}
		// Enemies moving onto players should also cause collision
		else if (this instanceof Enemy) {
			if (dungeon.findSpecificEntity(x, y, "player") instanceof Player)
				this.interact(dungeon, dungeon.getPlayer());
		}
		
		// Checks if all goals are complete after player movement
		dungeon.checkSetGameComplete();
		
		return true;

	}


	/**
	 * Checks if there is a barrier in front of moving entity
	 * @param x the given x
	 * @param y the given y
	 * @param dungeon the given dungeon
	 * @return if there is a barrier or not
	 */
	public boolean isBarrierInFront(int x, int y, Dungeon dungeon) {
		
		int newX = 0, newY = 0;
		
		switch (this.getDirection()){
			case Up:
				newX = x;
				newY = y - 1;
				
				if (isBarrier(newX, newY, dungeon)) {
					return true;
				}
				return false;
				
			case Down:
				newX = x;
				newY = y + 1;
				if (isBarrier(newX, newY, dungeon)) {
					return true;
				}
				return false;
				
			case Left:
				newX = x - 1;
				newY = y;
				if (isBarrier(newX, newY, dungeon)) {
					return true;
				}
				return false;
			case Right:
				newX = x + 1;
				newY = y;
				if (isBarrier(newX, newY, dungeon)) {
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
	
	/**
	 * Checks if barrier exists at a given position
	 * @param x coord
	 * @param y coord
	 * @param dungeon
	 * @return if there is barrier or not
	 */
	public boolean isBarrier(int x, int y, Dungeon dungeon) {
		List<Entity> items = dungeon.findEntity(x, y);
		for (Entity item : items) {
			if (item.getType().equals("wall") || item.getType().equals("locked door") || item.getType().equals("boulder")) {
				return true;
			} 
		}
		return false;
		
	}
}
