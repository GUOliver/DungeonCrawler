package unsw.playerState;

import unsw.dungeon.Player;
import unsw.movementStrategy.MovementStrategy;
import unsw.movementStrategy.MovingAwayPlayer;

public class InvincibleState implements PlayerState {

	/**
	 * Updates the state of the player
	 */
	@Override
	public void updateState(PlayerState state, Player player) {
		player.setPlayerState(state);
	}

	/**
	 * During invincible state, the strategy of enemies should be to run away
	 */
	@Override
	public MovementStrategy enemyStrategy() {
		return new MovingAwayPlayer();
	}

}
