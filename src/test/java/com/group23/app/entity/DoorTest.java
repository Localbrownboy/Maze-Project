package com.group23.app.entity;

import com.group23.app.level.MazeLevel;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DoorTest {
    private MazeLevel level;
    @BeforeEach
    void setup(){
        this.level =new MazeLevel(100 , 100 , 10);
        this.level.testMode = true;
    }

//    !!!! NOT DONE YET !!!! need way to check if win occured, also need to set background music so that it can stop it
    @Test
    void DoorEnterTest(){
        Door door = new Door(level);
        level.spawnEntity(door , 10 ,10);
        door.onPickUp(new Player(level));
        level.updateTick();
        assertEquals(1, level.winState);
    }

}
