package com.group23.app.entity;

import java.awt.*;
import java.util.*;


import javax.imageio.ImageIO;


import java.io.IOException;
import com.group23.app.*;
import com.group23.app.level.*;

/**
 * Main enemy entity in the game, chases down the player and insta-kills them
 * extends {@link MobileEntity}
 */
public class Golem extends MobileEntity {

    // private Player player;

    public boolean active;
    private DijkstraPath _path;
    


    /**
     * Constructor for the entity
     * @param L the level that the entity will spawn on
     */
    public Golem(MazeLevel L) {
        super(L);
        drawLayer = 10;
        _path = new DijkstraPath();
        try{
            texture = ImageIO.read(getClass().getResourceAsStream("/sprites/entities/Golem.png"));
            additionalTextures.add(ImageIO.read(getClass().getResourceAsStream("/sprites/entities/GolemDirUp.png")));
            additionalTextures.add(ImageIO.read(getClass().getResourceAsStream("/sprites/entities/GolemDirDown.png")));
            additionalTextures.add(ImageIO.read(getClass().getResourceAsStream("/sprites/entities/GolemDirLeft.png")));
            additionalTextures.add(ImageIO.read(getClass().getResourceAsStream("/sprites/entities/GolemDirRight.png")));
        } catch(IOException e){
            e.printStackTrace();
        }
        
    }


    /**
     * creating the path the golem will take
     * @param endpoint
     * @return the arraylist of points that the golem will attempt to take
     */
    public DijkstraPath findDijkstraPath(Point endpoint){

        DijkstraNode start = new DijkstraNode(getPosition()[0], getPosition()[1]);
        DijkstraNode end = new DijkstraNode(endpoint.x, endpoint.y);

        if(start.x == end.x && start.y == end.y){
            ArrayList<Point> lol = new ArrayList<Point>();
            lol.add(endpoint);
            return new DijkstraPath(lol, 0);
        }

        DijkstraNode[][] grid = calculateShortestPathGrid(level.width, level.height, start, end);
            
    
        return createPathFromGrid(grid, start, end);
    }

    /**
     * Calculates relative distance values across a tile grid using a priority queue
     * @param xsize the width of the grid
     * @param ysize the height of the grid
     * @param start the intended start point in grid coordinates
     * @param end the intended end point in grid coordinates
     * @return the grid with adjusted distance values
     */
    public DijkstraNode[][] calculateShortestPathGrid(int xsize, int ysize, DijkstraNode start, DijkstraNode end){
        DijkstraNode[][] grid = new DijkstraNode[xsize][ysize];

        for(int i = 0; i < xsize; ++i){
            for(int j = 0; j < ysize; ++j){
                grid[i][j] = new DijkstraNode(i, j);
                if(level.tileAt(i,j).checkifCollides()){
                    grid[i][j].extraDist = 1000000;
                }else{
                    boolean hasgolem = false;
                    for(int k = 0; k <level.tileAt(i, j).entities.size(); ++k){
                        Entity e = level.tileAt(i, j).entities.get(k);
                        if(e.getProperties().contains(EntityProperty.Instakill)){
                            hasgolem = true;
                        }
                    }

                    if(hasgolem){
                        grid[i][j].extraDist = 6;
                    }else{
                        grid[i][j].extraDist = 1;
                    }

                }
            }
        }

        Comparator<DijkstraNode> adjacencyComparator = (left, right) -> {
            if (left.distance > (right.distance)) {
            return 1;
            }
            return -1;
            };

        start.distance = 0;


        //create priority queue
        Queue<DijkstraNode> queueB = new PriorityQueue<DijkstraNode>(xsize>ysize ? xsize : ysize, adjacencyComparator);

        queueB.add(start);

        while (queueB.size() > 0) {
            DijkstraNode current = queueB.remove();
            DijkstraNode t;
           
            // Up
            if (current.y - 1 >= 0) {
                t = grid[current.x][current.y-1];
                if (!t.visited &&  t.distance > current.distance + t.extraDist) {
                    t.distance = current.distance + t.extraDist;
                    t.parent = current;
                    queueB.add(t);
                }
            }
           
            // Left
            if (current.x - 1 >= 0) {
                t = grid[current.x-1][current.y];
                if (!t.visited &&  t.distance > current.distance + t.extraDist) {
                    t.distance = current.distance + t.extraDist;
                    t.parent = current;
                    queueB.add(t);
                }
            }

            // Down
            if (current.y + 1 < ysize) {
                t = grid[current.x][current.y+1];
                if (!t.visited &&  t.distance > current.distance + t.extraDist) {
                    t.distance = current.distance + t.extraDist;
                    t.parent = current;
                    queueB.add(t);
                }
            }

            // Right
            if (current.x + 1 < xsize) {
                t = grid[current.x+1][current.y];
                if (!t.visited &&  t.distance > current.distance + t.extraDist) {
                    t.distance = current.distance + t.extraDist;
                    t.parent = current;
                    queueB.add(t);
                }
            }
            current.visited = true;
        }

        return grid;
    }

    /**
     * Takes in a path grid to find the the list of next steps to make
     * @param grid the path grid
     * @param start the start point
     * @param end the end point
     * @return a DijkstraPath object including the steps to take, and the modified angle of the path
     */
    public DijkstraPath createPathFromGrid(DijkstraNode[][] grid, DijkstraNode start, DijkstraNode end){
        
        ArrayList<Point> path = new ArrayList<Point>();
        // Checking if a path exists
        if (!(grid[end.x][end.y].distance == Integer.MAX_VALUE)) {
            //Trace back the path
            DijkstraNode current = grid[end.x][end.y];

            while (current.parent != null) {
                path.add(new Point(current.x, current.y));
                current = current.parent;
            }
        } else{
            System.out.println("No possible path");
            return null;
        } 

        double d = grid[end.x][end.y].distance;
        //System.out.println(d);

        return new DijkstraPath(path, d);


    }


    /**
     * checks collision with other entities on this tile. if it collides with a player entity, it will set their health to 0
     * @param othersOnTile an ArrayList of Entities that are share a tile with this entity 
     */
    @Override
    public void checkCollision(ArrayList<Entity> othersOnTile){

        super.checkCollision(othersOnTile);
        for(int i = 0; i < othersOnTile.size(); ++i){
            Entity e = othersOnTile.get(i);
            if(e.getProperties().contains(EntityProperty.Player)){
                level.playerHealthCurrent = 0;
                
                //Game.stopMusic(true);
            }
        }
    }

    /**
     * Will try to move to the tile that is determined by the {@link #findDijkstraPath(Point)}
     */
    @Override
    public void movementBehaviour() {
        if (this.level == null) {
            return;
        }

        //&&_path.distance<1000000
        if(_path!=null&&_path.path.size()>0){
            if(_path.distance<1000000){
                active = true;
            }
            
            Point next = _path.path.get(_path.path.size()-1);
            if(active){
                tryMoveTo(next.x, next.y);
            }
        }
        _path = findDijkstraPath(level.findPlayerPosition());
    }

    
    @Override

    
    public void draw(Graphics2D g){
        
        super.draw(g);
        if(animFrames==1){
            Point next = _path.path.get(_path.path.size()-1);
            Point current = getPositionAsPoint();

            if(next.x-current.x<0){
                curDirection = Direction.Left;
            }else if(next.x-current.x>0){
                curDirection = Direction.Right;
            }else if(next.y-current.y<0){
                curDirection = Direction.Up;
            }else if(next.y-current.y>0){
                curDirection = Direction.Down;
            }
        }
        /*
        if(texture==null){
            return;
        }
        int ts = level.getTileSize();

        //find the location based on the game environment point draw bounds
        Point drawPosition = new Point(level.getRenderBox().x + (getPosition()[0]*level.getTileSize()), level.getRenderBox().y + (getPosition()[1]*level.getTileSize()));

        g.drawImage(texture, drawPosition.x, drawPosition.y, ts, ts, null);

        for(int i = 0; i < _path.size(); ++i){
            Point gcoord = _path.get(i);
            Point drawPosition2 = new Point(level.getRenderBox().x + (gcoord.x*level.getTileSize()), level.getRenderBox().y + (gcoord.y*level.getTileSize()));

            g.drawImage(texture, drawPosition2.x, drawPosition2.y, ts/2, ts/2, null);

        }*/
    }



    /**
     * node class that is used by {@link Golem#findDijkstraPath(Point)}
     */
    class DijkstraNode {
        int x;
        int y;
        double distance = Integer.MAX_VALUE;
        DijkstraNode parent = null;
        boolean visited;
        double extraDist;

        public DijkstraNode(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    /**
     * Attempts to Relocate the entity to a new tile, does not move the entity if it tries to move to a solid tile
     * @param x the x coordinate to move to
     * @param y the y coordinate to move to
     */
    @Override
    public boolean tryMoveTo(int x,int y){

        if(x<0 || x >= level.width|| y < 0 || y >= level.height){
            return false;
        }

        if(level.tileAt(x, y).checkifCollides() == true){
            return false;
        }

        boolean hasgolem = false;
        for(int k = 0; k < level.tileAt(x, y).entities.size(); ++k){
            Entity e = level.tileAt(x, y).entities.get(k);
            if(e.getProperties().contains(EntityProperty.Instakill)){
                hasgolem = true;
            }
        }

        if(hasgolem){
            return false;
        }
        
        MoveTo(x, y);
        return true;
    }

    class DijkstraPath{
        ArrayList<Point> path;
        double distance;

        public DijkstraPath(){
            path = new ArrayList<Point>();
            distance = 0;
        }

        public DijkstraPath(ArrayList<Point> p, double l){
            path = p;
            distance = l;
        }
    }
}
