package unsw.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import unsw.dungeon.*;
public class DungeonBoardTest {
	
	
	@Test
	public void settingUpTest() {
		Dungeon testDungeon = new Dungeon(10, 10);
		assertTrue(testDungeon.getHeight() == 10);
		assertTrue(testDungeon.getWidth() == 10);
		
	}
	
	@Test
	public void addingPlayer() {
		Dungeon testDungeon = new Dungeon(10, 10);
		Player oliver = new Player(testDungeon, 1, 1);
		testDungeon.addEntity(oliver);
	}
	
	
	@Test
	public void positionTest() {
		Dungeon testDungeon = new Dungeon(10, 10);
		Player oliver = new Player(testDungeon, 0, 0);
		oliver.setX(1);
		oliver.setY(2);
		assertTrue(oliver.getX() == 1);
		assertTrue(oliver.getY() == 2);
	}
	
	@Test
	public void moveCapabilityTest() {
		Dungeon testDungeon = new Dungeon(10, 10);
		Player player = new Player(testDungeon, 1, 1);
		FloorSwitch switch1 = new FloorSwitch(1, 2);
		testDungeon.addEntity(player);
		testDungeon.addEntity(switch1);
		
		// player can walk onto floor switch
		assertTrue(switch1.canMoveOnto(testDungeon, player));
		
		// player can not move onto wall
		Wall wall = new Wall(2, 3);
		testDungeon.addEntity(wall);
		assertFalse(wall.canMoveOnto(testDungeon, player));
	}
	
}
