package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import unsw.collisionBehaviour.CollisionWithPlayer;
import unsw.movementStrategy.MovementStrategy;
import unsw.movementStrategy.MovingToPlayer;
import unsw.playerObserve.Observer;
import unsw.playerObserve.Subject;

public class Enemy extends MovingEntity implements Observer {
	private MovementStrategy howMove;
	private IntegerProperty playerX,playerY;
	
	/**
	 *  Constructor for an enemy entity, which keeps track of player position
	 *  Player position is updated with Observer pattern, movement of enemy is defined
	 *  with strategy pattern
	 * @param x the x coord
	 * @param y the y coord
	 */
	public Enemy(int x, int y) {
		super(x, y, "enemy");
		this.howMove = new MovingToPlayer();
		this.setPlayerPos(x,y);
		this.setCollisionBehaviour(new CollisionWithPlayer());
	}
	
	/**
	 * get the strategy
	 * @return the movement strategy
	 */
	public MovementStrategy getMovementStrategy() {
		return this.howMove;
	}
	
	
	/**
	 * Moves the enemy according to the movement strategy
	 * @param dungeon
	 */
	public void moveEnemy(Dungeon dungeon) {
		howMove.moveDirection(dungeon, this);
	}
	
	/**
	 * Sets the player position
	 * @param x x coord
	 * @param y y coord
	 */
	public void setPlayerPos(int x, int y) {
		this.playerX = new SimpleIntegerProperty(x);
		this.playerY = new SimpleIntegerProperty(y);
	}

	/**
	 * Updates the player position for the enemy, and the movement strategy to use
	 */
	@Override
	public void update(Subject obj) {
		Player person = (Player)obj;
		this.setPlayerPos(person.getX(), person.getY());
		this.howMove = person.getStrategy();
	}

	/**
	 * @return integer value from IntegerProperty
	 */
	public int getPlayerX() {
		return playerX.get();
	}

	/**
	 * @return integer value from IntegerProperty
	 */
	public int getPlayerY() {
		return playerY.get();
	}

	/**
	 * Only players can move onto enemies, enemies cannot stack onto other enemies
	 */
	@Override
	public boolean canMoveOnto(Dungeon dungeon, Entity character) {
		if (character instanceof Player)
			return true;
		else
			return false;
	}
	
	/**
	 * Enemy returns true always
	 */
	@Override
	public boolean isEnemy() {
		return true;
	}
	
}
