package castler;

import castler.Sprites.Tiles.Tile;
import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;

public class ShortestPathBetweenCellsBFS {

    private static class Cell  {
        int x;
        int y;
        int dist;  	//distance
        int weight;
        Cell prev;  //parent cell in the path

        Cell(int x, int y, int dist, Cell prev, int weight) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.prev = prev;
            this.weight = weight;
        }
        
        @Override
        public String toString(){
        	return "(" + x + "," + y + ")";
        }
    }
    
    //BFS, Time O(n^2), Space O(n^2)
    public ArrayList<Point> shortestPath(Tile[][] matrix, Point start, Point end, Boolean climber) {
        int tolerance = 1;
        if(climber) tolerance = 10;
        
		int sx = start.x, sy = start.y;
		int dx = end.x, dy = end.y;
    	//if start or end value is 0, return with error
           if(((sx == 0 && sy == matrix.length-1) || (sx == matrix.length-1 && sy == 0)) && ((dx == 0 && dy == matrix.length-1) || (dx == matrix.length-1 && dy == 0))){
               tolerance = tolerance;
           }
           else if ((matrix[sx][sy].getModifier() > tolerance && !climber) || (matrix[dx][dy].getModifier() >= 42069 && !climber))
		   return null;
	   
	    int m = matrix.length;
	    int n = matrix[0].length;	    
        Cell[][] cells = new Cell[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j].getModifier() <= tolerance  || (i == dx && j == dy) || (i == sx && j == sy) || (climber && matrix[i][j].getModifier() >= 42069)) {
                    cells[i][j] = new Cell(i, j, Integer.MAX_VALUE, null,1);
                    
                }
            }
        }
       
        LinkedList<Cell> queue = new LinkedList<>();       
        Cell src = cells[sx][sy];
        src.dist = 0;
        queue.add(src);
        Cell dest = null;
        Cell p;
        while ((p = queue.poll()) != null) {
        	//find destination 
            if (p.x == dx && p.y == dy) { 
                dest = p;
                break;
            }
            visit(cells, queue, p.x - 1, p.y, p);
            visit(cells, queue, p.x + 1, p.y, p);
            visit(cells, queue, p.x, p.y - 1, p);
            visit(cells, queue, p.x, p.y + 1, p);
        }

        if (dest == null) {
            return null;
        } else {
            LinkedList<Cell> path = new LinkedList<>();
            ArrayList<Point> path2 = new ArrayList<>();
            Point a;
            p = dest;
            do {
                a = new Point(p.x,p.y);
                path2.add(a);
                path.addFirst(p);
            } while ((p = p.prev) != null);
            reverseArrayList(path2);
            return path2;
        }
    }

    static void visit(Cell[][] cells, LinkedList<Cell> queue, int x, int y, Cell parent) { 
        if (x < 0 || x >= cells.length || y < 0 || y >= cells[0].length || cells[x][y] == null) {
            return;
        }
        
        int dist = parent.dist + 1;
        Cell p = cells[x][y];
        if (dist < p.dist) {
            p.dist = dist;
            p.prev = parent;
            queue.add(p);
        }
    }

    public ArrayList<Point> reverseArrayList(ArrayList<Point> alist)
    {
        for (int i = 0; i < alist.size() / 2; i++) {
            Point temp = alist.get(i);
            alist.set(i, alist.get(alist.size() - i - 1));
            alist.set(alist.size() - i - 1, temp);
        }
 
        return alist;
    }
}
