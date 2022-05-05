package castler.Sprites.Units;

import castler.Sprites.Player;

import javax.swing.*;
import java.awt.Image;
import java.awt.Point;

public class Fighter extends Unit{
    public Fighter(Point pos, Player owner, Point waypoint)  {
        super(new ImageIcon(owner.getColor() == castler.Sprites.Color.BLUE ? Fighter.class.getResource("/images/spearman_blue.png") : Fighter.class.getResource("/images/spearman_red.png")).getImage(),100,pos,owner,waypoint);
        this.price = 50;
        this.waypoint=waypoint;
        speed=2;
        hitPoint = 5;
        maxHp = 5;
    }
    
    public void detectNearby(){
        // TODO
    }
    @Override
    public String whatami(){
        return "Fighter";
    }    
}
