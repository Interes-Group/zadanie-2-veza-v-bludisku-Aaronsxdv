package sk.stuba.fei.uim.oop;

import javax.swing.*;

public class Player {
    int x;
    int y;
    Maze maze;
    Player(int xi, int yi,Maze maze){
        this.x = xi;
        this.y = yi;
        this.maze = maze;
    }
}
