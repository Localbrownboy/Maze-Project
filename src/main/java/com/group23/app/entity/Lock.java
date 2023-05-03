package com.group23.app.entity;

import javax.imageio.ImageIO;

import com.group23.app.level.MazeLevel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * entity that spawns and prevents entity movement to that block
 *
 * extends {@link Wall}
 */
public class Lock extends Wall{


    /**
     * Constructor for entity
     * @param L the level that the entity exists on
     */
    public Lock(MazeLevel L){
        super(L);
        drawLayer = 5;
        try{
            texture = ImageIO.read(getClass().getResourceAsStream("/sprites/entities/Lock.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
    }


    /**
     * Function that is run every game tick
     * when all the level keys are collected, this entity despawns
     */
    @Override
    public void update() {
        super.update();
        if(level.totalKeys == level.collectedKeys){
            System.out.println("all keys collected");
            this.remove();
        }
    }
}
