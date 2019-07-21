package unsw.dungeon;
//import unsw.collisionBehaviour.*;
public class Wall extends Entity {
	
	/**
	 * 
	 * @param x x coord
	 * @param y y coord
	 */
    public Wall(int x, int y) {
        super(x, y, "wall");
        // nothing can move onto the wall
        //setCollisionBehaviour(none);
        
    }

	/**
	 * checking if it can be moved onto or not
	 */
    
    @Override
    public boolean canMoveOnto(Dungeon dungeon, Entity mover) {
		return false;
	}
    
}
