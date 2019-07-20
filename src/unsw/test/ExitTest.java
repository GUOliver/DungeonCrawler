package unsw.test;
import unsw.dungeon.*;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


/*
 * User Story 1.8: Exit
 */
public class ExitTest {

	@Test
	public void testOnlyOneExit() {
		Dungeon dungeon = new Dungeon(10, 10);
		Exit exit = new Exit(10, 10);
		dungeon.addEntity(exit);
		assertTrue(exit.getX() == 10);
		assertTrue(exit.getY() == 10);
		
		// only one exit can exit
		Exit exit2 = new Exit(0, 0);
		dungeon.addEntity(exit2);
		assertTrue(dungeon.findEntity(0, 0).size() == 0);
	}
	// 0----9
	//0
	//|		
	//|
	//9
	@Test
	public void testOnlyPlayerCanMoveThrough() {
		Dungeon dungeon = new Dungeon(10, 10);
		Exit exit = new Exit(9, 9);
		dungeon.addEntity(exit);
		Player oliver = new Player(dungeon, 8, 9);
		dungeon.addEntity(oliver);
		dungeon.setPlayer(oliver);
		
		
		oliver.moveRight(dungeon);
		assertTrue(oliver.getX() == 9);
		assertTrue(oliver.getY() == 9);
		
		assertTrue(dungeon.isReachExit() == true);
	}
	
	
	
	
}
