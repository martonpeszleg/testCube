package castler.Sprites.Structures;

import castler.Sprites.Player;
import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;

public class Barrack extends Structure {
    
    protected static int price = 400;

    public Barrack(Point pos, Player owner) {
        super(new ImageIcon(owner.getColor() == castler.Sprites.Color.BLUE ? Barrack.class.getResource("/images/barakk_blue.png") : Barrack.class.getResource("/images/barakk_red.png")).getImage(), 50, 50, 100, pos, owner);
    }

    public void build() {
        // TODO
    }

    @Override
    public String whatami() {
        return "Barrack";
    }
    
    public static int getPrice(){
        return price;
    }
}
