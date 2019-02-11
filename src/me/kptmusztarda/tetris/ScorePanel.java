package me.kptmusztarda.tetris;

import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {

    private static final int PANEL_WIDTH = 200;
    private static final int PANEL_HEIGHT = 200;
    private static final int PANEL_BORDER = 1;
    private static final int TEXT_VERTICAL_SPACING = 5;
    private static final int LEFT_MARGIN = 5;
    private static final int TEXT_SIZE = 16;
    private static final Color TEXT_COLOR = Color.WHITE;

    private int score = 0;
    private boolean gameover = true;

    ScorePanel(int x, int y) {
        setLayout(null);
        setBackground(new Color(Color.TRANSLUCENT));
        setBounds(x, y,
                PANEL_WIDTH,
                PANEL_HEIGHT);
        setBorder(BorderFactory.createMatteBorder(PANEL_BORDER, PANEL_BORDER, PANEL_BORDER, PANEL_BORDER, Color.WHITE));
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
        repaint();
    }

    public void addScore(int fullLines) {
        this.score += fullLines*100 + (fullLines-1)*50;
        repaint();
    }

    void gameOver(boolean b) {
        this.gameover = b;
        repaint();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(TEXT_COLOR);
        setFont(new Font("Helvetica", Font.PLAIN, TEXT_SIZE));
        g.drawString(" Score: " + score, LEFT_MARGIN, getFont().getSize() + TEXT_VERTICAL_SPACING);
        if(gameover) g.drawString("Press SPACE to start", 2*LEFT_MARGIN, 6 * (getFont().getSize() + TEXT_VERTICAL_SPACING));
    }
}
