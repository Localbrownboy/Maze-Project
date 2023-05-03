package com.group23.app.env;

import javax.swing.*;

import com.group23.app.Game;
import com.group23.app.GameState;
import com.group23.app.level.MazeLevel;
import com.group23.app.level.TileManager;

import java.awt.*;


/**
* Class for GameEnvironment. Extends {@link JPanel} and implements the {@link Runnable} thread.
 * This is the screen for the main game.
 */
public class GameEnvironment extends JPanel implements Runnable {

    public final static int levelRenderSquareWidth = 760;
    public final static Point levelRenderSquareCentre = new Point(800, 400);
    
    public static final int FPSCap = 60;

    public MazeLevel level;

    public long songPosition;

    TileManager tileM = new TileManager(this);
    public Thread processThread;// = null;
    public UI ui = new UI(this);

    public GameEnvironment(){
        this.setPreferredSize(new Dimension(Game.screenWidth, Game.screenHeight));
        this.setBackground(Color.DARK_GRAY);
        //this.setDoubleBuffered(true);
        this.addKeyListener(Game.Input);
        this.setFocusable(true);
        
    }

    /**
     * Function to start the game thread.
     */
    public void startThread(){

            processThread = new Thread(this);
            processThread.start();
        
        
    }

    /*public void stopThread(){
        if(processThread==null){
            return;
        }
       
        try {
            processThread.wait();
        } catch (InterruptedException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }*/

    
    /**
     * Function to begin running the game.
     */
    @Override
    public void run() {
        

        double interval = 1000000000/FPSCap;
        double nextDraw = System.nanoTime() + interval;

        System.out.print(".");
        while(processThread!=null){
            
            if(level!=null){
              
            if(Game.CurrentState==GameState.GameScreen&&!Game.Paused){
                
                updateLevel();
            
                repaint();
            }

            try {
                
                double remaining = nextDraw - System.nanoTime();
                remaining = remaining/1000000;

                if(remaining<0){
                    remaining = 0;

                }

                Thread.sleep((long)remaining);

                nextDraw+= interval;

            } catch (InterruptedException e) {
                e.printStackTrace();
               
            }
        }
        //System.out.println("Thread Running with game state: " + Game.CurrentState);

        }
    }


    /**
     * Function to update the level.
     */
    public void updateLevel(){

        //player.update();


        --level.ticksBeforeUpdate;


        if(level.ticksBeforeUpdate<=0){
            if(Game.ActiveQueue!=null){
                System.out.println("Attempting Automatic Action");
                Game.ActiveQueue.advanceQueue();
            }
            
            System.out.println("Level Tick");
            level.updateTick();
            level.ticksBeforeUpdate = level.tickspeed;
            Game.Input.setKeysFalse();
        }


    }

    /**
     * Function to draw the components needed on the screen.
     * @param g is the component we want to draw.
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        if(level!=null){
            
            //g2.setColor(Color.RED);
            //check bounding box
            //g2.fillRect(level.getRenderBox().x, level.getRenderBox().y, level.getRenderBox().width, level.getRenderBox().height);

            //draw all entities in level (now handled by the TileManager)
            tileM.draw(g2);

            // UI
            ui.drawGraphics(g2);


            
            /*
            for(int i = 0; i < level.entities.size(); i++){
               level.entities.get(i).draw(g2);
            }*/
        }
        g2.dispose();
    }


}
