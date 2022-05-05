package castler.Sprites.Units;

import castler.Map;
import castler.ShortestPathBetweenCellsBFS;
import castler.Sprites.GameObject;
import castler.Sprites.Player;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

public abstract class Unit extends GameObject{
    
    protected Point waypoint;
    protected int speed;
    protected static int price;
    
    public Unit(Image img, int hitPoint, Point pos, Player owner, Point waypoint)  {
        super(img,50,50,hitPoint,pos,owner);
        this.waypoint=waypoint;
    }    
    
    public void attack(GameObject target){
        // TODO
        target.getHit(this);
    }

    public static int getPrice(){
        return price;
    }
    
    public abstract String whatami();

    public Point getWaypoint() {
        return waypoint;
    }

    public void setWaypoint(Point waypoint) {
        this.waypoint = waypoint;
    }
    

    public int getSpeed() {
        return speed;
    }
        public ArrayList<Point> LOS(){
        ShortestPathBetweenCellsBFS algo = new ShortestPathBetweenCellsBFS();
        ArrayList<Point> path;
        if (whatami() == "Mountaineer") {
            path = algo.shortestPath(Map.getMap(), getPos(), getWaypoint(), true);
        } else {
            path = algo.shortestPath(Map.getMap(), getPos(), getWaypoint(), false);
        }
        return path;
    }
}