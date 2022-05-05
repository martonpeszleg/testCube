package castler.Sprites.Tiles;

import castler.Sprites.GameObject;

import javax.swing.*;
import java.awt.Image;
import java.awt.Point;

public class Swamp extends Tile{
    public Swamp(GameObject entity, Point pos) {
        super(new ImageIcon(Swamp.class.getResource("/images/swamp.png")).getImage(), 50, 50, entity, pos);
        super.modifier = 42069;
    }

    @Override
    public String getType(){
        return "Swamp";
    }

    @Override
    public String toString() {
        return "S";
    }
}
