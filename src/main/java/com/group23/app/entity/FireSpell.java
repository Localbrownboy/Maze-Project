package com.group23.app.entity;

import javax.imageio.ImageIO;

import com.group23.app.level.MazeLevel;

//import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Fire that the player can cast around them to break blocks
 * an extension of PickUp
 * interacts with Entities that have the property breakable causing them to break
 * implements NonPermanent
 *
 *
 */
public class FireSpell extends Pickup {
    /**
     * the ticks remaining till the spell can be cast again
     */
    private int durationRemaining;


    /**
     * Constructor for Fire entity
     * @param L the level that the entity will spawn on
     */
    public FireSpell(MazeLevel L){
        super(L);
        //set life span
        super.durationRemaining = 5;
        try{
            texture = ImageIO.read(getClass().getResourceAsStream("/sprites/entities/FireSpell.png"));
//            additionalTextures.add(ImageIO.read(getClass().getResourceAsStream("/sprites/entities/PlayerDirUp.png")));
//            additionalTextures.add(ImageIO.read(getClass().getResourceAsStream("/sprites/entities/PlayerDirDown.png")));
//            additionalTextures.add(ImageIO.read(getClass().getResourceAsStream("/sprites/entities/PlayerDirLeft.png")));
//            additionalTextures.add(ImageIO.read(getClass().getResourceAsStream("/sprites/entities/PlayerDirRight.png")));

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<EntityProperty> getProperties(){
        return new ArrayList<EntityProperty>(List.of(EntityProperty.Damaging, EntityProperty.Temporary));
    }
}
