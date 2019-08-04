package unsw.dungeon;
//import unsw.collisionBehaviour.*;
public class Wall extends Entity {
	
	/**
	 * Wall constructor
	 * @param x x coord
	 * @param y y coord
	 */
    public Wall(int x, int y) {
        super(x, y, "wall");
        
    }

	/**
	 * No entity can move past a wall
	 */
    
    @Override
    public boolean canMoveOnto(Dungeon dungeon, Entity mover) {
    	if (mover instanceof Player && ((Player) mover).getThroughWallTime() > 0) {
    		return true;
    	}
		return false;
	}
    
}
