package unsw.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import unsw.dungeon.*;
import unsw.playerState.InvincibleState;
import unsw.playerState.NormalState;
public class InvincibilityTest {
	
	@Test
	public void testPotionExist() {
		Dungeon maze = new Dungeon(15, 15);
		Player c1 = new Player(maze, 1, 1);
		InvincibilityPotion c2 = new InvincibilityPotion(2,1);
		maze.addEntity(c1);
		maze.setPlayer(c1);
		maze.addEntity(c2);
		c1.registerObservers();
		
		List<Entity> box = maze.findEntity(2, 1);
		assertEquals(c2,box.get(0));
	}
	
	
	@Test
	public void testPotionPickup() {
		Dungeon maze = new Dungeon(15, 15);
		Player c1 = new Player(maze, 1, 1);
		InvincibilityPotion c2 = new InvincibilityPotion(2,1);
		maze.addEntity(c1);
		maze.setPlayer(c1);
		maze.addEntity(c2);
		c1.registerObservers();
		
		assertTrue(c1.getPlayerState() instanceof NormalState);
		c1.moveRight(maze);
		assertTrue(c1.getPlayerState() instanceof InvincibleState);
		assertEquals(20,c1.getInvincibleTime());
		c1.moveDown(maze);
		assertEquals(19,c1.getInvincibleTime());
		List<Entity> box = maze.findEntity(2, 1);
		assertTrue(box.isEmpty());
		
	}
	
	@Test
	public void testPotionRunOut() {
		Dungeon maze = new Dungeon(15, 15);
		Player c1 = new Player(maze, 1, 1);
		InvincibilityPotion c2 = new InvincibilityPotion(2,1);
		maze.addEntity(c1);
		maze.setPlayer(c1);
		maze.addEntity(c2);
		c1.registerObservers();
		
		// Pickup, time = 20
		c1.moveRight(maze);
		
		//Time = 19
		c1.moveDown(maze);
		c1.moveUp(maze);
		c1.moveDown(maze);
		c1.moveUp(maze);
		c1.moveDown(maze);
		c1.moveUp(maze);
		c1.moveDown(maze);
		c1.moveUp(maze);
		c1.moveDown(maze);
		c1.moveUp(maze);
		c1.moveDown(maze);
		c1.moveUp(maze);
		c1.moveDown(maze);
		c1.moveUp(maze);
		c1.moveDown(maze);
		c1.moveUp(maze);
		c1.moveDown(maze);
		c1.moveUp(maze);
		c1.moveDown(maze);
		c1.moveUp(maze);
		assertEquals(0,c1.getInvincibleTime());
		assertTrue(c1.getPlayerState() instanceof NormalState);
	}
	
	@Test
	public void testPotionKill() {
		Dungeon maze = new Dungeon(15, 15);
		Player c1 = new Player(maze, 1, 1);
		InvincibilityPotion c2 = new InvincibilityPotion(2,1);
		Enemy c3 = new Enemy(4,1);
		Wall c4 = new Wall(6,1);
		maze.addEntity(c1);
		maze.setPlayer(c1);
		maze.addEntity(c2);
		maze.addEntity(c3);
		maze.addEntity(c4);
		c1.registerObservers();
		
		//pickup potion, enemy in next cell
		c1.moveRight(maze);
		// Chase enemy
		c1.moveRight(maze);
		// Chase enemy, enemy at wall
		c1.moveRight(maze);
		// kill enemy
		c1.moveRight(maze);
		
		// enemy was killed, only player is in (5, 1) now
		List<Entity> entities = maze.findEntity(5,1);
		assertEquals(1, entities.size());
		assertTrue(entities.contains(c1));
	}
}
