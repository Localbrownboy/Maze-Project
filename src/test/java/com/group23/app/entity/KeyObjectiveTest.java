package com.group23.app.entity;

import com.group23.app.level.MazeLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class KeyObjectiveTest {
    private MazeLevel level;

    @BeforeEach
    void setup(){
        this.level =new MazeLevel(100 , 100 , 10);
        this.level.testMode = true;
    }
    @Test
    void TestPlayerCollide(){
        KeyObjective key = new KeyObjective(level);
        level.spawnEntity(key , 10,10);
        key.onPickUp(new Player(level));
        level.updateTick();
        assert(!level.tileAt(10,10).entities.contains(key));
        int score = 30;
        assert(level.getScore() == score);
        assert (level.collectedKeys == 1);
    }
}
