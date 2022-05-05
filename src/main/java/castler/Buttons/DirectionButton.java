/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package castler.Buttons;

/**
 *
 * @author marton552
 */
import castler.CastlerFrame;
import javax.swing.plaf.basic.BasicArrowButton;

public class DirectionButton extends BasicArrowButton{
    public DirectionButton(CastlerFrame container,int i){
        super(i);
        
        this.addActionListener(e -> {
            container.getGamePanel().getGame().getCurrentActiveTile().getEntity().upgradeLvl(i);
            container.getGamePanel().getGame().drawLOS();
            container.getGamePanel().repaint();
        });
    }
}