package com.group23.app.entity;

import com.group23.app.level.MazeLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BreakableWallTest {

    private MazeLevel level;

    @BeforeEach
    void setup(){
        this.level =new MazeLevel(100 , 100 , 10);
        this.level.testMode = true;
    }
    @Test
    void testFireCollide(){
        BreakableWall wall = new BreakableWall(level);
        level.spawnEntity(wall , 10,10);
        level.spawnEntity(new FireSpell(level), 10,10);

        level.updateTick();
        assert(!level.tileAt(10,10).entities.contains(wall));
    }
}
