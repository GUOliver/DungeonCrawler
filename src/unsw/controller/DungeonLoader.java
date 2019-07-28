package unsw.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import unsw.dungeon.Bomb;
import unsw.dungeon.Boulder;
import unsw.dungeon.Door;
import unsw.dungeon.Dungeon;
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
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        
        // Adding player to all enemy hit-lists
        Player player = dungeon.getPlayer();
        player.registerObservers();
        return dungeon;
    }

    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y);
            onLoad(wall);
            entity = wall;
            break;
        case "bomb":
            Bomb bomb = new Bomb(x, y);
            onLoad(bomb);
            entity = bomb;
            break;
        case "boulder":
            Boulder boulder = new Boulder(x, y);
            onLoad(boulder);
            entity = boulder;
            break;
        case "key":
            Key key = new Key(dungeon, x, y);
            onLoad(key);
            entity = key;
            break;
        case "door":
            Door door = new Door(dungeon, x, y);
            onLoad(door);
            entity = door;
            break;
        case "enemy":
            Enemy enemy = new Enemy(x, y);
            onLoad(enemy);
            entity = enemy;
            break;
        case "switch":
            FloorSwitch floorSwitch = new FloorSwitch(x, y);
            onLoad(floorSwitch);
            entity = floorSwitch;
            break;
        case "potion":
            InvincibilityPotion potion = new InvincibilityPotion(x, y);
            onLoad(potion);
            entity = potion;
            break;
        case "sword":
            Sword sword = new Sword(x, y);
            onLoad(sword);
            entity = sword;
            break;
        case "treasure":
            Treasure treasure = new Treasure(x, y);
            onLoad(treasure);
            entity = treasure;
            break;
        // TODO Handle other possible entities
        }
        
        if (entity != null) {
        	dungeon.addEntity(entity);
        }
    }

    public abstract void onLoad(Player player);

    public abstract void onLoad(Wall wall);
    
    public abstract void onLoad(Bomb bomb);
    
    public abstract void onLoad(Boulder boulder);
    
    public abstract void onLoad(Key key);
    
    public abstract void onLoad(Door door);
    
    public abstract void onLoad(Enemy enemy);
    
    public abstract void onLoad(FloorSwitch floorSwitch);
    
    public abstract void onLoad(InvincibilityPotion potion);
    
    public abstract void onLoad(Sword sword);
    
    public abstract void onLoad(Treasure treasure);

    // TODO Create additional abstract methods for the other entities

}