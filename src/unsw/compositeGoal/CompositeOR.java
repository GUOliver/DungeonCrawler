package unsw.compositeGoal;

import java.util.ArrayList;

import unsw.dungeon.Dungeon;

public class CompositeOR implements Component {

	private String goal; 
	private boolean complete;
	 
	ArrayList<Component>  children = new ArrayList<Component>();
	
	/**
	 * Constructor for a composite goal, stores Leafgoals in an array
	 */
	public CompositeOR() {
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
			goal += " OR ";
			goal += child.goalName();
		}
	}
	
	/**
	 * Checks that one of the leafgoals are complete
	 */
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
	
	/**
	 * Returns the goal name as a combination of leafgoal names
	 */
	@Override
	public String goalName() {
		return goal;
	}
}
