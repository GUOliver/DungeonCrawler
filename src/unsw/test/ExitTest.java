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
		Exit exit1 = new Exit(9, 9);
		dungeon.addEntity(exit1);
		assertTrue(exit1.getX() == 9);
		assertTrue(exit1.getY() == 9);
		assertTrue(dungeon.isHasExit());
		
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
		Exit exit1 = new Exit(9, 9);
		dungeon.addEntity(exit1);
		Player oliver = new Player(dungeon, 8, 9);
		dungeon.addEntity(oliver);
		dungeon.setPlayer(oliver);
		
		
		oliver.moveRight(dungeon);
		assertTrue(oliver.getX() == 9);
		assertTrue(oliver.getY() == 9);
		
		assertTrue(dungeon.isReachExit() == true);
	}
	
	
	
	
}
