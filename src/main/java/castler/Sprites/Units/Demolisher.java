package castler.Sprites.Units;

import castler.Sprites.Player;

import javax.swing.*;
import java.awt.Image;
import java.awt.Point;

public class Demolisher extends Unit {

    public Demolisher(Point pos, Player owner, Point waypoint) {
        super(new ImageIcon(owner.getColor() == castler.Sprites.Color.BLUE ? Demolisher.class.getResource("/images/demolisher_blue.png") : Demolisher.class.getResource("/images/demolisher_red.png")).getImage(), 200, pos, owner, waypoint);
        this.waypoint = waypoint;
        speed = 2;
        hitPoint = 10;
        maxHp = 10;
    }

    public void isTurretNearby() {
        // TODO
    }

    public void blow() {
        // TODO
    }

    @Override
    public String whatami() {
        return "Demolisher";
    }
    
}
