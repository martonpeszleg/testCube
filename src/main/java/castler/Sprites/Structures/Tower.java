package castler.Sprites.Structures;

import castler.Sprites.Player;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.Position;

public class Tower extends Structure {

    protected static int price = 50;
    protected int lvl = 0;
    //3+1 féle torony lesz, a fejlesztés elején választani kell az egyik irányból és utána azt fejlesztgeted
    //default: csak egy torony ami ott áll és blockol
    //area_damage: egy adott sugarú körzetben sebez
    //line_damage: egy irányban sebez csak, de többet
    //explore: növeli az építhető területet és aranyat generál
    protected String type = "default";

    public Tower(Point pos, Player owner) {
        super(new ImageIcon(owner.getColor() == castler.Sprites.Color.BLUE ? Tower.class.getResource("/images/tower_blue.png") : Tower.class.getResource("/images/tower_red.png")).getImage(), 50, 50, 100, pos, owner);
        hitPoint = 5;
        maxHp = 5;
    }
    

    public void lvlUp(){
        lvl++;
    }

    public int getLvl() {
        return lvl;
    }

    @Override
    public String whatami() {
        return "Tower";
    }
    public static int getPrice(){
        return price;
    }
}
