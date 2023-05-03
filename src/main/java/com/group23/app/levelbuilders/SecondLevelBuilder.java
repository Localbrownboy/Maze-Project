package com.group23.app.levelbuilders;

import com.group23.app.entity.*;
import com.group23.app.level.*;
//import com.group23.app.level.MazeLevel;


public class SecondLevelBuilder extends LevelBuilder {
    
    @Override
    public int[][] getGridArray(){
        int[][] walls = 
        {
         { 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1 }, 
         { 1,2,0,0,0,0,1,1,1,0,0,0,6,1,0,2,3,1 },
         { 1,0,0,1,1,0,0,0,1,0,1,1,3,1,0,1,6,1 }, 
         { 1,0,1,1,1,1,2,0,1,0,0,6,6,1,0,1,6,1 }, 
         { 1,6,6,1,0,1,0,0,1,0,1,1,1,1,0,1,0,1 }, 
         { 1,5,6,1,1,1,0,0,0,0,2,0,0,0,0,0,0,1 }, 
         { 1,6,6,1,3,6,0,1,0,0,0,6,2,0,1,1,0,1 }, 
         { 1,0,1,1,1,1,0,1,0,0,1,1,1,0,0,1,0,1 }, 
         { 1,0,0,0,0,0,0,1,0,0,1,0,1,0,0,1,0,1 }, 
         { 1,0,1,1,1,1,1,1,2,0,1,1,1,0,0,1,0,4 }, 
         { 1,0,0,0,0,0,0,7,0,0,6,3,6,0,0,0,0,1 }, 
         { 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1 }, 

         
        
        };
        return walls;
    }

    @Override
    public MazeLevel loadLevel(){
       return buildLevelFromGridArray();
    }
}
