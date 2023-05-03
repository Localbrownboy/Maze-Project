package com.group23.app.utils;

import java.util.ArrayList;

import com.group23.app.*;
import com.group23.app.utils.*;
import java.awt.event.*;

/**
 * Class used in testing to allow for automatically playing back certain moves
 */
public class AutomaticPlayerActionQueue {
    public ArrayList<AutomaticPlayerAction> keyQueue = new ArrayList<AutomaticPlayerAction>();
    public int queuePosition = 0;
    

    public AutomaticPlayerActionQueue(){
        keyQueue = new ArrayList<AutomaticPlayerAction>();
        queuePosition = 0;
    }

    public AutomaticPlayerActionQueue(ArrayList<AutomaticPlayerAction> keys){
        keyQueue = keys;
        queuePosition = 0;
    }

    public boolean canAdvance(){
        return queuePosition<keyQueue.size()&&keyQueue!=null;
    }


    public boolean advanceQueue(){
        if(!canAdvance()){
            System.out.println("Queue advance failed, queue is null or you have reached the end");
            return false;
        }else if(Game.GameEnv.level==null){
            System.out.println("Queue advance failed, game does not have valid level");
            return false;
        }else if(Game.CurrentState!=GameState.GameScreen){
            System.out.println("Queue advance failed, game is in wrong screen state");
            return false;
        }else if(Game.Input==null){
            System.out.println("Queue advance failed, game input handler does not exist");
            return false;
        }
        
        if(queuePosition<0)
            queuePosition = 0;

        AutomaticPlayerAction a = keyQueue.get(queuePosition);

        switch(a.movement){
            case Up:
                Game.Input.keyPressBehavior(KeyEvent.VK_UP);
                break;
            case Down:
                Game.Input.keyPressBehavior(KeyEvent.VK_DOWN);
                break;
            case Left:
                Game.Input.keyPressBehavior(KeyEvent.VK_LEFT);
                break;
            case Right:
                Game.Input.keyPressBehavior(KeyEvent.VK_RIGHT);
                break;
            case None:
                break; 

        }

        switch(a.spellcast){
            case None:
            break;
            case Fire:
                Game.Input.keyPressBehavior(KeyEvent.VK_Z);
                break;
            case Ice:
                Game.Input.keyPressBehavior(KeyEvent.VK_X);
                break;

        }

        queuePosition++;

        return true;
    }

}
