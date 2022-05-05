package castler.Buttons;

import castler.CastlerFrame;

public class MountaineerButton extends ActionButton{
    public MountaineerButton(CastlerFrame container){
        super(container);
        this.addActionListener(e -> {
            this.container.getGamePanel().getGame().trainMountaineer();
            this.container.getGamePanel().repaint();
        });
    }
}
