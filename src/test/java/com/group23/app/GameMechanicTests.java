package com.group23.app;

import org.junit.jupiter.api.*;//BeforeEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.group23.app.entity.Entity;
import com.group23.app.entity.*;
import com.group23.app.entity.EntityProperty;
import com.group23.app.levelbuilders.IntroLevelBuilder;
import com.group23.app.levelbuilders.testLevels.*;
import com.group23.app.utils.*;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


/**
 * Larg scale system/integration tests which test how everything is put together ingame
 */
public class GameMechanicTests {
    

  ArrayList<AutomaticPlayerAction> moves;
    /**
     * Initializes the game
     */
    @BeforeEach
    public void init(){
        Game.initialize(true);
        moves = new ArrayList<AutomaticPlayerAction> ();
        
    }

    @AfterEach
    public void reset(){
        //Game.exit();
        
        Game.setGameState(GameState.TitleScreen);
        Game.GameEnv.level = null;
    }

    /**
     * Tests a pattern of player movement to verify collision
     */
    @Test
    public void playerMovementCollisionTest()
    {
       
          moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Right, AutomaticPlayerSpell.None));
          moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Right, AutomaticPlayerSpell.None));
          //moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Right, AutomaticPlayerSpell.None));
          moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Down, AutomaticPlayerSpell.None));
          //moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Down, AutomaticPlayerSpell.None));
          moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Right, AutomaticPlayerSpell.None));
          //moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Up, AutomaticPlayerSpell.None));
          moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Up, AutomaticPlayerSpell.None));
          moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Up, AutomaticPlayerSpell.None));
          moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Right, AutomaticPlayerSpell.None));
          moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Up, AutomaticPlayerSpell.None));
          moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Up, AutomaticPlayerSpell.None));
          moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Down, AutomaticPlayerSpell.None));
          moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Down, AutomaticPlayerSpell.None));
          moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Down, AutomaticPlayerSpell.None));
          moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Right, AutomaticPlayerSpell.None));
          moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Right, AutomaticPlayerSpell.None));
          moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Down, AutomaticPlayerSpell.None));
          moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Left, AutomaticPlayerSpell.None));
          moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Left, AutomaticPlayerSpell.None));
          moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Down, AutomaticPlayerSpell.None));
          moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Down, AutomaticPlayerSpell.None));
          moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Left, AutomaticPlayerSpell.None));
          //moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.None, AutomaticPlayerSpell.Fire));
          //moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Left, AutomaticPlayerSpell.None));
          moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Left, AutomaticPlayerSpell.None));
          moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Left, AutomaticPlayerSpell.None));
          moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Up, AutomaticPlayerSpell.None));
          moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Up, AutomaticPlayerSpell.None));

          Game.ActiveQueue = new AutomaticPlayerActionQueue(moves);

          Game.enterLevel(new TestLevelBuilderWallMovement());
            
          boolean b = true;
          while(true){
            
            System.out.print("");//fsr the test doesn't terminate without this, requires more meta-testing


            //Game.ActiveQueue.canAdvance();
             b = Game.ActiveQueue.canAdvance();

            if(!b){
                System.out.println("Test pattern complete");
                Point p = Game.GameEnv.level.findPlayerPosition();
                boolean a = p.x == 2 && p.y == 3;
                assertTrue( a );
                break;
            }

          }

          ///System.out.println("Test pattern complete");

        
    }

    /**
     * Tests that players can win the game by reaching a door without a lock
     */
    @Test
    public void playerDoorLevelCompletionTest(){
        
        moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Right, AutomaticPlayerSpell.None));
        moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Right, AutomaticPlayerSpell.None));
        moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Right, AutomaticPlayerSpell.None));
        moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Right, AutomaticPlayerSpell.None));
        moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.None, AutomaticPlayerSpell.None));
        
        Game.ActiveQueue = new AutomaticPlayerActionQueue(moves);

        Game.enterLevel(new TestLevelBuilderLevelEnd());

        boolean b = true;
          while(true){
            System.out.print("");//fsr the test doesn't terminate without this, requires more meta-testing
             b = Game.ActiveQueue.canAdvance()&&Game.CurrentState==GameState.GameScreen;
            if(!b){
                System.out.println("Test  complete");
                
                assertEquals(Game.CurrentState, GameState.GameWinScreen);
                break;
            }

          }

    }

     /**
     * Tests that players can win the game by reaching a door after collecting all keys
     */
    @Test
    public void playerKeyLevelCompletionTest(){
        
        moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Right, AutomaticPlayerSpell.None));
        moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Left, AutomaticPlayerSpell.None));
        moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Left, AutomaticPlayerSpell.None));
        moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Right, AutomaticPlayerSpell.None));
        moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Right, AutomaticPlayerSpell.None));
        moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Right, AutomaticPlayerSpell.None));
        moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Left, AutomaticPlayerSpell.None));
        moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Left, AutomaticPlayerSpell.None));
        moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Left, AutomaticPlayerSpell.None));
        moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Right, AutomaticPlayerSpell.None));
        moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Right, AutomaticPlayerSpell.None));
        moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Right, AutomaticPlayerSpell.None));
        moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Right, AutomaticPlayerSpell.None));
        
        Game.ActiveQueue = new AutomaticPlayerActionQueue(moves);

        Game.enterLevel(new TestLevelBuilderKeys());

        boolean b = true;
          while(true){
            System.out.print("");//fsr the test doesn't terminate without this, requires more meta-testing
             b = Game.ActiveQueue.canAdvance()||Game.CurrentState==GameState.GameScreen;
            if(!b){
                System.out.println("Test  complete, queue position " + Game.ActiveQueue.queuePosition);
                //oolean testSuccessful = Game.CurrentState==GameState.GameWinScreen&&Game.ActiveQueue.queuePosition>=13;
                assertEquals(Game.CurrentState, GameState.GameWinScreen);
                assertEquals(Game.ActiveQueue.queuePosition, moves.size());
                break;
            }

          }

    }


    /**
     * Tests the player fire spell functionality
     */
    @Test
    public void playerSpellsTest(){

      moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Right, AutomaticPlayerSpell.Ice));
      moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.None, AutomaticPlayerSpell.Fire));
      moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.None, AutomaticPlayerSpell.None));
      moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Right, AutomaticPlayerSpell.Ice));
      moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.None, AutomaticPlayerSpell.Fire));
      moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.None, AutomaticPlayerSpell.None));
      Game.ActiveQueue = new AutomaticPlayerActionQueue(moves);

      Game.enterLevel(new TestLevelBuilderFireSpell());

      boolean b = true;
          while(true){
            System.out.print("");//fsr the test doesn't terminate without this, requires more meta-testing
             b = Game.ActiveQueue.canAdvance();
            if(!b){
                
                
              boolean a = true;
              boolean c = false;
              Entity p = null;
              for(int i = 0; i < Game.GameEnv.level.entities.size(); ++i){
                Entity e = Game.GameEnv.level.entities.get(i);
                if(e.getProperties().contains(EntityProperty.Player)){
                  p = e;
                }
                if(e.getProperties().contains(EntityProperty.Breakable)&&!e.getProperties().contains(EntityProperty.Temporary)){
                  a = false;
                }

                if(e.getProperties().contains(EntityProperty.Damaging)){
                  c = true;
                }
              }



              assertTrue(a);
              assertTrue(c);
              assert(p!=null);
              assert(p instanceof Player);
              System.out.println(((Player)p).fireCooldown);
              System.out.println(((Player)p).iceCooldown);
              boolean d = ((Player)p).fireCooldown == 2;
              boolean f = ((Player)p).iceCooldown == 1;
              assert(d);
              assert(f);
                //assertTrue(Game.GameEnv.level.entities.contains((x)=>x.properties.contains))
                //assertEquals(Game.ActiveQueue.queuePosition, moves.size());
                break;
            }

          }
    }

    /**
     * Tests that players will lose the game when their health reaches zer0, and that it brings the player to the right screen
     */
    @Test
    public void playerDeathTest(){
        
        moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Right, AutomaticPlayerSpell.None));
        moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Right, AutomaticPlayerSpell.None));
        moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.None, AutomaticPlayerSpell.None));
        moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.None, AutomaticPlayerSpell.None));
        moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.None, AutomaticPlayerSpell.None));
        moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.None, AutomaticPlayerSpell.None));
        
        Game.ActiveQueue = new AutomaticPlayerActionQueue(moves);

        Game.enterLevel(new TestLevelBuilderDeath());

        boolean b = true;
          while(true){
            System.out.print("");//fsr the test doesn't terminate without this, requires more meta-testing
             b = Game.ActiveQueue.canAdvance()&&Game.CurrentState==GameState.GameScreen;
            if(!b){
                System.out.println("Test  complete");
                
                assertEquals(Game.CurrentState, GameState.GameOverScreen);
                break;
            }

          }
    }

    /**
     * Tests that after the game is won, that title quitting works as intended
     */
    @Test
    public void playerWinQuitTest(){
      playerDoorLevelCompletionTest();

      Game.WinEnv.button2.doClick();
      assert(Game.CurrentState==GameState.TitleScreen);
    }

    /**
     * Tests that after the game is won, that title quitting works as intended
     */
    @Test
    public void playerWinLoadNextLevelTest(){
      int a = Game.currentLevel;
      assert(a==0);
      playerDoorLevelCompletionTest();

      Game.WinEnv.button1.doClick();
      assert(Game.currentLevel==a+1);
      assert(Game.CurrentState==GameState.GameScreen);
    }

    /**
     * Tests that after the game is lost, that title quitting works as intended
     */
    @Test
    public void playerLoseQuitTest(){
      playerDeathTest();

      Game.WinEnv.button2.doClick();
      assert(Game.CurrentState==GameState.TitleScreen);
    }


    /**
     * Tests that after the game is lost, that title quitting works as intended
     */
    @Test
    public void gamePauseTest(){
      Game.enterLevel(new IntroLevelBuilder());

      Game.Input.keyPressBehavior(KeyEvent.VK_ESCAPE);

      assert(Game.CurrentState==GameState.PauseScreen);

      
      
    }

    /**
     * Tests that after the game is lost, that title quitting works as intended
     */
    @Test
    public void gamePauseResumeTest(){
      
      gamePauseTest();
      Game.PauseEnv.button1.doClick();

      assert(Game.CurrentState==GameState.GameScreen);
      
    }

    /**
     * Tests that after the game is lost, that title quitting works as intended
     */
    @Test
    public void gamePauseQuitTest(){
      gamePauseTest();

      Game.PauseEnv.button2.doClick();

      assert(Game.CurrentState==GameState.TitleScreen);
      
    }

    /**
     * Tests a mechanical edge case about the player
     */
    @Test
    public void playerGolemSwapCollisionTest(){
      moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.None, AutomaticPlayerSpell.None));
      moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Right, AutomaticPlayerSpell.None));
      moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Right, AutomaticPlayerSpell.None));
      moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Right, AutomaticPlayerSpell.None));
      moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.Right, AutomaticPlayerSpell.None));
      moves.add(  new AutomaticPlayerAction(AutomaticPlayerMovement.None, AutomaticPlayerSpell.None));
      Game.ActiveQueue = new AutomaticPlayerActionQueue(moves);

      Game.enterLevel(new TestLevelBuilderGolemEdgeCase());

      boolean b = true;
          while(true){
            System.out.print("");// the test doesn't terminate without something to keep the thread going, requires more meta-testing
            b = Game.ActiveQueue.canAdvance()&&Game.CurrentState == GameState.GameScreen;
            if(!b){
                
                
              



              assert(Game.CurrentState == GameState.GameOverScreen);
              //assertTrue(c);
                //assertTrue(Game.GameEnv.level.entities.contains((x)=>x.properties.contains))
                //assertEquals(Game.ActiveQueue.queuePosition, moves.size());
                break;
            }

          }
    }
}
