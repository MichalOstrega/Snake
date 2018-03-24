package sample;

public class MainGame {
    public static void main(String[] args) {
        SnakeGame snakeGame = new SnakeGame(new Snake(new Point(0, 0)));
        snakeGame.start();

    }
}
