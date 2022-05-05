package castler.Sprites;

import castler.Game;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

public abstract class GameObject extends Sprite{
    protected int hitPoint;
    protected Point pos;
    protected Player owner;
    protected int movedRound = 0;
    protected int lvl =1;
    protected double maxHp = 100;
    protected GameObject isShooting = null;
    protected int drawedTimes = 0;
    protected Bullet bullet = null;
    protected Point animatedpos;
    protected int dirX;
    protected int dirY;
    protected char direction;

    public GameObject(Image img, int width, int height,/* eddig Sprite  */ int hitPoint, Point pos, Player owner) {
        super(img,width,height,pos);
        this.hitPoint=hitPoint;
        this.pos=pos;
        this.owner=owner;
        this.hitPoint = 100;
        this.animatedpos = new Point(pos.x * 50,pos.y * 50);
    }

    public void getHit(GameObject source){
        hitPoint-=source.getHitPoint();
        if(hitPoint <0 )hitPoint = 0;
    }

    public void getHit(int n){
        hitPoint-=n;
        if(hitPoint <0 )hitPoint = 0;
    }

    public int getHitPoint() {
        return hitPoint;
    }

    public abstract String whatami();

    public Player getOwner() {
        return owner;
    }

    public int getMovedRound() {
        return movedRound;
    }

    public void setMovedRound(int m) {
        this.movedRound = m;
    }

    protected void setHitPoint(int hpoint) {
        this.hitPoint = hpoint;
    }

    public void upgradeLvl(){
        lvl++;
    }
    public void upgradeLvl(int i){
        lvl=i;
    }

    public int getLvl(){
        return lvl;
    }

    public ArrayList<Point> LOS(){
        return null;
    }

    public double getMaxHp() {
        return maxHp;
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }


    @Override
    public void draw(Graphics g) {
        if(hitPoint < 0) hitPoint = 0;
        g.drawImage(img, super.pos.x * width, super.pos.y * height, width, height, null);
        if(whatami() != "TowerRuin"){
        g.setColor(java.awt.Color.BLACK);
        g.fillRect(super.pos.x * width, super.pos.y * width, width, height/5);
        g.setColor(java.awt.Color.RED);
        g.fillRect(super.pos.x * width+2, super.pos.y * height+2, (int)((hitPoint/maxHp)*50), height/5-4);}
    }

    public void drawOnAnimatedPosition(Graphics g){
        g.drawImage(img, this.animatedpos.x, this.animatedpos.y, width, height, null);
        g.setColor(java.awt.Color.BLACK);
        g.fillRect(this.animatedpos.x, this.animatedpos.y, width, height/5);
        g.setColor(java.awt.Color.RED);
        g.fillRect(this.animatedpos.x+2, this.animatedpos.y+2, (int)((hitPoint/maxHp)*50), height/5-4);
        moveAnimation();
    }

    public void initAnimation(){
        dirX = (int)Math.signum(super.pos.x * width - this.animatedpos.x);
        dirY = (int)Math.signum(super.pos.y * height - this.animatedpos.y);
    }

    public void moveAnimation(){
        if(!isXMovementOver() && direction == 'x'){
            this.animatedpos.x += dirX * 5;
        }
        else if(!isYMovementOver()){
            this.animatedpos.y += dirY * 5;
        }
        else if(!isXMovementOver()){
            this.animatedpos.x += dirX * 5;
        }
    }

    public boolean isXMovementOver(){
        boolean over = false;
        switch(dirX){
            case -1:
                over = animatedpos.x <= super.pos.x * 50;
                break;
            case 0:
                over = true;
                break;
            case 1:
                over = animatedpos.x >= super.pos.x * 50;
                break;
        }
        return over;
    }

    public boolean isYMovementOver(){
        boolean over = false;
        switch(dirY){
            case -1:
                over = animatedpos.y <= super.pos.y * 50;
                break;
            case 0:
                over = true;
                break;
            case 1:
                over = animatedpos.y >= super.pos.y * 50;
                break;
        }
        return over;
    }

    public void endAnimation(){
        this.animatedpos.x = super.pos.x * width;
        this.animatedpos.y = super.pos.y * height;
    }

    @Override
    public void drawLines(Graphics g, Game game){
        if(isShooting != null){
            //g.setColor(java.awt.Color.red);
            //g.drawLine(super.pos.x*width+(width/2), super.pos.y*height+(height/2), isShooting.getPos().x*width+(width/2), isShooting.getPos().y*height+(height/2));
            bullet.draw(g, width, height);
            double x = bullet.getPos().x+(isShooting.getPos().x*width+(width/2) - bullet.getPos().x)/5;
            double y = bullet.getPos().y+(isShooting.getPos().y*width+(width/2) - bullet.getPos().y)/5;
            bullet.setPos(new Point((int)x,(int)y));
            drawedTimes--;
            if(drawedTimes < 0){
                isShooting = null;
                bullet = null;
                game.setFinishedShooting(true);
            } 
        }
    }

    public void shootingTo(GameObject g){
        isShooting = g;
        drawedTimes = 5;
        bullet = new Bullet(super.pos.x*width+(width/2),super.pos.y*width+(width/2));
    }

    public GameObject getIsShooting() {
        return isShooting;
    }

    
}
