package sample;

import java.util.*;

public class Snake {
    private Point head;
    private Deque<Point> body;
    private int jump = 10;


    public Snake(int x, int y) {

        this.head = new Point(x, y);
        this.body = new ArrayDeque<>();
        body.addLast(head);
    }


    public Point getHead() {
        return head;
    }

    public Deque<Point> getBody() {
        return body;
    }


    public void move(Direction direction, int boundaryX, int boundaryY, Point apple) {
        //Dodanie do kolejki punktu głowy
        body.addLast(new Point(head));

        //Określenie ruchu weża w zadeklarowanych granicach
        switch (direction) {
            case UP:
                if (head.getY() > 0) {
                    head.setY(head.getY() - jump);
                } else {
                    head.setY(boundaryY);
                }
                break;
            case DOWN:
                if (head.getY() < boundaryY) {
                    head.setY(head.getY() + jump);
                } else {
                    head.setY(0);
                }
                break;
            case LEFT:
                if (head.getX() > 0) {
                    head.setX(head.getX() - jump);
                } else {
                    head.setX(boundaryX);
                }
                break;
            case RIGHT:
                if (head.getX() < boundaryX) {
                    head.setX(head.getX() + jump);

                } else {
                    head.setX(0);
                }
                break;
        }

        //Jeżeli głowa nie napotkała jabłka to usuwamy pierwszy element z kolejki
        if (!head.equals(apple)) {
            body.removeFirst();
        }
    }

    @Override
    public String toString() {
        return "Snake{" +
                "head=" + head +
                '}';
    }
}
