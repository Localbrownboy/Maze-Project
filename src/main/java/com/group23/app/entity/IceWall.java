package com.group23.app.entity;
import javax.imageio.ImageIO;

import com.group23.app.level.MazeLevel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity that is cast by the player that acts as a temporary wall, can be destroyed by the {@link FireSpell}
 * extends {@link BreakableWall}
 */
public class IceWall extends BreakableWall{
    /**
     * number of ticks before the entity deSpawns
     */
    private int durationRemaining;


    /**
     * Constructor for the entity
     * @param L the level that the entity has spawned on
     */
    public IceWall(MazeLevel L){
        super(L);
        super.durationRemaining = 10;
        drawLayer = 1;
        try{
            texture = ImageIO.read(getClass().getResourceAsStream("/sprites/entities/WallIce.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<EntityProperty> getProperties(){
        return new ArrayList<EntityProperty>(List.of(EntityProperty.Solid, EntityProperty.Breakable, EntityProperty.Temporary));
    }

}
