package unsw.movementStrategy;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.MovingEntity;

public class MovingAwayPlayer extends dfsSearchPath implements MovementStrategy{

	/**
	 * Defining behaviour for an enemy moving away from player position
	 * Extra entities can be added, does not assume entity is enemy
	 */
			
	@Override
	public void moveDirection(Dungeon dungeon, MovingEntity entity) {
		
		
		if (entity.isEnemy()) {
			Enemy enemy = (Enemy)entity;
			moveNext(dungeon, entity, enemy.getPlayerX(), enemy.getPlayerY());
			
		}
	}

	
	/**
	 * Calculates with dfs shortest path the correct next step, the opposite direction
	 * @param dungeon Current dungeon
	 * @param entity Moving entity
	 * @param destX X coord
	 * @param destY Y coord
	 */
	public void moveNext(Dungeon dungeon, MovingEntity entity, int destX, int destY) {
		int fromX = entity.getX();
		int fromY = entity.getY();
		Enemy enemy = (Enemy)entity;

//		System.out.println("going from (" + fromX + ", " + fromY + ") to (" + destX + ", " + destY + ")");
		if (fromX == destX && fromY == destY) {
			return;
		}
		
		if(! dungeon.canMoveOnto(entity, destX, destY)) {
			// if we can't get to the final square, return NONE
			//System.out.println("cant get to " + destX + "," + destY + " entities on point: " + dungeon.findEntity(destX, destY));
			return;
		}
		
		int dest = this.coordToIndex(destX, destY, dungeon);

		int src = this.coordToIndex(fromX, fromY, dungeon);
	
		int dungeonSize = dungeon.getHeight() * dungeon.getWidth();
		
		int[] visited = new int[dungeonSize];
		for (int i = 0; i < visited.length; i++) {
			visited[i] = -2;
		}
		
		if (this.findPathBFS(dungeon, entity, src, dest, visited)) {
			
			int v = dest;
			int pre = 0;
			while (v != src) {
				pre = v;
				v = visited[v];
			}
		
			int nextX = this.indexToCoordX(pre, dungeon);
			int nextY = this.indexToCoordY(pre, dungeon);			
			
			if (nextX == fromX -1 && nextY == fromY) {
				// means left orginally, but now has to move right
				nextX = fromX + 1;
				if (dungeon.canMoveOnto(enemy, nextX, nextY)) {
					enemy.moveRight(dungeon);
				} else {
					if (dungeon.canMoveOnto(enemy, fromX, fromY + 1) && dungeon.canMoveOnto(enemy, fromX, fromY - 1)) {
						
						if (Math.random() > 0.5) {enemy.moveUp(dungeon);}
						else {enemy.moveDown(dungeon);}
					} else if (dungeon.canMoveOnto(enemy, fromX, fromY + 1)) {
						enemy.moveDown(dungeon);
					} else {
						enemy.moveUp(dungeon);
					}
				}
				
				
			} else if (nextX == fromX + 1 && nextY == fromY) {
				nextX = fromX - 1;
				// means right, but now has to go left
				if (dungeon.canMoveOnto(enemy, nextX, nextY)) {
					enemy.moveLeft(dungeon);
				} else {
					if (dungeon.canMoveOnto(enemy, fromX, fromY + 1) && dungeon.canMoveOnto(enemy, fromX, fromY - 1)) {
						if (Math.random() < 0.5) {enemy.moveUp(dungeon);}
						else {enemy.moveDown(dungeon);}
					} else if (dungeon.canMoveOnto(enemy, fromX, fromY + 1)) {
						enemy.moveDown(dungeon);
					} else {
						enemy.moveUp(dungeon);
					}
				}
				
			} 
			
			else if (nextX == fromX && nextY == fromY - 1) {
				nextY = fromY + 1;
				// means up, but now has to go down
				if (dungeon.canMoveOnto(enemy, nextX, nextY)) {
					
					enemy.moveDown(dungeon);
					
				} else {
					if (dungeon.canMoveOnto(enemy, fromX+1, fromY) && dungeon.canMoveOnto(enemy, fromX-1, fromY)) {
						if (Math.random() < 0.5) {enemy.moveLeft(dungeon);}
						else {enemy.moveRight(dungeon);}
					} else if (dungeon.canMoveOnto(enemy, fromX+1, fromY)) {
						enemy.moveRight(dungeon);
					} else {
						enemy.moveLeft(dungeon);
					}
				}
			} else if (nextX == fromX && nextY == fromY + 1) {
				
				nextY = fromY - 1;
				if (dungeon.canMoveOnto(enemy, nextX, nextY)) {
					
					enemy.moveUp(dungeon);
					
				} else {
					
					if (dungeon.canMoveOnto(enemy, fromX+1, fromY) && dungeon.canMoveOnto(enemy, fromX-1, fromY)) {
						if (Math.random() > 0.5) {enemy.moveLeft(dungeon);}
						else {enemy.moveRight(dungeon);}
					} else if (dungeon.canMoveOnto(enemy, fromX+1, fromY)) {
						enemy.moveRight(dungeon);
					} else {
						enemy.moveLeft(dungeon);
					}
				}
			} else {
				// for debugging
				System.out.println("not supposed to happen nextX = " + nextX + ", nextY = " + nextY);
				return;
			}
		} else {
			return;
		}
		
	}
	
}
