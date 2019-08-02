package unsw.compositeGoal;

import unsw.dungeon.Dungeon;

public class LeafExit implements Component {

	private String goal; 
	private boolean complete;
	
	public LeafExit() {
		this.goal = "reach the Exit";
		this.complete = false;
	}

	@Override
	public String goalName() {
		return goal;
	}

	@Override
	public boolean checkComplete(Dungeon dungeon) {
		if (dungeon.isReachExit())
			complete = true;
		return complete;
	}
	
	

}
