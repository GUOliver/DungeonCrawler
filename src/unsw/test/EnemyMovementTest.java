package unsw.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import unsw.dungeon.*;
import unsw.playerState.InvincibleState;
public class EnemyMovementTest {
	
	@Test
	public void testMoveLeft() {
		Dungeon maze = new Dungeon(15, 15);
		Player c1 = new Player(maze, 1, 1);
		Enemy c2 = new Enemy(3,1);
		Enemy c3 = new Enemy(6,6);
		maze.addEntity(c1);
		maze.setPlayer(c1);
		maze.addEntity(c2);
		maze.addEntity(c3);
		c1.registerObservers();
		assertEquals(2,maze.getEnemyTotal());
		
		c1.moveLeft(maze);
		assertEquals(2,c2.getX());
	}
	
	@Test
	public void testMoveRight() {
		Dungeon maze = new Dungeon(15, 15);
		Player c1 = new Player(maze, 5, 1);
		Enemy c2 = new Enemy(3,1);
		maze.addEntity(c1);
		maze.setPlayer(c1);
		maze.addEntity(c2);
		c1.registerObservers();
		
		c1.moveRight(maze);
		assertEquals(4,c2.getX());
	}
	
	@Test
	public void testMoveUp() {
		Dungeon maze = new Dungeon(15, 15);
		Player c1 = new Player(maze, 2, 1);
		Enemy c2 = new Enemy(3,6);
		maze.addEntity(c1);
		maze.setPlayer(c1);
		maze.addEntity(c2);
		c1.registerObservers();
		
		c1.moveRight(maze);
		assertEquals(5,c2.getY());
	}
	
	@Test
	public void testMoveDown() {
		Dungeon maze = new Dungeon(15, 15);
		Player c1 = new Player(maze, 2, 6);
		Enemy c2 = new Enemy(3,1);
		maze.addEntity(c1);
		maze.setPlayer(c1);
		maze.addEntity(c2);
		c1.registerObservers();
		
		c1.moveRight(maze);
		assertEquals(2,c2.getY());
	}
	
	
	//  0 1 2 3 4
	// 0  _ e _ _ _
	// 1  _ w _ _ _
	// 2  ->p
	// 3
	// 4
	@Test
	public void testMovementCheckingWall() {
		Dungeon maze = new Dungeon(5, 5);
		Player c1 = new Player(maze, 1, 2);
		Enemy c2 = new Enemy(2,0);
		Wall c3 = new Wall(2,1);
		maze.addEntity(c1);
		maze.setPlayer(c1);
		maze.addEntity(c2);
		maze.addEntity(c3);
		c1.registerObservers();
		
		c1.moveRight(maze);
		assertEquals(0,c2.getY());
	}
	
	//  0 1 2 3 4 5
	// 0  _ _ _ _ _
	// 1  _ e b _ p
	// 2
	// 3
	// 4
	@Test
	public void testMovementPushingBoulder() {
		Dungeon maze = new Dungeon(6, 6);
		Player c1 = new Player(maze, 5, 0);
		Enemy c2 = new Enemy(2,1);
		Boulder c3 = new Boulder(3,1);
		maze.addEntity(c1);
		maze.setPlayer(c1);
		maze.addEntity(c2);
		maze.addEntity(c3);
		c1.registerObservers();
		
		c1.moveDown(maze);
		assertEquals(2,c2.getX());
	}
	
	@Test
	public void testInteractKey() {
		Dungeon maze = new Dungeon(7, 7);
		Player c1 = new Player(maze, 5, 0);
		Enemy c2 = new Enemy(2,1);
		Key c3 = new Key(3,1);
		maze.addEntity(c1);
		maze.setPlayer(c1);
		maze.addEntity(c2);
		maze.addEntity(c3);
		c1.registerObservers();
		
		c1.moveDown(maze);
		c1.moveRight(maze);
		assertEquals(4,c2.getX());
		// Check item still exists
		List<Entity> box = maze.findEntity(3, 1);
		assertTrue(!box.isEmpty());
	}
	
	@Test
	public void testInteractSwitch() {
		Dungeon maze = new Dungeon(7, 7);
		Player c1 = new Player(maze, 5, 0);
		Enemy c2 = new Enemy(2,1);
		FloorSwitch c3 = new FloorSwitch(3,1);
		maze.addEntity(c1);
		maze.setPlayer(c1);
		maze.addEntity(c2);
		maze.addEntity(c3);
		c1.registerObservers();
		
		c1.moveDown(maze);
		c1.moveRight(maze);
		assertEquals(4,c2.getX());
		// Check item still exists
		List<Entity> box = maze.findEntity(3, 1);
		assertTrue(!box.isEmpty());
	}
	
	@Test
	public void testInteractTreasure() {
		Dungeon maze = new Dungeon(7, 7);
		Player c1 = new Player(maze, 5, 0);
		Enemy c2 = new Enemy(2,1);
		Treasure c3 = new Treasure(3,1);
		maze.addEntity(c1);
		maze.setPlayer(c1);
		maze.addEntity(c2);
		maze.addEntity(c3);
		c1.registerObservers();
		
		c1.moveDown(maze);
		c1.moveRight(maze);
		assertEquals(4,c2.getX());
		// Check item still exists
		List<Entity> box = maze.findEntity(3, 1);
		assertTrue(!box.isEmpty());
	}
	
	@Test
	public void testInteractDoorOpen() {
		Dungeon maze = new Dungeon(7, 7);
		Player c1 = new Player(maze, 5, 0);
		Enemy c2 = new Enemy(2,1);
		Door c3 = new Door(3,1);
		c3.setIsOpen(true);
		maze.addEntity(c1);
		maze.setPlayer(c1);
		maze.addEntity(c2);
		maze.addEntity(c3);
		c1.registerObservers();
		
		c1.moveDown(maze);
		c1.moveRight(maze);

		// Enemy moves through open door
		assertEquals(4,c2.getX());
	}
	
	@Test
	public void testInteractDoorClosed() {
		Dungeon maze = new Dungeon(7, 7);
		Player c1 = new Player(maze, 5, 0);
		Enemy c2 = new Enemy(2,1);
		Door c3 = new Door(3,1);
		maze.addEntity(c1);
		maze.setPlayer(c1);
		maze.addEntity(c2);
		maze.addEntity(c3);
		c1.registerObservers();
		
		c1.moveDown(maze);
		c1.moveRight(maze);

		// Enemy cant move through closed door
		assertEquals(2,c2.getX());
	}
	
	@Test
	public void testInteractPotion() {
		Dungeon maze = new Dungeon(7, 7);
		Player c1 = new Player(maze, 5, 0);
		Enemy c2 = new Enemy(2,1);
		InvincibilityPotion c3 = new InvincibilityPotion(3,1);
		maze.addEntity(c1);
		maze.setPlayer(c1);
		maze.addEntity(c2);
		maze.addEntity(c3);
		c1.registerObservers();
		
		c1.moveDown(maze);
		c1.moveRight(maze);
		assertEquals(4,c2.getX());
		// Check item still exists
		List<Entity> box = maze.findEntity(3, 1);
		assertTrue(!box.isEmpty());
	}
	
	@Test
	public void testInteractSword() {
		Dungeon maze = new Dungeon(7, 7);
		Player c1 = new Player(maze, 5, 0);
		Enemy c2 = new Enemy(2,1);
		Sword c3 = new Sword(3,1);
		maze.addEntity(c1);
		maze.setPlayer(c1);
		maze.addEntity(c2);
		maze.addEntity(c3);
		c1.registerObservers();
		
		c1.moveDown(maze);
		c1.moveRight(maze);
		assertEquals(4,c2.getX());
		// Check item still exists
		List<Entity> box = maze.findEntity(3, 1);
		assertTrue(!box.isEmpty());
	}
	
	@Test
	public void testMoveDownInvincible() {
		Dungeon maze = new Dungeon(15, 15);
		Player c1 = new Player(maze, 2, 6);
		Enemy c2 = new Enemy(3,1);
		maze.addEntity(c1);
		maze.setPlayer(c1);
		maze.addEntity(c2);
		c1.registerObservers();
		c1.setPlayerState(new InvincibleState());
		
		c1.moveRight(maze);
		assertEquals(0,c2.getY());
	}
	
	
}
