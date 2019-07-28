package unsw.controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * cited partly from Tutotial code week 7
 * @author chengyuanguo
 *
 */
public class MainMenuScene {
	private Stage stage;
    private String title;
    private MainMenuController controller;
    private Scene scene;
    
    /**
     * the first scene of the game 
     * @param stage the primar
     * @throws IOException
     */
    public MainMenuScene(Stage stage) throws IOException {
        this.stage = stage;
        title = "Game Menu";

        controller = new MainMenuController(stage);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenuScene.fxml"));
        loader.setController(controller);

        // load into a Parent node called root
        Parent root = loader.load();
        scene = new Scene(root);
    }
    
    /**
     * start and show the scene
     */
    public void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * get the controller
     * @return
     */
    public MainMenuController getController() {
        return controller;
    }
    
    /**
     * set the controller
     * @param c the main menu controller
     */
    public void setController(MainMenuController c) {
    	this.controller = c;
    }

}
