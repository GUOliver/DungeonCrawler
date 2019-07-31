package unsw.controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import unsw.dungeon.Bomb;
import unsw.dungeon.Boulder;
import unsw.dungeon.Door;
import unsw.dungeon.Enemy;
import unsw.dungeon.Entity;
import unsw.dungeon.Exit;
import unsw.dungeon.FloorSwitch;
import unsw.dungeon.InvincibilityPotion;
import unsw.dungeon.Key;
import unsw.dungeon.Player;
import unsw.dungeon.Sword;
import unsw.dungeon.Treasure;
import unsw.dungeon.Wall;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

    private List<ImageView> entities;

    //Images
    private Image playerImage;
    private Image wallImage;
	private Image unlitBombImage;
	private Image lit1BombImage;
	private Image lit2BombImage;
	private Image lit3BombImage;
	private Image explodeBombImage;
	private Image boulderImage;
	private Image invincibilityImage;
	private Image invincibilePlayerImage;
	private Image lockedDoorImage;
	private Image openDoorImage;
	private Image exitImage;
	private Image enemyImage;
	private Image treasureImage;
	private Image swordImage;
	private Image keyImage;
	private Image switchImage;

    public DungeonControllerLoader(String filename, Stage stage)
            throws FileNotFoundException {
        super(filename, stage);
        entities = new ArrayList<>();
        playerImage = new Image("/human_new.png");
        wallImage = new Image("/brick_brown_0.png");
        unlitBombImage = new Image("/bomb_unlit.png");
        lit1BombImage = new Image("/bomb_lit_1.png");
        lit2BombImage = new Image("/bomb_lit_2.png");
        lit3BombImage = new Image("/bomb_lit_3.png");
        explodeBombImage = new Image("/bomb_lit_4.png");
        boulderImage = new Image("/boulder.png");
        invincibilityImage = new Image("/brilliant_blue_new.png");
        //invincibilePlayerImage = new Image("/human_invincible.png");
        lockedDoorImage = new Image("/closed_door.png");
        openDoorImage = new Image("/open_door.png");
        exitImage = new Image("/exit.png");
        enemyImage = new Image("/gnome.png");
        treasureImage = new Image("/gold_pile.png");
        swordImage = new Image("/greatsword_1_new.png");
        keyImage = new Image("/key.png");
        switchImage = new Image("/pressure_plate.png");
        
    }

    @Override
    public void onLoad(Player player) {
        ImageView view = new ImageView(playerImage);
        addEntity(player, view);
    }

    @Override
    public void onLoad(Wall wall) {
        ImageView view = new ImageView(wallImage);
        addEntity(wall, view);
    }
    
    @Override
	public void onLoad(Bomb bomb) {
    	ImageView view = new ImageView(unlitBombImage);
    	/*bomb.tickProperty().addListener(
    			changed() {
    				view.setImage(value);
    			}
    	)*/
        addEntity(bomb, view);
	}

	@Override
	public void onLoad(Boulder boulder) {
		ImageView view = new ImageView(boulderImage);
        addEntity(boulder, view);
	}

	@Override
	public void onLoad(Key key) {
		ImageView view = new ImageView(keyImage);
        addEntity(key, view);
	}

	@Override
	public void onLoad(Door door) {
		ImageView view = new ImageView(lockedDoorImage);
        addEntity(door, view);
	}

	@Override
	public void onLoad(Enemy enemy) {
		ImageView view = new ImageView(enemyImage);
        addEntity(enemy, view);
	}

	@Override
	public void onLoad(FloorSwitch floorSwitch) {
		ImageView view = new ImageView(switchImage);
        addEntity(floorSwitch, view);
	}

	@Override
	public void onLoad(InvincibilityPotion potion) {
		ImageView view = new ImageView(invincibilityImage);
        addEntity(potion, view);
	}

	@Override
	public void onLoad(Sword sword) {
		ImageView view = new ImageView(swordImage);
        addEntity(sword, view);
	}

	@Override
	public void onLoad(Treasure treasure) {
		ImageView view = new ImageView(treasureImage);
        addEntity(treasure, view);
	}
	
	@Override
	public void onLoad(Exit exit) {
		ImageView view = new ImageView(exitImage);
        addEntity(exit, view);
	}


    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entities.add(view);
    }

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        });
    }

    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * @return
     * @throws FileNotFoundException
     */
    public DungeonController loadController() throws FileNotFoundException {
        return new DungeonController(getStage(),load(), entities, getFilename());
    }

	

}
