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
public class StartScreen {
	private Stage stage;
    private String title;
    private DungeonMenuController controller;
    private Scene scene;

    public StartScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Game Menu";

        controller = new DungeonMenuController(stage);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
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

    public DungeonMenuController getController() {
        return controller;
    }

}
