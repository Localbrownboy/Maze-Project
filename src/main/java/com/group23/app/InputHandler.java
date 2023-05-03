package com.group23.app;

import java.awt.event.*;

/**
 * InputHandler class for listening to keyboard inputs relevant to our game.
 * implements {@link KeyListener}
 */
public class InputHandler implements KeyListener {
    

    public InputHandler(){
        QueuedMove = MovementDirection.None;
        ZQueued = false;
        XQueued = false;
    }

    
    public boolean ZQueued;
    public boolean XQueued;

    public MovementDirection QueuedMove;
    //public boolean EscQueued;


    @Override
    public void keyTyped(KeyEvent e){
        
    }

    /**
     * Keeps track of which game keys have been pressed
     * @param e the event to be processed, is of type {@link KeyEvent}
     */
    @Override
    public void keyPressed(KeyEvent e){
        
        int code = e.getKeyCode();
        keyPressBehavior(code);
        
    }

    public void keyPressBehavior(int code){
        if(Game.CurrentState != GameState.GameScreen){
            return;
        }

        switch (code){
            case KeyEvent.VK_LEFT:
                
                QueuedMove = MovementDirection.Left;
                System.out.println("Queued left movement");
            break;
            case KeyEvent.VK_RIGHT:
                
                QueuedMove = MovementDirection.Right;
                System.out.println("Queued right movement");
            break;
            case KeyEvent.VK_UP:
                
                QueuedMove = MovementDirection.Up;
                System.out.println("Queued up movement");
            break;
            case KeyEvent.VK_DOWN:
               
                QueuedMove = MovementDirection.Down;
                System.out.println("Queued down movement");
            break;
            case KeyEvent.VK_ESCAPE:
                if(Game.CurrentState==GameState.PauseScreen){
                    System.out.println("attempted unpaused");
                    Game.setGameState(GameState.GameScreen);
                }else if(Game.CurrentState == GameState.GameScreen){
                    System.out.println("attempted paused");
                    Game.setGameState(GameState.PauseScreen);
                }
            break;
            case KeyEvent.VK_Z:
                    ZQueued = true;
            break;
            case KeyEvent.VK_X:
                XQueued = true;

        }
    }

    @Override
    public void keyReleased(KeyEvent e){
    
    }
    

    /**
     * every level tick, all the boolean values must be set back to false
     */
    public void setKeysFalse(){
       
        this.QueuedMove = MovementDirection.None;
         this.ZQueued = false;
         this.XQueued = false;
        // this.CQueued = false;
        // this.EscQueued = false;
    }

}
