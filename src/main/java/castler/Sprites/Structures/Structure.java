package castler.Sprites.Structures;
import castler.Sprites.GameObject;
import castler.Sprites.Player;
import java.awt.Image;
import java.awt.Point;

public abstract class Structure extends GameObject{
    char type;
    
    public Structure(Image img, int width, int height,int hitPoint, Point pos, Player owner)  {
        super(img,width,height,hitPoint,pos,owner);
    }
    
    public void destroy()
    {
        // TODO
    }
    
    public void createActionPanel() {
        // TODO
    }
    
    
            
            
}
