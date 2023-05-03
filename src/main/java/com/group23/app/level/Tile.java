package com.group23.app.level;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.group23.app.entity.Entity;
import com.group23.app.entity.EntityProperty;

public class Tile {
    
    boolean collision = false;
    public boolean needsSort = false;
    public ArrayList<Entity> entities;
    
    //public BufferedImage image;
    public Tile(){
        entities = new ArrayList<Entity>();
    }

    public boolean checkifCollides(){
        //boolean solid = false;
        for(int i = 0; i < entities.size(); ++i){
            Entity e = entities.get(i);
            if(e.getProperties().contains(EntityProperty.Solid)){
                return true;
            }
        }

        return false;

    }
}
