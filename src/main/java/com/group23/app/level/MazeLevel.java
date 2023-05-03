package com.group23.app.level;

import java.util.ArrayList;

//import com.group23.app.Tile;
import com.group23.app.*;
import com.group23.app.entity.*;
import com.group23.app.env.*;

import java.util.Properties;
import java.util.concurrent.*;


import java.awt.*;;

/**
 * The base class which handles the mazes and storing their entities
 */

public class MazeLevel {
    
    public int collectedKeys;
    public int totalKeys;
    public boolean hasCrystal;
    public int scoreNum;
    public int collectedCrystals;
    public int playerHealthMax = 3;
    public int playerHealthCurrent;
    public int width;
    public int height;

    public int tickspeed;
    public int ticksBeforeUpdate;

    public int fireCooldown;

    public int iceCooldown;

    public boolean testMode;

    private boolean needsSort;

    public int winState;


    // all of the entities in the level
    public ArrayList<Entity> entities;

    //the grid of tiles
    public ArrayList<ArrayList<Tile>> _tileMap;
 


//Initializes all basic features of Maze, such as the maze itself, tiles, and entities
    public MazeLevel(int w, int h, int speed){
        width = w;
        height = h;
        tickspeed = speed;
        ticksBeforeUpdate = speed;
        playerHealthCurrent = playerHealthMax = 3;
        collectedCrystals = 0;
        hasCrystal = false;
        needsSort = true;
        testMode = false;
        winState = 0;


        scoreNum = 0;

        entities = new ArrayList<Entity>();

        _tileMap = new ArrayList<ArrayList<Tile>>();

        for(int i = 0; i < width; i++){
            ArrayList<Tile> column = new ArrayList<Tile>();

            for(int j = 0; j < height; j++){
                column.add(new Tile());
            }
            
            _tileMap.add(column);
        }
    }


    //public void initialize(){} //unused

    public Tile tileAt(int x, int y){
        if(x < 0 || x >= width || y < 0 || y >= height){
            System.out.println("Could not get tile, coordinates out of range");
            return null;
        }

        return _tileMap.get(x).get(y);

    }



//Spawn crystal at randomly generated spot
    public void spawnCrystal(){
        //
        int randomX = ThreadLocalRandom.current().nextInt(0, width);
        int randomY = ThreadLocalRandom.current().nextInt(0, height);

//        if(tileAt(randomX , randomY).checkifCollides() ) {
//            return;
//        }
        int n = tileAt(randomX , randomY).entities.size();
        for (int i = n-1; i >= 0; i--){
            ArrayList<EntityProperty> properties = tileAt(randomX , randomY).entities.get(i).getProperties();
            int k = properties.size();
            for (int j = 0 ; j < k ; k++) {
                if (properties.get(j) == EntityProperty.Solid || properties.get(j) == EntityProperty.Hazard) {
                    return;
                }
            }
        }
                spawnEntity(new Crystal(this), randomX, randomY);
                hasCrystal = true;
    }
    // automatically place an entity in the level
    public void spawnEntity(Entity e, int x, int y){

        if(entities.contains(e)){
            return;
        }
        if(x>=0&&x < width&&y>=0 && y<height) {
            entities.add(e);
            e.setPosition(x, y);
            tileAt(x, y).entities.add(e);
            tileAt(x, y).needsSort = true;
        }


    }

    /**
     * Updates when there is a level tick, which occurs once a certain amount of frames determined by TickSpeed;
     * 
     */
    public void updateTick(){

        //first iteration to verify tile sorting

        /*if(!needsSort){
            sortAllEntities();
            needsSort = true;
        }*/
        if(needsSort){
            sortAllEntities();
            needsSort = false;
            }
        

        //first iteration for updates 
        for(int i = 0; i < entities.size(); i++){
            entities.get(i).update();
        }
        //second iteration to verify tile sorting
        sortEntities();
       
        //third iteration to check entity collision
        for(int i = 0; i < entities.size(); i++){
            Entity e = entities.get(i);
            e.checkCollision(e.getCurrentTile().entities);   
        }
        //tries to spawn a bonus crystal if one is not onscreen
        if(hasCrystal == false)
            spawnCrystal();

        //stops the game if the player is killed
        if(playerHealthCurrent<=0){
            //if(!testMode){
                Game.playSE(2);
            //}
            leave(false);
            
        }

    }

    /**
     * exits the level to the appropriate screen
     * @param win whether or not the level was won or lost
     */
    public void leave(boolean win){
        winState = win ? 1 : -1;
        if(testMode){
            return;
        }
        
        if(win){
            scoreNum+=100;
        }
        Game.stopMusic(true);
        Game.accumulatingScore += scoreNum;
        if(win){
            Game.setGameState(GameState.GameWinScreen);
        }else{
            Game.setGameState(GameState.GameOverScreen);
        }
    }


    /**
     * Sorts entities by draw layer so all the drawing is correctly ordered
     */
    public void sortEntities(){
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; ++j ){
                Tile t = tileAt(i, j);
                if(t.needsSort){
                    t.entities.sort((o1, o2) -> Integer.compare(o1.drawLayer, o2.drawLayer));
                    t.needsSort = false;
                }
                
            }
        }
    }

    /**
     * Sorts entities by draw layer so all the drawing is correctly ordered
     */
    public void sortAllEntities(){
        
        entities.sort((o1, o2) -> Integer.compare(o1.drawLayer, o2.drawLayer));
         
    }


    public Rectangle getRenderBox(){
        
        float longer = width;
        float shorter = height;
        boolean taller = false;

        if(height>width){
            longer = height;
            shorter = width;
            taller = true;
        }

        Rectangle rect = new Rectangle();

        if(taller){
            rect.y = (int)((float)GameEnvironment.levelRenderSquareCentre.y - (GameEnvironment.levelRenderSquareWidth/2f));
            rect.x = (int)((float)GameEnvironment.levelRenderSquareCentre.x - ((GameEnvironment.levelRenderSquareWidth/2f)*(shorter/longer)));
            rect.width = (int)((float)GameEnvironment.levelRenderSquareWidth * (shorter/longer));
            rect.height = (int)((float)GameEnvironment.levelRenderSquareWidth);

        }else{
            rect.x = (int)((float)GameEnvironment.levelRenderSquareCentre.x - (GameEnvironment.levelRenderSquareWidth/2f));
            rect.y = (int)((float)GameEnvironment.levelRenderSquareCentre.y - ((GameEnvironment.levelRenderSquareWidth/2f)*(shorter/longer)));
            rect.height = (int)((float)GameEnvironment.levelRenderSquareWidth * (shorter/longer));
            rect.width = (int)((float)GameEnvironment.levelRenderSquareWidth);
        }

        return rect;

    }

    public int getTileSize(){
        float longer = width > height ? width : height;
        
        return (int)(GameEnvironment.levelRenderSquareWidth / longer);
    }

    public int getScore()
    {
        int score = 0;
        score += this.collectedCrystals*20;
        score += this.collectedKeys*30;
        return score;
    }



    public Point findPlayerPosition(){
        for(int i = 0; i < entities.size(); ++i){
            if(entities.get(i).getProperties().contains(EntityProperty.Player)){
                return new Point(entities.get(i).getPosition()[0], entities.get(i).getPosition()[1]);
            }
        }
        return null;
    }

}
