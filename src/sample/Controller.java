package sample;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

import javax.print.attribute.standard.DialogTypeSelection;

public class Controller {
    //Deklaracja obiektów FXML
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

    //Kierunek startowy dla węza
    private Direction direction = Direction.RIGHT;
    //Punkt starowy gry
    private int startX = 400;
    private int startY = 400;
    //skok węża i bok kwadratu
    private int jump = 10;

    //Obiekt gry
    private SnakeGame snakeGame;

    //Obiekt reprezentujący interfejs graficzny
    private GraphicsContext graphicsContext;

    public void initialize(Scene scene) {

        this.scene = scene;
        //pobranie z canvas Contextuu graficznego
        graphicsContext = canvas.getGraphicsContext2D();

//        graphicsContext.setTextAlign(TextAlignment.RIGHT);
//        graphicsContext.setTextBaseline(VPos.TOP);



        //ustawienie reakcji przycisków pod polem gry
        buttonsAction();
        //ustawienie reakcji na strzałki klawiwatury
        arrowsAction(scene);

        //nowa gra
        snakeGame = new SnakeGame(startX, startY);

        //Ustawienie rysowania węza wg wywolania interfejsu onMove.run()
        snakeGame.setOnMove(() -> {
            //Wyczysc plansze
            graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            graphicsContext.setFill(Color.BLUE);
            //Narysuj calego weza wg jego bodu
            for (Point point : snakeGame.getSnake().getBody()) {

                graphicsContext.fillRect(point.getX(), point.getY(), jump, jump);

            }
            //Narysuj jablko
            graphicsContext.setFill(Color.GREEN);
            graphicsContext.fillRect(snakeGame.getApple().getX(), snakeGame.getApple().getY(), jump, jump);
            graphicsContext.setFill(Color.RED);
            graphicsContext.setTextAlign(TextAlignment.CENTER);
            graphicsContext.fillText(
                    "Points: " + String.valueOf(snakeGame.getPoints()),
                    550,
                    20);
        });
        //Start gry
        snakeGame.start();

        /*Thread thread = new Thread(() -> {
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
        thread.start();*/
    }

    private void arrowsAction(Scene scene) {
        scene.setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            if (code.isArrowKey()) {
                switch (code) {
                    case UP:
                        if (direction.compareTo(Direction.DOWN) != 0) {
                            direction = Direction.UP;
                        }
                        break;
                    case DOWN:
                        if (direction.compareTo(Direction.UP) != 0) {
                            direction = Direction.DOWN;
                        }
                        break;
                    case LEFT:
                        if (direction.compareTo(Direction.RIGHT) != 0) {
                            direction = Direction.LEFT;
                        }
                        break;
                    case RIGHT:
                        if (direction.compareTo(Direction.LEFT) != 0) {
                            direction = Direction.RIGHT;
                        }
                        break;
                    default:
                        break;

                }
            }
            snakeGame.setDirection(direction);
        });
    }


    private void buttonsAction() {
        up.setOnAction(event -> {
            if (direction.compareTo(Direction.DOWN) != 0) {
                direction = Direction.UP;
                snakeGame.setDirection(direction);
            }
        });
        left.setOnAction(event -> {
            if (direction.compareTo(Direction.RIGHT) != 0) {
                direction = Direction.LEFT;
                snakeGame.setDirection(direction);
            }
        });
        down.setOnAction(event -> {
            if (direction.compareTo(Direction.UP) != 0) {
                direction = Direction.DOWN;
                snakeGame.setDirection(direction);
            }
        });
        right.setOnAction(event -> {
            if (direction.compareTo(Direction.LEFT) != 0) {
                direction = Direction.RIGHT;
                snakeGame.setDirection(direction);
            }
        });


    }
    /*   private void changeDirection(int krok, int[] position) {
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
       }*/
}
