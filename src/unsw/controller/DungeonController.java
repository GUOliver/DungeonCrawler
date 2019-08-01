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
import unsw.dungeon.Door;
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
	private Image invinciblePlayerImage;
	private Image openDoorImage;
	private Image swordPlayerImage;
	private Image lockedDoorImage;
	private Image num0;
	private Image num1;
	private Image num2;
	private Image num3;
	private Image num4;
	private Image num5;
	private Image num6;
	private Image num7;
	private Image num8;
	private Image num9;

	public DungeonController(Stage stage, Dungeon dungeon, List<ImageView> initialEntities, String filename) {
		super(stage);
		this.dungeon = dungeon;
		this.player = dungeon.getPlayer();
		this.initialEntities = new ArrayList<>(initialEntities);
		this.filename = filename;
		lit1BombImage = new Image("/bomb_lit_1.png");
		lit2BombImage = new Image("/bomb_lit_2.png");
		lit3BombImage = new Image("/bomb_lit_3.png");
		explodeBombImage = new Image("/bomb_lit_4.png");
		unlitBombImage = new Image("/bomb_unlit.png");
		playerImage = new Image("/human_new.png");
		invinciblePlayerImage = new Image("/human_invincible.png");
		swordPlayerImage = new Image("/human_sword.png");
		openDoorImage = new Image("/open_door.png");
        lockedDoorImage = new Image("/closed_door.png");
        num0 = new Image("/number_0.png");
        num1 = new Image("/number_1.png");
        num2 = new Image("/number_2.png");
        num3 = new Image("/number_3.png");
        num4 = new Image("/number_4.png");
        num5 = new Image("/number_5.png");
        num6 = new Image("/number_6.png");
        num7 = new Image("/number_7.png");
        num8 = new Image("/number_8.png");
        num9 = new Image("/number_9.png");
	}

	@FXML
	public void initialize() {
		Image ground = new Image("/dirt_0_new.png");
		Image sidebar = new Image("/dirt_1_new.png");
        Image swordImage = new Image("/greatsword_1_new.png");
        Image equals = new Image("/equals.png");
        Image inv1 = new Image("/inventory_1.png");
        Image inv2 = new Image("/inventory_2.png");
        Image inv3 = new Image("/inventory_3.png");
        Image keyImage = new Image("/key.png");
        
        int width = dungeon.getWidth();
        int height = dungeon.getHeight();

		// Add the ground first so it is below all other entities
		for (int x = 0; x < width+5; x++) {
			for (int y = 0; y < height; y++) {
				if (x < width)
					squares.add(new ImageView(ground), x, y);
				else
					if (x == width+1 && y == 0)
						squares.add(new ImageView(inv1), width+1, 0);
					else if (x == width+2 && y == 0)
						squares.add(new ImageView(inv2), width+2, 0);
					else if (x == width+3 && y == 0)
						squares.add(new ImageView(inv3), width+3, 0);
					else
						squares.add(new ImageView(sidebar), x,y);
			}
		}
		
		// Inventory Items
		squares.add(new ImageView(swordImage), width+1, 1);
		squares.add(new ImageView(equals), width+2, 1);
		squares.add(new ImageView(unlitBombImage), width+1, 2);
		squares.add(new ImageView(equals), width+2, 2);
		squares.add(new ImageView(keyImage), width+1, 3);
		squares.add(new ImageView(equals), width+2, 3);
		
		handleSwordNum(player,width+3,1);
		handleBombNum(player,width+3,2);
		handleKeyNum(player,width+3,3);

		for (ImageView entity : initialEntities)
			squares.getChildren().add(entity);

		handlePlayerImage(player);
		for (Entity ent : dungeon.getEntities())
			if (ent instanceof Door)
				handleDoorImage((Door)ent);
		trackEntityList(dungeon.getEntities());
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

	/**
	 * Gets the filename of dungeon loaded in form of "filename.json"
	 * @return
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * Checks if the game is over due to completion or player death
	 */
	private void checkGameState() throws IOException {
		if (dungeon.getGameState() == true)
			if (dungeon.getEntities().contains(dungeon.getPlayer()))
				handleWin();
			else
				handleDeath();
	}

	/**
	 * loads the game over scene on player death
	 */
	private void handleDeath() throws IOException {
		BasicScene gameOver = new BasicScene(this.getStage(), "Game Over", "GameOverScene.fxml");
		GameOverController goc = new GameOverController(this.getStage(), getFilename());
		gameOver.start(goc);
	}

	/**
	 * loads the level complete on player death
	 */
	private void handleWin() throws IOException {
		BasicScene gameWin = new BasicScene(this.getStage(), "Level Complete", "GameWinScene.fxml");
		GameWinController gwc = new GameWinController(this.getStage(), getFilename());
		gameWin.start(gwc);
	}
	
	/**
	 * quits to main menu
	 */
	private void handleQuit() throws IOException {
		BasicScene menuScreen = new BasicScene(this.getStage(), "Game Menu", "MainMenuScene.fxml");
		MainMenuController dmc = new MainMenuController(this.getStage());
		menuScreen.start(dmc);
	}
	
	/**
	 * allows dropping bomb by player
	 */
	private void handleDropBomb(){
		dungeon.getPlayer().dropBomb();
	}
	
	/**
	 * Adds inventory sword quantity displayer, and attaches listener
	 * @param player Player
	 * @param x X coordinate for displayer
	 * @param y Y coordinate for displayer
	 */
	private void handleSwordNum(Player player, int x, int y) {
		ImageView view = new ImageView(num0);
		GridPane.setColumnIndex(view, x);
		GridPane.setRowIndex(view, y);
		player.getSwordNumProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				//System.out.println(newValue.intValue());
				if (newValue.intValue() == 5) {
					view.setImage(num5);
				} else if (newValue.intValue() == 4) {
					view.setImage(num4);
				} else if (newValue.intValue() == 3) {
					view.setImage(num3);
				} else if (newValue.intValue() == 2) {
					view.setImage(num2);
				} else if (newValue.intValue() == 1) {
					view.setImage(num1);
				} else if (newValue.intValue() == 0) {
					view.setImage(num0);
				}
			}
		});
		squares.getChildren().add(view);
	}
	
	/**
	 * Adds bomb quantity displayer, and attaches listener
	 * @param player Player
	 * @param x X coordinate for displayer
	 * @param y Y coordinate for displayer
	 */
	private void handleBombNum(Player player, int x, int y) {
		ImageView view = new ImageView(num0);
		GridPane.setColumnIndex(view, x);
		GridPane.setRowIndex(view, y);
		player.getBombNumProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				//System.out.println(newValue.intValue());
				if (newValue.intValue() == 9) {
					view.setImage(num9);
				} else if (newValue.intValue() == 8) {
					view.setImage(num8);
				} else if (newValue.intValue() == 7) {
					view.setImage(num7);
				} else if (newValue.intValue() == 6) {
					view.setImage(num6);
				} else if (newValue.intValue() == 5) {
					view.setImage(num5);
				} else if (newValue.intValue() == 4) {
					view.setImage(num4);
				} else if (newValue.intValue() == 3) {
					view.setImage(num3);
				} else if (newValue.intValue() == 2) {
					view.setImage(num2);
				} else if (newValue.intValue() == 1) {
					view.setImage(num1);
				} else if (newValue.intValue() == 0) {
					view.setImage(num0);
				}
			}
		});
		squares.getChildren().add(view);
	}
	
	/**
	 * Adds key number quantity displayer, and attaches listener
	 * @param player Player
	 * @param x X coordinate for displayer
	 * @param y Y coordinate for displayer
	 */
	private void handleKeyNum(Player player, int x, int y) {
		ImageView view = new ImageView(num0);
		GridPane.setColumnIndex(view, x);
		GridPane.setRowIndex(view, y);
		player.getKeyNumProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				//System.out.println(newValue.intValue());
				if (newValue.intValue() == 9) {
					view.setImage(num9);
				} else if (newValue.intValue() == 8) {
					view.setImage(num8);
				} else if (newValue.intValue() == 7) {
					view.setImage(num7);
				} else if (newValue.intValue() == 6) {
					view.setImage(num6);
				} else if (newValue.intValue() == 5) {
					view.setImage(num5);
				} else if (newValue.intValue() == 4) {
					view.setImage(num4);
				} else if (newValue.intValue() == 3) {
					view.setImage(num3);
				} else if (newValue.intValue() == 2) {
					view.setImage(num2);
				} else if (newValue.intValue() == 1) {
					view.setImage(num1);
				} else if (newValue.intValue() == 0) {
					view.setImage(num0);
				}
			}
		});
		squares.getChildren().add(view);
	}

	/**
	 * Handles image after dropping a lit bomb. 
	 * Adds listener to change bomb depending on tick
	 * @param bomb Bomb being placed
	 */
	private void handleBombImage(Bomb bomb) {
		ImageView view = new ImageView(unlitBombImage);
		GridPane.setColumnIndex(view, bomb.getX());
		GridPane.setRowIndex(view, bomb.getY());
		bomb.getTickProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				//System.out.println(newValue.intValue());
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

	/**
	 * Handles player image change based on player states
	 * @param player Player
	 */
	private void handlePlayerImage(Player player) {
		ImageView playerView = findPlayerImage();
		player.getInvincibleTimeProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				//System.out.println(newValue.intValue());
				if (newValue.intValue() > 0) {
					playerView.setImage(invinciblePlayerImage);
				} else if (newValue.intValue() == 0) {
					if (player.getSwordNum()==0)
						playerView.setImage(playerImage);
					else
						playerView.setImage(swordPlayerImage);
				}
			}
		});
		
		player.getSwordNumProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				//System.out.println(newValue.intValue());
				if (newValue.intValue() > 0) {
					if (player.getInvincibleTime()==0)
						playerView.setImage(swordPlayerImage);
				} else if (newValue.intValue() == 0) {
					if (player.getInvincibleTime()>0)
						playerView.setImage(invinciblePlayerImage);
					else
						playerView.setImage(playerImage);
				}
			}
		});
	}
	
	/**
	 * Handles door image change depending on open/close
	 * @param door Door being listened to
	 */
	private void handleDoorImage(Door door) {
		ImageView doorView = findLockedDoorImage(door);
		door.isOpenProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, 
					Boolean oldValue, Boolean newValue) {
				if (newValue == true)
					doorView.setImage(openDoorImage);
			}});
	}

	/** 
	 * Tracks location of all entities to react to movement
	 * @param entities Entity list
	 */
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

	/**
	 * Removes image representing a backend entity
	 * @param entity Entity to have image removed
	 */
	private void removeImage(Entity entity) {
		ImageView image = findImage(entity);
		//System.out.println("About to remove image");
		squares.getChildren().remove(image);
		initialEntities.remove(image);
	}

	/**
	 * Compares two images to check if identical
	 * @param viewImage Image 1
	 * @param image Image 2
	 * @return boolean result true if Image 1 and Image 2 are identical
	 */
	private boolean compareImageFiles(Image viewImage, Image image) {
		for (int i = 0; i<viewImage.getWidth();i++) {
			for (int j = 0; j<viewImage.getHeight();j++) {
				if (viewImage.getPixelReader().getArgb(i, j) != 
						image.getPixelReader().getArgb(i, j)) {
					//System.out.println("Compare return false");
					return false;
				}
			}
		}
		//System.out.println("Compare return true");
		return true;
	}

	/**
	 * Find the image representing input entity
	 * @param entity Input entity
	 * @return ImageView node representing entity
	 */
	private ImageView findImage(Entity entity) {
		ImageView image = null;
		for (ImageView view : initialEntities) {
			if (GridPane.getColumnIndex(view)==entity.getX() && 
					GridPane.getRowIndex(view)==entity.getY()) {
				if(compareImageFiles(view.getImage(),playerImage)==false && 
						compareImageFiles(view.getImage(),invinciblePlayerImage)==false && 
						compareImageFiles(view.getImage(),swordPlayerImage)==false) {
					//System.out.println("Found item that is not player");
					image = view;
				}
			}
		}
		return image;
	}
	
	/**
	 * Find the image representing specific locked door
	 * @param entity player
	 * @return ImageView node representing entity
	 */
	private ImageView findLockedDoorImage(Door door) {
		ImageView image = null;
		for (ImageView view : initialEntities) {
			if (GridPane.getColumnIndex(view)==door.getX() && 
					GridPane.getRowIndex(view)==door.getY()) {
				if(compareImageFiles(view.getImage(),lockedDoorImage)==true) {
					//System.out.println("Found item that is door");
					image = view;
				}
			}
		}
		return image;
	}

	/**
	 * Find the image representing player
	 * @return ImageView node representing entity
	 */
	private ImageView findPlayerImage() {
		ImageView image = null;
		for (ImageView view : initialEntities) {
			if (GridPane.getColumnIndex(view)==player.getX() && 
					GridPane.getRowIndex(view)==player.getY()){
				if(compareImageFiles(view.getImage(),playerImage)==true || 
						compareImageFiles(view.getImage(),invinciblePlayerImage)==true || 
						compareImageFiles(view.getImage(),swordPlayerImage)==true) {
					//System.out.println("Found item that is player");
					image = view;
				}
			}
		}
		return image;
	}

}

