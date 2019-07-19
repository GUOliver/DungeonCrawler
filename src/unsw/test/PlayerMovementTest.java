package unsw.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;
import unsw.dungeon.*;
public class PlayerMovementTest {
	
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
	
	//  0 1 2 3 4
	// 0  _ _ _ _ _
	// 1  _ p b _ _
	// 2
	// 3
	// 4
	@Test
	public void testMovementPushingBoulder() {
		Dungeon maze = new Dungeon(5, 5);
		Player c1 = new Player(maze, 1, 1);
		Boulder boulder = new Boulder(2, 1);
		maze.addEntity(c1);
		maze.addEntity(boulder);
		maze.setPlayer(c1);
		
		assertTrue(boulder.getX() == 2);
		assertTrue(boulder.getY() == 1);
		
		assertTrue(c1.getX() == 1);
		assertTrue(c1.getY() == 1);
		
		assertTrue(boulder.canMoveOnto(maze, c1));
		
		c1.moveRight(maze);
		assertTrue(c1.getX() == 2);
		assertTrue(c1.getY() == 1);
		assertTrue(c1.getDirection() == Direction.Right);
		
		c1.moveUp(maze);
		// the boulder was pushed one grid right, successfully
		assertTrue(maze.findEntity(2, 1).size() == 0);
		assertTrue(boulder.getX() == 3);
		assertTrue(boulder.getY() == 1);
		//  0 1 2 3 4
		// 0  _ _ P _ _
		// 1  _ _ b E _
		// 2	  B
		// 3
		// 4
		c1.moveRight(maze);
		c1.moveDown(maze);
		assertTrue(boulder.getX() == 3);
		assertTrue(boulder.getY() == 2);
		
		assertTrue(maze.getGameState() == false);
		
		
		// game over
		Enemy oliver = new Enemy(4, 1);
		maze.addEntity(oliver);
		c1.moveRight(maze);
		assertTrue(maze.getGameState() == true);
		
	}
	
	
}
