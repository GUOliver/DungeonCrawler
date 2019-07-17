package unsw.playerObserve;
public interface Subject {

	public void registerObservers();
	public void removeObserver(Observer o);
	public void notifyObservers();
	
}
