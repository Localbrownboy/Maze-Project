package com.group23.app.env;
import com.group23.app.Game;
import com.group23.app.action.*;

import javax.swing.*;

import java.awt.*;

/**
 * Class for the PauseEnvironment. Extends {@link TwoButtonEnvironment}. Used for displaying the pause screen.
 */
public class PauseEnvironment extends TwoButtonEnvironment {
    
    public JButton returnButton;
    public JButton exitButton;

    public PauseEnvironment(){
        super(new ReturnToGameAction("Return to game ", null, TOOL_TIP_TEXT_KEY, null), new QuitToTitleAction("Quit to title", null, TOOL_TIP_TEXT_KEY, null), "", "/sprites/menus/IngameArt.png");
        
    }

}
