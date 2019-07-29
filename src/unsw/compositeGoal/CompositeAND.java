package unsw.compositeGoal;

import java.util.ArrayList;

import unsw.dungeon.Dungeon;

public class CompositeAND implements Component {

	private String goal; 
	private boolean complete;
	 
	ArrayList<Component>  children = new ArrayList<Component>();
	
	public CompositeAND() {
		this.goal = null;
		this.complete = false;
	}
	
	public void add(Component child) {
		children.add(child);
		if (goal == null) {
			goal = child.goalName();
		} else {
			goal += " AND ";
			goal += child.goalName();
		}
	}
	
	@Override
	public boolean checkComplete(Dungeon dungeon) {
		boolean check = true;
		for (Component goal : children) {
			check = check && goal.checkComplete(dungeon);
		}
		if (check == true)
			complete = true;
		return complete;
	}

	@Override
	public String goalName() {
		return goal;
	}
	
	
	
	
}
