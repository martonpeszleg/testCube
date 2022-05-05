/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package castler.Sprites.Structures;

import castler.Sprites.Player;
import java.awt.Point;

/**
 *
 * @author marton552
 */
public class DefaultTower extends Tower {
    

    public DefaultTower(Point pos, Player owner) {
        super(pos, owner);
        type = "default";
        hitPoint = 5;
        maxHp = 5;
    }
    
}
