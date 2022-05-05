package castler.Buttons;

import castler.CastlerFrame;

public class DemolisherButton extends ActionButton{
    public DemolisherButton(CastlerFrame container){
        super(container);
        this.addActionListener(e -> {
            this.container.getGamePanel().getGame().trainDemolisher();
            this.container.getGamePanel().repaint();
        });
    }
}
