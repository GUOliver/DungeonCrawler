package unsw.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import unsw.compositeGoal.LeafExit;
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
		LeafExit exit = new LeafExit();
		maze.setGoal(exit);
		
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
		LeafExit exit = new LeafExit();
		maze.setGoal(exit);
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
		LeafExit exit = new LeafExit();
		maze.setGoal(exit);
		
		c1.moveDown(maze);
		assertEquals(1,c1.getBombNum());
		c1.dropBomb();
		assertEquals(0,c1.getBombNum());
		c1.moveDown(maze);
		List<Entity> box = maze.findEntity(1, 2);
		assertTrue(box.get(0) instanceof Bomb);
	}
	
	
	@Test
	public void testBomb() {
		/*
		 *   0 1 2 3 4         0 1 2 3 4
		 * 0 _ _ _ _ _       0 _ _ _ _ _
		 * 1 _ E W B _       1 _ E _ B _ 
		 * 2 _ E L B _  -->  2 _ _ _ _ _ 
		 * 3 _ _ P _ _       3 _ _ _ _ _
		 * 4 _ _ _ _ _       4 _ _ _ _ _
		 */
		Dungeon dungeon = new Dungeon(5 ,5);
		Player player = new Player(dungeon, 2 ,3);
		Bomb bomb = new Bomb(2,2);
		dungeon.addEntity(bomb);
		dungeon.addEntity(player);
		dungeon.setPlayer(player);
		LeafExit exit = new LeafExit();
		dungeon.setGoal(exit);
		
		Enemy enemy1 = new Enemy(1,1);
		Enemy enemy2 = new Enemy(1,2);
		Boulder boulder1 = new Boulder(3, 1);
		Boulder boulder2 = new Boulder(3, 2);
		Wall wall = new Wall(2, 1);
		
		dungeon.addEntity(enemy1);
		dungeon.addEntity(enemy2);
		dungeon.addEntity(boulder1);
		dungeon.addEntity(boulder2);
		dungeon.addEntity(wall);
		
		// get the unlit bomb
		player.moveUp(dungeon);
		bomb = player.dropBomb();
		
		//bomb.tickTock(dungeon);
		player.moveDown(dungeon);
		assertEquals(3, bomb.getTick());
		//bomb.tickTock(dungeon);
		player.moveDown(dungeon);
		assertEquals(2, bomb.getTick());
		//bomb.tickTock(dungeon);
		player.moveLeft(dungeon);
		assertEquals(1, bomb.getTick());
		player.moveLeft(dungeon);
		player.moveRight(dungeon);
		
		List<Entity> entities = dungeon.findEntity(2,2);
		
		assertEquals(0, entities.size());
		entities = dungeon.findEntity(2, 3);
		assertEquals(0, entities.size());
		entities = dungeon.findEntity(1, 2);
		assertEquals(0, entities.size());
		entities = dungeon.findEntity(2, 1);
		assertTrue(entities.contains(wall));
		entities = dungeon.findEntity(1,1);
		assertTrue(entities.contains(enemy1));
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
		LeafExit exit = new LeafExit();
		maze.setGoal(exit);
		
		// Player picks up bomb
		boolean checkMove = c1.moveLeft(maze);
		assertTrue(checkMove);
		// Player places bomb
		c2 = c1.dropBomb();
		// Can move off the lit bomb
		checkMove = c1.moveRight(maze);
		assertTrue(checkMove);
		//Can't move back onto lit bomb
		checkMove = c1.moveLeft(maze);
		assertTrue(!checkMove);
		assertEquals(3, c2.getTick());
		
	}
	
	@Test
	public void testExplodedBombPickup() {
		Dungeon maze = new Dungeon(15,15);
		Player c1 = new Player(maze, 2, 1);
		Bomb c2 = new Bomb(1,1);
		maze.addEntity(c1);
		maze.setPlayer(c1);
		maze.addEntity(c2);
		c1.registerObservers();
		LeafExit exit = new LeafExit();
		maze.setGoal(exit);
		
		// Player picks up bomb
		boolean checkMove = c1.moveLeft(maze);
		assertTrue(checkMove);
		// Player places bomb
		Bomb bomb = c1.dropBomb();
		assertTrue(bomb!=null);
		// Can move off the lit bomb
		c1.moveRight(maze);
		assertEquals(3, bomb.getTick());
		c1.moveRight(maze);
		assertEquals(2, bomb.getTick());
		c1.moveRight(maze);
		assertEquals(1, bomb.getTick());
		c1.moveRight(maze);
		assertEquals(0, bomb.getTick());
		
		c1.moveLeft(maze);
		c1.moveLeft(maze);
		c1.moveLeft(maze);
		c1.moveLeft(maze);
		c1.moveLeft(maze);
		assertEquals(0,c1.getBombNum());
		
	}
	
}