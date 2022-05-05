/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package castler.Sprites;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;

/**
 *
 * @author marton552
 */
public class Bullet {
    private Point pos;
    private Image image;
    private int x;
    private int y;
    public Bullet(Point pos) {
        this.pos = pos;
        image = new ImageIcon(this.getClass().getResource("/images/bullet.png")).getImage();
    }
    
    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
        pos = new Point(x,y);
        image = new ImageIcon(this.getClass().getResource("/images/bullet.png")).getImage();
    }
    
    public Image getImage() {
        return image;
    }
    
    public void draw(Graphics g, int width, int height){
        g.drawImage(image, x, y, 20, 20, null);
    }

    public void setPos(Point pos) {
        this.pos = pos;
        this.x = pos.x;
        this.y = pos.y;
    }

    public Point getPos() {
        return pos;
    }
    
    
}
