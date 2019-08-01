package unsw.compositeGoal;

import unsw.dungeon.Dungeon;

public class LeafEnemy implements Component {

	private String goal; 
	private boolean complete;
	
	
	/**
	 * Constructor for enemy Leafgoal. Requires all enemies to be killed
	 */
	public LeafEnemy() {
		this.goal = "Enemies";
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
		if (dungeon.getEnemyTotal()==0)
			complete = true;
		return complete;
	}
	
	

}
