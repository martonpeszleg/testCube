package castler.Sprites.Tiles;
import castler.Game;
import castler.Sprites.GameObject;
import castler.Sprites.Sprite;

import java.awt.*;

public abstract class Tile extends Sprite {
    protected GameObject entity;
    protected Point pos;
    protected boolean visited;

    public Tile(Image img, int width, int height, GameObject entity, Point pos) {
        super(img,width,height,pos);
        this.entity=entity;
        this.pos=pos;
        this.visited = false;
    }

    public Point getPos() {
        return pos;
    }

    @Override
    public String toString() {
        return "Z";
    }

    public GameObject getEntity() {
        return entity;
    }

    public int hasEntity(){
        if(getEntity() == null) return 0;
        else if(getEntity().whatami() == "Fighter" || getEntity().whatami() == "Mountaineer" || getEntity().whatami() == "Demolisher") return 0;
        else return 1000;
    }

    public double getDist(Tile that){
        return Math.sqrt(Math.pow((this.getPos().x - that.getPos().x),2) + Math.pow((this.getPos().y - that.getPos().y),2));
    }

    public void setEntity(GameObject entity) {
        this.entity=null;
        this.entity = entity;
    }

    public boolean getVisited(){
        return visited;
    }
    public void setVisited(boolean x){
        visited = x;
    }


    public void draw(Graphics g){
        super.draw(g);
        if(entity != null){
            entity.draw(g);
        }
    }

    public void animatedDraw(Graphics g){
        if(entity != null){
            entity.drawOnAnimatedPosition(g);
        }
    }

    public void drawWithoutUnit(Graphics g){
        super.draw(g);
        if(entity != null && entity.whatami() != "Fighter" && entity.whatami() != "Mountaineer" && entity.whatami() != "Demolisher"){
            entity.draw(g);
        }
    }

    public void drawLines(Graphics g, Game game){
        if(entity != null){
            entity.drawLines(g,game);
        }
    }

    public abstract String getType();

    @Override
    public double getModifier(){
        return modifier+hasEntity();
    }

}
