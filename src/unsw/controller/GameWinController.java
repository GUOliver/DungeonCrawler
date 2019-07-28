package unsw.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GameWinController extends BasicController{
	
	@FXML
    private Button replay;

    @FXML
    private Button exit;

    @FXML
    private Button back;

	public GameWinController(Stage stage) {
		super(stage);
	}
	
	@FXML
    void handleReplay(ActionEvent event) {

    }

    @FXML
    void handleExit(ActionEvent event) {
    	System.exit(1);
    }

    @FXML
    void handleBack(ActionEvent event) throws IOException {
    	
    	BasicScene menuScreen = new BasicScene(this.getStage(), "Game Menu", "MainMenuScene.fxml");
    	MainMenuController dmc = new MainMenuController(this.getStage());
		menuScreen.start(dmc);
    }

}
