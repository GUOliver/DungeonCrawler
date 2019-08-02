package unsw.compositeGoal;

import unsw.dungeon.Dungeon;

public class LeafExit implements Component {

	private String goal; 
	private boolean complete;
	
	/**
	 * Constructor for enemy Leafgoal. Requires all enemies to be killed
	 */
	public LeafExit() {
		this.goal = "reach the Exit";
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
		if (dungeon.isReachExit())
			complete = true;
		return complete;
	}
	
	

}
