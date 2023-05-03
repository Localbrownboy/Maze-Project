package com.group23.app.entity;

import com.group23.app.level.LevelBuilder;
import com.group23.app.level.MazeLevel;
import com.group23.app.levelbuilders.testLevels.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GolemTest {

    private MazeLevel level;

    @BeforeEach
    void setup(){
        this.level = new TestLevelBuilderGolemPathfinding(){}.loadLevel();
        this.level.testMode = true;
    }

    @Test
    void testCollision(){
        level.spawnEntity(new Player(level){}, 1, 1);
        level.spawnEntity(new Golem(level){}, 1, 1);
        level.updateTick();
        assert(level.winState == -1);
    }


    @Test
    void testPathfinding(){
        int steps = 0;
        while(level.winState==0){
            level.updateTick();
            steps++;
        }
        System.out.println("Steps: " + steps);
        assert(level.winState == -1);
        assert(steps == 19);
    }
}
