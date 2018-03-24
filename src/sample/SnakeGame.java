package sample;

public class SnakeGame {
    private Snake snake;
    private Direction direction = Direction.RIGHT;

    public void setOnMove(Runnable onMove) {
        this.onMove = onMove;
    }

    private Runnable onMove;

    public SnakeGame(int x, int y) {
        this.snake = new Snake(x,y);
    }

    public void start(){
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                snake.move(direction,100,100);
                onMove.
                System.out.println(snake);
            }
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }
}
