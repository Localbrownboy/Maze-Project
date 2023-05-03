package com.group23.app.entity;
import com.group23.app.*;

import javax.imageio.ImageIO;

import com.group23.app.level.MazeLevel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Door Entity that the user must leave, when game is initialized it will normally share its tile with a {@link Lock}
 * extends the Pickup Class
 */
public class Door extends Pickup{



    /**
     *  Constructor for Door
     * @param L the level in which the entity will be spawned on
     */
    public Door(MazeLevel L){
        super(L);
        drawLayer = 1;
        try{
            texture = ImageIO.read(getClass().getResourceAsStream("/sprites/entities/Door.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * player leaves that level of the maze when they are able to collide with this.
     * @param e the entity that triggered the pickup of the object
     */
    @Override
    public void onPickUp(Entity e) {
        System.out.println("player left maze");
        this.level.leave(true);
    }


//    @Override
//    public ArrayList<EntityProperty> getProperties(){
//        return new ArrayList<EntityProperty>(List.of(EntityProperty.Objective));
//    }
}
