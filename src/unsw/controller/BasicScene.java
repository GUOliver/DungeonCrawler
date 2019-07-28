package unsw.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class BasicScene {
	private Stage stage;
    private String screenTitle;
    private FXMLLoader fxmlLoader;

    public BasicScene(Stage stage, String screenTitle, String fxmlFile) {
        this.stage = stage;
        this.screenTitle = screenTitle;
        this.fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
        
    }
    
    /**
     * 
     * @param bc
     * @throws IOException
     */
    public void start(BasicController bc) throws IOException{
    	this.stage.setTitle(this.screenTitle);
        this.fxmlLoader.setController(bc);
       
        Parent root = this.fxmlLoader.load();
        Scene sc = new Scene(root);
        this.stage.setScene(sc);
        this.stage.show();
       
    }
    
    public void start(DungeonController dc) throws IOException{
    	this.stage.setTitle(this.screenTitle);
        this.fxmlLoader.setController(dc);
       
        Parent root = this.fxmlLoader.load();
        Scene sc = new Scene(root);
        this.stage.setScene(sc);
        this.stage.show();
       
    }
    
    public Stage getStage() {
        return this.stage;
    }
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    

	
}
