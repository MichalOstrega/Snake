package sample;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private Point head;
    private List<Point> body;



    @Override
    public String toString() {
        return "Snake{" +
                "head=" + head +
                '}';
    }

    public Snake(int x, int y) {

        this.head = new Point(x,y);
        this.body = new ArrayList<>();
    }

    public Point getHead() {
        return head;
    }

    public void setHead(Point head) {
        this.head = head;
    }

    public List<Point> getBody() {
        return body;
    }

    public void setBody(List<Point> body) {
        this.body = body;
    }

    public void move(Direction direction, int boundaryX, int boundaryY) {

        body.add(new Point(head));
        switch (direction) {
            case UP:
                if (head.getY()!=0){
                    head.setY(head.getY()-1);
                }
                else {
                    head.setY(boundaryY);
                }
                break;
            case DOWN:
                if (head.getY()!=boundaryY) {
                    head.setY(head.getY()+1);
                }
                else {
                    head.setY(0);
                }
                break;
            case LEFT:
                if (head.getX()!=0) {
                    head.setX(head.getX() - 1);
                }
                else {
                    head.setX(boundaryX);
                }
                break;
            case RIGHT:
                if (head.getX()!=boundaryX){
                    head.setX(head.getX()+1);

                }
                else  {
                    head.setX(0);
                }
                break;
        }
    }
}
