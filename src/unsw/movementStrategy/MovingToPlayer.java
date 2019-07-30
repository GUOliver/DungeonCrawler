package unsw.movementStrategy;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.Entity;
import unsw.dungeon.MovingEntity;


public class MovingToPlayer implements MovementStrategy{
	
	/**
	 * Defining behaviour for an enemy moving away from player position
	 * Extra entities can be added, does not assume entity is enemy
	 */
	@Override
	public void moveDirection(Dungeon dungeon, MovingEntity entity) {
		
		
		if (entity.isEnemy()) {
			Enemy enemy = (Enemy)entity;
			//int dungeonSize = dungeon.getHeight() * dungeon.getWidth();
//			if (enemy.getX()<enemy.getPlayerX()) {
//				enemy.moveRight(dungeon);
//			} else if (enemy.getX()>enemy.getPlayerX()) {
//				enemy.moveLeft(dungeon);
//			} else if (enemy.getY()<enemy.getPlayerY()) {
//				enemy.moveDown(dungeon);
//			} else if (enemy.getY()>enemy.getPlayerY()) {
//				enemy.moveUp(dungeon);
//			}
			
			// move the enemy towards player using dfs
			
			
			moveNext(dungeon, entity, enemy.getPlayerX(), enemy.getPlayerY());
			
		}
	}

	
	public void moveNext(Dungeon dungeon, MovingEntity entity, int destX, int destY) {
		int fromX = entity.getX();
		int fromY = entity.getY();
		
//		System.out.println("going from (" + fromX + ", " + fromY + ") to (" + destX + ", " + destY + ")");
		if (fromX == destX && fromY == destY) {
			return;
		}
		
		if(! dungeon.canMoveOnto(entity, destX, destY)) {
			// if we can't get to the final square, return NONE
//			System.out.println("cant get to " + destX + "," + destY + " entities on point: " + dungeon.findEntity(destX, destY));
			return;
		}
		
		int dest = this.coordToIndex(destX, destY, dungeon);
//		System.out.println(dest);
		
		int src = this.coordToIndex(fromX, fromY, dungeon);
//		System.out.println(src);
		
		int dungeonSize = dungeon.getHeight() * dungeon.getWidth();
		
		int[] visited = new int[dungeonSize];
		if (this.findPathBFS(dungeon, entity, src, dest, visited)) {
			
			int v = dest;
			int pre = 0;
			while (v != src) {
//				System.out.printf("%d - ", v);
				pre = v;
				v = visited[v];
			}
//			System.out.printf("%d\n", src);
			
//			System.out.printf("next move: %d\n", pre);
			
			
	
			int nextX = this.indexToCoordX(pre, dungeon);
			int nextY = this.indexToCoordY(pre, dungeon);
//			System.out.printf("nextX: %d, nextY: %d\n", nextX, nextY);
			
			if (nextX == fromX -1 && nextY == fromY) {
				// means left
				((Enemy) entity).moveLeft(dungeon);
				
			} else if (nextX == fromX + 1 && nextY == fromY) {
				// means right
				((Enemy) entity).moveRight(dungeon);
			} else if (nextX == fromX && nextY == fromY - 1) {
				// means up
				((Enemy) entity).moveUp(dungeon);
			} else if (nextX == fromX && nextY == fromY + 1) {
				((Enemy) entity).moveDown(dungeon);
			} else {
//				System.out.println("not supposed to happen nextX = " + nextX + ", nextY = " + nextY);
				return;
			}
		} else {
			return;
		}
		
	}
	
	
	/**
	 * implementing Bfs algorithm to get the shortest Path
	 * @param dungeon
	 * @param charcter basically the enemy
	 * @param destX the coordX enemy would like to go to
	 * @param destY the coordY enemy would like to go to
	 * @return the List of integer which stores the distance to that coord Pair
	 */
	public Boolean findPathBFS(Dungeon dungeon, Entity entity, int src, int dest, int[] visited) {
		int v;
		
		for (int i = 0; i < visited.length; i++) {
			visited[i] = -1;
		}
		
		// udpate src
		visited[src] = src;
		// System.out.println(dest);
		Deque<Integer> q = new ArrayDeque<Integer>();
		q.addFirst(src);
		while (! q.isEmpty()) {
			v = q.removeLast();
			//System.out.println(v);
			for (int w = 0; w < visited.length; w++) {
				ArrayList<Integer> neighbours = new ArrayList<Integer>();
				this.addNeighbour(dungeon, neighbours, entity, v);
				
				if (visited[w] == -1 && neighbours.contains(w)) {
					visited[w] = v;
					if (w == dest) {
						return true;
					} else {
						q.addFirst(w);
					}
				}
			}
			
		}
		
		return false;
		
		
	}
		


	 	
	public ArrayList<Integer> addNeighbour(Dungeon dungeon, ArrayList<Integer>neighbours, 
					Entity character, int src) {
		
		// add each neighbour to the current vertex to an array list
	   // System.out.println(src);
		int nextX = this.indexToCoordX(src, dungeon);
		int nextY = this.indexToCoordY(src, dungeon);
		// System.out.printf("next x: %d, next y in neighbours: %d\n", nextX, nextY);
		// error checking is neccessary
		if(dungeon.isValidCoord(nextX - 1, nextY)) {
			if (dungeon.canMoveOnto(character, nextX - 1, nextY)) {
				int index = this.coordToIndex(nextX - 1, nextY, dungeon);
				neighbours.add(index);
			}
		}
		
		if(dungeon.isValidCoord(nextX + 1, nextY)) {
			if (dungeon.canMoveOnto(character, nextX + 1, nextY)) {
				int index = this.coordToIndex(nextX+1, nextY, dungeon);
				neighbours.add(index);
			}
		}
		if(dungeon.isValidCoord(nextX, nextY - 1)) {
			if (dungeon.canMoveOnto(character, nextX, nextY - 1)) {
				int index = this.coordToIndex(nextX, nextY - 1, dungeon);
				neighbours.add(index);
			}
		}
		if(dungeon.isValidCoord(nextX, nextY + 1)) {
			if (dungeon.canMoveOnto(character, nextX, nextY + 1)) {
				int index = this.coordToIndex(nextX, nextY + 1, dungeon);
				neighbours.add(index);
			}
		}
		
		return neighbours;
		
	}


	/**
	 * transform x and y coordindates to 1-d array index
	 * @param x the coord x
	 * @param y the coord y
	 * @param dungeon the dungoen in 
	 * @return the index in 1-d array
	 */
	public int coordToIndex (int x, int y, Dungeon dungeon) {
		return y * dungeon.getWidth() + x;
	}
	
	
	/**
	 * transform 1-d array index to x coord and y coord
	 * using the modular
	 * @param index 1-d array index 
	 * @param dungeon the dungeon currently in 
	 * @return the coord x
	 */
 	public int indexToCoordX (int index, Dungeon dungeon) {
 		return index % dungeon.getWidth();
 	}
 	
 	//   0  1  2
 	//0	 _  _  _
 	//1  _  _  _
 	//2  _  _  _
 	
 	/**
 	 * use integer part of the result to 
 	 * get the coord y from the 1-d array index
 	 * @param index the index
 	 * @param dungeon the dungeon
 	 * @return the row index from the dungeon 
 	 */
 	public int indexToCoordY (int index, Dungeon dungeon) {
 		// special case
 		// when width = 1 
 		if (dungeon.getWidth() == 1) {
 			return index;
 		}
 		return index / dungeon.getHeight();
 	}
	
	
}
