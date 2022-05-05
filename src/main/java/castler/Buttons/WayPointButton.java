/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package castler.Buttons;

import castler.CastlerFrame;

/**
 *
 * @author marton552
 */
public class WayPointButton extends ActionButton {
        public WayPointButton(CastlerFrame container){
        super(container);
        this.addActionListener(e -> {
            this.container.getGamePanel().getGame().setWaypointUnit(true);
            this.container.getGamePanel().repaint();    

        });
    }
}
