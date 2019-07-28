package unsw.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DungeonMenuController extends BasicController{

    @FXML
    private Button startButton;

    @FXML
    private Button exitButton;
    
    public DungeonMenuController(Stage stage) {
		super(stage);
	}
    @FXML
    public void handleExit(ActionEvent event) {
    	System.exit(1);
    }

    @FXML
    public void handleStart(ActionEvent event) throws IOException {
		BasicScreen map = new BasicScreen(this.getStage(), "Map selection", "MapScreen.fxml");
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
