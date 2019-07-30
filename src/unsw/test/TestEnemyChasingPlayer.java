package unsw.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;


import unsw.compositeGoal.LeafExit;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.Player;


public class TestEnemyChasingPlayer {
	
	@Test
	public void testEnemyChasingPlayer() {
		Dungeon maze = new Dungeon(10, 10);
		
		Player c1 = new Player(maze, 3, 3);
		Enemy c2 = new Enemy(9, 9);
		
		maze.addEntity(c1);
		maze.setPlayer(c1);
		maze.addEntity(c2);
		c1.registerObservers();
		assertEquals(1, maze.getEnemyTotal());
		
		LeafExit exit = new LeafExit();
		maze.setGoal(exit);
		
		// 3, 4
		c1.moveDown(maze);
		
		assertTrue(9 == c2.getX());
		assertTrue(8 == c2.getY());
		
		// 3, 5
		c1.moveDown(maze);
		
		assertTrue(9 == c2.getX());
		assertTrue(7 == c2.getY());
		
		// 4, 5
		c1.moveRight(maze);
		assertTrue(9 == c2.getX());
		assertTrue(6 == c2.getY());
		
		// 5, 5
		c1.moveRight(maze);
		assertTrue(9 == c2.getX());
		assertTrue(5 == c2.getY());
		
		// 6, 5
		c1.moveRight(maze);
		assertTrue(8 == c2.getX());
		assertTrue(5 == c2.getY());
		
		
		// 5, 5
		c1.moveLeft(maze);
		
		assertTrue(7 == c2.getX());
		assertTrue(5 == c2.getY());
		
		
		// 4, 5
		c1.moveLeft(maze);
		assertTrue(6 == c2.getX());
		assertTrue(5 == c2.getY());
		
		// 4, 4
		c1.moveUp(maze);
		assertTrue(6 == c2.getX());
		assertTrue(4 == c2.getY());
		
		// 4, 3
		c1.moveUp(maze);
		assertTrue(6 == c2.getX());
		assertTrue(3 == c2.getY());
		
		// 5, 3
		c1.moveRight(maze);
		assertTrue(5 == c2.getX());
		assertTrue(3 == c2.getY());
		
		// the player get killed by the enemy
		assertTrue(maze.getGameState());
		assertEquals(maze.findEntity(5, 3).size(), 1);
		assertTrue(maze.findEntity(5, 3).get(0) instanceof Enemy);
	
	}
}
