package com.group23.app.levelbuilders.testLevels;

import com.group23.app.level.*;
import com.group23.app.entity.*;



public class TestLevelBuilderKeys extends LevelBuilder {
    
    @Override
    public int[][] getGridArray(){
        int[][] a = {
            { 1,1,1,1,1,1,1 }, 
            { 1,0,0,0,0,0,1 }, 
            { 1,0,3,3,0,5,4 }, 
            { 1,0,0,0,0,0,1 }, 
            { 1,1,1,1,1,1,1 }, 
           
       };
       return a;
    }


    @Override
    public MazeLevel loadLevel(){
        MazeLevel lv = buildLevelFromGridArray(3);
        return lv;
    }
}
