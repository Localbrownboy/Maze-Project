package com.group23.app.entity;

import com.group23.app.level.MazeLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

public class CrystalTest {
    private MazeLevel level;

    @BeforeEach
    void setup(){
        this.level =new MazeLevel(100 , 100 , 10);
        this.level.testMode = true;
    }
    @Test
    void TestPlayerCollide(){
        Crystal crystal = new Crystal(level);
        level.spawnEntity(crystal , 10,10);
        crystal.onPickUp(new Player(level));
        level.updateTick();
        assert(!level.tileAt(10,10).entities.contains(crystal));
        int score = 20;
        assert(level.getScore() == score);
    }

    //This test covers all despawns because runs on same logic
    @Test
    void testCrystalTimeOut(){
        Crystal crystal = new Crystal(level);
        crystal.durationRemaining =1;
        level.spawnEntity(crystal , 10,10);
        level.updateTick();
        assert(!level.tileAt(10,10).entities.contains(crystal));

    }

}
