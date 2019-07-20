package unsw.test;

import unsw.dungeon.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;


import java.util.List;

import org.junit.jupiter.api.BeforeEach;


public class WeaponTest {
	private Dungeon dungeon;
	
	@BeforeEach
	public void setUp() {
		dungeon = new Dungeon(5,5);
		
	}
	
	@Test
	public void testSwordKill() {
		/*
		 *   0 1 2 3
		 * 0 P S _ _
		 * 1 _ E _ _
		 * 2 _ E _ _
		 * 3 _ _ _ _
		 */
		dungeon = new Dungeon(5, 5);
		Player player = new Player(dungeon, 0, 0);
		dungeon.addEntity(player);
		Sword sword = new Sword(1, 0);
		dungeon.addEntity(sword);
		Enemy enemy1 = new Enemy(1, 1);
		dungeon.addEntity(enemy1);
		Enemy enemy2 = new Enemy(1, 2);
		dungeon.addEntity(enemy2);
		
		enemy1.moveUp(dungeon);
		enemy1.moveDown(dungeon);
		
		List<Entity> entities = dungeon.findEntity(1, 0);
		assertTrue(entities.contains(sword));
		
		player.moveRight(dungeon);
		entities = dungeon.findEntity(1, 0);
		System.out.println(entities);
		assertEquals(1, entities.size());
		assertTrue(entities.contains(player));
		assertFalse(entities.contains(sword));
		assertEquals(5, player.getSwordNum());
		
		player.moveDown(dungeon);
		entities = dungeon.findEntity(1, 1);
		assertEquals(1, entities.size());
		assertTrue(entities.contains(player));
		assertEquals(4, player.getSwordNum());
		
		player.moveDown(dungeon);
		entities = dungeon.findEntity(1,2);
		
		
		// enemy was killed, only player is in (1, 2) now
		assertEquals(1, entities.size());
		assertTrue(entities.contains(player));
		assertEquals(3, player.getSwordNum());
	}
	
	
	
	
}
