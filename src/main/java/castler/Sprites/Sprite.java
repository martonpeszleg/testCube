
package castler.Sprites;

import castler.Game;
import java.awt.*;

public abstract class Sprite {
    protected Point pos;
    protected Image img;
    protected int width;
    protected int height;
    protected double modifier=1;
    protected Boolean selected = false;
    protected Boolean red = false;

    public Sprite(Image img, int width, int height, Point pos) {
        this.pos = pos;
        this.img = img;
        this.width=width;
        this.height=height;
    }
    public Point getPos() {
        return this.pos;
    }
    public Image getImg() {
        return img;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public void setImg(Image img) {
        this.img = img;
    }
    public void draw(Graphics g) {
        g.drawImage(img, pos.x * width, pos.y * height, width, height, null);
    }

    public void setPos(Point pos) {
        this.pos = pos;
    }

    public double getModifier() {
        return modifier;
    }
    public void setModifier(double d){
        modifier = d;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public Boolean getRed() {
        return red;
    }

    public void setRed(Boolean red) {
        this.red = red;
    }

    public void drawLines(Graphics g, Game game){
    }
}
