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
	
	public Enemy(int x, int y) {
		super(x, y, "enemy");
		this.howMove = new MovingToPlayer();
		this.setPlayerPos(x,y);
		this.setCollisionBehaviour(new CollisionWithPlayer());
	}
	
	/**
	 * 
	 * @return the movement strategy
	 */
	public MovementStrategy getMovementStrategy() {
		return this.howMove;
	}
	
	/**
	 * This method moves the enemy according to the strategy it holds
	 */
	public void moveEnemy(Dungeon dungeon) {
		System.out.println("Enemy about to move");
		howMove.moveDirection(dungeon, this);
	}
	
	public void setPlayerPos(int x, int y) {
		this.playerX = new SimpleIntegerProperty(x);
		this.playerY = new SimpleIntegerProperty(y);
	}

	@Override
	public void update(Subject obj) {
		Player person = (Player)obj;
		this.setPlayerPos(person.getX(), person.getY());
		this.howMove = person.getStrategy();
	}

	public int getPlayerX() {
		return playerX.get();
	}

	public int getPlayerY() {
		return playerY.get();
	}

	@Override
	public boolean canMoveOnto(Dungeon dungeon, Entity character) {
		if (character instanceof Player)
			return true;
		else
			return false;
	}
	
	@Override
	public boolean isEnemy() {
		return true;
	}
	
}
