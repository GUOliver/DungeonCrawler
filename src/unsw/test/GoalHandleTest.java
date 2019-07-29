package unsw.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import unsw.compositeGoal.*;
import unsw.dungeon.*;

class GoalHandleTest {
	
	/**	  0 1 2 3 4 5 6 7
	 *  0 p
	 *  2 s
	 *  3
	 *  4
	 *  5 e
	 *  6 e
	 *  7 e
	 */
	@Test
	public void testEnemy() {
		Dungeon maze = new Dungeon(10, 10);
		Player c1 = new Player(maze, 0, 0);
		Enemy c2 = new Enemy(0,5);
		Sword c3 = new Sword(0,1);
		Enemy c4 = new Enemy(0,6);
		Enemy c5 = new Enemy(0,7);
		maze.addEntity(c1);
		maze.setPlayer(c1);
		maze.addEntity(c2);
		maze.addEntity(c3);
		maze.addEntity(c4);
		maze.addEntity(c5);
		c1.registerObservers();
		LeafEnemy goal = new LeafEnemy();
		maze.setGoal(goal);
		
//		System.out.printf("Player at (%d,%d)\n",c1.getX(),c1.getY());
//		System.out.printf("c2 enemy at (%d,%d)\n",c2.getX(),c2.getY());
//		System.out.printf("c4 enemy at (%d,%d)\n",c4.getX(),c4.getY());
//		System.out.printf("c5 enemy at (%d,%d)\n",c5.getX(),c5.getY());
//		System.out.println();
		assertEquals(3,maze.getEnemyTotal());
		
		c1.moveDown(maze);
//		System.out.printf("Player at (%d,%d)\n",c1.getX(),c1.getY());
//		System.out.printf("c2 enemy at (%d,%d)\n",c2.getX(),c2.getY());
//		System.out.printf("c4 enemy at (%d,%d)\n",c4.getX(),c4.getY());
//		System.out.printf("c5 enemy at (%d,%d)\n",c5.getX(),c5.getY());
//		System.out.println();
		
		c1.moveDown(maze);
//		System.out.printf("Player at (%d,%d)\n",c1.getX(),c1.getY());
//		System.out.printf("c2 enemy at (%d,%d)\n",c2.getX(),c2.getY());
//		System.out.printf("c4 enemy at (%d,%d)\n",c4.getX(),c4.getY());
//		System.out.printf("c5 enemy at (%d,%d)\n",c5.getX(),c5.getY());
//		System.out.println();
		
		c1.moveDown(maze);
//		System.out.printf("Player at (%d,%d)\n",c1.getX(),c1.getY());
//		System.out.printf("c2 enemy at (%d,%d)\n",c2.getX(),c2.getY());
//		System.out.printf("c4 enemy at (%d,%d)\n",c4.getX(),c4.getY());
//		System.out.printf("c5 enemy at (%d,%d)\n",c5.getX(),c5.getY());
//		System.out.println();
		assertEquals(1,maze.getEnemyTotal());
		
		c1.moveDown(maze);
//		System.out.printf("Player at (%d,%d)\n",c1.getX(),c1.getY());
//		System.out.printf("c2 enemy at (%d,%d)\n",c2.getX(),c2.getY());
//		System.out.printf("c4 enemy at (%d,%d)\n",c4.getX(),c4.getY());
//		System.out.printf("c5 enemy at (%d,%d)\n",c5.getX(),c5.getY());
//		System.out.println();
		assertEquals(0,maze.getEnemyTotal());
		assertTrue(maze.getGameState());
	}
	
	@Test
	public void testExit() {
		Dungeon maze = new Dungeon(3, 3);
		Player c1 = new Player(maze, 0, 0);
		Exit c2 = new Exit(0,2);
		maze.addEntity(c1);
		maze.setPlayer(c1);
		maze.addEntity(c2);
		c1.registerObservers();
		LeafExit goal = new LeafExit();
		maze.setGoal(goal);
		
		assertTrue(!maze.isReachExit());
		assertTrue(!maze.getGameState());
		
		c1.moveDown(maze);
		assertTrue(!maze.isReachExit());
		assertTrue(!maze.getGameState());
		
		c1.moveDown(maze);
		assertTrue(maze.isReachExit());
		assertTrue(maze.getGameState());
	}
	
	@Test
	public void testSwitch() {
		Dungeon maze = new Dungeon(4, 4);
		Player c1 = new Player(maze, 0, 0);
		FloorSwitch c2 = new FloorSwitch(0,3);
		Boulder c3 = new Boulder(0,2);
		maze.addEntity(c1);
		maze.setPlayer(c1);
		maze.addEntity(c2);
		maze.addEntity(c3);
		c1.registerObservers();
		LeafSwitch goal = new LeafSwitch();
		maze.setGoal(goal);
		
		assertEquals(0,maze.getBoulderOnSwitch());
		assertTrue(!maze.getGameState());
		
		c1.moveDown(maze);
		assertEquals(0,maze.getBoulderOnSwitch());
		assertTrue(!maze.getGameState());
		
		c1.moveDown(maze);
		assertEquals(1,maze.getBoulderOnSwitch());
		assertTrue(maze.getGameState());
	}
	
	@Test
	public void testTreasure() {
		Dungeon maze = new Dungeon(3, 3);
		Player c1 = new Player(maze, 0, 0);
		Treasure c2 = new Treasure(0,2);
		Treasure c3 = new Treasure(0,1);
		maze.addEntity(c1);
		maze.setPlayer(c1);
		maze.addEntity(c2);
		maze.addEntity(c3);
		c1.registerObservers();
		LeafTreasure goal = new LeafTreasure();
		maze.setGoal(goal);
		
		assertEquals(2,maze.getTreasureTotal());
		assertTrue(!maze.getGameState());
		
		c1.moveDown(maze);
		assertEquals(1,maze.getTreasureTotal());
		assertTrue(!maze.getGameState());
		
		c1.moveDown(maze);
		assertEquals(0,maze.getTreasureTotal());
		assertTrue(maze.getGameState());
	}
	
	@Test
	public void testTreasureANDExit() {
		Dungeon maze = new Dungeon(4, 4);
		Player c1 = new Player(maze, 0, 0);
		Treasure c2 = new Treasure(0,2);
		Treasure c3 = new Treasure(0,1);
		Exit c4 = new Exit(0,3);
		maze.addEntity(c1);
		maze.setPlayer(c1);
		maze.addEntity(c2);
		maze.addEntity(c3);
		maze.addEntity(c4);
		c1.registerObservers();
		LeafTreasure treasure = new LeafTreasure();
		LeafExit exit = new LeafExit();
		CompositeAND goal = new CompositeAND();
		goal.add(treasure);
		goal.add(exit);
		maze.setGoal(goal);
		
		assertTrue(!maze.getGameState());
		
		c1.moveDown(maze);
		assertTrue(!maze.getGameState());
		
		c1.moveDown(maze);
		assertTrue(!maze.getGameState());
		
		c1.moveDown(maze);
		assertTrue(maze.getGameState());
	}
	
	@Test
	public void testTreasureORExit() {
		Dungeon maze = new Dungeon(4, 4);
		Player c1 = new Player(maze, 0, 0);
		Treasure c2 = new Treasure(1,2);
		Treasure c3 = new Treasure(1,1);
		Exit c4 = new Exit(0,3);
		maze.addEntity(c1);
		maze.setPlayer(c1);
		maze.addEntity(c2);
		maze.addEntity(c3);
		maze.addEntity(c4);
		c1.registerObservers();
		LeafTreasure treasure = new LeafTreasure();
		LeafExit exit = new LeafExit();
		CompositeOR goal = new CompositeOR();
		goal.add(treasure);
		goal.add(exit);
		maze.setGoal(goal);
		
		assertTrue(!maze.getGameState());
		
		c1.moveDown(maze);
		assertTrue(!maze.getGameState());
		
		c1.moveDown(maze);
		assertTrue(!maze.getGameState());
		
		c1.moveDown(maze);
		assertTrue(maze.getGameState());
	}
	
	@Test
	public void testTreasureANDExitOREnemy() {
		Dungeon maze = new Dungeon(1, 15);
		Player c1 = new Player(maze, 0, 0);
		Treasure c2 = new Treasure(0,2);
		Treasure c3 = new Treasure(0,1);
		Exit c4 = new Exit(0,3);
		Enemy c5 = new Enemy(0,14);
		maze.addEntity(c1);
		maze.setPlayer(c1);
		maze.addEntity(c2);
		maze.addEntity(c3);
		maze.addEntity(c4);
		maze.addEntity(c5);
		c1.registerObservers();
		LeafTreasure treasure = new LeafTreasure();
		LeafExit exit = new LeafExit();
		LeafEnemy enemy = new LeafEnemy();
		CompositeOR subgoal = new CompositeOR();
		CompositeAND goal = new CompositeAND();
		subgoal.add(enemy);
		subgoal.add(exit);
		goal.add(treasure);
		goal.add(subgoal);
		maze.setGoal(goal);
		
		assertTrue(!maze.getGameState());
		
		c1.moveDown(maze);
		assertTrue(!maze.getGameState());
		
		c1.moveDown(maze);
		assertTrue(!maze.getGameState());
		
		c1.moveDown(maze);
		assertTrue(maze.getGameState());
	}
	
	@Test
	public void testTreasureANDEnemyORExit() {
		Dungeon maze = new Dungeon(1, 15);
		Player c1 = new Player(maze, 0, 0);
		Treasure c2 = new Treasure(0,2);
		Treasure c3 = new Treasure(0,1);
		Exit c4 = new Exit(0,14);
		Enemy c5 = new Enemy(0,6);
		maze.addEntity(c1);
		maze.setPlayer(c1);
		maze.addEntity(c2);
		maze.addEntity(c3);
		maze.addEntity(c4);
		maze.addEntity(c5);
		c1.registerObservers();
		LeafTreasure treasure = new LeafTreasure();
		LeafExit exit = new LeafExit();
		LeafEnemy enemy = new LeafEnemy();
		CompositeOR subgoal = new CompositeOR();
		CompositeAND goal = new CompositeAND();
		subgoal.add(enemy);
		subgoal.add(exit);
		goal.add(treasure);
		goal.add(subgoal);
		maze.setGoal(goal);
		
		assertTrue(!maze.getGameState());
		
		c1.moveDown(maze);
		assertTrue(!maze.getGameState());
		
		c1.moveDown(maze);
		assertTrue(!maze.getGameState());
		
		c1.moveDown(maze);
		assertTrue(maze.getGameState());
	}

}
