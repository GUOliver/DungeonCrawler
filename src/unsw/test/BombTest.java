package unsw.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import unsw.dungeon.*;
public class BombTest {
	
	@Test
	public void testBombExist() {
		Dungeon maze = new Dungeon(15, 15);
		Player c1 = new Player(maze, 1, 1);
		Bomb c2 = new Bomb(3,1);
		maze.addEntity(c1);
		maze.setPlayer(c1);
		maze.addEntity(c2);
		c1.registerObservers();
		
		List<Entity> box = maze.findEntity(3, 1);
		assertEquals(c2,box.get(0));
	}
	
	
	@Test
	public void testBombPickup() {
		Dungeon maze = new Dungeon(15, 15);
		Player c1 = new Player(maze, 1, 1);
		Bomb c2 = new Bomb(1,2);
		Bomb c3 = new Bomb(1,3);
		maze.addEntity(c1);
		maze.setPlayer(c1);
		maze.addEntity(c2);
		maze.addEntity(c3);
		c1.registerObservers();
		
		c1.moveDown(maze);
		assertEquals(1,c1.getBombNum());
		c1.moveDown(maze);
		List<Entity> box = maze.findEntity(1, 2);
		assertTrue(box.isEmpty());
		assertEquals(2,c1.getBombNum());
	}
	
	@Test
	public void testBombPlace() {
		Dungeon maze = new Dungeon(15, 15);
		Player c1 = new Player(maze, 1, 1);
		Bomb c2 = new Bomb(1,2);
		maze.addEntity(c1);
		maze.setPlayer(c1);
		maze.addEntity(c2);
		c1.registerObservers();
		
		c1.moveDown(maze);
		assertEquals(1,c1.getBombNum());
		c1.dropBomb();
		assertEquals(0,c1.getBombNum());
		c1.moveDown(maze);
		List<Entity> box = maze.findEntity(1, 2);
		assertTrue(box.get(0) instanceof LitBomb);
	}
	
	//  0 1 2 3 4
	// 0  _ _ _ _ _
	// 1  _ p _ _ _
	// 2  w b w
	// 3
	// 4
	@Test
	public void testBombExplode() {
		Dungeon maze = new Dungeon(15, 15);
		Player c1 = new Player(maze, 2, 1);
		Bomb c2 = new Bomb(2,2);
		Wall c3 = new Wall(1,2);
		Wall c4 = new Wall(3,2);
		maze.addEntity(c1);
		maze.setPlayer(c1);
		maze.addEntity(c2);
		maze.addEntity(c3);
		maze.addEntity(c4);
		c1.registerObservers();
		
		c1.moveDown(maze);
		c1.dropBomb();
		// Tick 1
		c1.moveDown(maze);
		
		// Tick 2
		c1.moveDown(maze);
		
		// Tick 3
		c1.moveDown(maze);
		
		// Explode
		c1.moveDown(maze);
		List<Entity> box = maze.findEntity(2, 2);
		assertTrue(box.isEmpty());
		
		// Check both walls are gone
		box = maze.findEntity(1, 2);
		assertTrue(box.isEmpty());
		box = maze.findEntity(3, 2);
		assertTrue(box.isEmpty());
	}
	
	//   0 1 2 3 4
	// 0   w w 
	// 1 <-b p _ e
	// 2   w w
	// 3
	// 4
	// Note: if bomb explodes on tick 4, player can never be blown up by own bomb
	@Test
	public void testBombKill() {
		Dungeon maze = new Dungeon(15,15);
		Player c1 = new Player(maze, 2, 1);
		Bomb c2 = new Bomb(1,1);
		Enemy c3 = new Enemy(4,1);
		Wall c4 = new Wall(1,0);
		Wall c5 = new Wall(2,0);
		Wall c6 = new Wall(1,2);
		Wall c7 = new Wall(2,2);
		maze.addEntity(c1);
		maze.setPlayer(c1);
		maze.addEntity(c2);
		maze.addEntity(c3);
		maze.addEntity(c4);
		maze.addEntity(c5);
		maze.addEntity(c6);
		maze.addEntity(c7);
		c1.registerObservers();
		// Check player has observer
		assertTrue(!c1.getObserversList().isEmpty());
		
		// Player picks up bomb, enemy is 2 spots behind
		c1.moveLeft(maze);
		assertEquals(3,c3.getX());
		// Player places bomb, enemy is 1 spots behind
		c1.dropBomb();
		assertEquals(2,c3.getX());
		
		// Tick 1, enemy cant move past bomb
		c1.moveLeft(maze);
		assertEquals(2,c3.getX());
		
		// Tick 2
		c1.moveDown(maze);
		
		// Tick 3
		c1.moveUp(maze);
		
		// Explode
		c1.moveDown(maze);
		// Check enemy is dead
		List<Entity> box = maze.findEntity(2, 1);
		assertTrue(box.isEmpty());
		
		// Check player no longer has observers
		assertTrue(c1.getObserversList().isEmpty());
	}
	
	@Test
	public void testLitBombPickup() {
		Dungeon maze = new Dungeon(15,15);
		Player c1 = new Player(maze, 2, 1);
		Bomb c2 = new Bomb(1,1);
		maze.addEntity(c1);
		maze.setPlayer(c1);
		maze.addEntity(c2);
		c1.registerObservers();
		
		// Player picks up bomb
		boolean checkMove = c1.moveLeft(maze);
		assertTrue(checkMove);
		// Player places bomb
		c1.dropBomb();
		// Can move off the lit bomb
		checkMove = c1.moveRight(maze);
		assertTrue(checkMove);
		// Can NOT move onto the lit bomb
		checkMove = c1.moveLeft(maze);
		assertTrue(!checkMove);
	}
	
}