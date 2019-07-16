package unsw.test;

import unsw.dungeon.*;
public class MovementTest {
	private Dungeon maze = new Dungeon(15, 15);
	
	public void testMoveUp() {
		Player c1 = new Player(maze, 0, 0);
		
		
	}
	public static void main(String[] args) {

		MovementTest t1 = new MovementTest();
		t1.testMoveUp();

	}
}
