package com.group23.app.action;

import com.group23.app.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * action that is allows user to resume level
 * extends {@link AbstractAction}
 */
public class ReturnToGameAction extends AbstractAction {
    
    public ReturnToGameAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
        super(text, icon);
        putValue(SHORT_DESCRIPTION, desc);
        putValue(MNEMONIC_KEY, mnemonic);
    }

    /**
     * resumes game
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e) {
        //displayResult("Action for first button/menu item", e);
        //System.out.println("Thing Performed");
        //Game.enterLevel(new TestLevelBuilder());
        Game.setGameState(GameState.GameScreen);

    }
}
