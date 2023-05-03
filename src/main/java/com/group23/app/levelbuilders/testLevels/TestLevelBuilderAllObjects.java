package com.group23.app.levelbuilders.testLevels;

import com.group23.app.entity.*;
import com.group23.app.level.*;
//import com.group23.app.level.MazeLevel;


public class TestLevelBuilderAllObjects extends LevelBuilder {
    
    //a builder class that creates a test level featuring all types of available objects

    @Override
    public MazeLevel loadLevel(){
        MazeLevel lv = new MazeLevel(20, 20, 30);
        lv.totalKeys = 3;
        
        for(int i = 0; i< lv.width; ++i){
            for(int j = 0; j < lv.height; ++j){
                if(i==0||j==0||i==lv.width-1||j==lv.width-1){
                    lv.spawnEntity(new Wall(lv){}, i, j);
                }else{
                    lv.spawnEntity(new Floor(lv){}, i, j);
                }
            }
        }
        
        lv.spawnEntity(new Wall(lv){}, 12, 12);
        lv.spawnEntity(new Wall(lv){},12, 13);
        lv.spawnEntity(new Wall(lv){}, 12, 14);
        lv.spawnEntity(new Wall(lv){}, 12, 15);
        lv.spawnEntity(new Wall(lv){}, 12, 16);
        lv.spawnEntity(new Wall(lv){}, 11, 16);
        lv.spawnEntity(new Wall(lv){}, 10, 16);
        lv.spawnEntity(new Wall(lv){}, 9, 16);
        lv.spawnEntity(new Wall(lv){}, 8, 16);
        lv.spawnEntity(new Wall(lv){}, 7, 16);
        //lv.spawnEntity(new Wall(lv){}, 1, 1);
        lv.spawnEntity(new Wall(lv){}, 2, 3);
        lv.spawnEntity(new Wall(lv){}, 4, 5);
        Player player = new Player(lv);
        lv.spawnEntity(player, 2, 6);
        // lv.spawnEntity( new Golem(lv ,  player ))
        lv.spawnEntity(new SpikeHazard(lv){} , 7,10);
        lv.spawnEntity(new KeyObjective(lv){} , 1,2 );
        lv.spawnEntity(new KeyObjective(lv){} , 4,8 );
        lv.spawnEntity(new KeyObjective(lv){} , 7,8);
        lv.spawnEntity(new Door(lv){}, 8 ,9);
        lv.spawnEntity(new Lock(lv){} , 8 , 9);

        lv.spawnEntity(new BreakableWall(lv){} , 2 ,4);

        lv.spawnEntity(new IceWall(lv){} , 9 ,9);

        lv.spawnEntity(new Golem(lv){} , 18 ,18);

        lv.sortEntities();
        return lv;
    }
}
