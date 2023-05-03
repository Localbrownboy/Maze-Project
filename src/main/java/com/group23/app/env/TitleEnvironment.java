package com.group23.app.env;

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
  * Class for the TitleEnvironment. Extends {@link JPanel}. Used for displaying the title screen.
 */
public class TitleEnvironment extends TwoButtonEnvironment {
    
    //new StartGameAction("Start Game", null, TOOL_TIP_TEXT_KEY, null)
    //"/sprites/menus/ArcanistText.png"
    public TitleEnvironment(){
       super(new StartGameAction("Start Game", null, TOOL_TIP_TEXT_KEY, null), new ExitGameAction("Exit", null, TOOL_TIP_TEXT_KEY, null), "/sprites/menus/ArcanistText.png", "/sprites/menus/TitleArt.png");
        
    }

    

}
