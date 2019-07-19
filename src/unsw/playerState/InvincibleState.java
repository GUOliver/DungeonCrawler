package unsw.playerState;

import unsw.dungeon.Player;
import unsw.movementStrategy.MovementStrategy;
import unsw.movementStrategy.MovingAwayPlayer;

public class InvincibleState implements PlayerState {

	@Override
	public void updateState(PlayerState state, Player player) {
		player.setPlayerState(state);
	}

	@Override
	public MovementStrategy enemyStrategy() {
		return new MovingAwayPlayer();
	}

}
