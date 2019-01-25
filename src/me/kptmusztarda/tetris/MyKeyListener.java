package me.kptmusztarda.tetris;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKeyListener implements KeyListener {

    Bricks bricks;

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
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    protected void addBricks(Bricks bricks) {
        this.bricks = bricks;
    }

    protected void repaint() {

    }
}
