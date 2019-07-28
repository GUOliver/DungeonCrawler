package unsw.controller;
import javafx.stage.Stage;

/**
 * a basic controller for all the game controllers
 * @author chengyuanguo
 *
 */
public class BasicController {
	
	private Stage stage;
	
	/**
	 * the game stage 
	 * @param stage
	 */
	public BasicController (Stage stage) {
		this.stage = stage;
	}
	
	/**
	 * 
	 * @return the stage
	 */
	public Stage getStage() {
		return this.stage;
	}
	
	/**
	 * set the game stage
	 * @param stage
	 */
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
}
