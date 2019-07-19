package unsw.dungeon;

import java.util.List;
public abstract class MovingEntity extends Entity {

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



		}
		// move ! And use x() and y() integerProperty to make it consistent
		x().set(x);
		y().set(y);

		// call the collision for this colliding with the entities on the tile, unless moving entity is enemy
		if (this.isPlayer()) {
			Player player = (Player) this;
			for (Entity entity : items) {
				entity.interact(dungeon, player);
			}
			player.notifyObservers();
			// If the entity moving is player, after player moves make enemies move
			for (Entity entity : dungeon.getEntities()) {
				if (entity.isEnemy()) {
					Enemy enemy = (Enemy)entity;
					enemy.moveEnemy(dungeon);
				}
			}
		}
		return true;

	}



	/* abandon this block of codes cuz it obeys OOP principle, hardcode
	public boolean isValidMovement(Dungeon dungeon, Entity other) {
		if (other.getType().equals("boulder")) {
			Boulder blouder = (Boulder) other;
			return false;
		} else if (other.getType().equals("wall")) {
			Wall wall = (Wall) other;
			return wall.getCanMoveOnto();
		} 
		return true;

	}
	 */


}
