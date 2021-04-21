package sk.stuba.fei.uim.oop;
import javax.sound.sampled.AudioInputStream;
import javax.swing.*;
import java.awt.*;


import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.applet.*;
import java.net.*;
import java.util.*;

public class Main extends JFrame{
    public static void main(String[] args) throws InterruptedException {
        MyFrame myframe = new MyFrame(0);
        myframe.call();
        myframe.dispose();
        MyFrame myframe2 = new MyFrame(0);
        myframe2.call();


    }

}