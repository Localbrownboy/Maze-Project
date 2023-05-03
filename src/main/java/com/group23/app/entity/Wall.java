package com.group23.app.entity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.group23.app.level.MazeLevel;

/**
 * constructor for the wall entity, other entities cannot share move onto a tile that contains a wall
 * extends {@link Entity}
 */
abstract public class Wall extends Entity  {


    /**
     * Constructor for wall entity
     * @param L the level that the entity is spawned on
     */
    public Wall(MazeLevel L){
        super(L);
        drawLayer = 2;
        try{
            texture = ImageIO.read(getClass().getResourceAsStream("/sprites/entities/Wall.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * gets the properties of this entity
     * @return  {@link ArrayList<EntityProperty>} that contains the properties of this entity
     */
    @Override
    public ArrayList<EntityProperty> getProperties(){
        return new ArrayList<EntityProperty>(List.of(EntityProperty.Solid));
    }
     
}

