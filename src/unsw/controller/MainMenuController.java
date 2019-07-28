package unsw.controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenuController extends BasicController{

    @FXML
    private Button startButton;

    @FXML
    private Button exitButton;
    
    public MainMenuController(Stage stage) {
		super(stage);
	}
    @FXML
    /**
     * handle the exit button, exit the game
     * @param event
     */
    public void handleExit(ActionEvent event) {
    	System.exit(1);
    }

    @FXML
    /**
     * handle the start button, then go to the map chosing scene
     * @param event
     * @throws IOException
     */
    public void handleStart(ActionEvent event) throws IOException {
		BasicScene map = new BasicScene(this.getStage(), "Map selection", "MapScene.fxml");
		MapController mc = new MapController(this.getStage());
		map.start(mc);
    }
    
    @FXML
    // is this one needed ?
    public void initialize() {
        assert startButton != null : "fx:id=\"startButton\" was not injected: check your FXML file 'mainMenu.fxml'.";
        assert exitButton != null : "fx:id=\"exitButton\" was not injected: check your FXML file 'mainMenu.fxml'.";
    }
}
