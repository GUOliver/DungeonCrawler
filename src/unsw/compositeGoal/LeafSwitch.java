package unsw.compositeGoal;

import unsw.dungeon.Dungeon;

public class LeafSwitch implements Component {

	private String goal; 
	private boolean complete;
	
	/**
	 * Constructor for enemy Leafgoal. Requires all enemies to be killed
	 */
	public LeafSwitch() {
		this.goal = "Switches";
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
		//System.out.printf("Boulder on switch %d and switchTotal %d\n",dungeon.getBoulderOnSwitch(),dungeon.getSwitchTotal());
		if (dungeon.getBoulderOnSwitch() == dungeon.getSwitchTotal())
			complete = true;
		return complete;
	}
	
	

}
