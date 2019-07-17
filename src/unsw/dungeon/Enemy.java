package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import unsw.movementStrategy.MovementStrategy;
import unsw.movementStrategy.MovingAwayPlayer;
import unsw.movementStrategy.MovingToPlayer;
import unsw.playerObserve.Observer;
import unsw.playerObserve.Subject;

public class Enemy extends MovingEntity implements Observer {
	private MovementStrategy howMove;
	private IntegerProperty playerX,playerY;
	
	public Enemy(int x, int y, String name) {
		super(x, y, name);
		this.howMove = new MovingToPlayer();
	}
	
	/**
	 * This method moves the enemy according to the strategy it holds
	 */
	public void moveEnemy(Dungeon dungeon) {
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
		if (person.isInvincible()==true) {
			this.howMove = new MovingAwayPlayer();
		} else {
			this.howMove = new MovingToPlayer();
		}
	}

	public int getPlayerX() {
		return playerX.get();
	}

	public int getPlayerY() {
		return playerY.get();
	}
}
