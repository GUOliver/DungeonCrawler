package unsw.test;

import unsw.dungeon.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
		dungeon = new Dungeon(5 ,5);
		Player player = new Player(dungeon, 2 ,3);
		Bomb bomb = new Bomb(2,2);
		dungeon.addEntity(bomb);
		dungeon.addEntity(player);
		dungeon.setPlayer(player);
		
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
		player.dropBomb();
		
		bomb.tickTock(dungeon);
		assertEquals(2, bomb.getTick());
		bomb.tickTock(dungeon);
		assertEquals(1, bomb.getTick());
		bomb.tickTock(dungeon);
		assertEquals(0, bomb.getTick());
		
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



	
}
