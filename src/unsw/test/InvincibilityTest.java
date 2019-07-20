package unsw.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import unsw.dungeon.*;
public class InvincibilityTest {
	
	@Test
	public void testPotionExist() {
		Dungeon maze = new Dungeon(5, 15);
		Player c1 = new Player(maze, 1, 1);
		InvincibilityPotion c2 = new InvincibilityPotion(3,1);
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
		// TODO place bomb
		//c1.placeBomb();
		assertEquals(0,c1.getBombNum());
		c1.moveDown(maze);
		List<Entity> box = maze.findEntity(1, 2);
		assertTrue(box.get(0) instanceof LitBomb);
	}
}
