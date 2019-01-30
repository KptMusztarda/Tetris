package me.kptmusztarda.tetris;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKeyListener implements KeyListener {

    private Bricks bricks;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println("KeyEvent: " + e.getKeyCode());
        switch(e.getKeyCode()) {
            case KeyEvent.VK_A: bricks.moveActive(Bricks.MOVE_LEFT); break;
            case KeyEvent.VK_D: bricks.moveActive(Bricks.MOVE_RIGHT); break;
            case KeyEvent.VK_S: bricks.moveActive(Bricks.MOVE_DOWN); break;
            case KeyEvent.VK_Q: bricks.rotateActive(Bricks.ROTATE_COUNTERCLOCKWISE); break;
            case KeyEvent.VK_E: bricks.rotateActive(Bricks.ROTATE_CLOCKWISE); break;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    void addBricks(Bricks bricks) {
        this.bricks = bricks;
    }

    void repaint() {

    }
}
