package castler.Sprites.Structures;

import castler.Sprites.Player;

import javax.swing.*;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

public class Castle extends Structure {
    
    private int[] levelAreas = {0, 7, 9, 11, 13, 15};

    public Castle(int width, int height, int hitPoint, Point pos, Player owner) {
        super(new ImageIcon(owner.getColor() == castler.Sprites.Color.BLUE ? Castle.class.getResource("/images/castle_blue.png") : Castle.class.getResource("/images/castle_red.png")).getImage(), width, height, hitPoint, pos, owner);
    }
    
    public void build(){
        //TODO
    }
    
    public void upgrade() {
        // TODO
    }
    
    @Override
    public String whatami(){
        return "Castle";
    }

    @Override
    public String toString() {
        return "C";
    }
    
    @Override
    public ArrayList<Point> LOS(){
        ArrayList<Point> los = new ArrayList<>();
        for (int i = 1; i <= levelAreas[lvl]; i++) {
            for (int j = 0; j <= levelAreas[lvl]-i; j++) {
                los.add(new Point(pos.x+j,pos.y+i));
                los.add(new Point(pos.x+(-1*j),pos.y+i));
                los.add(new Point(pos.x+j,pos.y+(i*-1)));
                los.add(new Point(pos.x+(j*-1),pos.y+(i*-1)));
                
                los.add(new Point(pos.x+i,pos.y));
                los.add(new Point(pos.x+(i*-1),pos.y));
            }
        }
        return los;
    }
}
