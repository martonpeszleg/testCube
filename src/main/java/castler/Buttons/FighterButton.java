package castler.Buttons;

import castler.CastlerFrame;
import castler.Sprites.Units.Fighter;

import java.awt.event.ActionListener;

public class FighterButton extends ActionButton{
    public FighterButton(CastlerFrame container){
        super(container);
        this.addActionListener(e -> {
           this.container.getGamePanel().getGame().trainFighter();
           this.container.getGamePanel().repaint();
        });
    }
}
