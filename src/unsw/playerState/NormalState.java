package unsw.playerState;

import unsw.dungeon.Player;
import unsw.movementStrategy.MovementStrategy;
import unsw.movementStrategy.MovingToPlayer;

public class NormalState implements PlayerState {

	/**
	 * Updates the state of the player
	 */
	@Override
	public void updateState(PlayerState state, Player player) {
		player.setPlayerState(state);
	}

	/**
	 * When player is in a normal state, enemies should try to kill the player
	 */
	@Override
	public MovementStrategy enemyStrategy() {
		return new MovingToPlayer();
	}

}
