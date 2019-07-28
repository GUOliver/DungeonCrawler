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
    
    public void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public MainMenuController getController() {
        return controller;
    }

}
