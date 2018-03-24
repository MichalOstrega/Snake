package sample;

import java.util.Random;

public class SnakeGame {
    //Wąż
    private Snake snake;
    //Kierunki ruchu
    private Direction direction = Direction.RIGHT;
    //Punkty jabłka
    private Point apple;
    //Operator stanu gry -> przy porażce zmiana na false i wyjście z pętli while w grze
    private boolean on = true;
    //Interfejs, którego, metoda run jest nadpisana w Controllerze celem sterowania interfejsem graficznym JavaFX
    private Runnable onMove;
    //Pole punktów
    private int points;

    private int speed;


    //Konstruktor gry
    public SnakeGame(Integer x, Integer y) {
        this.points = 0;
        this.speed = 1;
        this.snake = new Snake(x, y);
        //Tymczasowe utworzenie apple w punkcie startu
        apple = new Point(x, y);
        //Jabłko zawarte jest w snake'u, więc losujemy nowe jabłko, dopóki będzie poza wężem
        while (snakeContainsApple()) {
            newApple();
        }
    }

    public void start() {
        Thread thread = new Thread(() -> {
            while (on) {
                snake.move(direction, 600, 600, apple);
                //Sprawdzam czy zjedzono jablko, jeżeli tak to losuje nowe jabłko do takie, którego nie ma w wężu
                while (snakeContainsApple()) {
                    newApple();
                    points++;
                    if (points%5==0){
                        speed++;
                    }
                }
                //Sprawdzam czy wąż nie uderzył w siebie, jeżeli tak to koniec gry
                if (snakeHitHimself()) {
                    on = false;
                }

                //Wywołuje na interfejsie Runnable metodę run() -> Pole onMove jest ustalone za pomccą settera w Controllerze, i tam nadpisany run do wywołania grafiki
                onMove.run();
                try {
                    Thread.sleep(250/speed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    private void newApple() {
        Integer i1 = new Random().nextInt(60) * 10;
        Integer i2 = new Random().nextInt(60) * 10;
        apple = new Point(i1, i2);
    }

    private boolean snakeContainsApple() {
        return snake.getBody().stream().anyMatch(point -> point.equals(apple));
    }

    private boolean snakeHitHimself() {
        return snake.getBody().stream().anyMatch(point -> point.equals(snake.getHead()));
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setOnMove(Runnable onMove) {
        this.onMove = onMove;
    }

    public Point getApple() {
        return apple;
    }

    public Snake getSnake() {
        return snake;
    }

    public int getPoints() {
        return points;
    }
}
