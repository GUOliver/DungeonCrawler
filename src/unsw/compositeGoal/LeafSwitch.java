package unsw.compositeGoal;

import unsw.dungeon.Dungeon;

public class LeafSwitch implements Component {

	private String goal; 
	private boolean complete;
	
	public LeafSwitch() {
		this.goal = "move boulders on all Switches";
		this.complete = false;
	}

	@Override
	public String goalName() {
		return goal;
	}

	@Override
	public boolean checkComplete(Dungeon dungeon) {
		if (dungeon.getBoulderOnSwitch() == dungeon.getSwitchTotal())
			complete = true;
		return complete;
	}
	
	

}
