package sample;

import java.util.Random;

public class SnakeGame {
    private Snake snake;
    private Direction direction = Direction.RIGHT;
    private Point apple;
    private boolean on = true;

    private Runnable onMove;

    public Point getApple() {
        return apple;
    }

    public SnakeGame(Integer x, Integer y) {
        this.snake = new Snake(x, y);
        Integer i1 = x;
        Integer i2 = y;
        while (i1 == x && i2 == y) {
            i1 = new Random().nextInt(40) * 10;
            i2 = new Random().nextInt(40) * 10;
        }
        apple = new Point(i1, i2);


    }

    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public void start() {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                snake.move(direction, 600, 600, i, apple);
                //Sprawdzam czy zjedzono jablko
                while (snake.getBody().stream().anyMatch(point -> point.equals(apple))) {
                    Integer i1 = new Random().nextInt(40) * 10;
                    Integer i2 = new Random().nextInt(40) * 10;
                    apple = new Point(i1, i2);
                }
                if (snake.getBody().stream().anyMatch(point -> point.equals(snake.getHead()))) {
                    break;
                }


                onMove.run();


                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setOnMove(Runnable onMove) {
        this.onMove = onMove;
    }
}
