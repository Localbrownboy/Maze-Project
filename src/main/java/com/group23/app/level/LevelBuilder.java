package com.group23.app.level;

import com.group23.app.entity.*;

public abstract class LevelBuilder {
    public LevelBuilder(){
        //gridArray = new int[][]
    }

    public int[][] getGridArray(){
        int[][] a = {
            { 1,1,1,1,1,1,1,1,1,1 }, 
            { 1,0,0,6,0,0,0,0,0,1 }, 
            { 1,0,0,6,0,0,0,2,0,1 }, 
            { 1,5,0,6,0,3,0,2,0,4 }, 
            { 1,0,0,6,0,0,0,2,0,1 }, 
            { 1,0,0,6,0,0,0,0,0,1 }, 
            { 1,1,1,1,1,1,1,1,1,1 }, 
           
       };
       return a;
    }

    public MazeLevel buildLevelFromGridArray(){
        return buildLevelFromGridArray(30);
    }

    public MazeLevel buildLevelFromGridArray(int speed){
        int[][]gridArray = getGridArray();
        int height = gridArray.length;
        int width = 0;
        for(int i = 0; i < gridArray.length; ++i){
            if(gridArray[i].length>width){
                width = gridArray[i].length;
            }
        }

        MazeLevel lv = new MazeLevel(width, height, speed);
        int keys = 0;
        
        for(int j = 0; j< lv.height; ++j){
            for(int i = 0; i < lv.width; ++i){
                if(i>=gridArray[j].length){
                    lv.spawnEntity(new Floor(lv){}, i, j);
                    continue;
                }else{
                    switch(gridArray[j][i]){
                        case 0:
                        lv.spawnEntity(new Floor(lv){}, i, j);
                        break;
                        case 1:
                        lv.spawnEntity(new Wall(lv){}, i, j);
                        break;
                        case 2:
                        lv.spawnEntity(new Floor(lv){}, i, j);
                        lv.spawnEntity(new SpikeHazard(lv){}, i, j);
                        break;
                        case 3:
                        lv.spawnEntity(new Floor(lv){}, i, j);
                        lv.spawnEntity(new KeyObjective(lv){}, i, j);
                        keys++;
                        break;
                        case 4:
                        lv.spawnEntity(new Door(lv){}, i, j);
                        lv.spawnEntity(new Lock(lv){}, i, j);
                        break;
                        case 5:
                        lv.spawnEntity(new Floor(lv){}, i, j);
                        Player player = new Player(lv);
                        lv.spawnEntity(player, i, j);
                        //lv.spawnEntity(new Door(lv){}, i, j);
                        //lv.spawnEntity(new Lock(lv){}, i, j);
                        break;
                        case 6:
                        lv.spawnEntity(new Floor(lv){}, i, j);
                        lv.spawnEntity(new BreakableWall(lv){}, i, j);
                        break;
                        case 7:
                        lv.spawnEntity(new Floor(lv){}, i, j);
                        lv.spawnEntity(new Golem(lv){}, i, j);
                        break;
    
                    }
                }


                
            }
        }
        lv.totalKeys = keys;
        
        lv.sortEntities();
        return lv;

        
    }

    public MazeLevel loadLevel(){
        throw new UnsupportedOperationException("Not Implemented");
    }
}
