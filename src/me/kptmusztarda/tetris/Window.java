package me.kptmusztarda.tetris;

import javax.swing.*;
import java.awt.*;

class Window extends JFrame {

    Window() {
        setTitle("Tetris");
        setLayout(null);
        //setSize(500,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

    }

    void calculateAndSetSize() {

        Rectangle bounds = getContentPane().getComponent(0).getBounds();

//        System.out.println(bounds.x);
//        System.out.println(bounds.y);
//        System.out.println(bounds.height);
//        System.out.println(bounds.width);


        Insets insets = getInsets();

        //System.out.println(getWidth() + " " + getHeight() + " " + insets.top + " " + insets.right + " " + insets.bottom + " " + insets.left);

        setSize(insets.left + insets.right + 2*bounds.x + bounds.width, insets.top + insets.bottom + 2*bounds.y + bounds.height);
    }
}
