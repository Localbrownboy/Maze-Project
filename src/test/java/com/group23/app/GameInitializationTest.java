package com.group23.app;

//import static org.junit.jupiter.api;


import org.junit.jupiter.api.*;//BeforeEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Structural test to verify that the game has correctly initialized.
 */
public class GameInitializationTest
{
    /**
     * Initializes the game
     */
    @BeforeEach
    public void init(){
        Game.initialize();
    }


    /**
     * Tests to confirm level loading
     */
    @Test
    public void levelsExist()
    {
        boolean a =  Game.AllLevels!=null&&Game.AllLevels.size()>0;
        
        assertTrue( a );
    }

    @Test
    public void windowExists()
    {
        boolean a = Game.Window !=null;
        
        assertTrue( a );
    }

    @Test
    public void environmentsExist()
    {
        boolean a = Game.GameEnv !=null && Game.TitleEnv !=null && Game.PauseEnv!=null && Game.WinEnv!=null && Game.LoseEnv!=null;
        
        assertTrue( a );
    }

    @Test
    public void inputHandlerExists()
    {
        boolean a = Game.Input !=null;
        
        assertTrue( a );
    }

    
}
