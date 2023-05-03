package com.group23.app.entity;

import com.group23.app.level.MazeLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SpikeHazardTest {

    private MazeLevel level;
    private Player player = new Player(level);
    @BeforeEach
    void setup(){
        this.level =new MazeLevel(100 , 100 , 10);
        this.player = new Player(level);
        this.level.testMode = true;
        level.spawnEntity(player , 10 , 10);
        level.spawnEntity(new SpikeHazard(level), 10, 10);
    }

    @Test
    void willKillPlayer(){
        assert(level.playerHealthCurrent==level.playerHealthMax);
        while(level.playerHealthCurrent>0){
            level.updateTick();
        }
        assert(level.winState==-1);
    // think ben is working on this, since it also needs the game state
    }

    @Test
    void willTakeDamage(){
        assert(level.playerHealthCurrent==level.playerHealthMax);
        level.updateTick();
        assert(level.playerHealthCurrent== level.playerHealthMax-1);
        // think ben is working on this, since it also needs the game state
    }
}
