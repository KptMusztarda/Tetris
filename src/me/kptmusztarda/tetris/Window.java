package me.kptmusztarda.tetris;

import javax.swing.*;

public class Window extends JFrame {

    public Window() {
        setTitle("Tetris");
        setLayout(null);
        setSize(400,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

}
