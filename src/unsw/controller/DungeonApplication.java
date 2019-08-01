package unsw.controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

public class DungeonApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
    	MainMenuScene startScreen = new MainMenuScene(primaryStage);
        startScreen.start();
        
    }

    public static void main(String[] args) {
        launch(args);
    }

}
