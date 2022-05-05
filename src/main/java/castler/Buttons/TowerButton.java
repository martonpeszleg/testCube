package castler.Buttons;

import castler.CastlerFrame;

public class TowerButton extends ActionButton {

    public TowerButton(CastlerFrame container){
        super(container);
        this.addActionListener(e -> {
            if(this.container.getGamePanel().getGame().getCurrentActiveTile() == null){
                return;
            }
            this.container.getGamePanel().getGame().putTurret();
            this.container.getGamePanel().repaint();    

        });
    }
}
