package castler.Sprites;

import castler.Sprites.Structures.Castle;
import java.awt.Image;
import java.awt.Point;
import javax.swing.*;
import javax.swing.text.Position;

;

public class Player {
    private int gold;
    private Castle castle;
    private Color color;
    private String name;
    private int castleLevel;
    private int maxTower;
    private int towerCount;
    
    public Player(int hitPoint, Point pos, Color color,String name) {
        this.gold=500; // még kérdéses
        this.color = color;
        this.castle= new Castle(50,50,hitPoint,pos,this);
        this.name = name;
        this.castleLevel=1;
        this.maxTower=3;
        this.towerCount=0;
    }

    public Castle getCastle(){
        return this.castle;
    }

    public Point getPos(){
        return this.castle.getPos();
    }

    public Color getColor(){
        return this.color;
    }

    public int getGold() {
        return gold;
    }

    public void addGold(int gold) {
        this.gold += gold;
    }
    
    public boolean addTower() {
        if (this.towerCount+1>this.maxTower)
        {
            return false;
        }
        this.towerCount++;
        return true;
    }
 
    public Boolean hasEnoughMoney(int needed){
        return (gold >=needed);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
    
    public void upgrade() { if (this.castleLevel<3) this.castleLevel++; this.maxTower=2*this.castleLevel; this.gold-=500;}

    public int getCastleLevel() {
        return castleLevel;
    }

    public int getMaxTower() {
        return maxTower;
    }
    
    public int getTowerCount() {
        return towerCount;
    }
    
    
    
}
