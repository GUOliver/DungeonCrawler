package unsw.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MapController extends BasicController{

    @FXML
    private Button levelOne;

    @FXML
    private Button levelTwo;

    @FXML
    private Button levelThree;

    @FXML
    private Button back;
    
    
    public MapController(Stage stage) {
		super(stage);
	}

    @FXML
    void handleBack(ActionEvent event) throws IOException {
    	
    	BasicScene menuScreen = new BasicScene(this.getStage(), "Game Menu", "MainMenuScene.fxml");
    	MainMenuController dmc = new MainMenuController(this.getStage());
		menuScreen.start(dmc);
    }

    @FXML
    void handleLevelOne(ActionEvent event) throws IOException {
    	
    	BasicScene gameScene = new BasicScene(this.getStage(), "Dungeon", "DungeonView.fxml");
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("maze.json");
        DungeonController controller = dungeonLoader.loadController();
        gameScene.start(controller);

    }

    @FXML
    void handleLevelTwo(ActionEvent event) throws IOException {
    	
    	BasicScene gameScene = new BasicScene(this.getStage(), "Dungeon", "DungeonView.fxml");
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("boulders.json");
        DungeonController controller = dungeonLoader.loadController();
        gameScene.start(controller);

    }

    @FXML
    void handleLevelThree(ActionEvent event) throws IOException {
    	BasicScene gameScene = new BasicScene(this.getStage(), "Dungeon", "DungeonView.fxml");
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("advanced.json");
        DungeonController controller = dungeonLoader.loadController();
        gameScene.start(controller);
    }

}
