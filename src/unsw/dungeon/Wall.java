package unsw.dungeon;
//import unsw.collisionBehaviour.*;
public class Wall extends Entity {
	
    public Wall(int x, int y) {
        super(x, y, "wall");
        // nothing can move onto the wall
        //setCollisionBehaviour(none);
        
    }
    public boolean canMoveOnto(Dungeon dungeon, Entity mover) {
		return false;
	}
    
}
