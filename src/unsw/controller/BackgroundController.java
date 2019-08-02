package unsw.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class BackgroundController extends BasicController {

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private Button playButton;

  
	private String file;
    
    public BackgroundController(Stage stage) {
		super(stage);
	}
    public BackgroundController(Stage stage, String file) {
		super(stage);
		this.file = file;
	}

    @FXML
    void handleBack(ActionEvent event) throws IOException {
    	BasicScene menuScreen = new BasicScene(this.getStage(), "Game Map", "MapScene.fxml");
    	MapController dmc = new MapController(this.getStage());
		menuScreen.start(dmc);
    }

    @FXML
    void handlePlay(ActionEvent event) throws IOException {
    	BasicScene gameScene = new BasicScene(this.getStage(), "Dungeon", "DungeonView.fxml");
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(file, this.getStage());
        DungeonController controller = dungeonLoader.loadController();
        gameScene.start(controller);
    }

    @FXML
    void initialize() {
    	
    }
}
