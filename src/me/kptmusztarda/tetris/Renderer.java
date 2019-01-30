package me.kptmusztarda.tetris;

import javax.swing.*;
import java.awt.*;

public class Renderer extends JPanel {

    private static final int SQUARE_SIDE_LENGTH = 30;
    private static final int SPACING = 3;
    private static final int MARGIN_TOP = 40;
    private static final int MARGIN_LEFT = 40;
    private static final int PANEL_BORDER = 1;
    private static int squaresVertically;
    private static int squaresHorizontally;

    private Bricks bricks;

    Renderer(int hor, int vert) {
        squaresVertically = vert;
        squaresHorizontally = hor;

        setLayout(null);
        setBackground(new Color(Color.TRANSLUCENT));
        setBounds(MARGIN_LEFT, MARGIN_TOP,
                (PANEL_BORDER * 2) + (squaresHorizontally * SQUARE_SIDE_LENGTH) + ((squaresHorizontally + 1) * SPACING),
                (PANEL_BORDER * 2) + (squaresVertically * SQUARE_SIDE_LENGTH) + ((squaresVertically + 1) * SPACING));
        setBorder(BorderFactory.createMatteBorder(PANEL_BORDER, PANEL_BORDER, PANEL_BORDER, PANEL_BORDER, Color.WHITE));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //System.out.println("Repainting");

        //Draw active brick
        Brick brick = bricks.getActiveBrick();
        if(brick != null) {
            int[] position = {brick.getX(), brick.getY()};
            for (int i = 0; i < brick.getWidth(); i++)
                for (int j = 0; j < brick.getHeight(); j++)
                    if (brick.getArray()[i][j])
                        fillSquare(g, (position[0] + i), (position[1] + j), brick.getColorIndex());
        }


        //Draw remaining bricks
        int[][] field = bricks.getField();
        for(int i=0; i<field.length; i++)
            for(int j=0; j<field[0].length; j++)
                if(field[i][j] > -1) fillSquare(g, i, j, field[i][j]);


        if(Main.DEBUG)
            for(int i = 0; i< squaresHorizontally; i++)
                for(int j = 0; j< squaresVertically; j++) {
                    g.setColor(Color.RED);
                    g.drawString(i + "," + j,
                            (PANEL_BORDER) + (i * SQUARE_SIDE_LENGTH) + ((i + 1) * SPACING),
                            (PANEL_BORDER) + (j * SQUARE_SIDE_LENGTH) + ((j + 1) * SPACING) + SQUARE_SIDE_LENGTH/2);
                }



    }

    private void fillSquare(Graphics g, int x, int y, int colorIndex) {
        g.setColor(Brick.COLORS[colorIndex]);
        g.fillRect(
                (PANEL_BORDER) + (x * SQUARE_SIDE_LENGTH) + ((x + 1) * SPACING),
                (PANEL_BORDER) + (y * SQUARE_SIDE_LENGTH) + ((y + 1) * SPACING),
                SQUARE_SIDE_LENGTH,
                SQUARE_SIDE_LENGTH);
    }

    void addBricks(Bricks bricks) {
        this.bricks = bricks;
    }
}
