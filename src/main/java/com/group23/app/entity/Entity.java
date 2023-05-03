package com.group23.app.entity;

//import com.group23.app.*;
//import com.group23.app.env.*;
import com.group23.app.level.*;

import java.util.ArrayList;
import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.IOException;


/**
 * The basic class from which ingame objects are derived
 * 
 */

public abstract class Entity {
    

    //used for
    public int drawLayer;
    public int durationRemaining;


    private int[] _position;

    protected MazeLevel level;

    public BufferedImage texture = null;
    public BufferedImage readyButton = null;

    protected ArrayList<BufferedImage> additionalTextures = new ArrayList<BufferedImage>();
    //turned into method which checks if the properties list contains EntityProperty.Solid we'll consider it as solid


    /**
     * Constructor for base entity class
     * @param L the level that the entity would be spawned on
     */
    public Entity(MazeLevel L){
        durationRemaining = -1;
        _position = new int[2];
        level = L;
        drawLayer = 1;
        //scoreNum = 0;
    }


    /**
     * returns the position of the entity
     * @return  returns array of size 2 with the x and y coordinates of the entity
     */
    public int[] getPosition(){
        return _position;
    }

    /**
     * returns the position of the entity
     * @return  returns a point object with the x and y coordinates of the entity
     */
    public Point getPositionAsPoint(){
        return new Point(_position[0], _position[1]);

    }

    /**
     * sets the position of the entity
     * @param x x position on the tilemap
     * @param y y position on the tilemap
     * @return null
     */
    public void setPosition(int x, int y){
        _position[0] = x;
        _position[1] = y;
    }

    /**
     * gets tile that the entity is currently residing on if
     * @return  returns the tile
     */
    public Tile getCurrentTile(){
        if(level==null){
            // don't return anything if this entity doesn't exist as part of a level with tiles
            return null;
        }
        return level.tileAt(getPosition()[0], getPosition()[1]);
    }

    /**
     * Something that the entity does on each tick
     */
    public void update(){
        if(durationRemaining > 0){
            durationRemaining--;
        }
        if(durationRemaining == 0){
            remove();
        }
    }

    /**
     * checks entity against all other entities on tiles
     */
    public void checkCollision(ArrayList<Entity> othersOnTile){
        
    }

    public ArrayList<EntityProperty> getProperties(){
        return new ArrayList<EntityProperty>();
    }

    /**
     * removes the current entity off the tile and level that it exists on
     */
    public void remove(){
        if(level==null){
            return;
        }

        if(getCurrentTile().entities.contains(this)){
            getCurrentTile().entities.remove(this);
        }

        if(level.entities.contains(this)){
            level.entities.remove(this);
        }
    }

    /**
     * handles the drawing of the entity's textures
     */
    public void draw(Graphics2D g){
        if(texture==null){
            return;
        }
        int ts = level.getTileSize();

        //find the location based on the game environment point draw bounds
        Point drawPosition = new Point(level.getRenderBox().x + (getPosition()[0]*level.getTileSize()), level.getRenderBox().y + (getPosition()[1]*level.getTileSize()));

        g.drawImage(texture, drawPosition.x, drawPosition.y, ts, ts, null);
    }

}
