package com.group23.app.entity;

import com.group23.app.*;

import javax.imageio.ImageIO;

import com.group23.app.level.MazeLevel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * class that damages that player on collision
 * extends {@link Pickup}
 *
 */
public class SpikeHazard extends Pickup{


    /**
     * Constructor for the entity
     * @param L the leve that the entity is spawned on
     */
    public SpikeHazard(MazeLevel L){
        super(L);
        drawLayer = 3;
        try{
            texture = ImageIO.read(getClass().getResourceAsStream("/sprites/entities/Spikes.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * behaviour of class on collision with the player
     * @param e the entity that triggered the collision, will be the player in this case
     */
    @Override
    public void onPickUp(Entity e) {

             this.level.playerHealthCurrent--;
             Game.playSE(4);

         System.out.println("player lost 1 HP");
    }

    /**
     * Gets the properties of this entity
     * @return an {@link ArrayList<EntityProperty>} that contains the properties of this entity
     */
    @Override
    public ArrayList<EntityProperty> getProperties(){
        return new ArrayList<EntityProperty>(List.of(EntityProperty.Pickup,  EntityProperty.Hazard));
    }
}
