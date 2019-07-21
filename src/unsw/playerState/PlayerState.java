package unsw.playerState;

import unsw.dungeon.Player;
import unsw.movementStrategy.MovementStrategy;

/*
 * Defines a player's state
 */
public interface PlayerState {
	/**
	 * Updates player state
	 * @param state The new state of player
	 * @param player The player
	 */
	void updateState(PlayerState state, Player player);
	/**
	 * Sets the strategy of the enemy depending on which state the player is in
	 * @return the movement strategy
	 */
	MovementStrategy enemyStrategy();
}
