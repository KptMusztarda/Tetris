package me.kptmusztarda.tetris;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Renderer extends JPanel {

    private static final int SQUARE_SIDE_LENGTH = 30;
    private static final int SPACING = 3;
    private static final int MARGIN_TOP = 40;
    private static final int MARGIN_LEFT = 40;
    private static final int PANEL_BORDER = 1;
    private static int squaresVertically;
    private static int squaresHorizontally;

    private ArrayList bricks;

    public Renderer(int hor, int vert) {
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

        System.out.println("Repainting");



        for(int i=0; i<bricks.size(); i++) {
            Brick brick = (Brick) bricks.get(i);
            //System.out.println("Rendering brick " + i);

            g.setColor(Brick.COLORS[brick.getColor()]);

            int[] position = {brick.getX(), brick.getY()};
            for(int j=0; j<brick.getWidth(); j++) {
                for(int k=0; k<brick.getHeight(); k++) {
                    if(brick.getArray()[j][k]) {
                        g.fillRect(
                        (PANEL_BORDER) + ((position[0] + j) * SQUARE_SIDE_LENGTH) + ((position[0] + j + 1) * SPACING),
                        (PANEL_BORDER) + ((position[1] + k) * SQUARE_SIDE_LENGTH) + ((position[1] + k + 1) * SPACING),
                        SQUARE_SIDE_LENGTH,
                        SQUARE_SIDE_LENGTH);
                    }
                }
            }
        }

        for(int i = 0; i< squaresHorizontally; i++) {
            for(int j = 0; j< squaresVertically; j++) {
//                g.fillRect(
//                        (PANEL_BORDER) + (i * SQUARE_SIDE_LENGTH) + ((i + 1) * SPACING),
//                        (PANEL_BORDER) + (j * SQUARE_SIDE_LENGTH) + ((j + 1) * SPACING),
//                        SQUARE_SIDE_LENGTH,
//                        SQUARE_SIDE_LENGTH);
                if(Main.DEBUG) {
                    g.setColor(Color.RED);
                    g.drawString(i + "," + j,
                            (PANEL_BORDER) + (i * SQUARE_SIDE_LENGTH) + ((i + 1) * SPACING),
                            (PANEL_BORDER) + (j * SQUARE_SIDE_LENGTH) + ((j + 1) * SPACING) + SQUARE_SIDE_LENGTH/2);
                }
            }
        }
    }

    protected void addBricks(Bricks bricks) {
        this.bricks = bricks.getBricks();
    }
}
