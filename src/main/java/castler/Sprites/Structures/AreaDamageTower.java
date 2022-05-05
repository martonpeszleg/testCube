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


public class AreaDamageTower extends Structure{
    private int[] levelAreas = {0,1,1,2,2,3};
    private int[] levelDamages = {0,15,25,30,40,50};

    public AreaDamageTower(Point pos, Player owner) {
        super(new ImageIcon(owner.getColor() == castler.Sprites.Color.BLUE ? AreaDamageTower.class.getResource("/images/tower_area_blue.png") : AreaDamageTower.class.getResource("/images/tower_area_red.png")).getImage(), 50, 50, 100, pos, owner);
        this.setHitPoint(levelDamages[lvl]);
        hitPoint = 7;
        maxHp = 7;
    }
    
    
    @Override
    public String whatami() {return "area";}
    
    @Override
    public ArrayList<Point> LOS(){
        ArrayList<Point> los = new ArrayList<>();
        for (int i = 1; i <= levelAreas[lvl]; i++) {
            for (int j = 0; j <= levelAreas[lvl]-i; j++) {
                los.add(new Point(pos.x+j,pos.y+i));
                los.add(new Point(pos.x+(-1*j),pos.y+i));
                los.add(new Point(pos.x+j,pos.y+(i*-1)));
                los.add(new Point(pos.x+(j*-1),pos.y+(i*-1)));
                
                los.add(new Point(pos.x+i,pos.y));
                los.add(new Point(pos.x+(i*-1),pos.y));
            }
        }
        return los;
    }

}
