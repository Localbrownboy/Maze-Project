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
 * screen that is shown when the user wins the level
 */
public class WinEnvironment extends TwoButtonEnvironment {
    
    public JButton continueButton;
    public JButton exitButton;
    public BufferedImage titleText;
    public Font arial40;

    public WinEnvironment(){
        super(new NextLevelAction("Continue", null, TOOL_TIP_TEXT_KEY, null), new QuitToTitleAction("Quit", null, TOOL_TIP_TEXT_KEY, null),"/sprites/menus/EscapedText.png", "/sprites/menus/WinArt.png");

        
    }

    @Override
   public String subtitleDisplay(){
        return "Total score: " + Game.accumulatingScore;
   }

}
