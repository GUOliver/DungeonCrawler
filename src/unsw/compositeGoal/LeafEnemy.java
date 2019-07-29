package unsw.compositeGoal;

import unsw.dungeon.Dungeon;

public class LeafEnemy implements Component {

	private String goal; 
	private boolean complete;
	
	public LeafEnemy() {
		this.goal = "Enemies";
		this.complete = false;
	}

	@Override
	public String goalName() {
		return goal;
	}

	@Override
	public boolean checkComplete(Dungeon dungeon) {
		if (dungeon.getEnemyTotal()==0)
			complete = true;
		return complete;
	}
	
	

}
