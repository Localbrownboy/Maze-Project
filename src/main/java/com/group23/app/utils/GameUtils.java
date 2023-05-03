package com.group23.app.utils;

import java.awt.*;

/**
 * A class which is used to hold helper methods for game and entity classes
 */
public class GameUtils {
    
    public static Point pointLerp(Point p1, Point p2, float v){
        float dx = p2.x - p1.x;
        float dy = p2.y - p1.y;
        
        float lx = ((float)p1.x) + (v*dx);
        float ly = ((float)p1.y) + (v*dy);

        return new Point((int)lx, (int)ly);


    }


}
