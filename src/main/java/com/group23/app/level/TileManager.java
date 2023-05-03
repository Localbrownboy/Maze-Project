package com.group23.app.level;

import com.group23.app.level.*;

import javax.imageio.ImageIO;

import com.group23.app.env.*;

import java.awt.*;
import java.io.IOException;

public class TileManager {
    private GameEnvironment _ge;
    
    //not needed since we have the grid in the GameEnvironment's level
    //Tile[] tile; 
    

    public TileManager(GameEnvironment ge){
        this._ge = ge;
        //tile = new Tile[8];
        getTimeImage();
    }

    public void getTimeImage(){

//         try{
//                 // tile[0] = new Tile();
//                 // tile[0].image = ImageIO.read(getClass().getResourceAsStream("/Floor.png"));
                
// //            tile[1] = new Tile();
// //            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/sprites/entities/Wall.png"));
// //            tile[1].collision = true;

// //            tile[2] = new Tile();
// //            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/sprites/entities/Door.png"));
// //            tile[2].collision = true;

//         } catch (IOException e){
//             e.printStackTrace();
//         }
    }

    public void draw(Graphics2D g2){
        if(_ge.level==null){
            return;
        }

        /*for(int k = 0; k < _ge.level.entities.size(); ++k){
            _ge.level.entities.get(k).draw(g2);
        }*/
        
        for(int i = 0; i < _ge.level.width; i++){
            for(int j = 0; j < _ge.level.height; j++){

                Tile t = _ge.level.tileAt(i, j);
                

                for(int k = 0; k < t.entities.size(); ++k){
                    t.entities.get(k).draw(g2);
                }

            }
        }
        
    }
}
