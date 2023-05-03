package com.group23.app.env;
import com.group23.app.Game;
import com.group23.app.action.*;

import javax.swing.*;

import java.awt.*;
import com.group23.app.*;
import com.group23.app.action.*;

import javax.swing.*;

import java.awt.*;

import java.awt.Color;
import java.awt.Graphics2D;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Class for LoseEnvironment. Extends {@link TwoButtonEnvironment}. Used for displaying the losing screen.
 */
public class LoseEnvironment extends TwoButtonEnvironment {
    
    
    
    public LoseEnvironment(){
        super(null, new QuitToTitleAction("Return", null, TOOL_TIP_TEXT_KEY, null), "/sprites/menus/DefeatedText.png", "/sprites/menus/LoseArt.png");

    }

   @Override
   public String subtitleDisplay(){
        return "Total score: " + Game.accumulatingScore;
   }

}
