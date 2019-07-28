package unsw.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GameWinController extends BasicController{
	
	private String filename;
	
	@FXML
    private Button replay;

    @FXML
    private Button exit;

    @FXML
    private Button back;

	public GameWinController(Stage stage, String filename) {
		super(stage);
		this.filename = filename;
	}
	
	@FXML
    void handleReplay(ActionEvent event) throws IOException {
		BasicScene gameScene = new BasicScene(this.getStage(), "Dungeon", "DungeonView.fxml");
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(getFilename(),this.getStage());
        DungeonController controller = dungeonLoader.loadController();
        gameScene.start(controller);
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
    
    public String getFilename() {
		return filename;
	}

}
