package unsw.playerObserve;

public interface Observer {
	
	/**
	 * Updates observer with Subject details
	 * @param obj The subject that is being observed
	 */
	public void update(Subject obj);
	
}

