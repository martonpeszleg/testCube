package castler.Sprites.Structures;

import castler.Sprites.Player;
import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;

public class TowerRuin extends Structure {

    public TowerRuin(Point pos, Player owner) {
        super(new ImageIcon(owner.getColor() == castler.Sprites.Color.BLUE ? TowerRuin.class.getResource("/images/tower_ruin_blue.png") : TowerRuin.class.getResource("/images/tower_ruin_red.png")).getImage(), 50, 50, 100, pos, owner);
        hitPoint = 1000;
        maxHp = 1000;
    }
    @Override
    public String whatami(){
        return "TowerRuin";
    }
}
