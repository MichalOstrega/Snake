package sample;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import javax.print.attribute.standard.DialogTypeSelection;

public class Controller {

    @FXML
    private Canvas canvas;
    @FXML
    private Button up;

    @FXML
    private Button down;

    @FXML
    private Button right;

    @FXML
    private Scene scene;
    @FXML
    private Button left;

    private Direction direction = Direction.DOWN;
    private int[] position = {400, 400};
    private int l1 = 10;

    private SnakeGame snakeGame;


    public void initialize(Scene scene) {
        this.scene = scene;
        buttonsAction();
        arrowsAction(scene);
        snakeGame = new SnakeGame(position[0],position[1]);

        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setFill(Color.BLUE);


        Thread thread = new Thread(() -> {
            for (int i = 0; i < 100; i++) {

                changeDirection(l1, position);
                graphicsContext.fillRect(position[0], position[1], l1, l1);

                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            }


        });
        thread.setDaemon(true);


        thread.start();


    }

    private void arrowsAction(Scene scene) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyCode code = event.getCode();
                if (code.isArrowKey()) {
                    switch (code) {
                        case UP:
                            direction = Direction.UP;
                            break;
                        case DOWN:
                            direction = Direction.DOWN;
                            break;
                        case LEFT:
                            direction = Direction.LEFT;
                            break;
                        case RIGHT:
                            direction = Direction.RIGHT;
                            break;
                            default:
                                break;

                    }
                }
            }
        });
    }


    private void changeDirection(int krok, int[] position) {
        switch (direction) {
            case UP:
                position[0] = position[0];
                position[1] = position[1] - krok;

                break;
            case DOWN:
                position[0] = position[0];
                position[1] = position[1] + krok;
                break;
            case LEFT:
                position[0] = position[0] - krok;
                position[1] = position[1];
                break;
            case RIGHT:
                position[0] = position[0] + krok;
                position[1] = position[1];
                break;
        }
    }

    private void buttonsAction() {
        up.setOnAction(event -> direction = Direction.UP);
        left.setOnAction(event -> direction = Direction.LEFT);
        down.setOnAction(event -> direction = Direction.DOWN);
        right.setOnAction(event -> direction = Direction.RIGHT);
    }
}
