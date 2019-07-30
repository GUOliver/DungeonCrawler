package unsw.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController extends BasicController{

    @FXML
    private GridPane squares;

    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;
    
    private String filename;
    
    
    public DungeonController(Stage stage, Dungeon dungeon, List<ImageView> initialEntities, String filename) {
        super(stage);
    	this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
        this.filename = filename;
    }

    @FXML
    public void initialize() {
        Image ground = new Image("/dirt_0_new.png");

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);

    }

    @FXML
    public void handleKeyPress(KeyEvent event) throws IOException {
        switch (event.getCode()) {
        case UP:
            player.moveUp(dungeon);
            if (dungeon.getGameState() == true)
            	if (dungeon.getEntities().contains(dungeon.getPlayer()))
            		handleWin();
            	else
            		handleDeath();
            break;
        case DOWN:
            player.moveDown(dungeon);
            if (dungeon.getGameState() == true)
            	if (dungeon.getEntities().contains(dungeon.getPlayer()))
            		handleWin();
            	else
            		handleDeath();
            break;
        case LEFT:
            player.moveLeft(dungeon);
            if (dungeon.getGameState() == true)
            	if (dungeon.getEntities().contains(dungeon.getPlayer()))
            		handleWin();
            	else
            		handleDeath();
            break;
        case RIGHT:
            player.moveRight(dungeon);
            if (dungeon.getGameState() == true)
            	if (dungeon.getEntities().contains(dungeon.getPlayer()))
            		handleWin();
            	else
            		handleDeath();
            break;
        case SPACE:
        	handleDropBomb();
        	break;
        case Q:
        	handleQuit();
        	break;
        default:
            break;
        }
    }

	public String getFilename() {
		return filename;
	}
	
	private void handleDeath() throws IOException {
		BasicScene gameOver = new BasicScene(this.getStage(), "Game Over", "GameOverScene.fxml");
    	GameOverController goc = new GameOverController(this.getStage(), getFilename());
		gameOver.start(goc);
	}
	
	private void handleWin() throws IOException {
		BasicScene gameWin = new BasicScene(this.getStage(), "Level Complete", "GameWinScene.fxml");
    	GameWinController gwc = new GameWinController(this.getStage(), getFilename());
		gameWin.start(gwc);
	}
	
	private void handleQuit() throws IOException {
		BasicScene menuScreen = new BasicScene(this.getStage(), "Game Menu", "MainMenuScene.fxml");
    	MainMenuController dmc = new MainMenuController(this.getStage());
		menuScreen.start(dmc);
	}
	
	private void handleDropBomb(){
		dungeon.getPlayer().dropBomb();
	}


}

