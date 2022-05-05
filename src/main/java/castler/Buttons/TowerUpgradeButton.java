package castler.Buttons;

import castler.CastlerFrame;

public class TowerUpgradeButton extends ActionButton{
    public TowerUpgradeButton(CastlerFrame container) {
        super(container);
        this.addActionListener(e -> {
            if(this.container.getGamePanel().getGame().getCurrentActiveTile()== null){
                return;
            }
            this.container.getGamePanel().getGame().upgradeTower();
            this.container.getGamePanel().getGame().drawLOS();
            this.container.getGamePanel().repaint();    

        });
    }
}
