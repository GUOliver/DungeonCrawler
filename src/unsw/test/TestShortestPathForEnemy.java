package unsw.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

//import java.util.List;

import org.junit.Test;

import unsw.compositeGoal.LeafExit;
import unsw.dungeon.*;


public class TestShortestPathForEnemy {
	@Test
	public void testMoveLeft() {
		Dungeon maze = new Dungeon(3, 3);
		
		Player c1 = new Player(maze, 0, 0);
		Enemy c2 = new Enemy(2, 2);
		maze.addEntity(c1);
		maze.setPlayer(c1);
		maze.addEntity(c2);
		c1.registerObservers();
		assertEquals(1, maze.getEnemyTotal());
		
		
		LeafExit exit = new LeafExit();
		maze.setGoal(exit);
		
		c1.moveDown(maze);
		
		assertTrue(2 == c2.getX());
		assertTrue(1 == c2.getY());
		
		c1.moveDown(maze);
		
		assertTrue(1 == c2.getX());
		assertTrue(1 == c2.getY());
		
		
		c1.moveRight(maze);
		assertTrue(1 == c2.getX());
		assertTrue(2 == c2.getY());
		
		// be killed by the enemy 
		assertTrue(maze.getGameState());
		assertTrue(maze.findEntity(1, 2).size() == 1);
		assertTrue(maze.findEntity(1, 2).get(0) instanceof Enemy);
	
	}
	
		//   0	 1   2
		//0	 		
		//1	 _
		//2			 E
		
	@Test
	public void testKillBySword() {
		Dungeon maze = new Dungeon(10, 10);
		
		Player c1 = new Player(maze, 0, 0);
		Enemy c2 = new Enemy(2, 2);
		Sword sword = new Sword(0, 1);
		maze.addEntity(c1);
		maze.addEntity(sword);
		maze.setPlayer(c1);
		maze.addEntity(c2);
		c1.registerObservers();
		assertEquals(1, maze.getEnemyTotal());
		
		LeafExit exit = new LeafExit();
		maze.setGoal(exit);
		
		// get the sword
		c1.moveDown(maze);
		
		assertTrue(2 == c2.getX());
		assertTrue(1 == c2.getY());
		
		c1.moveDown(maze);
		
		assertTrue(1 == c2.getX());
		assertTrue(1 == c2.getY());
		
		// kill the enemy
		c1.moveRight(maze);
		assertTrue(1 == c2.getX());
		assertTrue(2 == c2.getY());
		
		assertTrue(maze.findEntity(1, 2).size() == 1);
		assertTrue(maze.findEntity(1, 2).get(0) instanceof Player);
		assertEquals(c1.getSwordNum(), 4);
		
	
	}
}
