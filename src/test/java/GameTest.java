/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import castler.Game;
import castler.Sprites.Color;
import castler.Sprites.Player;
import castler.Sprites.Structures.Barrack;
import castler.Sprites.Structures.Castle;
import castler.Sprites.Structures.Tower;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Tamas
 */
public class GameTest {

    public GameTest() {
    }

    @Test
    public void doesExist() {
        Game g = new Game("Blue", "Red", 15, 1, 1, 1, 1);
        assertNotEquals(g, null);
    }

    @BeforeAll
    public static void setUp() {
        Game g = new Game(15, 1);
    }

    @Test
    public void moneyOK() {
        Game g = new Game(15, 1);
        assertEquals(g.getCurrentPlayer().getGold(), 500);
    }

    @Test
    public void nextPlayer() {
        Game g = new Game(15, 1);
        assertEquals(g.getCurrentPlayer().getColor(), Color.BLUE);
        g.nextPlayer();
        assertEquals(g.getCurrentPlayer().getColor(), Color.RED);
    }

    @Test
    public void activeTileGood() {
        Game g = new Game(15, 1);
        g.setCurrentActiveTile(5 * 50, 6 * 50);
        assertEquals(g.getCurrentActiveTile().getPos().getX(), 5);
        assertEquals(g.getCurrentActiveTile().getPos().getY(), 6);
    }

    @Test
    public void putTurretOK() {
        Game g = new Game(15, 1);
        Point p = g.getCurrentPlayer().getCastle().LOS().get(1);
        g.setCurrentActiveTile((int) p.getX() * 50, (int) p.getY() * 50);
        g.putTurret();
        assertEquals(g.getCurrentActiveTile().getEntity().getClass(), Tower.class);
    }

    @Test
    public void putBarrackOK() {
        Game g = new Game(15, 1);
        Point p = g.getCurrentPlayer().getCastle().LOS().get(1);
        g.setCurrentActiveTile((int) p.getX() * 50, (int) p.getY() * 50);
        g.putBarrack();
        assertEquals(g.getCurrentActiveTile().getEntity().getClass(), Barrack.class);
    }

    @Test
    public void putTurretNotOK() {
        Game g = new Game(15, 1);
        g.setCurrentActiveTile(0, 0);
        g.putTurret();
        assertEquals(g.getError(), "Csak a saját\ntérfeledre építkezhetsz!");
    }

    @Test
    public void putBarrackNotOK() {
        Game g = new Game(15, 1);
        g.setCurrentActiveTile(0, 0);
        g.putBarrack();
        assertEquals(g.getError(), "Csak a saját\ntérfeledre építkezhetsz!");
    }

    @Test
    public void bothCastlesOK() {
        Game g = new Game(15, 1);
        g.setCurrentActiveTile(14 * 50, 0 * 50);
        assertEquals(g.getCurrentActiveTile().getEntity().getClass(), Castle.class);
        g.setCurrentActiveTile(0 * 50, 14 * 50);
        assertEquals(g.getCurrentActiveTile().getEntity().getClass(), Castle.class);
    }

    @Test
    public void nextPlayerOK() {
        Game g = new Game(15, 1);
        int prevRound = g.getRoundNumber();
        g.nextPlayer();
        assertEquals(g.getRoundNumber(), prevRound + 1);
    }

    @Test
    public void upgradeTurretArea() {
        Game g = new Game(15, 1);
        Point p = g.getCurrentPlayer().getCastle().LOS().get(1);
        g.setCurrentActiveTile((int) p.getX() * 50, (int) p.getY() * 50);
        g.putTurret();
        assertEquals(g.getCurrentActiveTile().getEntity().getClass(), Tower.class);
        g.putTurret("area");
        assertEquals(g.getCurrentActiveTile().getEntity().whatami(), "area");
    }

    @Test
    public void upgradeTurretLine() {
        Game g = new Game(15, 1);
        Point p = g.getCurrentPlayer().getCastle().LOS().get(1);
        g.setCurrentActiveTile((int) p.getX() * 50, (int) p.getY() * 50);
        g.putTurret();
        assertEquals(g.getCurrentActiveTile().getEntity().getClass(), Tower.class);
        g.putTurret("line");
        assertEquals(g.getCurrentActiveTile().getEntity().whatami(), "line");
    }

    @Test
    public void upgradeTurretExplore() {
        Game g = new Game(15, 1);
        Point p = g.getCurrentPlayer().getCastle().LOS().get(1);
        g.setCurrentActiveTile((int) p.getX() * 50, (int) p.getY() * 50);
        g.putTurret();
        assertEquals(g.getCurrentActiveTile().getEntity().getClass(), Tower.class);
        g.putTurret("explore");
        assertEquals(g.getCurrentActiveTile().getEntity().whatami(), "explore");
    }

    @Test
    public void addGoldWorks() {
        Game g = new Game(15, 1);
        Player p = g.getPlayers().get(0);
        assertEquals(p.getGold(), 500);
        p.addGold(100);
        assertEquals(p.getGold(), 600);
    }

    @Test
    public void nextPlayerIncreasesRoundUmber() {
        Game g = new Game(15, 1);
        assertEquals(g.getRoundNumber(), 1);
        g.nextPlayer();
        assertEquals(g.getRoundNumber(), 2);
        g.nextPlayer();
        System.out.println(g.getRoundNumber());
        assertEquals(g.getRoundNumber(), 3);
    }

    @Test
    public void nextPlayerIncreasesGold() {
        Game g = new Game(15, 1);
        Player p1 = g.getPlayers().get(0);
        Player p2 = g.getPlayers().get(1);
        assertEquals(p1.getGold(), 500);
        assertEquals(p2.getGold(), 500);
        g.nextPlayer();
        assertEquals(p1.getGold(), 600);
        g.nextPlayer();
        assertEquals(p2.getGold(), 600);
    }

    @Test
    public void trainFighter() {
        Game g = new Game(15, 1);
        Player p1 = g.getPlayers().get(0);
        Point p = g.getCurrentPlayer().getCastle().LOS().get(1);
        g.setCurrentActiveTile((int) p.getX() * 50, (int) p.getY() * 50);
        g.putBarrack();
        g.trainFighter();
        assertEquals(p1.getGold(), 50);
    }

    @Test
    public void trainDemolisher() {
        Game g = new Game(15, 1);
        Player p1 = g.getPlayers().get(0);
        Point p = g.getCurrentPlayer().getCastle().LOS().get(1);
        g.setCurrentActiveTile((int) p.getX() * 50, (int) p.getY() * 50);
        g.putBarrack();
        g.trainDemolisher();
        assertEquals(p1.getGold(), 50);
    }

    @Test
    public void trainMountaineer() {
        Game g = new Game(15, 1);
        Player p1 = g.getPlayers().get(0);
        Point p = g.getCurrentPlayer().getCastle().LOS().get(1);
        g.setCurrentActiveTile((int) p.getX() * 50, (int) p.getY() * 50);
        g.putBarrack();
        g.trainMountaineer();
        assertEquals(p1.getGold(), 25);
    }

    @Test
    public void upgradeCastle() {
        Game g = new Game(15, 1);
        Player p1 = g.getPlayers().get(0);
        p1.addGold(1400);
        p1.upgrade();
        assertEquals(p1.getCastleLevel(), 2);
    }

    @Test
    public void upgradeCastleMax() {
        Game g = new Game(15, 1);
        Player p1 = g.getPlayers().get(0);
        p1.addGold(1400);
        p1.upgrade();
        p1.upgrade();
        p1.upgrade();
        assertEquals(p1.getCastleLevel(), 3);
    }
    
    @Test
    public void cantUpgradeCastleBeyondMax() {
        Game g = new Game(15, 1);
        Player p1 = g.getPlayers().get(0);
        p1.addGold(1900);
        p1.upgrade();
        p1.upgrade();
        p1.upgrade();
        p1.upgrade();
        assertEquals(p1.getCastleLevel(), 3);
        assertEquals(p1.getGold(), 400);
    }    

    /*
    explosions null hiba mint lent
    @Test
    public void fighterHitsCastle() {
        Game g = new Game(15,1);
        Player p1 = g.getPlayers().get(0);
        Player p2 = g.getPlayers().get(1);
        Point p = g.getCurrentPlayer().getCastle().LOS().get(1);
        g.setCurrentActiveTile((int)p.getX() * 50, (int)p.getY() * 50);
        g.putBarrack();
        g.trainFighter();
        for (int i=0;i<30;i++) {
            g.nextPlayer();
            g.nextPlayer();
        }
        assertEquals(p2.getCastle().getHitPoint(),95);   
    }
    
    @Test
    public void demolisherHitsCastle() {
        Game g = new Game(15,1);
        Player p1 = g.getPlayers().get(0);
        Player p2 = g.getPlayers().get(1);
        Point p = g.getCurrentPlayer().getCastle().LOS().get(1);
        g.setCurrentActiveTile((int)p.getX() * 50, (int)p.getY() * 50);
        g.putBarrack();
        g.trainDemolisher();
        for (int i=0;i<30;i++) {
            g.nextPlayer();
            g.nextPlayer();
        }
        assertEquals(p2.getCastle().getHitPoint(),95);   
    }    
    
    @Test
    public void mountaineerHitsCastle() {
        Game g = new Game(15,1);
        Player p1 = g.getPlayers().get(0);
        Player p2 = g.getPlayers().get(1);
        Point p = g.getCurrentPlayer().getCastle().LOS().get(1);
        g.setCurrentActiveTile((int)p.getX() * 50, (int)p.getY() * 50);
        g.putBarrack();
        g.trainMountaineer();
        for (int i=0;i<30;i++) {
            g.nextPlayer();
            g.nextPlayer();
        }
        assertEquals(p2.getCastle().getHitPoint(),95);   
    }
    
     */
    //Valamiért a robbanásokkal össze van kötve a modell
    /*
    @Test
    public void Winning() {
        Game g = new Game(15,1);
        Player p1 = g.getPlayers().get(0);
        p1.addGold(500);
        Point p = g.getCurrentPlayer().getCastle().LOS().get(1);
        g.setCurrentActiveTile((int)p.getX() * 50, (int)p.getY() * 50);
        g.putBarrack();
        while (g.getWinner()==null) {
            g.trainDemolisher();
            g.trainDemolisher();
            g.trainDemolisher();
            g.nextPlayer();
            g.nextPlayer();
        }
        assertEquals(g.getWinner(),p1.getName());
        
        
    }
     */
}
