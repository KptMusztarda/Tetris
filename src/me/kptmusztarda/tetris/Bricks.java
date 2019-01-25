package me.kptmusztarda.tetris;

import java.util.Arrays;

class Bricks {

    static final int MOVE_DOWN = 0;
    static final int MOVE_LEFT = 1;
    static final int MOVE_RIGHT = 2;

    private Brick activeBrick;
    private int[][] field;


    Bricks() {
        field = new int[Main.SQUARES_HORIZONTALLY][Main.SQUARES_VERTICALLY];
        for(int[] row : field)
            Arrays.fill(row, -1);
//        for (int[] row : field) {
//            for (int j = 0; j < field[0].length; j++)
//                System.out.print(row[j] + " ");
//            System.out.print("\n");
//        }
    }

    void createNew(int type) {
        if(activeBrick != null) {
            for (int i = 0; i < activeBrick.getWidth(); i++)
                for (int j = 0; j < activeBrick.getHeight(); j++)
                    if (activeBrick.getArray()[i][j])
                        field[activeBrick.getX() + i][activeBrick.getY() + j] = activeBrick.getColorIndex();
        }
        activeBrick = new Brick(type);
    }

    void move(int direction) {

        boolean isPossibleToMove = true;

        switch(direction) {

            case MOVE_DOWN:

                //check if the brick hits the ground
                if(activeBrick.getY() + activeBrick.getHeight() < Main.SQUARES_VERTICALLY) {
                    //check if blocks under the brick are empty
                    for(int i=0; i < activeBrick.getWidth(); i++)
                        if(!isEmpty(activeBrick.getX() + i, activeBrick.getLowestPointYAtWidth(i) + 1)) isPossibleToMove = false;
                } else isPossibleToMove = false;


                if(isPossibleToMove) {
                    System.out.println("Moving brick active brick down");
                    activeBrick.setY(activeBrick.getY() + 1);
                } else {
                    System.out.println("Stopping active brick");
                    createNew(Brick.TYPE_RANDOM);
                }
                break;

            case MOVE_LEFT:

                //check if the brick hits the left wall
                if(activeBrick.getX() > 0) {
                    //check if blocks on the left of the brick are empty
                    for(int i=0; i < activeBrick.getHeight(); i++)
                        if(!isEmpty(activeBrick.getLeftPointXAtHeight(i) - 1, activeBrick.getY() + i)) isPossibleToMove = false;
                } else isPossibleToMove = false;


                if(isPossibleToMove) {
                    activeBrick.setX(activeBrick.getX() - 1);
                    System.out.println("Moving active brick left");
                } else {
                    System.out.println("Unable to move active brick left");
                }
                break;

            case MOVE_RIGHT:

                //check if the brick hits the right wall
                if(activeBrick.getX() + activeBrick.getWidth() < Main.SQUARES_HORIZONTALLY) {
                    //check if blocks on the right of the brick are empty
                    for(int i=0; i < activeBrick.getHeight(); i++)
                        if(!isEmpty(activeBrick.getRightPointXAtHeight(i) + 1, activeBrick.getY() + i)) isPossibleToMove = false;
                } else isPossibleToMove = false;


                if(isPossibleToMove) {
                    activeBrick.setX(activeBrick.getX() + 1);
                    System.out.println("Moving active brick right");
                } else {
                    System.out.println("Unable to move active brick right");
                }
                break;

        }
    }

    private boolean isEmpty(int x, int y) {
        return field[x][y] == -1;
    }

    Brick getActiveBrick() {
        return activeBrick;
    }

    int[][] getField() {
        return field;
    }

}
