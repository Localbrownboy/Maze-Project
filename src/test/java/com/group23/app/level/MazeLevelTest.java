package com.group23.app.level;

import com.group23.app.entity.Floor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MazeLevelTest {

    private MazeLevel level;

    @BeforeEach
    void setup(){
        this.level =new MazeLevel(100 , 100 , 10);
        this.level.testMode = true;
    }
    @Test
    void testSpawn(){
        Floor floor = new Floor(level);
        level.spawnEntity(floor, 10 , 10);
        assert (level.tileAt(10,10).entities.contains(floor));
    }

    @Test
    void testScore(){
        level.collectedCrystals = 3;
        level.collectedKeys = 2;
        int score = 3*20 + 2*30;
        assert (level.getScore()== score);
    }

}
