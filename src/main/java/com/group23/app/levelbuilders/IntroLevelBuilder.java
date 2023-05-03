package com.group23.app.levelbuilders;

import com.group23.app.entity.*;
import com.group23.app.level.*;
//import com.group23.app.level.MazeLevel;


public class IntroLevelBuilder extends LevelBuilder {
    
    

    @Override
    public MazeLevel loadLevel(){
        return buildLevelFromGridArray();
    }
}
