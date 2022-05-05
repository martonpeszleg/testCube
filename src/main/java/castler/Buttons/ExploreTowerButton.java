package castler.Buttons;
import castler.CastlerFrame;

public class ExploreTowerButton extends ActionButton {

    public ExploreTowerButton(CastlerFrame container){
        super(container);
        this.addActionListener(e -> {
            this.container.getGamePanel().getGame().putTurret("explore");
            this.container.getGamePanel().repaint();    

        });
    }
}
