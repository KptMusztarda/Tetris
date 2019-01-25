package me.kptmusztarda.tetris;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    public static final int SQUARES_HORIZONTALLY = 10;  //poziomo
    public static final int SQUARES_VERTICALLY = 20;    //pionowo

    public static final boolean DEBUG = true;

    public static void main(String[] args) {
        Window window = new Window();
        MainPanel mainPanel = new MainPanel();
        window.setContentPane(mainPanel);

        Bricks bricks = new Bricks();

        Renderer renderer = new Renderer(SQUARES_HORIZONTALLY, SQUARES_VERTICALLY);
        renderer.addBricks(bricks);
        MyKeyListener keyListener = new MyKeyListener() {
            @Override
            protected void repaint() {
                renderer.repaint();
            }
        };
        keyListener.addBricks(bricks);
        window.addKeyListener(keyListener);
        mainPanel.add(renderer);
        renderer.repaint();

        bricks.createNew(Brick.TYPE_Z);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Tick");
                bricks.move(bricks.getBricks().size() - 1, Bricks.MOVE_DOWN);
                renderer.repaint();
            }
        }, 1000, 1000);

    }

}
