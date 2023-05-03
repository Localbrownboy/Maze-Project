package com.group23.app.entity;

import com.group23.app.*;
import com.group23.app.level.MazeLevel;
import com.group23.app.utils.GameUtils;

import java.util.ArrayList;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Base class of all entities that have the ability to move between tiles
 * Extends {@link Entity}
 */
abstract public class MobileEntity extends Entity{


    /**
     * Constructor for the entity
     * @param L Level that the entity spawns in on
     */
    public MobileEntity(MazeLevel L){
        super(L);
        moved = false;
         
    }

    /**
     * the direction that the entity is currently facing
     */
    protected Direction curDirection = Direction.Up;

    /**
     * stores frames for playing the movement animation
     */
    protected int animFrames = 0;
    protected int maxAnimFrames = 0;

    /**
     * stores the last position of the entity, for animation purposes
     */
    public int[] previousPosition = new int[2];

    /**
     * checks if the entity has moved
     */
    protected boolean moved = false;


    /**
     * Attempts to Relocate the entity to a new tile, does not move the entity if it tries to move to a solid tile
     * @param x the x coordinate to move to
     * @param y the y coordinate to move to
     */
    public boolean tryMoveTo(int x,int y){

        if(x<0 || x >= level.width|| y < 0 || y >= level.height){
            return false;
        }

        if(level.tileAt(x, y).checkifCollides() == true){
            return false;
        }
        MoveTo(x, y);
        return true;
    }

    /**
     * Relocates the entity to a new tile
     * @param x the x coordinate to move to
     * @param y the y coordinate to move to
     */
    public void MoveTo(int x,int y){

        if(x<0 || x >= level.width|| y < 0 || y >= level.height){
            return;
        }
        moved = true;
        previousPosition = getPosition().clone();

        if(level.tileAt(getPosition()[0], getPosition()[1]).entities.contains(this)){
            level.tileAt(getPosition()[0], getPosition()[1]).entities.remove(this);
        }

        setPosition(x, y);

        level.tileAt(x, y).entities.add(this);
        //level.tileAt(x, y).needsSort = true;

        maxAnimFrames = (int)(((float)level.tickspeed)/5f);
        animFrames = maxAnimFrames;
       // System.out.println("Anim frames set to:" + maxAnimFrames);
       // System.out.println("Prev Position: " + previousPosition[0] +", " + previousPosition[1]);
       // System.out.println("Current Position:" + getPosition()[0] +", " + getPosition()[1]);

        
    }

    @Override
    public void update(){
        checkCollision(getCurrentTile().entities);
        movementBehaviour();
    }

    public void movementBehaviour(){


    }

    @Override
    public void draw(Graphics2D g){
        
        int ts = level.getTileSize();
        
        Point drawPosition;
        Point drawPosition1;
        Point drawPosition2 = new Point(level.getRenderBox().x + (getPosition()[0]*level.getTileSize()), level.getRenderBox().y + (getPosition()[1]*level.getTileSize()));
        
        float anim = ((float)animFrames)/((float)maxAnimFrames);
        
        if(moved){
            drawPosition1= new Point(level.getRenderBox().x + (previousPosition[0]*level.getTileSize()), level.getRenderBox().y + (previousPosition[1]*level.getTileSize()));
            drawPosition = GameUtils.pointLerp(drawPosition1, drawPosition2, 1f-anim);
        }else{
            drawPosition1 = drawPosition2;
            drawPosition = drawPosition2;
        }

        if(additionalTextures.size()>=4){
            
            switch(curDirection){
                case Up:
                g.drawImage(additionalTextures.get(Direction.Up.ordinal()), drawPosition.x, drawPosition.y, ts, ts, null);
                break;
                case Down:
                g.drawImage(additionalTextures.get(Direction.Down.ordinal()), drawPosition.x, drawPosition.y, ts, ts, null);
                break;
                case Left:
                g.drawImage(additionalTextures.get(Direction.Left.ordinal()), drawPosition.x, drawPosition.y, ts, ts, null);
                break;
                case Right:
                g.drawImage(additionalTextures.get(Direction.Right.ordinal()), drawPosition.x, drawPosition.y, ts, ts, null);
                break;
            }

        }else if(texture==null){
            return;
            
        }else{
            g.drawImage(texture, drawPosition.x, drawPosition.y, ts, ts, null);
        }
        
        if(animFrames>0){
            animFrames--;
        }
        //find the location based on the game environment point draw bounds
        

        
    }


      


    
}
