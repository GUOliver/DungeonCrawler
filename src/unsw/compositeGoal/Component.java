package unsw.compositeGoal;

import unsw.dungeon.Dungeon;

public interface Component {
	
	public String goalName();
	public boolean checkComplete(Dungeon dungeon);
	
}
