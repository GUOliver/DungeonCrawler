package unsw.dungeon;
import unsw.collisionBehaviour.*;
public class Enemy extends MovingEntity{
	
	public Enemy(int x, int y) {
		super(x, y, "enemy");
		setCollisionBehaviour(new killEnermy());
	}

	@Override
	public boolean canMoveOnto(Dungeon dungeon, Entity character) {
		// TODO Auto-generated method stub
		return true;
	}
	
}
