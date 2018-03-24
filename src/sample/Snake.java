package sample;

import java.util.*;

public class Snake {
    private Point head;
    private Deque<Point> body;
    private int jump = 10;



    @Override
    public String toString() {
        return "Snake{" +
                "head=" + head +
                '}';
    }

    public Deque<Point> getBody() {
        return body;
    }

    public void setBody(Deque<Point> body) {
        this.body = body;
    }

    public Snake(int x, int y) {

        this.head = new Point(x,y);
        this.body = new ArrayDeque<>();
    }

    public Point getHead() {
        return head;
    }

    public void setHead(Point head) {
        this.head = head;
    }



    public void move(Direction direction, int boundaryX, int boundaryY, int i,Point apple) {

        body.addLast(new Point(head));
        if(i!=0) {
            body.removeFirst();
        }
        Point tmpHead = new Point(head);
        switch (direction) {
            case UP:
                if (head.getY()>0){
                    head.setY(head.getY()-jump);
                }
                else {
                    head.setY(boundaryY);
                }
                break;
            case DOWN:
                if (head.getY()<boundaryY) {
                    head.setY(head.getY()+jump);
                }
                else {
                    head.setY(0);
                }
                break;
            case LEFT:
                if (head.getX()>0) {
                    head.setX(head.getX() - jump);
                }
                else {
                    head.setX(boundaryX);
                }
                break;
            case RIGHT:
                if (head.getX()<boundaryX){
                    head.setX(head.getX()+jump);

                }
                else  {
                    head.setX(0);
                }
                break;
        }
        if (head.equals(apple)){
            body.addLast(tmpHead);
        }
    }
}
