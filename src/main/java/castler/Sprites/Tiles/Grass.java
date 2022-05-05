package castler.Sprites.Tiles;

import castler.Sprites.GameObject;

import javax.swing.*;
import java.awt.Image;
import java.awt.Point;

public class Grass extends Tile{
    
    public Grass(GameObject entity, Point pos){
        super(new ImageIcon(Grass.class.getResource("/images/grass.png")).getImage(),50,50,entity,pos);
        super.modifier=1;
    }

    @Override
    public String getType(){
        return "Grass";
    }

    @Override
    public String toString() {
        return "G";
    }
}
