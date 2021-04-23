package sk.stuba.fei.uim.oop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Displayer implements ActionListener {
    Player playerx;
    public Displayer(Player player){
        playerx = player;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.printf("%d,%d\n",this.playerx.x,this.playerx.y);
    }
}
