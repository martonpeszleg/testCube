package castler;
import castler.Sprites.Tiles.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel{
    private Game game;
    private Boolean animationActive = false;
    private int iterations = 0;
    private Timer timer;
    private BufferedImage currentImage;

    public GamePanel(String p1name,String p2name, int size, int swampcnt, int swampsize, int mountaincnt, int mountainsize){
        super();
        game = new Game(p1name,p2name,size,swampcnt,swampsize,mountaincnt,mountainsize);
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                game.setCurrentActiveTile(e.getX(), e.getY());
                game.setError("-1");
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        timer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(animationActive){
                    repaint();
                    iterations += 1;
                    if(iterations >= 35){
                        game.endAnimationForEach();
                        animationActive = false;
                    }
                }else{
                    iterations = 0;
                }
            }
        });
        timer.start();
        this.setPreferredSize(new Dimension(game.getMapSize() * 50, game.getMapSize() * 50));
    }

    protected void paintComponent(Graphics g){
        if(animationActive){
            super.paintComponent(g);
            g.drawImage(currentImage, 0,0,null);
            game.getMap().animatedDraw(g);
        }else{
            super.paintComponent(g);
            game.getMap().draw(g,game);
            game.drawExplosions(g);
        }
    }

    public BufferedImage getCurrentImage(){
        Dimension d = this.getSize();
        BufferedImage current = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = current.createGraphics();
        game.getMap().drawWithoutUnits(g);
        return current;
    }

    public Boolean isAnimationActive(){
        return this.animationActive;
    }

    public void startAnimation(){
        game.initAnimationForEach();
        animationActive = true;
        currentImage = getCurrentImage();
    }

    public Game getGame(){
        return this.game;
    }
}
