package castler.Buttons;
import castler.CastlerFrame;

public class AreaTowerButton extends ActionButton {

    public AreaTowerButton(CastlerFrame container){
        super(container);
        this.addActionListener(e -> {
            this.container.getGamePanel().getGame().putTurret("area");
            this.container.getGamePanel().repaint();    

        });
    }
}
