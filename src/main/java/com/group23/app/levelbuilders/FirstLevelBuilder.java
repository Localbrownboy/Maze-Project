package com.group23.app.levelbuilders;

import com.group23.app.entity.*;
import com.group23.app.level.*;
//import com.group23.app.level.MazeLevel;


public class FirstLevelBuilder extends LevelBuilder {
    
    @Override
    public int[][] getGridArray(){
        int[][] walls = 
        {
         { 1,1,1,1,1,1,1,1,1,1,1 }, 
         { 1,3,0,0,1,0,6,3,6,0,1 }, 
         { 1,6,1,0,0,0,0,1,0,0,4 }, 
         { 1,0,1,1,0,0,0,1,1,0,1 }, 
         { 1,0,1,1,0,1,0,0,1,0,1 }, 
         { 1,3,0,0,0,1,1,0,0,0,1 }, 
         { 1,7,1,1,1,1,1,1,0,0,1 }, 
         { 1,1,1,0,0,3,0,1,2,0,1 }, 
         { 1,5,0,0,0,2,0,6,0,0,1 }, 
         { 1,1,1,1,1,1,1,1,1,1,1 }, 
         

         
        
        };
        return walls;
    }

    @Override
    public MazeLevel loadLevel(){
       return buildLevelFromGridArray();
    }
}
