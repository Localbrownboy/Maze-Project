package com.group23.app.env;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * the interface that is shown to the user around the game screen
 */
public class UI {
    GameEnvironment ge;
    //Font arial_40;
    Font arial40;

    //Font arial40_;

    //Font arial401;
    //Font arial402;
    BufferedImage keyImage;
    BufferedImage crystalImage;

    BufferedImage fireImage;

    BufferedImage iceImage;

    BufferedImage readyImage;

    public UI(GameEnvironment ge)
    {
        this.ge = ge;
        //Create font for UI
        arial40 = new Font("Arial", Font.BOLD, 40);
        //arial_40 = new Font("Arial", Font.BOLD, 40);

        //Load the resource for the key sprite into variable keyImage
        try{
            keyImage= ImageIO.read(getClass().getResourceAsStream("/sprites/entities/Key.png"));
        } catch(IOException e){
            e.printStackTrace();
        }

        //Load the resource for the crystal sprite into variable crystalImage
        try{
            crystalImage= ImageIO.read(getClass().getResourceAsStream("/sprites/entities/Crystal.png"));
        } catch(IOException e){
            e.printStackTrace();
        }

        //Load the resource for the fire sprite into variable fireImage
        try{
            fireImage= ImageIO.read(getClass().getResourceAsStream("/sprites/entities/FireSpell.png"));
        } catch(IOException e){
            e.printStackTrace();
        }

        //Load the resource for the ice sprite into variable iceImage
        try{
            iceImage= ImageIO.read(getClass().getResourceAsStream("/sprites/entities/WallIce.png"));
        } catch(IOException e){
            e.printStackTrace();
        }

    }

    /**
     * draw the keys collected
     * @param g2    tool to draw to the window
     */
    public void drawKeyCount(Graphics2D g2)
    {
        g2.setFont(arial40);
        g2.setColor(Color.WHITE);
        g2.drawImage(keyImage, 0, 25, null);
        g2.drawString("= " + ge.level.collectedKeys, 80, 89);
    }

    /**
     * draw the crystals collected
     * @param g2    tool to draw to the window
     */
    public void drawCrystalScore(Graphics2D g2)
    {
        g2.setFont(arial40);
        g2.setColor(Color.WHITE);
        g2.drawImage(crystalImage, 6, 120, null);
        g2.drawString("= " + ge.level.collectedCrystals, 110, 180);
    }

    /**
     * draw the score
     * @param g2    tool to draw to the window
     */
    public void drawScore(Graphics2D g2)
    {
        g2.setFont(arial40);
        g2.setColor(Color.WHITE);
        g2.drawString("Score = " + ge.level.getScore(), 6, 260);
    }

    /**
     * draw the current player health
     * @param g2    tool to draw to the window
     */
    public void drawLife(Graphics2D g2)
    {
        g2.setFont(arial40);
        g2.setColor(Color.WHITE);
        g2.drawString("Life = " + ge.level.playerHealthCurrent + "/" + ge.level.playerHealthMax, 6, 340);
    }

    /**
     * draw the fire ability icon and its cooldown
     * @param g2    tool to draw to the window
     */
    public void drawFire(Graphics2D g2) {
        g2.setFont(arial40);
        g2.setColor(Color.WHITE);
        g2.drawImage(fireImage, 1200, 25, null);
        if (ge.level.fireCooldown > 0) {
            g2.drawString(" " + ge.level.fireCooldown, 1245, 36);
        } else {
            g2.drawImage(readyImage, 1255, 33, null);
        }
    }

    /**
     * draw the ice ability icon and its cooldown
     * @param g2    tool to draw to the window
     */
    public void drawIce(Graphics2D g2) {
        g2.setFont(arial40);
        g2.setColor(Color.RED);
        g2.drawImage(iceImage, 1220, 200, null);
        if (ge.level.iceCooldown > 0) {
            g2.drawString(" " + ge.level.iceCooldown, 1280, 226);
        }
        else {
            g2.drawImage(readyImage, 1290, 216, null);
        }
    }

    //Function that is called from GameEnvironment to draw the Graphics UI. Made in UI class in order to prevent
    //other classes from gaining info from other classes (law of demeter)
    public void drawGraphics(Graphics2D g2)
    {
        drawKeyCount(g2);
        drawCrystalScore(g2);
        drawScore(g2);
        drawLife(g2);
        drawFire(g2);
        drawIce(g2);
    }
}
