package com.group23.app.levelbuilders.testLevels;

import com.group23.app.level.*;
import com.group23.app.entity.*;
import com.group23.app.level.*;


public class TestLevelBuilderWallMovement extends LevelBuilder {
    
    @Override
    public int[][] getGridArray(){
        int[][] a = {
            { 1,1,1,1,1,1,1,1,1,1 }, 
            { 1,0,0,0,0,0,0,0,0,1 }, 
            { 1,0,0,0,0,0,0,0,0,1 }, 
            { 1,0,5,0,1,0,0,0,4 }, 
            { 1,0,0,0,1,0,0,0,0,1 }, 
            { 1,0,0,0,0,0,0,0,0,1 }, 
            { 1,1,1,1,1,1,1,1,1,1 }, 
           
       };
       return a;
    }


    
    @Override
    public MazeLevel loadLevel(){
        MazeLevel lv = buildLevelFromGridArray(3);
        return lv;
    }
}
