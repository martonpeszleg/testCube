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

public class ExploreTower extends Structure {

    private int[] levelAreas = {0, 2, 3, 4, 4, 5};
    private int[] levelMoneys = {0, 20, 30, 40, 50, 50};


    public ExploreTower(Point pos, Player owner) {
        super(new ImageIcon(owner.getColor() == castler.Sprites.Color.BLUE ? ExploreTower.class.getResource("/images/tower_explore_blue.png") : ExploreTower.class.getResource("/images/tower_explore_red.png")).getImage(), 50, 50, 100, pos, owner);
        hitPoint = 10;
        maxHp = 10;
    }


    @Override
    public String whatami() {
        return "explore";
    }
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
