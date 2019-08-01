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
	
	/**
	 * Reloads the dungeon
	 * @param event player input
	 */
	@FXML
    void handleReplay(ActionEvent event) throws IOException {
		BasicScene gameScene = new BasicScene(this.getStage(), "Dungeon", "DungeonView.fxml");
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(getFilename(),this.getStage());
        DungeonController controller = dungeonLoader.loadController();
        gameScene.start(controller);
    }

    /**
     * Exits the program
     * @param event player input
     */
    @FXML
    void handleExit(ActionEvent event) {
    	System.exit(1);
    }

    /**
     * Goes back to main menu
     * @param event player input
     */
    @FXML
    void handleBack(ActionEvent event) throws IOException {
    	
    	BasicScene menuScreen = new BasicScene(this.getStage(), "Game Menu", "MainMenuScene.fxml");
    	MainMenuController dmc = new MainMenuController(this.getStage());
		menuScreen.start(dmc);
    }
    
    /**
     * Gets the filename of the dungeon loaded
     * @return String filename in format of "name.json"
     */
    public String getFilename() {
		return filename;
	}

}
