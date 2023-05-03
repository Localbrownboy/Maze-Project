package com.group23.app.action;

import com.group23.app.*;
import com.group23.app.levelbuilders.testLevels.TestLevelBuilderAllObjects;

import javax.swing.*;
import java.awt.event.*;

/**
 * action that is allows user to start playing game
 * extends {@link AbstractAction}
 */
public class ExitGameAction extends AbstractAction {
    
    public ExitGameAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
        super(text, icon);
        putValue(SHORT_DESCRIPTION, desc);
        putValue(MNEMONIC_KEY, mnemonic);
    }

    /**
     * calls level constructor and begins the game
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e) {
        Game.exit();
    }
}
    

