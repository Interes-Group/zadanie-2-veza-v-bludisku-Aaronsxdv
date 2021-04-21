package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Random;

public class ResetButton implements ActionListener {

    MyFrame myframe;
    public ResetButton(MyFrame myframe){
        this.myframe = myframe;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        this.myframe.myMaze = new Maze(11,11);
        this.myframe.myPlayer = new Player(1,1,this.myframe.myMaze);
        //this.myframe.call();


    }
}
