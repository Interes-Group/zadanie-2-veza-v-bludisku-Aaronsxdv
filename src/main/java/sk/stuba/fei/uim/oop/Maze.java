package sk.stuba.fei.uim.oop;
import java.util.*;

public class Maze {

    int sizeX, sizeY;
    Cell[][] cells;
    List<Cell> backtracker = new ArrayList<>();

    public Maze(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.cells = new Cell[sizeX][sizeY];
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++)
                this.cells[i][j] = new Cell(this, i, j);
        }
        generate();
    }

    private void generate() {
        Cell current = this.cells[0][0];
        current.visit();
        boolean hasUnvisited = hasUnvisited();
        while (hasUnvisited) {
            current = current.pickNext();
            if (!current.isVisited()) {
                current.visit();
                this.backtracker.add(current);
                hasUnvisited = hasUnvisited();
            }
        }
    }

    private boolean hasUnvisited() {
        for (int i = 0; i < this.sizeX; i++) {
            for (int j = 0; j < this.sizeY; j++) {
                if (!this.cells[i][j].isVisited()) {
                    return true;

                }
            }
        }
        return false;
    }

    public Cell backtrack() {
        if (this.backtracker.size() == 0) return null;
        Cell cell = this.backtracker.get(this.backtracker.size() - 1);
        this.backtracker.remove(cell);
        return cell;
    }

    public Cell getCell(int x, int y) {
        return this.cells[x][y];
    }

}

class Cell {

    private Maze maze;
    int x, y;
    private boolean visited = false;
    // top, right, bottom. left
    boolean[] walls = new boolean[]{true, true, true, true};

    public Cell(Maze maze, int x, int y) {
        this.maze = maze;
        this.x = x;
        this.y = y;
    }

    public Cell pickNext() {
        List<Cell> neighbors = new ArrayList<>();
        if (this.y != 0){
            neighbors.add(this.maze.getCell(this.x, this.y - 1));
        }
        else{
            neighbors.add(null);
        };
        if (this.x != this.maze.sizeX - 1){
            neighbors.add(this.maze.getCell(this.x + 1, this.y));
        }
        else{
            neighbors.add(null);
        };
        if (this.y != this.maze.sizeY - 1){
            neighbors.add(this.maze.getCell(this.x, this.y + 1));
        }
        else{
            neighbors.add(null);
        };
        if (this.x != 0){
            neighbors.add(this.maze.getCell(this.x - 1, this.y));
        }
        else{
            neighbors.add(null);
        };

        boolean hasUnvisitedNeighbor = false;
        /*for (Cell c : neighbors) {
            if (c == null) continue;
            if (!c.isVisited()) hasUnvisitedNeighbor = true;
        }*/
        for (Cell neighbor : neighbors) {
            if (neighbor == null) {
                continue;
            }
            if (!neighbor.isVisited()) {
                hasUnvisitedNeighbor = true;
                break;
            }
        }

        if (hasUnvisitedNeighbor) {

            int random = (int) Math.floor(Math.random() * 4);
            Cell next = neighbors.get(random);
            while (next == null || next.isVisited()) {
                random = (int) Math.floor(Math.random() * 4);
                next = neighbors.get(random);
            }
            this.breakWall(random);
            next.breakWall((random + 2) % 4);

            /*System.out.printf("Neighbors of x:%d,y:%d :\n",this.x,this.y);
            for(int i = 0;i<4;i++){
                if(neighbors.get(i) == null){
                    System.out.printf("Neighbor %d is null\n",i);
                }
                else{
                    System.out.printf("Neighbor %d is x:%d,y:%d\n",i,neighbors.get(i).x,neighbors.get(i).y);
                }

            }
            System.out.print("\n");

            System.out.printf("Breaking a wall between 1 - x:%d,y:%d,wall_n: %d and 2 - x:%d,y:%d,wall_n: %d\n",this.x,this.y,random,next.x,next.y,((random + 2) % 4));*/
            return next;
        } else return this.maze.backtrack();
    }

    public void breakWall(int wall) {
        this.walls[wall] = false;
    }

    public void visit() {
        this.visited = true;
    }

    public boolean isVisited() {
        return this.visited;
    }

    public boolean hasUpWall(){
        return this.walls[0];
    }
    public boolean hasRightWall(){
        return this.walls[1];
    }
    public boolean hasDownWall(){
        return this.walls[2];
    }
    public boolean hasLeftWall(){
        return this.walls[3];
    }


}

