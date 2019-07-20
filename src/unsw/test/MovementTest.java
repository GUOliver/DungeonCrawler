package unsw.test;

import static org.junit.Assert.assertTrue;


import org.junit.Test;
import unsw.dungeon.*;


// userUser Story 1.7: Player movement

public class MovementTest {
	
	//    0 1 2 3 4
	// 0  _ _ _ _ _
 	// 1  _ _ _ _ _
	// 2
	// 3
	// 4
	@Test
	public void testMoveUp() {
		Dungeon maze = new Dungeon(15, 15);
		Player c1 = new Player(maze, 0, 1);
		maze.addEntity(c1);
		maze.setPlayer(c1);
		c1.moveUp(maze);
		assertTrue(c1.getX() == 0);
		assertTrue(c1.getY() == 0);
	}
	
	@Test
	public void testMoveDown() {
		Dungeon maze = new Dungeon(15, 15);
		Player c1 = new Player(maze, 1, 2);
		maze.addEntity(c1);
		maze.setPlayer(c1);
		
		c1.moveDown(maze);
		assertTrue(c1.getX() == 1);
		assertTrue(c1.getY() == 3);
	}
	
	
	@Test
	public void testMoveLeft() {
		Dungeon maze = new Dungeon(15, 15);
		Player c1 = new Player(maze, 1, 1);
		maze.addEntity(c1);
		maze.setPlayer(c1);
		
		c1.moveLeft(maze);
		assertTrue(c1.getX() == 0);
		assertTrue(c1.getY() == 1);
	}
	
	@Test
	public void testMoveRight() {
		Dungeon maze = new Dungeon(15, 15);
		Player c1 = new Player(maze, 1, 1);
		maze.addEntity(c1);
		maze.setPlayer(c1);
		
		c1.moveRight(maze);
		assertTrue(c1.getX() == 2);
		assertTrue(c1.getY() == 1);
	}
	
	//  0 1 2 3 4
	// 0  _ _ _ _ _
	// 1  p w _ _ _
	// 2
	// 3
	// 4
	@Test
	public void testMovementCheckingWall() {
		Dungeon maze = new Dungeon(5, 5);
		Player c1 = new Player(maze, 1, 1);
		Wall wall = new Wall(2, 1);
		maze.addEntity(c1);
		maze.addEntity(wall);
		maze.setPlayer(c1);
		assertTrue(wall.getX() == 2);
		assertTrue(wall.getY() == 1);
		
		assertTrue(c1.getX() == 1);
		assertTrue(c1.getY() == 1);
		
		// cannnot move to the wall, so stay the same place
		c1.moveRight(maze);
		assertTrue(c1.getX() == 1);
		assertTrue(c1.getY() == 1);
		
	}
	
	
	
	
}
