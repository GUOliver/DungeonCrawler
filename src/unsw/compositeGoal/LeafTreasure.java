package unsw.compositeGoal;

import unsw.dungeon.Dungeon;

public class LeafTreasure implements Component {

	private String goal; 
	private boolean complete;
	
	public LeafTreasure() {
		this.goal = "Treasure";
		this.complete = false;
	}

	@Override
	public String goalName() {
		return goal;
	}

	@Override
	public boolean checkComplete(Dungeon dungeon) {
		if (dungeon.getTreasureTotal() == 0)
			complete = true;
		return complete;
	}
}
