package castler.Buttons;

import castler.CastlerFrame;
import castler.Sprites.Color;

public class EndTurnButton extends ActionButton {

    public EndTurnButton(CastlerFrame container) {
        super(container);
        this.addActionListener(e -> {
            //this.container.setBlueHealth(this.container.getGamePanel().getGame().getPlayers().get(0).getCastle().getHitPoint());
            //this.container.setRedHealth(this.container.getGamePanel().getGame().getPlayers().get(1).getCastle().getHitPoint());
            if(this.container.getGamePanel().getGame().getCurrentPlayer().getColor() == Color.RED){
                    this.container.getGamePanel().startAnimation();
            }
            this.container.getGamePanel().getGame().nextPlayer();
        });
    }
}
