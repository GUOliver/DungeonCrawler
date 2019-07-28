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
import unsw.dungeon.Bomb;
import unsw.dungeon.Boulder;
import unsw.dungeon.Door;
import unsw.dungeon.Enemy;
import unsw.dungeon.Entity;
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

    public DungeonControllerLoader(String filename)
            throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
        playerImage = new Image("/human_new.png");
        wallImage = new Image("/brick_brown_0.png");
        //TODO images for other entities
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoad(Boulder boulder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoad(Key key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoad(Door door) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoad(Enemy enemy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoad(FloorSwitch floorSwitch) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoad(InvincibilityPotion potion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoad(Sword sword) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoad(Treasure treasure) {
		// TODO Auto-generated method stub
		
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
        return new DungeonController(load(), entities);
    }

	

}
