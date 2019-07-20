package unsw.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import unsw.dungeon.*;

// User Story 1.2: movable boulders and boulder-activated floor switches
public class BoulderTest {

	//   0 1 2 3 4
	// 0 _ _ _ _ _
	// 1 _ p b _ _
	// 2
	// 3
	// 4
	@Test
	public void testMovementPushingBoulder() {
		Dungeon maze = new Dungeon(5, 5);
		Player c1 = new Player(maze, 1, 1);
		Boulder boulder = new Boulder(2, 1);
		maze.addEntity(c1);
		maze.addEntity(boulder);
		maze.setPlayer(c1);
		
		assertTrue(boulder.getX() == 2);
		assertTrue(boulder.getY() == 1);
		
		assertTrue(c1.getX() == 1);
		assertTrue(c1.getY() == 1);
		
		assertTrue(boulder.canMoveOnto(maze, c1));
		
		c1.moveRight(maze);
		assertTrue(c1.getX() == 2);
		assertTrue(c1.getY() == 1);
		assertTrue(c1.getDirection() == Direction.Right);
		
		c1.moveUp(maze);
		// the boulder was pushed one grid right, successfully
		assertTrue(maze.findEntity(2, 1).size() == 0);
		assertTrue(boulder.getX() == 3);
		assertTrue(boulder.getY() == 1);
		//  0 1 2 3 4
		// 0  _ _ P _ _
		// 1  _ _ b E _
		// 2	  B
		// 3	  
		// 4
		
		FloorSwitch sw = new FloorSwitch(3, 2);
		maze.addEntity(sw);
		assertEquals(1, maze.getSwitchTotal());
		c1.moveRight(maze);
		c1.moveDown(maze);
		assertEquals(1, maze.getBoulderOnSwitch());
		assertTrue(boulder.getX() == 3);
		assertTrue(boulder.getY() == 2);
		// pushing the boulder into the switch, it is activated now
		assertTrue(sw.isActivated() == true);
	}
	
	@Test
	public void moveBoulderOffSwitchSucessful() {
		/*   0 1 2        0 1 2
		 * 0 _ _ _      0 _ _ _
		 * 1 P S _  --> 1 _pf B
		 * 2 _ _ _      2 _ _ _
		 */
		Dungeon dungeon = new Dungeon(3, 3);
		Player player = new Player(dungeon, 0, 1);
		Boulder boulder = new Boulder(1, 1);
		dungeon.addEntity(player);
		dungeon.addEntity(boulder);
		
		FloorSwitch sw = new FloorSwitch(1, 1);
		dungeon.addEntity(sw);
		
		assertTrue(player.moveRight(dungeon));
		assertTrue(boulder.getX() == 2);
		assertTrue(boulder.getY() == 1);
		
		
		List<Entity> entities = dungeon.findEntity(1, 1);
		assertEquals(2, entities.size());
		assertTrue(entities.contains(player));
		assertTrue(entities.get(0) instanceof FloorSwitch || entities.get(1) instanceof FloorSwitch);
		
		entities = dungeon.findEntity(2, 1);
		assertEquals(1, entities.size());
		assertTrue(entities.get(0) instanceof Boulder);
	}
	
	@Test
	public void moveBoulderOffSwitchFail() {
		/*   0 1 2        0 1 2
		 * 0 _ _ _      0 _ _ _
		 * 1 P S W  --> 1 P b s
		 * 2 _ _ _      2 _ _ _
		 */
		Dungeon dungeon = new Dungeon(3, 3);
		Player player = new Player(dungeon, 0, 1);
		Boulder boulder = new Boulder(1, 1);
		dungeon.addEntity(player);
		dungeon.addEntity(boulder);
		FloorSwitch sw = new FloorSwitch(1, 1);
		dungeon.addEntity(sw);
		
		Wall wall = new Wall(2, 1);
		
		dungeon.addEntity(wall);
		
		player.moveRight(dungeon);
		
		// should be the same place because cannot push the boulder
		// to the wall
		
		assertTrue(player.getX() == 0);
		assertTrue(player.getY() == 1);
		
		List<Entity> entities = dungeon.findEntity(0, 1);
		assertTrue(entities.contains(player));
		assertEquals(1, entities.size());
		
		entities = dungeon.findEntity(1, 1);
		assertTrue(entities.contains(sw));
		assertEquals(2, entities.size());  // boulder and switch
		
		entities = dungeon.findEntity(2, 1);
		assertTrue(entities.contains(wall));
		assertEquals(1, entities.size());  // wall only
		
		assertTrue(player.moveUp(dungeon));
		assertTrue(player.moveRight(dungeon));
		assertTrue(player.moveDown(dungeon));
		
		entities = dungeon.findEntity(1, 1);
		assertTrue(entities.contains(player));
		assertEquals(2, entities.size());
		assertTrue(entities.get(0) instanceof FloorSwitch || entities.get(1) instanceof FloorSwitch);
		
		entities = dungeon.findEntity(1,2);
		assertEquals(1, entities.size());
		assertTrue(entities.get(0) instanceof Boulder);
	}
	
	
	
	

}
