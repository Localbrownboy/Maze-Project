package com.group23.app.entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.group23.app.level.*;

/**
 * Class of breakable wall
 */
public class BreakableWall extends Wall {

    public BreakableWall(MazeLevel L){
        super(L);
        drawLayer = 2;
        try{
            texture = ImageIO.read(getClass().getResourceAsStream("/sprites/entities/WallBreakable.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
        this.getProperties().add(EntityProperty.Breakable);
    }

    /**
     * Is collision checker overridden to match the behaviour expected of a breakable wall
     * @param othersOnTile  list of other entities on the tile
     */
    @Override
    public void checkCollision(ArrayList othersOnTile) {
        super.checkCollision(othersOnTile);
        int n = othersOnTile.size();
        for(int i =n-1 ; i >= 0 ; i--){
            if(othersOnTile.get(i) instanceof FireSpell){
                this.wallBreak();
            }
        }
    }


    /**
     * only called by the classes deSpawn function to remove it off the level
     */
    private void wallBreak(){
        this.remove();
    }

    @Override
    public ArrayList<EntityProperty> getProperties(){
        return new ArrayList<EntityProperty>(List.of(EntityProperty.Solid, EntityProperty.Breakable));
    }
    
}
