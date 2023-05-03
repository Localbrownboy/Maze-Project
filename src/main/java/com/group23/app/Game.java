package com.group23.app;


import javax.swing.*;

import java.lang.*;
import java.util.ArrayList;

import com.group23.app.env.*;

import com.group23.app.level.*;
import com.group23.app.levelbuilders.*;
import com.group23.app.utils.*;

import java.awt.*;
import java.awt.event.WindowEvent;


/**
 * Main class
 *
 */
public class Game 
{
    public static JFrame Window;

    public static ArrayList<LevelBuilder> AllLevels;

    public static int currentLevel;
    

    public static CardLayout Layout;

    public static JPanel LayeringPanel;

    public static int accumulatingScore = 0;

    public static GameEnvironment GameEnv;
    public static TitleEnvironment TitleEnv;
    public static PauseEnvironment PauseEnv;
    public static LoseEnvironment LoseEnv;
    public static WinEnvironment WinEnv;


    public static GameState CurrentState;
    public static InputHandler Input;
    public static MazeLevel LoadedLevel;

    public static AutomaticPlayerActionQueue ActiveQueue = null;

    public static boolean Paused = false;
    
    public static final int screenWidth = 1600;
    public static final int screenHeight = 900;

    private static Sound sound = new Sound();

/**
 * The root of the project. This is the main function where it all begins.
 */
    public static void main( String[] args )
    {
        initialize();
        
    }

/**
 * Function to initialize the gamescreen and InputHandler.
 */
    public static void initialize(boolean testMode){
        //CurrentState = GameState.TitleScreen;
        CurrentState = GameState.GameScreen;
        Input = new InputHandler();
        Paused = false;
        AllLevels = new ArrayList<LevelBuilder>();
        AllLevels.add(new IntroLevelBuilder());
        AllLevels.add(new FirstLevelBuilder());
        AllLevels.add(new SecondLevelBuilder());

        Input = new InputHandler();
        sound = new Sound();
        //creates the window for testing purposes
        Window = new JFrame("Arcanist");
        Window.setSize(screenWidth, screenHeight);
        Window.setResizable(false);
        Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Layout = new CardLayout();
        LayeringPanel = new JPanel(Layout);
        LayeringPanel.addKeyListener(Game.Input);
        LayeringPanel.setFocusable(true);;
        
        GameEnv = new GameEnvironment();
        TitleEnv = new TitleEnvironment();
        PauseEnv = new PauseEnvironment();
        LoseEnv = new LoseEnvironment();
        WinEnv = new WinEnvironment();

        LayeringPanel.add(GameEnv, "GameScreen");
        LayeringPanel.add(TitleEnv, "MenuScreen");
        LayeringPanel.add(PauseEnv, "PauseScreen");
        LayeringPanel.add(LoseEnv, "GameOverScreen");
        LayeringPanel.add(WinEnv, "WinScreen");

        GameEnv.startThread();

        if(!testMode){
        Window.add(LayeringPanel);
        
        Window.setVisible(true);
        }
        
        setGameState(GameState.TitleScreen);
    }

/**
 * Function to initialize the gamescreen and InputHandler.
 */
public static void initialize(){
    initialize(false);

}


/**
 * Function for routing the game to different states.
 */
    public static void setGameState(GameState state){
        switch (state){
            case TitleScreen:
                CurrentState = GameState.TitleScreen;
                Layout.show(LayeringPanel, "MenuScreen");
                Paused = false;
                
                Window.pack();
                
            break;
            case GameScreen:
                CurrentState = GameState.GameScreen;
                Layout.show(LayeringPanel, "GameScreen");
                Paused = false;
                
                Window.pack();

            break;
            case PauseScreen:
                CurrentState = GameState.PauseScreen;
                
                Layout.show(LayeringPanel, "PauseScreen");
                
                
                Paused = true;
               
                Window.pack();
            break;
            case GameOverScreen:
                CurrentState = GameState.GameOverScreen;
                Layout.show(LayeringPanel, "GameOverScreen");
                Paused = false;
                
                Window.pack();
                
            break;
            case GameWinScreen:
                CurrentState = GameState.GameWinScreen;
                Layout.show(LayeringPanel, "WinScreen");
                Paused = false;
                
                Window.pack();
            break;
            

        }
    }



/**
 * Function to enter level.
 */
    public static void enterLevel(LevelBuilder lv){
        GameEnv.level = null;
        GameEnv.processThread = null;
        GameEnv.level = lv.loadLevel();
        GameEnv.startThread();
        setGameState(GameState.GameScreen);

        // Plays the background song here starting from 10645000 microseconds.
        playMusic(0, 10645000);
    }

/**
 * Function to advance the index.
 */
public static void findNextLevel(){
    currentLevel++;
    currentLevel = currentLevel % AllLevels.size();
}
    
/**
 * Function to leave level and return to title screen.
 */
    public static void leaveLevel(){
        setGameState(GameState.TitleScreen);
        GameEnv.level = null;
        accumulatingScore = 0;
        currentLevel = 0;
        // stopMusic();
    }

    

    /**
     * Function for the playing the background sound in the game.
     * @param i the index of the file in the directory
     * @param time the sound to start the file from
     */
    public static void playMusic(int i, long time){

            //The background music is started from the point provided in the parameters and loops continously.
            sound.setBgmFile(i);
            sound.playBgm();
            sound.clip_bgm.setMicrosecondPosition(time);
            sound.loopBgm();
    }

    
    /**
     * Stops the sounds in the game, for both the background music as well as any sound efects.
     */
    public static void stopMusic(boolean bgm) {

        // This is for stopping the background music.
        if(bgm){
            sound.stopBgm();
        }

        // This is for stopping sound effects.
        else{
            sound.stop();
        }
    
    }
    /**
     * Plays a non-looping sound effect.
     */
    public static void playSE(int i){
        sound.setFile(i);
        sound.play();

    }

    public static void exit(){
        Window.dispatchEvent(new WindowEvent(Window,WindowEvent.WINDOW_CLOSING));
        //System.exit(0);
    }

    

}
