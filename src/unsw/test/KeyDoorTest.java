package unsw.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import unsw.compositeGoal.LeafExit;
import unsw.dungeon.*;
public class KeyDoorTest {
	
	@Test
	public void testKeyDoorExist() {
		Dungeon maze = new Dungeon(5, 5);
		Player c1 = new Player(maze, 1, 1);
		Key c2 = new Key(maze,2,1);
		Door c3 = new Door(maze,3,1);
		maze.addEntity(c1);
		maze.setPlayer(c1);
		maze.addEntity(c2);
		maze.addEntity(c3);
		c1.registerObservers();
		LeafExit exit = new LeafExit();
		maze.setGoal(exit);
		
		List<Entity> box = maze.findEntity(2, 1);
		assertTrue(box.contains(c2));
		box = maze.findEntity(3, 1);
		assertTrue(box.contains(c3));
	}
	
	
	@Test
	public void testKeyPickup() {
		Dungeon maze = new Dungeon(5, 5);
		Player c1 = new Player(maze, 1, 1);
		Key c2 = new Key(maze,2,1);
		Door c3 = new Door(maze,3,1);
		maze.addEntity(c1);
		maze.setPlayer(c1);
		maze.addEntity(c2);
		maze.addEntity(c3);
		c1.registerObservers();
		LeafExit exit = new LeafExit();
		maze.setGoal(exit);
		
		c1.moveRight(maze);
		assertTrue(!c1.getKeys().isEmpty());
		assertTrue(c1.findKey(c2.getKeyID()));
	}
	
	@Test
	public void testKeyUnlock() {
		Dungeon maze = new Dungeon(5, 5);
		Player c1 = new Player(maze, 1, 1);
		Key c2 = new Key(maze,2,1);
		Door c3 = new Door(maze,3,1);
		maze.addEntity(c1);
		maze.setPlayer(c1);
		maze.addEntity(c2);
		maze.addEntity(c3);
		c1.registerObservers();
		LeafExit exit = new LeafExit();
		maze.setGoal(exit);
		
		// Check locked
		assertTrue(!c3.isOpen());
		// Check door id matches key id
		assertEquals(c2.getKeyID(), c3.getKeyId());
		//pickup key
		boolean checkMove = c1.moveRight(maze);
		assertTrue(checkMove);
		
		//move onto door and unlock
		checkMove = c1.moveRight(maze);
		assertTrue(checkMove);
		assertTrue(c3.isOpen());
		
		// move through door
		c1.moveRight(maze);
		assertEquals(4, c1.getX());
		for (Integer keyID :c1.getKeys()) {
			assertTrue(keyID<0);
		}
	}
	
	@Test
	public void testKeyDoorGenerate() {
		Dungeon maze = new Dungeon(10, 10);
		Player c1 = new Player(maze, 1, 1);
		Key c2 = new Key(maze,2,1);
		Door c3 = new Door(maze,3,1);
		Key c4 = new Key(maze,4,1);
		Door c5 = new Door(maze,5,1);
		Key c6 = new Key(maze,6,1);
		Door c7 = new Door(maze,7,1);
		maze.addEntity(c1);
		maze.setPlayer(c1);
		maze.addEntity(c2);
		maze.addEntity(c3);
		maze.addEntity(c4);
		maze.addEntity(c5);
		maze.addEntity(c6);
		maze.addEntity(c7);
		c1.registerObservers();
		LeafExit exit = new LeafExit();
		maze.setGoal(exit);
		
		assertEquals(c2.getKeyID(), c3.getKeyId());
		assertEquals(c4.getKeyID(), c5.getKeyId());
		assertEquals(c6.getKeyID(), c7.getKeyId());
	}
	
}
