package me.kptmusztarda.tetris;

import java.util.ArrayList;

public class Bricks {

    public static final int MOVE_DOWN = 0;
    public static final int MOVE_LEFT = 1;
    public static final int MOVE_RIGHT = 2;

    private ArrayList<Brick> bricks;


    public Bricks() {
        bricks = new ArrayList<>(1);
    }

    public void createNew(int type) {
        bricks.add(new Brick(type));
    }

    public void move(int brick_id, int direction) {

        Brick brick = bricks.get(brick_id);

        switch(direction) {

            case MOVE_DOWN:
                boolean isPossibleToMove = true;
                for(int i=0; i < brick.getWidth(); i++) {
                    //check if blocks under the brick are empty
                    if(!isEmpty(brick.getX() + i, brick.getLowestPointYAtWidth(i) + 1)) isPossibleToMove = false;
                    //check if the brick hits the ground
                    if(brick.getLowestPointYAtWidth(i) + 1 > Main.SQUARES_VERTICALLY - 1) isPossibleToMove = false;
                }
                if(isPossibleToMove) {
                    System.out.println("Moving brick " + brick_id + " down");
                    brick.setY(brick.getY() + 1);
                } else {
                    System.out.println("Stopping brick " + brick_id);
                    createNew(Brick.TYPE_RANDOM);
                }

                break;
        }
    }

    private boolean isEmpty(int x, int y) {
        boolean isEmpty = true;

        for(Brick brick : bricks) {
            int[] position = {brick.getX(), brick.getY()};
            for(int i=0; i<brick.getWidth(); i++) {
                for(int j=0; j<brick.getHeight(); j++) {
                    //System.out.println("Checking position " + x + "," + y + " with: position[0]=" + position[0] + " i=" + i + " position[0]=" + position[0] + " j=" + j + "empty?" + brick.getArray()[i][j]);
                    if(position[0] + i == x && position[1] + j == y && brick.getArray()[i][j]) isEmpty = false;
                }
            }
        }

        return isEmpty;
    }

    protected ArrayList getBricks() {
        return bricks;
    }

}
