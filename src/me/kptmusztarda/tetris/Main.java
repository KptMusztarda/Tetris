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
        renderer.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("KeyEvent: " + e.getKeyCode());
                switch(e.getKeyCode()) {
                    case KeyEvent.VK_A: bricks.move(bricks.getBricks().size() - 1, Bricks.MOVE_LEFT); break;
                    case KeyEvent.VK_D: bricks.move(bricks.getBricks().size() - 1, Bricks.MOVE_RIGHT); break;
                    case KeyEvent.VK_S: bricks.move(bricks.getBricks().size() - 1, Bricks.MOVE_DOWN); break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        mainPanel.add(renderer);

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
