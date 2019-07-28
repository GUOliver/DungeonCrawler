package unsw.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * a map controller
 * @author chengyuanguo
 *
 */
public class MapController extends BasicController{

    @FXML
    private Button levelOne;

    @FXML
    private Button levelTwo;

    @FXML
    private Button levelThree;

    @FXML
    private Button back;
    
    
    /**
     * the map constroller
     * @param stage the window
     */
    public MapController(Stage stage) {
		super(stage);
	}

    @FXML
    /**
     * when click the butto back
     * @param event the click 
     * @throws IOException 
     */
    void handleBack(ActionEvent event) throws IOException {
    	
    	BasicScene menuScreen = new BasicScene(this.getStage(), "Game Menu", "MainMenuScene.fxml");
    	MainMenuController dmc = new MainMenuController(this.getStage());
		menuScreen.start(dmc);
    }

    @FXML
    /**
     * when click the button levelone 
     * @param event
     * @throws IOException
     */
    void handleLevelOne(ActionEvent event) throws IOException {
    	
    	BasicScene gameScene = new BasicScene(this.getStage(), "Dungeon", "DungeonView.fxml");
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("maze.json",this.getStage());
        DungeonController controller = dungeonLoader.loadController();
        gameScene.start(controller);

    }

    @FXML
    /**
     * when click the button leveltwo
     * @param event
     * @throws IOException
     */
    void handleLevelTwo(ActionEvent event) throws IOException {
    	
    	BasicScene gameScene = new BasicScene(this.getStage(), "Dungeon", "DungeonView.fxml");
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("boulders.json",this.getStage());
        DungeonController controller = dungeonLoader.loadController();
        gameScene.start(controller);

    }

    @FXML
    /**
     * when click the button levelthree
     * @param event
     * @throws IOException
     */
    void handleLevelThree(ActionEvent event) throws IOException {
    	BasicScene gameScene = new BasicScene(this.getStage(), "Dungeon", "DungeonView.fxml");
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("advanced.json",this.getStage());
        DungeonController controller = dungeonLoader.loadController();
        gameScene.start(controller);
    }

}
