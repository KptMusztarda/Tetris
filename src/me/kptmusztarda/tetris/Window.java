package me.kptmusztarda.tetris;

import javax.swing.*;
import java.awt.*;

class Window extends JFrame {

    static final int MARGIN_TOP = 40;
    static final int MARGIN_LEFT = 40;

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
        Rectangle bounds2 = getContentPane().getComponent(1).getBounds();

//        System.out.println(bounds.x);
//        System.out.println(bounds.y);
//        System.out.println(bounds.height);
//        System.out.println(bounds.width);


        Insets insets = getInsets();

        //System.out.println(getWidth() + " " + getHeight() + " " + insets.top + " " + insets.right + " " + insets.bottom + " " + insets.left);

        setSize(insets.left + insets.right + 3*bounds.x + bounds.width + bounds2.width, insets.top + insets.bottom + 2*bounds.y + bounds.height);
    }
}
