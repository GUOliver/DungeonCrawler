package unsw.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * a basic controller to make life much easier
 * inspired by tute7 code
 * @author chengyuanguo
 *
 */
public class BasicScene {
	private Stage stage;
    private String screenTitle;
    private FXMLLoader fxmlLoader;
    
    /**
     * a template for all the controller
     * @param stage the window
     * @param screenTitle the title of a particular scene
     * @param fxmlFile the name of the .fxml file
     */
    public BasicScene(Stage stage, String screenTitle, String fxmlFile) {
        this.stage = stage;
        this.screenTitle = screenTitle;
        this.fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
        
    }
    
    /**
     * 
     * @param bc the controller 
     * @throws IOException
     */
    public void start(BasicController bc) throws IOException{
    	this.stage.setTitle(this.screenTitle);
        this.fxmlLoader.setController(bc);
       
        Parent root = this.fxmlLoader.load();
        Scene sc = new Scene(root);
        root.requestFocus();
        this.stage.setScene(sc);
        this.stage.show();
       
    }
    
    /**
     * 
     * @param dc start and show the current scene
     * @throws IOException
     */
    public void start(DungeonController dc) throws IOException{
    	this.stage.setTitle(this.screenTitle);
        this.fxmlLoader.setController(dc);
       
        Parent root = this.fxmlLoader.load();
        Scene sc = new Scene(root);
        root.requestFocus();
        this.stage.setScene(sc);
        this.stage.show();
       
    }
    
    /**
     * 
     * @return the window 
     */
    public Stage getStage() {
        return this.stage;
    }
    
    /**
     * set the stage
     * @param stage the window
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    

	
}
