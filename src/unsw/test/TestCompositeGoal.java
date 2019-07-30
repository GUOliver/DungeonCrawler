package unsw.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;


import unsw.compositeGoal.LeafExit;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.Entity;
import unsw.dungeon.Player;
import unsw.dungeon.Treasure;

public class TestCompositeGoal {
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
		LeafExit exit = new LeafExit();
		maze.setGoal(exit);
		
		c1.moveDown(maze);
		assertEquals(c2.getX(),3);
		
		c1.moveRight(maze);
		assertEquals(c2.getX(),4);
		// Check item still exists
		List<Entity> box = maze.findEntity(3, 1);
		assertTrue(!box.isEmpty());
	}
}
