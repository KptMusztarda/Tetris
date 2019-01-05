package me.kptmusztarda.tetris;

public class Main {

    private static final int SQUARES_VERTICALLY = 5;
    private static final int SQUARES_HORIZONTALLY = 10;


    public static void main(String[] args) {
        Window window = new Window();
        MainPanel mainPanel = new MainPanel();
        window.setContentPane(mainPanel);
        mainPanel.add(new Renderer(SQUARES_VERTICALLY, SQUARES_HORIZONTALLY));

        System.out.println("Papusz");
    }
}
