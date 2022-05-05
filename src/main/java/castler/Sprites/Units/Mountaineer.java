package castler.Sprites.Units;

import castler.Sprites.Player;

import javax.swing.*;
import java.awt.Image;
import java.awt.Point;

public class Mountaineer extends Unit {

    public Mountaineer(Point pos, Player owner, Point waypoint) {
        super(new ImageIcon(owner.getColor() == castler.Sprites.Color.BLUE ? Mountaineer.class.getResource("/images/mountaineer_blue.png") : Mountaineer.class.getResource("/images/mountaineer_red.png")).getImage(),75, pos, owner, waypoint);
        this.price = 75;
        this.waypoint = waypoint;
        speed = 3;
        hitPoint = 3;
        maxHp = 3;
    }
    @Override
    public String whatami(){
        return "Mountaineer";
    }
}
