package unsw.movementStrategy;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.MovingEntity;


public class MovingToPlayer extends dfsSearchPath implements MovementStrategy{
	
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

	
	public void moveNext(Dungeon dungeon, MovingEntity entity, int destX, int destY) {
		int fromX = entity.getX();
		int fromY = entity.getY();
		
		
//		System.out.println("going from (" + fromX + ", " + fromY + ") to (" + destX + ", " + destY + ")");
		if (fromX == destX && fromY == destY) {
			return;
		}
		
		if(! dungeon.canMoveOnto(entity, destX, destY)) {
			// if we can't get to the final square, return NONE
			System.out.println("cant get to " + destX + "," + destY + " entities on point: " + dungeon.findEntity(destX, destY));
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
//			System.out.printf("next move: (%d, %d)\n", nextX, nextY);
			
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
				System.out.println("not supposed to happen nextX = " + nextX + ", nextY = " + nextY);
				return;
			}
		} else {
			return;
		}
		
	}
	
}
