/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package castler.Sprites;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author marton552
 */
public class Explosion {

    private int x;
    private int y;
    private int picPos = -7;
    private Image image;
    public Explosion(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void nextFrame(){
        picPos++;
        if(picPos <= 0)image = new ImageIcon(this.getClass().getResource("/images/explosion/0.png")).getImage();
        else if(picPos <= 12)image = new ImageIcon(this.getClass().getResource("/images/explosion/"+picPos+".png")).getImage();
    }

    public int getPicPos() {
        return picPos;
    }

    public Image getImage() {
        return image;
    }
    
        public void draw(Graphics g, int width, int height){
        g.drawImage(image, (int)(x * width), (int)(y * height), width, height, null);
    }
        
    public boolean isOver(){
        return (picPos >= 12);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public void hurry(){
        if(picPos < 1) picPos = 1;
    }
}
