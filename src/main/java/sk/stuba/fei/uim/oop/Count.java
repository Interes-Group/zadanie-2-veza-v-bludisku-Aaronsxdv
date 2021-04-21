package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Count implements ActionListener {
    int countx;
    JLabel labelx;
    public Count(JLabel label,int count){
        this.labelx = label;
        this.countx = count;

    }
    @Override
    public void actionPerformed(ActionEvent e){
        this.countx ++;
        this.labelx.setText(String.valueOf(this.countx));
    }
}

