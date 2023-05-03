package com.group23.app.action;

import com.group23.app.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * action that is allows user to leave level
 * extends {@link AbstractAction}
 */
public class QuitToTitleAction extends AbstractAction {
    
    public QuitToTitleAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
        super(text, icon);
        putValue(SHORT_DESCRIPTION, desc);
        putValue(MNEMONIC_KEY, mnemonic);
    }

    /**
     * leaves the level and takes user back to title screen, also resets the level counter and score
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e) {
        //displayResult("Action for first button/menu item", e);
        //System.out.println("Thing Performed");
        //Game.enterLevel(new TestLevelBuilder());
        Game.leaveLevel();
        Game.stopMusic(true);

    }
}
