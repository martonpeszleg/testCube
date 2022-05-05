/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package castler.Sprites.Structures;

import castler.Sprites.Player;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author marton552
 */
public class LineDamageTower extends Structure {

    private int[] levelDistances = {0, 3, 4, 4, 5, 6};
    private int[] levelDamages = {0, 30, 40, 50, 50, 50};
    private Point direction = null;

    //Direction még kell
    public LineDamageTower(Point pos, Player owner) {
        super(new ImageIcon(owner.getColor() == castler.Sprites.Color.BLUE ? LineDamageTower.class.getResource("/images/tower_line_blue.png") : LineDamageTower.class.getResource("/images/tower_line_red.png")).getImage(), 50, 50, 100, pos, owner);
        hitPoint = 5;
        maxHp = 5;
    }

    @Override
    public String whatami() {
        return "line";
    }
    
    @Override
    public void upgradeLvl(int i){
        if(i == 0) lvl = 0;
        else if(lvl == 0){
            if(i == 1) direction = new Point(0,-1);
            if(i == 3) direction = new Point(1,0);
            if(i == 5) direction = new Point(0,1);
            if(i == 7) direction = new Point(-1,0);
            lvl++;
        }
        else lvl++;
    }
    
    @Override
    public ArrayList<Point> LOS(){
        ArrayList<Point> los = new ArrayList<>();
        for (int i = 0; i < levelDistances[lvl]; i++) {
            Point p = new Point(pos.x,pos.y);
            p.x += direction.x*(i+1);
            p.y += direction.y*(i+1);
            los.add(p);
        }
        return los;
    }
}
