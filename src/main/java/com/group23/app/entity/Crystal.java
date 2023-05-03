package com.group23.app.entity;

import com.group23.app.level.MazeLevel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;


/**
 * Crystal class that the user will attempt to pick up
 * extends PickUp
 * implements NonPermanent
 *
 */
public class Crystal extends Pickup{

    protected int durationRemaining;


    /**
     * constructor for the Crystal Class
     * @param L the level that the entity will be spawned into
     */
    public Crystal(MazeLevel L){
        super(L);
        this.durationRemaining = 20;
        try{
            texture = ImageIO.read(getClass().getResourceAsStream("/sprites/entities/Crystal.png"));

        } catch(IOException e){
            e.printStackTrace();
        }
    }


    /**
     * remove the crystal off the map
     */
    public void deSpawn(){
        this.remove();
        if(level != null)
            level.hasCrystal = false;
    }

    @Override
    public void update() {
        if(durationRemaining > 0 ){
            durationRemaining--;
        }
        if(durationRemaining ==0){
            deSpawn();
        }
    }

    /**
     * provides the functionality that will be run when an entity interacts with the crystal
     * @param e the entity whos collision called this method
     */
    @Override
    public void onPickUp(Entity e) {
        this.level.collectedCrystals++;
        this.level.scoreNum+=20;
        this.deSpawn();
    }

    @Override
    public ArrayList<EntityProperty> getProperties(){
        return new ArrayList<EntityProperty>(List.of(EntityProperty.Temporary));
    }
     
    
}
