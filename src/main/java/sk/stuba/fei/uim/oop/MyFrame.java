package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;


public class MyFrame extends JFrame{
    boolean on_player = false;
    JFrame frame;
    int wins;
    int msize_x = 11; //Maze size
    int msize_y = 11;
    Maze myMaze = new Maze(msize_x, msize_y);
    Player myPlayer = new Player(1,1,myMaze);
    int fin_x = msize_x;  //Maze exit point
    int fin_y = msize_y;
    ArrayList<Cell> avail_pos = hovering(myPlayer);
    JButton b1=new JButton("\uD83E\uDC15"); //UP
    JButton b2=new JButton("\uD83E\uDC17"); //DOWN
    JButton b3=new JButton("\uD83E\uDC14"); //LEFT
    JButton b4=new JButton("\uD83E\uDC16"); //RIGHT
    JLabel win = new JLabel();                  //WINS
    public MyFrame(int win_count){
        this.wins = win_count;
        this.frame = new JFrame("Maze runner v0.1");
        call();
    }
    void init_buttons(JButton b1,JButton b2,JButton b3,JButton b4){
        b1.addActionListener((ActionListener) new DirectionButton(0,-1,this.myPlayer));
        b2.addActionListener((ActionListener) new DirectionButton(0,1,this.myPlayer));
        b3.addActionListener((ActionListener) new DirectionButton(-1,0,this.myPlayer));
        b4.addActionListener((ActionListener) new DirectionButton(1,0,this.myPlayer));
    }
    void init_label(JLabel win,int x){
        win.setText("Wins: "+x);
    }
    public ArrayList<Cell> side_check(ArrayList<Cell> ans,int side,Player player){
        Cell cell = player.maze.cells[player.x-1][player.y-1];
        switch(side){
            case 0:
                for(int i = player.y-1;i>0;i--){ //UP
                    if(cell.neighbors.size() > 0 && cell.neighbors.get(0) != null && !cell.hasUpWall() && !cell.neighbors.get(0).hasDownWall()){
                        ans.add(cell.neighbors.get(0));
                        cell = cell.neighbors.get(0);
                    }
                }
                break;
            case 1:
                for(int i = player.x-1;i<player.maze.sizeX;i++){ //RIGHT
                    if(cell.neighbors.size() > 0 && cell.neighbors.get(1) != null && !cell.hasRightWall() && !cell.neighbors.get(1).hasLeftWall()){
                        ans.add(cell.neighbors.get(1));
                        cell = cell.neighbors.get(1);
                    }
                }
                break;
            case 2:
                for(int i = player.y-1;i<player.maze.sizeY;i++){ //DOWN
                    if(cell.neighbors.size() > 0 && cell.neighbors.get(2) != null && !cell.hasDownWall() && !cell.neighbors.get(2).hasUpWall()){
                        ans.add(cell.neighbors.get(2));
                        cell = cell.neighbors.get(2);
                    }
                }
                break;
            case 3:
                for(int i = player.x-1;i>=0;i--){ //LEFT
                    if(cell.neighbors.size() > 0 && cell.neighbors.get(3) != null && !cell.hasLeftWall() && !cell.neighbors.get(3).hasRightWall()){
                        ans.add(cell.neighbors.get(3));
                        cell = cell.neighbors.get(3);
                    }
                }
                break;
        }
        return ans;
    }
    public ArrayList<Cell> hovering(Player player){     //AVAILIABLE POSITIONS
        ArrayList<Cell> ans = new ArrayList<Cell>();
        ans = side_check(ans,0,player);
        ans = side_check(ans,1,player);
        ans = side_check(ans,2,player);
        ans = side_check(ans,3,player);
        return ans;
    }
    void call() {


        frame.setBounds(0, 0, 475, 600);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon image = new ImageIcon("src/logo.png");
        frame.setIconImage(image.getImage());

        JButton reset =new JButton("RESET");
        JButton disp=new JButton("PLAYER");
        reset.setBounds(70,470,95,30);
        win.setBounds(275,470,95,30);
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

                //g2.fillRect((avail_pos.get(0).x),avail_pos.get(0).y,20,20);
                if(on_player){
                    g2.setColor(Color.BLUE);
                    avail_pos = hovering(myPlayer);
                    if(avail_pos.size() > 0){
                        for(int i=0;i<avail_pos.size();i++){
                            if(avail_pos.get(i) != null){
                                int posx = 40*(avail_pos.get(i).x);
                                int posy = 40*(avail_pos.get(i).y);
                                if(avail_pos.get(i).isSelected){
                                    g2.setColor(Color.CYAN);
                                    g2.fillRect(posx+20,posy+20,20,20);
                                    g2.setColor(Color.BLUE);
                                }
                                else{
                                    g2.fillRect(posx+20,posy+20,20,20);
                                }


                            }

                        }
                    }

                }

                g2.setColor(Color.YELLOW);
                g2.fillRect((40*myPlayer.x)-20,(40*myPlayer.y)-20,20,20);


            }

        };
        init_label(this.win,0);
        init_buttons(this.b1,this.b2,this.b3,this.b4);
        reset.addActionListener((ActionListener) new ResetButton(this));

        panel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) { //when clicked on the component
                avail_pos = hovering(myPlayer);
                int x = e.getX();
                int y = e.getY();
                if ((x >= (40 * myPlayer.x) - 20 && x <= (40 * myPlayer.x)) && (y >= (40 * myPlayer.y) - 20 && y <= (40 * myPlayer.y))) { //Player coordinates
                    on_player = !on_player;
                    panel.repaint();

                }

                for(int i=0;i<avail_pos.size();i++){

                    if(avail_pos.get(i) != null && on_player){
                        if((x >= 40*(avail_pos.get(i).x)+20 && x <= 40*(avail_pos.get(i).x)+40) && (y >= 40*(avail_pos.get(i).y)+20 && y<=40*(avail_pos.get(i).y)+40)){
                            myPlayer.x = (avail_pos.get(i).x)+1;
                            myPlayer.y = (avail_pos.get(i).y)+1;
                            panel.repaint();

                        }

                    }
                }

            }

        });

        panel.addMouseMotionListener(new MouseAdapter() {

            public void mouseMoved(MouseEvent e) {

                super.mouseMoved(e);
                int x=e.getX();
                int y=e.getY();
                int counter = 0;
                for(int i=0;i<avail_pos.size();i++){

                    if(avail_pos.get(i) != null && on_player){

                        if((x >= 40*(avail_pos.get(i).x)+20 && x <= 40*(avail_pos.get(i).x)+40) && (y >= 40*(avail_pos.get(i).y)+20 && y<=40*(avail_pos.get(i).y)+40)){

                            avail_pos.get(i).isSelected = true;
                            //panel.repaint();

                        }


                    }

                }
                for(int i=0;i<avail_pos.size();i++){

                    if(avail_pos.get(i) != null && on_player){

                        if(!((x >= 40*(avail_pos.get(i).x)+20 && x <= 40*(avail_pos.get(i).x)+40) && (y >= 40*(avail_pos.get(i).y)+20 && y<=40*(avail_pos.get(i).y)+40))){
                            counter++;
                        }
                    }
                }
                if(counter == avail_pos.size()){
                    for(int i=0;i<avail_pos.size();i++){

                        avail_pos.get(i).isSelected = false;

                    }
                }
            }


        });
        frame.add(win);
        frame.add(reset);
        frame.add(b1);
        frame.add(b2);
        frame.add(b3);
        frame.add(b4);

        frame.setFocusable(true);
        panel.setFocusable(true);
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
                myMaze = new Maze(msize_x, msize_y);
                myPlayer = new Player(1,1,myMaze);
                init_buttons(this.b1,this.b2,this.b3,this.b4);
                this.wins++;
                init_label(this.win,this.wins);

            }
        }


    }


}