package com.group23.app.entity;

import javax.imageio.ImageIO;

import com.group23.app.level.MazeLevel;

import java.io.IOException;

/**
 * entity that the player can freely walk on
 */
public class Floor extends Entity{


    /**
     * constructor for floor entity
     * @param L the level that the entity is spawned on
     */
    public Floor(MazeLevel L){
        super(L);
        drawLayer = 0;
        try{
            texture = ImageIO.read(getClass().getResourceAsStream("/sprites/entities/Floor.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
