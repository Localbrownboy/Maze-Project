package com.group23.app.entity;

import com.group23.app.level.MazeLevel;
import com.group23.app.entity.*;

import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    private MazeLevel level;
    private Player player = new Player(level);
    @BeforeEach
    void setup(){
        this.level = new MazeLevel(100 , 100 , 10);
        this.level.testMode = true;
        this.player = new Player(level);
        level.spawnEntity(player , 10 , 10);
        
        level.spawnEntity(new Wall(level){}, 16, 10);
    }

    @Test
    void testMove(){
        player.tryMoveTo(40, 40);
        player.tryMoveTo(16, 10);

        assert(player.getPositionAsPoint().x==40);
        assert(player.getPositionAsPoint().y==40);
    }

    @Test
    void CoolDownsComeBackUp(){
        player.fireSpell();
        player.iceSpell();
        assert(player.iceCooldown == 7);
        assert(player.fireCooldown == 7);
        for(int i = 0 ; i < 7 ; i++){
            level.updateTick(); // cant be done without a game class
        }

        assert(player.iceCooldown == 0);
        assert(player.fireCooldown == 0); //when update tick happens these should be 0

    }
    @Test
    void fireSpell(){
        assert (player.fireCooldown == 0);
        player.fireSpell();
        assert(level.tileAt(11,11).entities.get(0) instanceof FireSpell);
    }
    @Test
    void iceSpell(){
        assert (player.iceCooldown == 0);
        player.iceSpell();
        //check if either of the two entities on the tile are the ice wall
        assert(level.tileAt(10,10).entities.get(0) instanceof IceWall
                ||
                level.tileAt(10,10).entities.get(1) instanceof IceWall );
    }
}
