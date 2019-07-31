package unsw.movementStrategy;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;

public abstract class dfsSearchPath {
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
			int checkX = this.indexToCoordX(i, dungeon);
			int checkY = this.indexToCoordY(i, dungeon);
			
			// only put the vertex that can move onto in the visited
			if (dungeon.canMoveOnto(entity, checkX, checkY)) {
				visited[i] = -1;
			}
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
				
				// if the vertex can not be moved onto, continue
				if (visited[w] == -2) {
					continue;
				}
				
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
 		
 		// when there is rectangular dungeon
 		if (dungeon.getWidth() != dungeon.getHeight()) {
 			return index / dungeon.getWidth();
 		}
 		
 		// when there is square dungeon
 		return index / dungeon.getHeight();
 	}
	
}
