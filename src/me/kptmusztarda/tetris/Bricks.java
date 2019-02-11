package me.kptmusztarda.tetris;

import java.util.Arrays;

class Bricks {

    static final int MOVE_DOWN = 0;
    static final int MOVE_LEFT = 1;
    static final int MOVE_RIGHT = 2;
    static final int ROTATE_CLOCKWISE = 3;
    static final int ROTATE_COUNTERCLOCKWISE = 4;

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

    void clear() {
        for(int[] row : field)
            Arrays.fill(row, -1);
        activeBrick = null;
    }

    private void stopActiveBrick() {
        if(activeBrick != null) {
            for (int i = 0; i < activeBrick.getWidth(); i++)
                for (int j = 0; j < activeBrick.getHeight(); j++)
                    if (activeBrick.getArray()[i][j])
                        field[activeBrick.getX() + i][activeBrick.getY() + j] = activeBrick.getColorIndex();
        }
        System.out.println("Stopping brick");
    }

    private void checkForFullLines() {
        int deletedLines = 0;
        for(int i=field[0].length-1; i>=0; i--) {
            //System.out.println("Checking line " + i);
            int blocksInLine = 0;
            for(int j=0; j<field.length; j++)
                if(!isEmpty(j, i)) blocksInLine++;
            //System.out.println("Blocks in line: " + blocksInLine);
            if(blocksInLine == field.length) {
                deleteLine(i++);
                deletedLines++;
            }
        }
        if(deletedLines > 0) onDeletedLines(deletedLines);
    }

    void onDeletedLines(int fullLines) {

    }

    private void deleteLine(int y) {
        System.out.println("Deleting line " + y);
        for(int i=y; i>0; i--)
            for(int j=0; j<field.length; j++)
                field[j][i] = field[j][i-1];
    }

    void createNew(int type) {
        Brick brick = new Brick(type);
        boolean gameOver = false;
        outerLoop:
        for(int i=0; i<brick.getWidth(); i++){
            for(int j=0; j<brick.getHeight(); j++) {
                System.out.println("Checking field " + (brick.getX() + brick.getWidth() - 1 - i) + ", " + (brick.getY() + j));
                if(brick.getArray()[i][j] && !isEmpty(brick.getX() + brick.getWidth() - 1 - i, brick.getY() + j)) {
                    gameOver = true;
                    break outerLoop;
               }
            }
        }

        if(gameOver) onGameOver();
        else activeBrick = brick;
    }

    void onGameOver() {
    }

    void moveActive(int direction) {

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
                    System.out.println("Moving brick down");
                    activeBrick.setY(activeBrick.getY() + 1);
                } else {
                    stopActiveBrick();
                    checkForFullLines();
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
                    System.out.println("Moving brick left");
                } else {
                    System.out.println("Unable to move brick left");
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
                    System.out.println("Moving brick right");
                } else {
                    System.out.println("Unable to brick right");
                }
                break;

        }
    }

    void rotateActive(int direction) {

        boolean[][] rotated_brick = new boolean[activeBrick.getHeight()][activeBrick.getWidth()];
        int[] newPivot = new int[2];
        int[] newPosition = new int[2];

        switch (direction) {
            case ROTATE_CLOCKWISE:

                for(int i=0; i<rotated_brick.length; i++) {
                    for(int j=0; j<rotated_brick[0].length; j++) {
                        rotated_brick[i][j] = activeBrick.getArray()[j][activeBrick.getHeight() - 1 - i];
                    }
                }

                newPivot[0] = activeBrick.getPivot()[1];
                newPivot[1] = activeBrick.getWidth() - 1 - activeBrick.getPivot()[0];

                break;
            case ROTATE_COUNTERCLOCKWISE:

                for(int i=0; i<rotated_brick.length; i++) {
                    for(int j=0; j<rotated_brick[0].length; j++) {
                        rotated_brick[i][j] = activeBrick.getArray()[activeBrick.getWidth() - 1 - j][i];
                    }
                }

                newPivot[0] = activeBrick.getHeight() - 1 - activeBrick.getPivot()[1];
                newPivot[1] = activeBrick.getPivot()[0];

                break;
        }

        //System.out.println("Old pivot: " + activeBrick.getPivot()[0] + "," + activeBrick.getPivot()[1]);
        //System.out.println("New pivot: " + newPivot[0] + "," + newPivot[1]);


        newPosition[0] = activeBrick.getX() - (rotated_brick.length - 1 - newPivot[0]) + (activeBrick.getWidth() - 1 - activeBrick.getPivot()[0]);
        newPosition[1] = activeBrick.getY() - newPivot[1] + activeBrick.getPivot()[1];

        boolean isPossibleToRotate = true;

        try {
            for (int i = 0; i < rotated_brick.length; i++)
                for (int j = 0; j < rotated_brick[0].length; j++)
                    if (rotated_brick[i][j])
                        if (!isEmpty(newPosition[0] + i, newPosition[1] + j))
                            isPossibleToRotate = false;
        } catch (IndexOutOfBoundsException e) {
            isPossibleToRotate = false;
        }

        if(isPossibleToRotate) {
            activeBrick.setArray(rotated_brick);
            activeBrick.setPivot(newPivot);
            activeBrick.setX(newPosition[0]);
            activeBrick.setY(newPosition[1]);
            System.out.print("Rotating brick ");
        } else
            System.out.print("Unable to rotate brick ");
        System.out.println(direction == ROTATE_CLOCKWISE ? "clockwise" : "counterclockwise");
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
