package castler.Buttons;
import castler.CastlerFrame;

public class LineTowerButton extends ActionButton {

    public LineTowerButton(CastlerFrame container){
        super(container);
        this.addActionListener(e -> {
            this.container.getGamePanel().getGame().putTurret("line");
            this.container.getGamePanel().repaint();    

        });
    }
}
