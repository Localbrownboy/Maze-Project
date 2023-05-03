package com.group23.app.action;

import com.group23.app.*;


import javax.swing.*;
import java.awt.event.*;

/**
 * action advances the user to the next level in the list
 * extends {@link AbstractAction}
 */
public class NextLevelAction extends AbstractAction {
    
    public NextLevelAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
        super(text, icon);
        putValue(SHORT_DESCRIPTION, desc);
        putValue(MNEMONIC_KEY, mnemonic);
    }

    /**
     * calls level constructor and begins the game
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e) {
        //displayResult("Action for first button/menu item", e);
        //System.out.println("Thing Performed");
        Game.findNextLevel();
        Game.enterLevel(Game.AllLevels.get(Game.currentLevel));
    }
}
    

