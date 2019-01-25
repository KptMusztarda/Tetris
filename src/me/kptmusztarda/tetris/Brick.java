package me.kptmusztarda.tetris;

import java.awt.*;

public class Brick {

    private static final int[] STARTING_POSITION = {Main.SQUARES_HORIZONTALLY/2, 0};
    public static final int TYPE_RANDOM = -1;
    public static final int TYPE_I = 0;
    public static final int TYPE_T = 1;
    public static final int TYPE_O = 2;
    public static final int TYPE_L = 3;
    public static final int TYPE_J = 4;
    public static final int TYPE_S = 5;
    public static final int TYPE_Z = 6;

    private static final boolean[][][] TYPES = {

            {{  true, true, true, true}},   //TYPE_I

            {{true, false},
             {true, true },                 //TYPE_T
             {true, false}},

            {{true, true},                  //TYPE_O
             {true, true}},

            {{true,  true,  true},          //TYPE_L
             {false, false, true}},

            {{false, false, true},          //TYPE_J
             {true,  true,  true}},

            {{false, true},
             {true,  true},                 //TYPE_S
             {true,  false}},

            {{true,  false},
             {true,  true},                 //TYPE_Z
             {false, true}},
    };

    public static final Color[] COLORS = {
            Color.RED,
            Color.BLUE,
            Color.YELLOW,
            Color.GREEN,
            Color.CYAN,
            Color.MAGENTA,
            Color.GRAY
    };

    private int x,y;
    private boolean[][] brick;
    private int color;

    public Brick(int type) {
        if(type == TYPE_RANDOM) type = (int)(Math.random()*TYPES.length);
        brick = TYPES[type];
        x = STARTING_POSITION[0];
        y = STARTING_POSITION[1];
        color = (int)(Math.random()*COLORS.length);
        System.out.println("Creating brick of TYPE " + type + " and COLOR " + color + " at " + x + "," + y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return brick.length;
    }

    public int getHeight() {
        return brick[0].length;
    }

    public boolean[][] getArray() {
        return brick;
    }

    public int getColor() {
        return color;
    }

    public int getLowestPointYAtWidth(int x) {
        int lowest = y;
        for(int i=0; i<getHeight(); i++) {
            if(brick[x][i]) lowest = y+i;
        }
        return lowest;
    }

    public int getLeftPointXAtHeight(int y) {
        int left = x + getWidth();
        for(int i=getWidth()-1; i>=0; i--) {
            if(brick[i][y]) left = x-i;
        }
        return left;
    }
}
