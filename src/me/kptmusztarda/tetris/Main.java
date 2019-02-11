package me.kptmusztarda.tetris;

import java.util.Timer;
import java.util.TimerTask;

public class Main {

    static final int SQUARES_HORIZONTALLY = 10;  //poziomo
    static final int SQUARES_VERTICALLY = 20;    //pionowo
    private static final int TICK_DELAY = 700;

    static final boolean DEBUG = false;

    static private Timer timer;
    static private TimerTask timerTask;
    static private Bricks bricks;
    static private ScorePanel scorePanel;
    static private Renderer renderer;
    static private MyKeyListener keyListener;

    public static void main(String[] args) {
        Window window = new Window();
        MainPanel mainPanel = new MainPanel();
        window.setContentPane(mainPanel);


        renderer = new Renderer(SQUARES_HORIZONTALLY, SQUARES_VERTICALLY);
        scorePanel = new ScorePanel(renderer.getX() + renderer.getWidth() + Window.MARGIN_LEFT, Window.MARGIN_TOP);
        bricks = new Bricks() {
            @Override
            void onDeletedLines(int fullLines) {
                scorePanel.addScore(fullLines);
            }

            @Override
            void onGameOver() {
                timer.cancel();
                timer.purge();
                scorePanel.gameOver(true);
                keyListener.setGameActive(false);
            }
        };

        renderer.addBricks(bricks);

        mainPanel.add(renderer);
        mainPanel.add(scorePanel);

        keyListener = new MyKeyListener() {
            @Override
            protected void repaint() {
                renderer.repaint();
            }

            @Override
            void start() {
                bricks.clear();
                bricks.createNew(Brick.TYPE_RANDOM);
                scorePanel.setScore(0);
                scorePanel.gameOver(false);
                setTimer();
                timer.scheduleAtFixedRate(timerTask, TICK_DELAY, TICK_DELAY);
                setGameActive(true);
            }
        };
        keyListener.addBricks(bricks);
        window.addKeyListener(keyListener);

        window.calculateAndSetSize();

        renderer.repaint();
    }

    static void setTimer() {
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Tick");
                bricks.moveActive(Bricks.MOVE_DOWN);
                renderer.repaint();
            }
        };
    }

}
