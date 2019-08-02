package unsw.compositeGoal;

import java.util.ArrayList;

import unsw.dungeon.Dungeon;

public class CompositeAND implements Component {

	private String goal; 
	private boolean complete;
	 
	ArrayList<Component>  children = new ArrayList<Component>();
	
	/**
	 * Constructor for a composite goal, stores Leafgoals in an array
	 */
	public CompositeAND() {
		this.goal = null;
		this.complete = false;
	}
	
	/**
	 * Adds a leafgoal to the Composite array
	 * @param child Add a leafgoal. Note that this can also be another CompositeGoal
	 * for further nesting.
	 */
	public void add(Component child) {
		children.add(child);
		if (goal == null) {
			goal = child.goalName();
		} else {
			goal += " AND ";
			goal += child.goalName();
		}
	}
	
	/**
	 * Checks that both leafgoals are complete
	 */
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

	/**
	 * Returns the goal name as a combination of leafgoal names
	 */
	@Override
	public String goalName() {
		return goal;
	}
	
	
	
	
}
