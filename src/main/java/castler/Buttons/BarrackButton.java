package castler.Buttons;

import castler.CastlerFrame;

public class BarrackButton extends ActionButton {

    public BarrackButton(CastlerFrame container){
        super(container);
        this.addActionListener(e -> {
            if(this.container.getGamePanel().getGame().getCurrentActiveTile() == null){
                return;
            }
            this.container.getGamePanel().getGame().putBarrack();
            this.container.getGamePanel().repaint();    

        });
    }
}