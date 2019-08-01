package unsw.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import unsw.dungeon.Bomb;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
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
	private Image lit1BombImage;
	private Image lit2BombImage;
	private Image lit3BombImage;
	private Image explodeBombImage;
	private Image unlitBombImage;
    private Image playerImage;
	private Image invincibilePlayerImage;
	private Image openDoorImage;
	private Image swordPlayerImage;

	public DungeonController(Stage stage, Dungeon dungeon, List<ImageView> initialEntities, String filename) {
		super(stage);
		this.dungeon = dungeon;
		this.player = dungeon.getPlayer();
		this.initialEntities = new ArrayList<>(initialEntities);
		this.filename = filename;
		trackEntityList(dungeon.getEntities());
		lit1BombImage = new Image("/bomb_lit_1.png");
		lit2BombImage = new Image("/bomb_lit_2.png");
		lit3BombImage = new Image("/bomb_lit_3.png");
		explodeBombImage = new Image("/bomb_lit_4.png");
		unlitBombImage = new Image("/bomb_unlit.png");
        playerImage = new Image("/human_new.png");
        invincibilePlayerImage = new Image("/human_invincible.png");
        swordPlayerImage = new Image("/human_sword.png");
        openDoorImage = new Image("/open_door.png");
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
			checkGameState();
			break;
		case DOWN:
			player.moveDown(dungeon);
			checkGameState();
			break;
		case LEFT:
			player.moveLeft(dungeon);
			checkGameState();
			break;
		case RIGHT:
			player.moveRight(dungeon);
			checkGameState();
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

	private void checkGameState() throws IOException {
		if (dungeon.getGameState() == true)
			if (dungeon.getEntities().contains(dungeon.getPlayer()))
				handleWin();
			else
				handleDeath();
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

	private void handleBombImage(Bomb bomb) {
		ImageView view = new ImageView(unlitBombImage);
		GridPane.setColumnIndex(view, bomb.getX());
        GridPane.setRowIndex(view, bomb.getY());
		bomb.getTickProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				System.out.println(newValue.intValue());
				if (newValue.intValue() == 3) {
					view.setImage(lit1BombImage);
				} else if (newValue.intValue() == 2) {
					view.setImage(lit2BombImage);
				} else if (newValue.intValue() == 1) {
					view.setImage(lit3BombImage);
				} else if (newValue.intValue() == 0) {
					view.setImage(explodeBombImage);
				}
			}
		});
		squares.getChildren().add(view);
		initialEntities.add(view);
	}

	private void trackEntityList(ObservableList<Entity> entities) {
		entities.addListener(new ListChangeListener<Entity>() {
			@Override
			public void onChanged(Change<? extends Entity> c) {
				while (c.next()) {
					if (c.wasAdded()) {
						for (Entity item : c.getAddedSubList()) {
							if (item instanceof Bomb)
								handleBombImage((Bomb)item);
						}
					} else if (c.wasRemoved()) {
						for (Entity item : c.getRemoved()) {
							removeImage(item);
						}
					}
				}}});
	}
	
	private void removeImage(Entity entity) {
		ImageView image = null;
		for (ImageView view : initialEntities) {
			if (GridPane.getColumnIndex(view)==entity.getX() && 
					GridPane.getRowIndex(view)==entity.getY() &&
					view.getImage()!=playerImage) {
				image = view;
			}
		}
		if (image!=null) {
			image.setImage(null);
		}
	}

}

