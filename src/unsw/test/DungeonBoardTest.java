package unsw.test;

import unsw.dungeon.*;

public class DungeonBoardTest {
	
	
	
	void settingUpTest() {
		Dungeon testDungeon = new Dungeon(10, 10);
		assert(testDungeon.getHeight() == 10);
		assert(testDungeon.getWidth() == 10);
		
	}
	
	void addingPlayer() {
		Dungeon testDungeon = new Dungeon(10, 10);
		Player oliver = new Player(testDungeon, 1, 1);
		testDungeon.addEntity(oliver);
	}
	
	
	
	void positionTest() {
		Dungeon testDungeon = new Dungeon(10, 10);
		Player oliver = new Player(testDungeon, 0, 0);
		oliver.setX(1);
		oliver.setY(2);
		assert(oliver.getX() == 1);
		assert(oliver.getY() == 2);
	}
	
	
	void moveCapabilityTest() {
		Dungeon testDungeon = new Dungeon(10, 10);
		Player player = new Player(testDungeon, 1, 1);
		FloorSwitch switch1 = new FloorSwitch(1, 2);
		testDungeon.addEntity(player);
		testDungeon.addEntity(switch1);
		
		// player can walk onto floor switch
		//assert(testDungeon.moveChecking(player, 1, 2) == true);
		
		// player can not move onto wall
		Wall wall = new Wall(2, 3);
		testDungeon.addEntity(wall);
		//assert(testDungeon.moveChecking(player, 1, 3) == false);
	}
	
}
