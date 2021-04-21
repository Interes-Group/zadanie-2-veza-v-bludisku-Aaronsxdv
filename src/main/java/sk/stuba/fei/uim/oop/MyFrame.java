package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Random;


public class MyFrame extends JFrame{
    JFrame frame;
    int wins;
    int msize_x = 11;
    int msize_y = 11;
    Maze myMaze = new Maze(msize_x, msize_y);
    Player myPlayer = new Player(1,1,myMaze);
    int fin_x = msize_x;
    int fin_y = msize_y;
    public MyFrame(int win_count){
        this.wins = win_count;
        this.frame = new JFrame("Maze runner v0.1");
    }

    void call() {

        frame.setBounds(0, 0, 475, 600);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon image = new ImageIcon("src/logo.png");
        frame.setIconImage(image.getImage());
        JButton b1=new JButton("\uD83E\uDC15"); //UP
        JButton b2=new JButton("\uD83E\uDC17"); //DOWN
        JButton b3=new JButton("\uD83E\uDC14"); //LEFT
        JButton b4=new JButton("\uD83E\uDC16"); //RIGHT
        JButton reset =new JButton("RESET");
        JButton disp=new JButton("PLAYER");
        reset.setBounds(70,470,95,30);
        b1.setBounds(170,470,95,30);
        b2.setBounds(170,510,95,30);
        b3.setBounds(70,510,95,30);
        b4.setBounds(275,510,95,30);
        disp.setBounds(700,180,200,30);


        JPanel panel = new JPanel() {

            Graphics2D g2;

            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g2 = (Graphics2D) g;
                g2.setColor(Color.WHITE);
                g2.setStroke(new BasicStroke(3));
                g2.drawRect(0,0,450,450);
                for (int i = 0; i < msize_x; i++) {
                    for (int j = 0; j < msize_y; j++) {
                        Cell cell = myMaze.cells[i][j];
                        g2.drawRect((i*40)+10,(j*40)+10,40,40);
                    }
                }
                g2.setColor(Color.BLACK);
                for (int n = 0; n < msize_x; n++) {
                    for (int m = 0; m < msize_y; m++) {
                        Cell cell = myMaze.cells[n][m];
                        if (cell.hasRightWall()) {
                            g2.drawLine((n*40)+50,(m*40)+10 , (n*40)+50,(m*40)+50);
                        }
                        if (cell.hasDownWall()) {
                            g2.drawLine((n*40)+10,(m*40)+50, (n*40)+50,(m*40)+50);
                        }
                        if (cell.hasLeftWall()) {
                            g2.drawLine((n*40)+10,(m*40)+10, (n*40)+10,(m*40)+50);
                        }
                        if (cell.hasUpWall()) {
                            g2.drawLine((n*40)+10,(m*40)+10, (n*40)+50,(m*40)+10);
                        }

                    }
                }
                g2.setColor(Color.RED);
                g2.fillRect((40*fin_x)-20,(40*fin_y)-20,20,20);
                g2.setColor(Color.YELLOW);
                g2.fillRect((40*myPlayer.x)-20,(40*myPlayer.y)-20,20,20);


            }

        };

        b1.addActionListener((ActionListener) new DirectionButton(0,-1,myPlayer));
        b2.addActionListener((ActionListener) new DirectionButton(0,1,myPlayer));
        b3.addActionListener((ActionListener) new DirectionButton(-1,0,myPlayer));
        b4.addActionListener((ActionListener) new DirectionButton(1,0,myPlayer));
        reset.addActionListener((ActionListener) new ResetButton(this));
        //disp.addActionListener((ActionListener) new Displayer(myPlayer));
        frame.add(reset);
        frame.add(b1);
        frame.add(b2);
        frame.add(b3);
        frame.add(b4);

        frame.setFocusable(true);
        //panel.setFocusable(false);
        b1.setFocusable(false);
        b2.setFocusable(false);
        b3.setFocusable(false);
        b4.setFocusable(false);
        reset.setFocusable(false);
        frame.add(panel, BorderLayout.CENTER);
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();

                switch (keyCode){
                    case KeyEvent.VK_UP:
                        new ArrowKeys(0,-1,myPlayer);
                        //System.out.println("Up key!");
                        break;
                    case KeyEvent.VK_DOWN:
                        new ArrowKeys(0,1,myPlayer);
                        //System.out.println("Down key!");
                        break;
                    case KeyEvent.VK_RIGHT:
                        new ArrowKeys(1,0,myPlayer);
                        //System.out.println("Up key!");
                        break;
                    case KeyEvent.VK_LEFT:
                        new ArrowKeys(-1,0,myPlayer);
                        //System.out.println("Down key!");
                        break;
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        frame.setVisible(true);
        while(myPlayer.x != fin_x || myPlayer.y != fin_y){
            frame.repaint();
            if(myPlayer.x == fin_x && myPlayer.y == fin_y){
                //System.out.printf("\nYOU WIN!Win count:%d\n",this.wins+1);
                myMaze = new Maze(msize_x, msize_y);
                myPlayer = new Player(1,1,myMaze);
                frame.repaint();
            }
        }


    }


}