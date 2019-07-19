package unsw.playerState;

import unsw.dungeon.Player;
import unsw.movementStrategy.MovementStrategy;

/*
 * Defines a player's state
 */
public interface PlayerState {
	void updateState(PlayerState state, Player player);
	MovementStrategy enemyStrategy();
}
