package model;

import controller.GameLoop;
import view.Platform;
import javafx.geometry.Point2D;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private Direction direction;
    private Point2D head;
    private Point2D prev_tail;
    private ArrayList<Point2D> body;
    private int score = 0;

    public Snake(Point2D position) {
        direction = Direction.DOWN;
        body = new ArrayList<>();
        this.head = position;
        this.body.add(this.head);
    }
    public void update() {
        head = head.add(direction.current);
        prev_tail = body.remove(body.size() - 1);
        body.add(0, head);
    }
    public void setCurrentDirection(Direction direction) {
        this.direction = direction;
    }
    public void grow() {
        if(GameLoop.answer == 1){
            body.add(prev_tail);
            body.add(prev_tail);
            body.add(prev_tail);
            body.add(prev_tail);
            body.add(prev_tail);
            this.score = this.score + 5;

        }else{
            body.add(prev_tail);
            this.score++;
        }
    }

    public Direction getCurrentDirection() {
        return this.direction;
    }

    public Point2D getHead() {
        return head;
    }

    public boolean isCollidingWith(Food food) {
        return head.equals(food.getPosition());
    }

    public int getLength() {
        return body.size();
    }

    public List<Point2D> getBody() {
        return body;
    }

    public boolean isDead() {
        boolean isOutOfBound = head.getX() < 0 || head.getY() < 0 || head.getX() >
                Platform.WIDTH || head.getY() > Platform.HEIGHT;
        boolean isHitBody = body.lastIndexOf(head) > 0;
        return isOutOfBound || isHitBody;
    }

    public int getScore() {
        return score;
    }

}