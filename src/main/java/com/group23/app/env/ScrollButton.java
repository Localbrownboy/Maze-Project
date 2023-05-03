package com.group23.app.env;

import com.group23.app.*;
import com.group23.app.action.*;

import javax.swing.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import java.io.IOException;


public class ScrollButton extends JButton {
    
    private BufferedImage scrollImage;
    
    public ScrollButton(Action a){
        super(a);

        try{
            scrollImage = ImageIO.read(getClass().getResourceAsStream("/sprites/menus/ScrollButton.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
    
        //this.setIcon(new ImageIcon(scrollImage));
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
       
        //this.set
        //this.seticon()
        
        
    }

    
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        
        if(scrollImage!=null){
            g2.drawImage(scrollImage, 0, 0, getSize().width, getSize().height, null);
        }else{
            System.out.println("image null");
        }

        super.paintComponent(g2);


    }




}
