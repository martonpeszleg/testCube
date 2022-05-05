package castler;

import castler.Sprites.Explosion;
import castler.Sprites.GameObject;
import castler.Sprites.Player;
import castler.Sprites.Structures.AreaDamageTower;
import castler.Sprites.Structures.Barrack;
import castler.Sprites.Structures.ExploreTower;
import castler.Sprites.Structures.LineDamageTower;
import castler.Sprites.Structures.Tower;
import castler.Sprites.Structures.TowerRuin;
import castler.Sprites.Tiles.Tile;
import castler.Sprites.Units.Demolisher;
import castler.Sprites.Units.Fighter;
import castler.Sprites.Units.Mountaineer;
import castler.Sprites.Units.Unit;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.ImageIcon;

enum State {
    BLUE_TURN, RED_TURN, SIMULATION
};

public class Game {

    private int mapSize;
    private Map map;
    private Tile currentActiveTile;
    private ArrayList<Player> players;
    private Player currentPlayer;
    private int roundNumber = 1;
    private String error = "-1";
    private ShortestPathBetweenCellsBFS algo;
    private ArrayList<Explosion> explosions;
    private ArrayList<Point> deadUnits;
    private ArrayList<GameObject> gotDmg;
    private ArrayList<GameObject> doDmg;
    private static boolean finishedShooting = false;
    private String winner;
    private boolean waypointUnit = false;
    private Unit waitingforWaypoint = null;


    public Game(String p1name,String p2name,int mapSize, int swampcnt, int swampsize, int mountaincnt, int mountainsize) {
        winner=null;
        this.mapSize = mapSize;
        map = new Map(swampcnt,swampsize,mountaincnt,mountainsize, mapSize);
        players = new ArrayList<>();
        createPlayers(p1name,p2name);
        setCastles();
        currentPlayer = players.get(0);
        System.out.println(map.at(new Point(mapSize - 1, 0)).getEntity().whatami());
        algo = new ShortestPathBetweenCellsBFS();
        explosions = new ArrayList<>();
        deadUnits = new ArrayList<>();
        gotDmg = new ArrayList<>();
        doDmg = new ArrayList<>();
    }


    public Game(int mapSize,int test) {
        winner=null;
        this.mapSize = mapSize;
        map = new Map(mapSize,test);
        players = new ArrayList<>();
        createPlayers("Blue","Red");
        setCastles();
        currentPlayer = players.get(0);
        System.out.println(map.at(new Point(mapSize - 1, 0)).getEntity().whatami());
        algo = new ShortestPathBetweenCellsBFS();
    }

    public boolean validBuild() {
        ArrayList<Point> los = currentPlayer.getCastle().LOS();
        for (int i = 0; i < map.getMap().length; i++) {
            for (int j = 0; j < map.getMap().length; j++) {
                System.out.println(map.getMap()[i][j]);
                if(map.getMap()[i][j].getEntity() != null && map.getMap()[i][j].getEntity().whatami() == "explore" && map.getMap()[i][j].getEntity().getOwner().equals(currentPlayer)){
                    ArrayList<Point> los1 = map.getMap()[i][j].getEntity().LOS();
                    los.addAll(los1);
                }
            }
        }
        for (int i = 0; i < los.size(); i++) {
            if(currentActiveTile.getPos().equals(los.get(i)))return true;
        }
        return false;
    }

    public Map getMap() {
        return this.map;
    }

    public void setCurrentActiveTile(int x, int y) {
        if(waypointUnit && algo.shortestPath(map.getMap(), currentActiveTile.getPos(), new Point(x/50,y/50), (((Unit)currentActiveTile.getEntity()).whatami() == "Mountaineer")) != null){
            waitingforWaypoint = (Unit)currentActiveTile.getEntity();
            waitingforWaypoint.setWaypoint(new Point(x/50,y/50));
        }
        else if(waypointUnit){
            this.currentActiveTile = map.getTile(x, y);
            map.setSelect(x, y);
             error = "Nem szabályos útvonal!";
        }
        else{
            this.currentActiveTile = map.getTile(x, y);
            map.setSelect(x, y);
        }
        drawLOS();
        waypointUnit = false;
    }

    public int getMapSize() {
        return this.mapSize;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Tile getCurrentActiveTile() {
        return this.currentActiveTile;
    }
    
    public void sellStructure(int amount) {
        this.currentPlayer.addGold(amount);
        this.getCurrentActiveTile().setEntity(null);
    }
    
    public boolean gameEnded() {
        return winner!=null;
    }
    
    public String getWinner() {
        return winner;
    }
    
    public void checkWinner() {
        if (!gameEnded()) {
            if ((int) (getPlayers().get(0).getCastle().getHitPoint() / getPlayers().get(0).getCastle().getMaxHp() * 100)<=0) {
                winner=players.get(1).getName();
            } else if ((int) (getPlayers().get(1).getCastle().getHitPoint() / getPlayers().get(1).getCastle().getMaxHp() * 100)<=0) {
                winner=players.get(0).getName();
            }
        }
    }

    public void putTurret() {
        Boolean stat1 = map.validTileToPlaceGameObject(currentActiveTile);
        Boolean stat2 = currentPlayer.hasEnoughMoney(Tower.getPrice());
        Boolean stat3 = validBuild();
        if (stat1 && stat2 && stat3 && this.currentPlayer.addTower()) {
            currentActiveTile.setEntity(new Tower(currentActiveTile.getPos(), currentPlayer));
            currentPlayer.addGold(-1 * Tower.getPrice());
            System.out.println(currentPlayer.getGold());
        } else {
            if (!stat1) {
                error = "Nem szabályos lehelyezés!";
            } else if (!stat2) {
                error = "Nincs elég pénz!";
            } else if (!stat3) {
                error = "Csak a saját\ntérfeledre építkezhetsz!";
            } else if (!this.currentPlayer.addTower()) {
                error = "Elérted a maximum toronyszámot!";
            }
               
        }
    }

    public void putTurret(String t) {
        Boolean stat2 = currentPlayer.hasEnoughMoney(Tower.getPrice());
        Boolean stat3 = validBuild();
        if (stat2 && stat3) {
            if ("area".equals(t)) {
                currentActiveTile.setEntity(new AreaDamageTower(currentActiveTile.getPos(), currentPlayer));
                currentPlayer.addGold(-1 * Tower.getPrice());
                drawLOS();
            }
            if ("explore".equals(t)) {
                currentActiveTile.setEntity(new ExploreTower(currentActiveTile.getPos(), currentPlayer));
                currentPlayer.addGold(-1 * Tower.getPrice());
                drawLOS();
            }

            if ("line".equals(t)) {
                currentActiveTile.setEntity(new LineDamageTower(currentActiveTile.getPos(), currentPlayer /* DIRECTION TODO  */));
                currentPlayer.addGold(-1 * Tower.getPrice());
                currentActiveTile.getEntity().upgradeLvl(0);
            }
            System.out.println(currentPlayer.getGold());
        } else {
            if (!stat2) {
                error = "Nincs elég pénz!";
            } else if (!stat3) {
                error = "Csak a saját\ntérfeledre építkezhetsz!";
            }
        }

    }

    public void putBarrack() {
        Boolean stat1 = map.validTileToPlaceGameObject(currentActiveTile);
        Boolean stat2 = currentPlayer.hasEnoughMoney(Barrack.getPrice());
        Boolean stat3 = validBuild();
        if (stat1 && stat2 && stat3) {
            currentActiveTile.setEntity(new Barrack(currentActiveTile.getPos(), currentPlayer));
            currentPlayer.addGold(-1 * Barrack.getPrice());
            System.out.println(currentPlayer.getGold());
        } else {
            if (!stat1) {
                error = "Nem szabályos lehelyezés!";
            } else if (!stat2) {
                error = "Nincs elég pénz!";
            } else if (!stat3) {
                error = "Csak a saját\ntérfeledre építkezhetsz!";
            }
        }
    }

    private void createPlayers(String p1name,String p2name) {
        players.add(new Player(100, new Point(mapSize - 1, 0), castler.Sprites.Color.BLUE, p1name));
        players.add(new Player(100, new Point(0, mapSize - 1), castler.Sprites.Color.RED, p2name));
    }

    private void setCastles() {
        map.at(players.get(0).getCastle().getPos()).setEntity(players.get(0).getCastle());
        map.at(players.get(1).getCastle().getPos()).setEntity(players.get(1).getCastle());
        map.at(new Point(mapSize - 2, 1)).setEntity(new Barrack(new Point(mapSize - 2, 1), players.get(0)));
        map.at(new Point(1, mapSize - 2)).setEntity(new Barrack(new Point(1, mapSize - 2), players.get(1)));
    }

    public void nextPlayer() {
        getCurrentPlayer().addGold(100);
        currentPlayer = currentPlayer == players.get(0) ? players.get(1) : players.get(0);
        error = "-1";
        roundNumber++;
        if (roundNumber % 2 != 0) {
            roundOver();
        }
    }

    public void roundOver() {
        for (int w = 0; w < mapSize*mapSize; w++) {
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                if (map.at(new Point(i, j)).getEntity() != null) {
                    if (map.at(new Point(i, j)).getEntity().whatami() == "Fighter" || map.at(new Point(i, j)).getEntity().whatami() == "Mountaineer" || map.at(new Point(i, j)).getEntity().whatami() == "Demolisher") {
                        if ((map.at(new Point(i, j)).getEntity()).getMovedRound() < roundNumber) {
                            Point newpos = moveUnit((Unit) (map.at(new Point(i, j)).getEntity()));
                            if (map.at(newpos).getEntity() == null) {
                                if(!newpos.equals(new Point(i,j))){
                                    map.at(newpos).setEntity((map.at(new Point(i, j)).getEntity()));
                                    map.at(new Point(i, j)).setEntity(null);
                                }
                            } else if(map.at(newpos).getEntity().whatami() == "Tower" || map.at(newpos).getEntity().whatami() == "area" ||
                                    map.at(newpos).getEntity().whatami() == "explore" || map.at(newpos).getEntity().whatami() == "line"
                                    || map.at(newpos).getEntity().whatami() == "Castle") {
                                int hp = map.at(newpos).getEntity().getHitPoint();
                                map.at(newpos).getEntity().getHit(map.at(new Point(i, j)).getEntity());
                                map.at(new Point(i, j)).getEntity().getHit(hp);
                                explosions.add(new Explosion(newpos.x,newpos.y));
                                if(map.at(newpos).getEntity().getHitPoint() <= 0){
                                    map.at(newpos).setEntity(new TowerRuin(newpos, map.at(newpos).getEntity().getOwner()));
                                }
                                if(map.at(new Point(i, j)).getEntity().getHitPoint() > 0){
                                    (map.at(new Point(i, j)).getEntity()).setPos(new Point(i, j));
                                    if((map.at(new Point(i, j)).getEntity()).getOwner().equals(players.get(0))) ((Unit)map.at(new Point(i, j)).getEntity()).setWaypoint(players.get(1).getCastle().getPos());
                                    else ((Unit)map.at(new Point(i, j)).getEntity()).setWaypoint(players.get(0).getCastle().getPos());
                                }
                                //deadUnits.add(new Point(i,j));
                            }
                        }
                    }
                }
            }
        }
        }
        initAnimationForEach();
        //first units hit towers
        towersHitUnits();
        checkWinner();
    }

    public Point moveUnit(Unit object) {
        boolean found = false;
        ArrayList<Point> path = null;
        if (object.whatami() == "Mountaineer") {
            path = algo.shortestPath(map.getMap(), object.getPos(), object.getWaypoint(), true);
        } else {
            path = algo.shortestPath(map.getMap(), object.getPos(), object.getWaypoint(), false);
        }
        if(path.size() == 1){
            if(object.getOwner().equals(players.get(0))) object.setWaypoint(players.get(1).getCastle().getPos());
            else object.setWaypoint(players.get(0).getCastle().getPos());
            
          if (object.whatami() == "Mountaineer") {
            path = algo.shortestPath(map.getMap(), object.getPos(), object.getWaypoint(), true);
        } else {
            path = algo.shortestPath(map.getMap(), object.getPos(), object.getWaypoint(), false);
        }
        }
        if (path.size() <= object.getSpeed()) {
            object.setPos(path.get(path.size() - 1));
        } else {
            for (int i = object.getSpeed(); i > 0 ; i--) {
                if(map.at(path.get(i)).getEntity() == null && !found){
                    object.setPos((path.get(i)));
                    System.out.println(path.get(i));
                    found = true;
                    if(map.at(path.get(i)).getPos().x != map.at(path.get(i-1)).getPos().x) object.setDirection('y');
                    else object.setDirection('x');
                }
            }
        }
        object.setMovedRound(roundNumber);
        return object.getPos();
    }

    public void towersHitUnits(){
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                if(map.at(new Point(i,j)).getEntity() != null && (map.at(new Point(i,j)).getEntity().whatami() == "line"
                        || map.at(new Point(i,j)).getEntity().whatami() == "area")){
                    ArrayList<Point> los = map.at(new Point(i,j)).getEntity().LOS();
                    for (int k = 0; k < los.size(); k++) {
                        if(map.inMap(new Point(los.get(k))) && map.at(los.get(k)).getEntity() != null && (map.at(los.get(k)).getEntity().whatami() == "Fighter"
                                || map.at(los.get(k)).getEntity().whatami() == "Mountaineer"
                                || map.at(los.get(k)).getEntity().whatami() == "Demolisher" )){
                            if(!map.at(new Point(i,j)).getEntity().getOwner().equals(map.at(los.get(k)).getEntity().getOwner()) && map.at(new Point(i,j)).getEntity().getIsShooting() == null){
                                gotDmg.add(map.at(los.get(k)).getEntity());
                                doDmg.add(map.at(new Point(i,j)).getEntity());
                                map.at(new Point(i,j)).getEntity().shootingTo(map.at(los.get(k)).getEntity());
                                finishedShooting = false;
                                if(map.at(los.get(k)).getEntity().getHitPoint()-map.at(new Point(i,j)).getEntity().getHitPoint() <= 0){
                                    explosions.add(new Explosion(map.at(los.get(k)).getEntity().getPos().x,map.at(los.get(k)).getEntity().getPos().y));
                                    deadUnits.add(los.get(k));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void initAnimationForEach(){
        for(Tile[] tiles : this.map.getMap()){
            for(Tile tile : tiles){
                if(tile.getEntity() != null && (tile.getEntity().whatami() == "Fighter" || tile.getEntity().whatami() == "Mountaineer" || tile.getEntity().whatami() == "Demolisher"))
                tile.getEntity().initAnimation();
            }
        }
    }

    public void endAnimationForEach(){
        for(Tile[] tiles : this.map.getMap()){
            for(Tile tile : tiles){
                if(tile.getEntity() != null && (tile.getEntity().whatami() == "Fighter" || tile.getEntity().whatami() == "Mountaineer" || tile.getEntity().whatami() == "Demolisher"))
                    tile.getEntity().endAnimation();
            }
        }
        for(Tile[] tiles : this.map.getMap()){
            for(Tile tile : tiles){
                if(tile.getEntity() != null && (tile.getEntity().whatami() == "Fighter" || tile.getEntity().whatami() == "Mountaineer" || tile.getEntity().whatami() == "Demolisher"))
                {if(tile.getEntity().getHitPoint() <= 0) tile.setEntity(null);}
            }
        }
    }
    
    public void dieUnits(){
        for (int i = 0; i < deadUnits.size(); i++) {
            map.at(deadUnits.get(i)).setEntity(null);
        }
        deadUnits.clear();
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void trainFighter() {
        Tile closestFreeTile = this.map.findClosestFreeTile(currentActiveTile);
        if (currentPlayer.hasEnoughMoney(Fighter.getPrice())) {
            if (closestFreeTile == null) {
                this.setError("Nincs megfelelő hely a katonának!");
            } else {
                closestFreeTile.setEntity(new Fighter(closestFreeTile.getPos(), currentPlayer, getEnemyCastlePos()));
                currentPlayer.addGold(-1 * Fighter.getPrice());
            }
        } else {
            this.setError("Nincs elég pénzed!");
        }
    }

    public void trainDemolisher() {
        Tile closestFreeTile = this.map.findClosestFreeTile(currentActiveTile);
        if (currentPlayer.hasEnoughMoney(Demolisher.getPrice())) {
            if (closestFreeTile == null) {
                this.setError("Nincs megfelelő hely a romboló egységnek!");
            } else {
                closestFreeTile.setEntity(new Demolisher(closestFreeTile.getPos(), currentPlayer, getEnemyCastlePos()));
                currentPlayer.addGold(-1 * Demolisher.getPrice());
            }
        } else {
            this.setError("Nincs elég pénzed!");
        }
    }

    public void trainMountaineer() {
        Tile closestFreeTile = this.map.findClosestFreeTile(currentActiveTile);
        if (currentPlayer.hasEnoughMoney(Mountaineer.getPrice())) {
            if (closestFreeTile == null) {
                this.setError("Nincs megfelelő hely a hegymászónak!");
            } else {
                closestFreeTile.setEntity(new Mountaineer(closestFreeTile.getPos(), currentPlayer, getEnemyCastlePos()));
                currentPlayer.addGold(-1 * Mountaineer.getPrice());
            }
        } else {
            this.setError("Nincs elég pénzed!");
        }
    }

    public void upgrade() {
        if (currentPlayer.getCastleLevel() >= 3) {
            this.setError("Nem lehet tovább fejleszteni!");
        } else {
            if (currentPlayer.hasEnoughMoney(500)) {
                currentPlayer.upgrade();
            } else {
                this.setError("Nincs elég pénzed!");
            }
        }
    }

    private Point getEnemyCastlePos() {
        switch (this.currentPlayer.getColor()) {
            case BLUE:
                return players.get(1).getCastle().getPos();
            case RED:
                return players.get(0).getCastle().getPos();
            default:
                return new Point(0, 0); // TODO: Ez igy csak temp
        }
    }

    public void upgradeTower(){
        if(currentPlayer.hasEnoughMoney((getCurrentActiveTile().getEntity().getLvl())*30)){
            currentPlayer.addGold(-1 * (getCurrentActiveTile().getEntity().getLvl()*30));
            getCurrentActiveTile().getEntity().upgradeLvl();
        }
    }

    public void drawLOS(){
        if(currentActiveTile.getEntity() != null && currentActiveTile.getEntity().whatami() == "Castle"){
            ArrayList<Point> los = currentActiveTile.getEntity().LOS();
            for (int i = 0; i < map.getMap().length; i++) {
                for (int j = 0; j < map.getMap().length; j++) {
                    if(map.getMap()[i][j].getEntity() != null && map.getMap()[i][j].getEntity().whatami() == "explore" && map.getMap()[i][j].getEntity().getOwner().equals(currentPlayer)){
                        ArrayList<Point> los1 = map.getMap()[i][j].getEntity().LOS();
                        los.addAll(los1);
                    }
                }
            }
            if(los != null) map.drawLOS(los);
        }
        else if(currentActiveTile.getEntity() != null){
            ArrayList<Point> los = currentActiveTile.getEntity().LOS();
            if(los != null) map.drawLOS(los);
        }
    }

    private void hitCastle(GameObject unit) {
        if(unit.getOwner().equals(players.get(0))) players.get(1).getCastle().getHit(unit);
        else players.get(0).getCastle().getHit(unit);
    }

    void drawExplosions(Graphics g) {
        for (int i = 0; i < explosions.size(); i++) {
            if(players.get(0).getCastle().getPos().equals(new Point(explosions.get(i).getX(),explosions.get(i).getY())) ||
               players.get(1).getCastle().getPos().equals(new Point(explosions.get(i).getX(),explosions.get(i).getY()))){
                explosions.get(i).hurry();
            }
            explosions.get(i).nextFrame();
            explosions.get(i).draw(g, 50, 50);
            if(explosions.get(i).isOver()){
                explosions.remove(i);
            }
        }
        if(finishedShooting)
        {
        for (int i = 0; i < gotDmg.size(); i++) {
            (gotDmg.get(i)).getHit(doDmg.get(i));
        }
        gotDmg.clear();
        doDmg.clear();
        finishedShooting = false;
        dieUnits();
        }
    }

    public static boolean isFinishedShooting() {
        return finishedShooting;
    }

    public static void setFinishedShooting(boolean finishedShooting) {
        Game.finishedShooting = finishedShooting;
}

    public boolean isWaypointUnit() {
        return waypointUnit;
    }

    public void setWaypointUnit(boolean waypointUnit) {
        this.waypointUnit = waypointUnit;
    }
    
}