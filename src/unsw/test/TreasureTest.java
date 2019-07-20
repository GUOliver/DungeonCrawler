package unsw.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import unsw.dungeon.*;
public class TreasureTest {
	
	@Test
	public void testTreasureExist() {
		Dungeon maze = new Dungeon(15, 15);
		Player c1 = new Player(maze, 1, 1);
		Treasure c2 = new Treasure(2,1);
		assertEquals(0,maze.getTreasureTotal());
		maze.addEntity(c1);
		maze.setPlayer(c1);
		maze.addEntity(c2);
		c1.registerObservers();
		
		List<Entity> box = maze.findEntity(2, 1);
		assertEquals(1,maze.getTreasureTotal());
		assertEquals(c2,box.get(0));
	}
	
	
	@Test
	public void testTreasurePickup() {
		Dungeon maze = new Dungeon(15, 15);
		Player c1 = new Player(maze, 1, 1);
		Treasure c2 = new Treasure(2,1);
		maze.addEntity(c1);
		maze.setPlayer(c1);
		maze.addEntity(c2);
		c1.registerObservers();
		
		// Collected treasure, game over?
		c1.moveRight(maze);
		assertEquals(1,c1.getTreasureCollected());
		List<Entity> box = maze.findEntity(2, 1);
		assertTrue(!box.contains(c2));
		assertTrue(maze.getGameState());
		
	}
	
	@Test
	public void testTreasureDestroy() {
		Dungeon maze = new Dungeon(15, 15);
		Player c1 = new Player(maze, 1, 1);
		Treasure c2 = new Treasure(3,1);
		Bomb c3 = new Bomb(2,1);
		maze.addEntity(c1);
		maze.setPlayer(c1);
		maze.addEntity(c2);
		maze.addEntity(c3);
		c1.registerObservers();
		
		c1.moveRight(maze);
		c1.dropBomb();
		c1.moveDown(maze);
		c1.moveDown(maze);
		c1.moveDown(maze);
		c1.moveDown(maze);
		
		// check treasure hasnt exploded
		List<Entity> box = maze.findEntity(3, 1);
		assertTrue(box.contains(c2));
		
	}
}
