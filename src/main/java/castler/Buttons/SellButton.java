package castler.Buttons;

import castler.CastlerFrame;

/**
 *
 * @author Tamas
 */
public class SellButton extends ActionButton {
    
    public SellButton(CastlerFrame container){
        super(container);
        this.addActionListener(e -> {
            if(this.container.getGamePanel().getGame().getCurrentActiveTile() == null){
                return;
            }
            switch (this.container.getGamePanel().getGame().getCurrentActiveTile().getEntity().whatami()) {
                case "Tower":this.container.getGamePanel().getGame().sellStructure(25); break;
                case "Area":this.container.getGamePanel().getGame().sellStructure(50);break;
                case "Barrack":this.container.getGamePanel().getGame().sellStructure(100);break;
                case "explore":
                case "area":
                case "line":this.container.getGamePanel().getGame().sellStructure(50);break;
            }
            
              

        });    
    
}
    
}
