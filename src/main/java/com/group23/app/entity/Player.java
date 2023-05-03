package com.group23.app.entity;

import java.awt.*;
import java.awt.Graphics2D;

import java.util.*;
import java.util.ArrayList;
import java.util.List;


import javax.swing.text.Position;
import javax.imageio.ImageIO;
import java.io.IOException;

import com.group23.app.*;
import com.group23.app.level.MazeLevel;

import com.group23.app.Sound;

/**
 * Class for the Player. Extends {@link MobileEntity}. This is the player class that the user interacts with to play the game.
 */

public class Player extends MobileEntity{
    /**
     * number of ticks before {@link FireSpell} can be cast again
     */
    public int fireCooldown;
    /**
     * number of ticks before {@link IceWall} can be cast again
     */
    public int iceCooldown;
    //private int grassCooldown;
    Sound sound = new Sound();


    /**
     * Constructor for player entity
     * @param L the level that the entity is spawned on
     */
    public Player(MazeLevel L){
        super(L);
        drawLayer = 5;
        try{
            texture = ImageIO.read(getClass().getResourceAsStream("/sprites/entities/PlayerDirUp.png"));
            additionalTextures.add(ImageIO.read(getClass().getResourceAsStream("/sprites/entities/PlayerDirUp.png")));
            additionalTextures.add(ImageIO.read(getClass().getResourceAsStream("/sprites/entities/PlayerDirDown.png")));
            additionalTextures.add(ImageIO.read(getClass().getResourceAsStream("/sprites/entities/PlayerDirLeft.png")));
            additionalTextures.add(ImageIO.read(getClass().getResourceAsStream("/sprites/entities/PlayerDirRight.png")));
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Defines the movement behaviour of the player class
     */
    @Override
    public void movementBehaviour(){
        if(level.testMode){
            return;
        //Once fire key is pressed, test if user is able to cast spell (not on cooldown)
        }//If spell is not on cooldown, call fireSpell(), otherwise ignore
        if(Game.Input.ZQueued == true  && fireCooldown == 0) {
            Game.playSE(1);
            fireSpell();
        }
        else{
            switch(Game.Input.QueuedMove){
                case Left:
                tryMoveTo(getPosition()[0]-1, getPosition()[1]);
                curDirection= Direction.Left;
                this.texture = additionalTextures.get(Direction.Left.ordinal());
                    break;
                case Right:
                    tryMoveTo(getPosition()[0]+1, getPosition()[1]);
                    curDirection = Direction.Right;
                    this.texture = additionalTextures.get(Direction.Right.ordinal());

                    break;
                case Up:
                    tryMoveTo(getPosition()[0], getPosition()[1]-1);
                    curDirection = Direction.Up;
                    this.texture = additionalTextures.get(Direction.Up.ordinal());
                    break;
                case Down:
                    tryMoveTo(getPosition()[0], getPosition()[1]+1);
                    curDirection = Direction.Down;
                    this.texture = additionalTextures.get(Direction.Down.ordinal());
                    break;
                default:
                break;
                    
            }
            if(Game.Input.XQueued == true && iceCooldown == 0){
                Game.playSE(2);
                iceSpell();
            }
        }
        
    }

    /**
     * function that is called every game tick
     * decrements {@link #fireCooldown} and {@link  #iceCooldown}
     */
    @Override
    public void update() {
        super.update();
        if (iceCooldown > 0){
            iceCooldown--;
            level.iceCooldown = this.iceCooldown;
        }
        if(fireCooldown >0){
            fireCooldown--;
            level.fireCooldown = this.fireCooldown;
        }
    }

    /**
     *  casts a circle of {@link FireSpell} entities around the player entity and sets the cooldown for the spell
     */
    public void fireSpell(){
        
        fireCooldown = 7;
        level.spawnEntity(new FireSpell(level){} , this.getPosition()[0]-1, this.getPosition()[1]-1 );
        level.spawnEntity(new FireSpell(level){} , this.getPosition()[0], this.getPosition()[1]-1 );
        level.spawnEntity(new FireSpell(level){} , this.getPosition()[0]+1, this.getPosition()[1]-1 );
        level.spawnEntity(new FireSpell(level){} , this.getPosition()[0]-1, this.getPosition()[1] );
        level.spawnEntity(new FireSpell(level){} , this.getPosition()[0]+1, this.getPosition()[1] );

        level.spawnEntity(new FireSpell(level){} , this.getPosition()[0], this.getPosition()[1]+1 );
        level.spawnEntity(new FireSpell(level){} , this.getPosition()[0]-1, this.getPosition()[1]+1 );
        level.spawnEntity(new FireSpell(level){} , this.getPosition()[0], this.getPosition()[1]+1 );
        level.spawnEntity(new FireSpell(level){} , this.getPosition()[0]+1, this.getPosition()[1]+1 );


    }


    /**
     * casts a 3 wide wall of {@link IceWall} perpendicular to the players direction
     * sets the cooldown for the spell
     */
    public void iceSpell(){
        iceCooldown = 7;
        if (curDirection == Direction.Up || curDirection == Direction.Down) {
            level.spawnEntity(new IceWall(level){} , this.getPosition()[0]-1, this.getPosition()[1]);

            level.spawnEntity(new IceWall(level){} , this.getPosition()[0] , this.getPosition()[1]);
            level.spawnEntity(new IceWall(level){} , this.getPosition()[0]+1, this.getPosition()[1]);

        }else{
            level.spawnEntity(new IceWall(level){} , this.getPosition()[0] , this.getPosition()[1]-1);
            level.spawnEntity(new IceWall(level){} , this.getPosition()[0] , this.getPosition()[1]);
            level.spawnEntity(new IceWall(level){} , this.getPosition()[0] , this.getPosition()[1]+1);


        }
        
    }

//    public boolean grassSpell(){
//
//        return true;
//
//
//    }

    /**
     * gets the properties of the player class
     * @return {@link ArrayList<EntityProperty>} containing the properties of the entity
     */
    @Override
    public ArrayList<EntityProperty> getProperties(){
        return new ArrayList<EntityProperty>(List.of(EntityProperty.Player));
    }

    // /**
    //  * plays music on player spawn in
    //  * @param i the index of the SoundFile array
    //  */
    // public void playMusic(int i){
    //     sound.setFile(i);
    //     sound.play();
    //     sound.loop();
    // }

    // // /**
    // //  * stops the playing of music
    // //  */
    // public void stopMusic() {
    //     sound.stop();
    // }

    // /**
    //  * changes the file that is playing
    //  * @param i the new file that will be played
    //  */
    // public void playSE(int i){
    //     sound.setFile(i);
    //     sound.play();

    // }





}
