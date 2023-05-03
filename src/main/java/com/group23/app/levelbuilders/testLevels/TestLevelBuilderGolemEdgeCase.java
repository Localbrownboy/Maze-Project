package com.group23.app.levelbuilders.testLevels;

import com.group23.app.level.*;

public class TestLevelBuilderGolemEdgeCase extends LevelBuilder {
    
    @Override
    public int[][] getGridArray(){
        int[][] a = {
            { 1,1,1,1,1,1,1,1 },     
            { 1,5,0,0,0,0,7,1 }, 
            { 1,1,1,1,1,1,1,1 }, 
           
       };
       return a;
    }


    @Override
    public MazeLevel loadLevel(){
        MazeLevel lv = buildLevelFromGridArray(3);
        return lv;
    }
}
