package castler;

import castler.Sprites.Structures.Tower;
import castler.Sprites.Tiles.Grass;
import castler.Sprites.Tiles.Mountain;
import castler.Sprites.Tiles.Swamp;
import castler.Sprites.Tiles.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author marton552
 */
public class Map {

    private int numOfLakes;
    private int sizeOfLakes;
    private int sizeOfMountains;
    private int numOfMountains;
    private static Tile[][] map;
    private int size;
    Random r;
    private String line = "";
    private ShortestPathBetweenCellsBFS algo;

    public Map(int numOfLakes, int sizeOfLakes, int numOfMountains, int sizeOfMountains, int size) {
        this.numOfLakes = numOfLakes; //number between 0 and 5
        this.sizeOfLakes = sizeOfLakes; //number between 0 and 100
        this.sizeOfMountains = sizeOfMountains;
        this.numOfMountains = numOfMountains;
        this.size = size;
        map = new Tile[size][size];
        r = new Random();
        algo = new ShortestPathBetweenCellsBFS();
        generate();
    }

    public Map(int size, int test) {
        this.size = size;
        map = new Tile[size][size];
        algo = new ShortestPathBetweenCellsBFS();
        generate();
    }

    private void generate() {
        do {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    map[i][j] = (new Grass(null, new Point(i, j)));
                }
            }
            for (int i = 0; i < numOfLakes; i++) {
                int x = r.nextInt(size);
                int y = r.nextInt(size);
                terraformLake(x, y, (double) sizeOfLakes, 0);
            }
            for (int i = 0; i < numOfMountains; i++) {
                int x = r.nextInt(size);
                int y = r.nextInt(size);
                terraformMountain(x, y, (double) sizeOfMountains, 0);
            }
        } while (!isValidPath(new Point(0, size - 1), new Point(size - 1, 0)));
    }

    void terraformLake(int x, int y, double percent, int nth) {
        if (map[x][y].getVisited() == false) {
            map[x][y] = null;
            map[x][y] = new Swamp(null, new Point(x, y));
            map[x][y].setVisited(true);
            if (x - 1 >= 0) {
                int rand = r.nextInt(100);
                if (Math.floor(percent) > rand) {
                    terraformLake(x - 1, y, percent / (nth + 1), nth + 1);
                }
            }
            if (x + 1 < size) {
                int rand = r.nextInt(100);
                if (Math.floor(percent) > rand) {
                    terraformLake(x + 1, y, percent / (nth + 1), nth + 1);
                }
            }
            if (y - 1 >= 0) {
                int rand = r.nextInt(100);
                if (Math.floor(percent) > rand) {
                    terraformLake(x, y - 1, percent / (nth + 1), nth + 1);
                }
            }
            if (y + 1 < size) {
                int rand = r.nextInt(100);
                if (Math.floor(percent) > rand) {
                    terraformLake(x, y + 1, percent / (nth + 1), nth + 1);
                }
            }
        }
    }

    void terraformMountain(int x, int y, double percent, int nth) {
        if (map[x][y].getVisited() == false) {
            map[x][y] = null;
            map[x][y] = new Mountain(null, new Point(x, y));
            map[x][y].setVisited(true);
            if (x - 1 >= 0) {
                int rand = r.nextInt(100);
                if (Math.floor(percent) > rand) {
                    terraformMountain(x - 1, y, percent / (nth + 1), nth + 1);
                }
            }
            if (x + 1 < size) {
                int rand = r.nextInt(100);
                if (Math.floor(percent) > rand) {
                    terraformMountain(x + 1, y, percent / (nth + 1), nth + 1);
                }
            }
            if (y - 1 >= 0) {
                int rand = r.nextInt(100);
                if (Math.floor(percent) > rand) {
                    terraformMountain(x, y - 1, percent / (nth + 1), nth + 1);
                }
            }
            if (y + 1 < size) {
                int rand = r.nextInt(100);
                if (Math.floor(percent) > rand) {
                    terraformMountain(x, y + 1, percent / (nth + 1), nth + 1);
                }
            }
        }
    }

    public Boolean isValidPath(Point start, Point end) {
        ArrayList<Point> res = algo.shortestPath(map, start, end, false);
        //System.out.println(res);
        return (res != null);
    }

    public void setSelect(int x, int y) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[i][j].getSelected()) {
                    if (map[i][j].toString().equals("G")) {
                        map[i][j].setImg(new ImageIcon(getClass().getResource("/images/grass.png")).getImage());
                    } else if (map[i][j].toString().equals("M")) {
                        map[i][j].setImg(new ImageIcon(getClass().getResource("/images/mountain.png")).getImage());
                    } else if (map[i][j].toString().equals("S")) {
                        map[i][j].setImg(new ImageIcon(getClass().getResource("/images/swamp.png")).getImage());
                    }
                }
            }
        }
        Tile s = getTile(x, y);
        s.setSelected(true);
        if (s.toString().equals("G")) {
            s.setImg(new ImageIcon(getClass().getResource("/images/grass1.png")).getImage());
        } else if (s.toString().equals("M")) {
            s.setImg(new ImageIcon(getClass().getResource("/images/mountain1.png")).getImage());
        } else if (s.toString().equals("S")) {
            s.setImg(new ImageIcon(getClass().getResource("/images/swamp1.png")).getImage());
        }
        drawLOS(null);
    }

    public void drawLOS(ArrayList<Point> los) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[i][j].getRed() && !map[i][j].getSelected()) {
                    if (map[i][j].toString().equals("G")) {
                        map[i][j].setImg(new ImageIcon(getClass().getResource("/images/grass.png")).getImage());
                    } else if (map[i][j].toString().equals("M")) {
                        map[i][j].setImg(new ImageIcon(getClass().getResource("/images/mountain.png")).getImage());
                    } else if (map[i][j].toString().equals("S")) {
                        map[i][j].setImg(new ImageIcon(getClass().getResource("/images/swamp.png")).getImage());
                    }
                }
            }
        }
        if (los != null) {
            for (int i = 0; i < los.size(); i++) {
                if (inMap(los.get(i))) {
                    Tile s = map[los.get(i).x][los.get(i).y];
                    //System.out.println(los.get(i));
                    s.setRed(true);
                    if (s.toString().equals("G")) {
                        s.setImg(new ImageIcon(getClass().getResource("/images/grass2.png")).getImage());
                    } else if (s.toString().equals("M")) {
                        s.setImg(new ImageIcon(getClass().getResource("/images/mountain2.png")).getImage());
                    } else if (s.toString().equals("S")) {
                        s.setImg(new ImageIcon(getClass().getResource("/images/swamp2.png")).getImage());
                    }
                }
            }
        }
    }

    public void draw(Graphics g, Game game) {
        for (Tile[] subtiles : map) {
            for (Tile t : subtiles) {
                t.draw(g);
            }
        }
        for (Tile[] subtiles : map) {
            for (Tile t : subtiles) {
                t.drawLines(g,game);
            }
        }
    }

    public void animatedDraw(Graphics g){
        for (Tile[] subtiles : map) {
            for (Tile t : subtiles) {
                t.animatedDraw(g);
            }
        }
    }

    public void drawWithoutUnits(Graphics g){
        for (Tile[] subtiles : map) {
            for (Tile t : subtiles) {
                t.drawWithoutUnit(g);
            }
        }
    }

    public static Tile[][] getMap() {
        return map;
    }

    public Tile getTile(int x, int y) {
        return map[x / 50][y / 50];
    }

    public boolean existsPathBetweenCastles(Tile tile) {
        double mod = map[tile.getPos().x][tile.getPos().y].getModifier();
        map[tile.getPos().x][tile.getPos().y].setModifier(100);
        Boolean res = isValidPath(new Point(0, size - 1), new Point(size - 1, 0));
        map[tile.getPos().x][tile.getPos().y].setModifier(mod);
        return res;
    }

    public boolean validTileToPlaceGameObject(Tile tile) {
        return tile.getEntity() == null && existsPathBetweenCastles(tile);
    }

    public Tile findClosestFreeTile(Tile source) {
        Tile closest = null;
        Point dest = null;
        if (source.getEntity().getOwner().equals(map[0][size - 1].getEntity().getOwner())) {
            dest = new Point(size - 1, 0);
        } else {
            dest = new Point(0, size - 1);
        }
        int minDist = Integer.MAX_VALUE;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                Point start = new Point((source.getPos().x + i), (source.getPos().y + j));
                if (inMap(start)) {
                    Tile tile = map[(source.getPos().x + i)][(source.getPos().y + j)];
                    ArrayList<Point> path = algo.shortestPath(map, start, dest, false);
                    int length;
                    if (path == null) {
                        length = -1;
                    } else {
                        length = path.size();
                    }
                    if (tile.getEntity() == null && tile.getType() == "Grass" && length != -1 && length < minDist) {
                        closest = tile;
                        minDist = length;
                    }
                }
            }
        }

        for (Tile[] tiles : map) {
            for (Tile tile : tiles) {

            }
        }
        return closest;
    }

    public Tile at(Point pos) {
        return map[pos.x][pos.y];
    }

    public Boolean inMap(Point p) {
        return (p.x >= 0 && p.x < size && p.y >= 0 && p.y < size);
    }
}
