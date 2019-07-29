package unsw.compositeGoal;

import java.util.ArrayList;

import unsw.dungeon.Dungeon;

public class CompositeOR implements Component {

	private String goal; 
	private boolean complete;
	 
	ArrayList<Component>  children = new ArrayList<Component>();
	
	public CompositeOR() {
		this.goal = null;
		this.complete = false;
	}
	
	public void add(Component child) {
		children.add(child);
		if (goal == null) {
			goal = child.goalName();
		} else {
			goal += " OR ";
			goal += child.goalName();
		}
	}
	
	@Override
	public boolean checkComplete(Dungeon dungeon) {
		boolean check = false;
		for (Component goal : children) {
			check = check || goal.checkComplete(dungeon);
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
