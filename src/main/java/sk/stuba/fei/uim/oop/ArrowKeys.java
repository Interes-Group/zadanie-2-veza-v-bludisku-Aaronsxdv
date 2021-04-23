package sk.stuba.fei.uim.oop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArrowKeys{
    int xm;
    int ym;
    Player playerx;
    public ArrowKeys(int x,int y,Player player){
        this.xm = x;
        this.ym = y;
        this.playerx = player;
        if(this.xm == 1){
            if(!(this.playerx.maze.cells[this.playerx.x-1][this.playerx.y-1].walls[1]) && !(this.playerx.maze.cells[this.playerx.x+this.xm-1][this.playerx.y+this.ym-1].walls[3])){
                //System.out.printf("First - %d,%d%s, Second - %d,%d,%s",this.playerx.x-1,this.playerx.y-1,this.playerx.maze.cells[this.playerx.x-1][this.playerx.y-1].walls[1],this.playerx.x+this.xm-1,this.playerx.y+this.ym-1,this.playerx.maze.cells[this.playerx.x+this.xm-1][this.playerx.y+this.ym-1].walls[3]);
                this.playerx.x += this.xm;
                this.playerx.y += this.ym;
            }
        }
        else if(this.xm == -1){
            if(!(this.playerx.maze.cells[this.playerx.x-1][this.playerx.y-1].walls[3])){
                if(!(this.playerx.maze.cells[this.playerx.x+this.xm-1][this.playerx.y+this.ym-1].walls[1])){
                    //System.out.printf("First - %d,%d%s, Second - %d,%d,%s", this.playerx.x - 1, this.playerx.y - 1, this.playerx.maze.cells[this.playerx.x - 1][this.playerx.y - 1].walls[0], this.playerx.x + this.xm - 1, this.playerx.y + this.ym - 1, this.playerx.maze.cells[this.playerx.x + this.xm - 1][this.playerx.y + this.ym - 1].walls[2]);
                    this.playerx.x += this.xm;
                    this.playerx.y += this.ym;
                }
            }

        }
        else if(this.ym == 1){
            if(!(this.playerx.maze.cells[this.playerx.x-1][this.playerx.y-1].walls[2]) && !(this.playerx.maze.cells[this.playerx.x+this.xm-1][this.playerx.y+this.ym-1].walls[0])){
                //System.out.printf("First - %d,%d%s, Second - %d,%d,%s",this.playerx.x-1,this.playerx.y-1,this.playerx.maze.cells[this.playerx.x-1][this.playerx.y-1].walls[2],this.playerx.x+this.xm-1,this.playerx.y+this.ym-1,this.playerx.maze.cells[this.playerx.x+this.xm-1][this.playerx.y+this.ym-1].walls[0]);
                this.playerx.x += this.xm;
                this.playerx.y += this.ym;
            }
        }
        else if(this.ym == -1){
            if (!(this.playerx.maze.cells[this.playerx.x - 1][this.playerx.y - 1].walls[0])){
                if(!(this.playerx.maze.cells[this.playerx.x + this.xm - 1][this.playerx.y + this.ym - 1].walls[2])){
                    //System.out.printf("First - %d,%d%s, Second - %d,%d,%s",this.playerx.x-1,this.playerx.y-1,this.playerx.maze.cells[this.playerx.x-1][this.playerx.y-1].walls[3],this.playerx.x+this.xm-1,this.playerx.y+this.ym-1,this.playerx.maze.cells[this.playerx.x+this.xm-1][this.playerx.y+this.ym-1].walls[1]);
                    this.playerx.x += this.xm;
                    this.playerx.y += this.ym;
                }
            }

        }
    }
}