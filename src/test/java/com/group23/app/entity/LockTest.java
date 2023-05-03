package com.group23.app.entity;

import com.group23.app.level.MazeLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LockTest {
    private MazeLevel level;

    @BeforeEach
    void setup(){
        this.level =new MazeLevel(100 , 100 , 10);
        this.level.testMode = true;
    }
    @Test
    void TestLockRemoval(){
        Lock lock = new Lock(level);
        level.spawnEntity(lock , 10,10);
        level.totalKeys =0;
        level.updateTick();
        assert(!level.tileAt(10,10).entities.contains(lock));
    }
}
