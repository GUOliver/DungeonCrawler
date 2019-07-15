package unsw.dungeon;

public abstract class MovingEntity extends Entity {

	public MovingEntity(int x, int y, String name) {
		super(x, y, name);
		// TODO Auto-generated constructor stub
	}
	
	// abstract method, implemented by the enermy and player
	public abstract void moveUp();
	
	public abstract void moveDown();
	
	public abstract void moveLeft();
	
	public abstract void moveRight();
	
	//  for testing
	public void moveTo(Dungeon dungeon, int x, int y) {
		// checking if it can be move to this place or not
		setX(x);
		setY(y);
		
	}
}
