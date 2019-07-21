package unsw.playerObserve;
public interface Subject {

	/**
	 * Registers all relevant observers
	 */
	public void registerObservers();
	/**
	 * Remove a specific observer
	 * @param o
	 */
	public void removeObserver(Observer o);
	/**
	 * notifies observers that an update is required
	 */
	public void notifyObservers();
	
}
