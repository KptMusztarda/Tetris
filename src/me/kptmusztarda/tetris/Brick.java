package me.kptmusztarda.tetris;

import java.awt.*;

class Brick {

    private static final int[] STARTING_POSITION = {Main.SQUARES_HORIZONTALLY/2, 0};
    static final int TYPE_RANDOM = -1;
    static final int TYPE_I = 0;
    static final int TYPE_T = 1;
    static final int TYPE_O = 2;
    static final int TYPE_L = 3;
    static final int TYPE_J = 4;
    static final int TYPE_S = 5;
    static final int TYPE_Z = 6;

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

    static final Color[] COLORS = {
            Color.RED,
            Color.BLUE,
            Color.YELLOW,
            Color.GREEN,
            Color.CYAN,
            Color.MAGENTA,
            Color.ORANGE
    };

    private static int lastColor;

    private int x,y;
    private boolean[][] brick;
    private int color;

    Brick(int type) {
        if(type == TYPE_RANDOM) type = (int)(Math.random()*TYPES.length);
        brick = TYPES[type];
        x = STARTING_POSITION[0];
        y = STARTING_POSITION[1];
        do {
            color = (int) (Math.random() * COLORS.length);
            System.out.println("Randomized color: " + color);
        }
        while (color == lastColor);
        System.out.println("Last color color: " + lastColor);
        lastColor = color;
        System.out.println("Creating brick of TYPE " + type + " and COLOR " + color + " at " + x + "," + y);
    }

    int getX() {
        return x;
    }

    void setX(int x) {
        this.x = x;
    }

    int getY() {
        return y;
    }

    void setY(int y) {
        this.y = y;
    }

    int getWidth() {
        return brick.length;
    }

    int getHeight() {
        return brick[0].length;
    }

    boolean[][] getArray() {
        return brick;
    }

    int getColorIndex() {
        return color;
    }

    int getLowestPointYAtWidth(int x) {
        int lowest = y;
        for(int i=0; i<getHeight(); i++) {
            if(brick[x][i]) lowest = y+i;
        }
        return lowest;
    }

    int getLeftPointXAtHeight(int y) {
        int left = x + getWidth();
        for(int i=getWidth()-1; i>=0; i--) {
            if(brick[i][y]) left = x+i;
        }
        System.out.println("Left x at y=" + y + " is " + left);
        return left;
    }

    int getRightPointXAtHeight(int y) {
        int right = x;
        for(int i=0; i<getWidth(); i++) {
            if(brick[i][y]) right = x+i;
        }
        System.out.println("Right x at y=" + y + " is " + right);
        return right;
    }
}
