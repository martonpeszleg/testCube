/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package castler.Buttons;

import castler.CastlerFrame;
import javax.swing.JButton;

/**
 *
 * @author Tamas
 */
public class UpgradeButton extends ActionButton{
    public UpgradeButton(CastlerFrame container) {
        super(container);
        this.addActionListener(e -> {
            this.container.getGamePanel().getGame().upgrade();
            this.container.repaint();
        });
    }  
}
