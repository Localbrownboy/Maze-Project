package com.group23.app.entity;

import com.group23.app.level.MazeLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

public class FloorTest {
    private MazeLevel level;
    @BeforeEach
    void setup(){
        this.level =new MazeLevel(100 , 100 , 10);
        this.level.testMode = true;
    }

    //this test covers all spawns
    @Test
    void SpawnTest(){
        Door door = new Door(level);
        level.spawnEntity(door, 10 ,10);
        assert (level.tileAt(10,10).entities.contains(door));
    }
}
