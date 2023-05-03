package com.group23.app.entity;

import com.group23.app.*;

import javax.imageio.ImageIO;

import com.group23.app.level.MazeLevel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KeyObjective extends Pickup{


    /**
     * Constructor for the entity
     * @param L the level that the entity spawns in on
     */
    public KeyObjective(MazeLevel L){
        super(L);
        drawLayer = 4;
        try{
            texture = ImageIO.read(getClass().getResourceAsStream("/sprites/entities/Key.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
    }


    /**
     * when a player collides with the key objective, it increments the field {@link MazeLevel#collectedKeys}
     * @param e the entity that triggered the pick up
     */
    public void onPickUp(Entity e) {
        Game.playSE(3);
        this.level.collectedKeys++;
        this.level.scoreNum+=30;
        this.remove();
        System.out.println("player grabbed key number:" + this.level.collectedKeys);
    }

    /**
     * returns an array list of properties that this entity has
     * @return
     */
    @Override
    public ArrayList<EntityProperty> getProperties(){
        return new ArrayList<EntityProperty>(List.of(EntityProperty.Objective));
    }



}
