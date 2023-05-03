package com.group23.app.env;


import com.group23.app.Game;
import com.group23.app.action.*;

import javax.swing.*;

import java.awt.*;

import java.awt.Color;
import java.awt.Graphics2D;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import java.io.IOException;


/**
 * Base class for a template game environment type with two buttons, used for a variety of menu type screens. Extends {@link JPanel}. 
 */
public class TwoButtonEnvironment extends JPanel {
    
    public ScrollButton button1;
    public ScrollButton button2;
    public BufferedImage titleImage;
    public BufferedImage backgroundImage;
    public Font arial40;

    public TwoButtonEnvironment(Action a1, Action a2, String texturePath, String bgPath){
        this.setPreferredSize(new Dimension(Game.screenWidth, Game.screenHeight));
        this.setBackground(Color.DARK_GRAY);
        this.setDoubleBuffered(true);
        this.addKeyListener(Game.Input);
        this.setFocusable(true);
        this.setLayout(null);

        arial40 = new Font("Arial", Font.BOLD, 40);

        if(a1!=null){
            button1 = new ScrollButton(a1);
            button1.setLayout(null);
            button1.setPreferredSize(new Dimension(200, 100));
            button1.setBounds(700, 300, 200, 100);
            this.add(button1);
        }
        
        if(a2!=null){
            button2 = new ScrollButton(a2);
            button2.setLayout(null);
            button2.setPreferredSize(new Dimension(200, 100));
            button2.setBounds(700, 420, 200, 100);
            this.add(button2);
        }

        if(texturePath!=null&&texturePath!=""){
            try{
                titleImage = ImageIO.read(getClass().getResourceAsStream(texturePath));
            } catch(IOException e){
                e.printStackTrace();
            }
        }

        if(bgPath!=null&&bgPath!=""){
            try{
                backgroundImage = ImageIO.read(getClass().getResourceAsStream(bgPath));
            } catch(IOException e){
                e.printStackTrace();
            }
        }
        

        
        //this.pack
    }

    public String subtitleDisplay(){
        return "";
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        
        if(backgroundImage!=null){
            g2.drawImage(backgroundImage, 0, 0, Game.screenWidth, Game.screenHeight, null);
        }

        if(titleImage!=null){
            g2.drawImage(titleImage, 800-(200), 150, 400, 75, null);
        }
        g2.setFont(arial40);
        g2.setColor(Color.WHITE);
        g2.drawString(subtitleDisplay(), 700, 280);
        
        //g2.dispose();
        
    }
}
