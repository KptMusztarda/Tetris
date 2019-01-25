package me.kptmusztarda.tetris;

import javax.swing.*;

class Window extends JFrame {

    Window() {
        setTitle("Tetris");
        setLayout(null);
        setSize(500,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

}
