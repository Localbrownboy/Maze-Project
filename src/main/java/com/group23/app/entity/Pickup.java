package com.group23.app.entity;

import java.util.ArrayList;

import com.group23.app.level.MazeLevel;

/**
 * Class of entities that have a unique behaviour on interaction with the player
 * extends {@link Entity}
 */
abstract public class Pickup extends Entity{



    /**
     * constructor for the entity
     * @param L the level that the entity spawns on
     */
    public Pickup(MazeLevel L){
        super(L);
    }

    /**
     * Checks to see if this entity is colliding with the player entity
     * @param othersOnTile  is an {@link ArrayList<Entity>} containing those entities which live on the same tile as this object
     */
    @Override
    public void checkCollision(ArrayList<Entity> othersOnTile) {
        super.checkCollision(othersOnTile);
        int n = othersOnTile.size();
        for(int i =0 ; i < n ; i++){
            if(othersOnTile.get(i).getProperties().contains(EntityProperty.Player)){
                onPickUp(othersOnTile.get(i));
            }
        }
    }

    public void onPickUp(Entity e){
        return;
    }


}
