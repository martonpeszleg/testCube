package castler.Sprites.Tiles;

import castler.Sprites.GameObject;

import javax.swing.*;
import java.awt.Image;
import java.awt.Point;

public class Mountain extends Tile {
    public Mountain(GameObject entity, Point pos) {
        super(new ImageIcon(Mountain.class.getResource("/images/mountain.png")).getImage(), 50, 50, entity, pos);
        super.modifier=42069;
    }

    @Override
    public String getType(){
        return "Mountain";
    }

    @Override
    public String toString() {
        return "M";
    }
}
