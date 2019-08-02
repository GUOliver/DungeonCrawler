package unsw.compositeGoal;

import unsw.dungeon.Dungeon;

public class LeafTreasure implements Component {

	private String goal; 
	private boolean complete;
	
	/**
	 * Constructor for enemy Leafgoal. Requires all enemies to be killed
	 */
	public LeafTreasure() {
		this.goal = "collect all the Treasure";
		this.complete = false;
	}
	
	/**
	 * Returns string representative of leafgoal
	 */
	@Override
	public String goalName() {
		return goal;
	}
	
	/**
	 * Check goal complete
	 */
	@Override
	public boolean checkComplete(Dungeon dungeon) {
		if (dungeon.getTreasureTotal() == 0)
			complete = true;
		return complete;
	}
}
