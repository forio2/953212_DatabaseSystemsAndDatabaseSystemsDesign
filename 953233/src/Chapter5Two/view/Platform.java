package Chapter5Two.view;

import Chapter5Two.controller.GameLoop;
import Chapter5Two.model.Food;
import Chapter5Two.model.Snake;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Platform extends Pane {
    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;
    public static final int TILE_SIZE = 10;
    private Canvas canvas;
    private KeyCode key;
    private ArrayList<Score> scoreList;
    public Platform() {
        this.setHeight(TILE_SIZE * HEIGHT);
        this.setWidth(TILE_SIZE * WIDTH);
        canvas = new Canvas(TILE_SIZE * WIDTH, TILE_SIZE * HEIGHT);
        scoreList = new ArrayList();
        scoreList.add(new Score(250,25));
        this.getChildren().add(canvas);
        this.getChildren().addAll(scoreList);
    }
    public void render(Snake snake, Food food) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0,0,WIDTH*TILE_SIZE,HEIGHT*TILE_SIZE);
        gc.setFill(Color.BLUE);
        snake.getBody().forEach(p -> gc.fillRect(p.getX() * TILE_SIZE,
                p.getY() * TILE_SIZE, TILE_SIZE, TILE_SIZE)); //makes snack to rectangle
        if(GameLoop.answer == 1){
            gc.setFill(Color.GREEN);
            gc.fillRect(food.getPosition().getX() * TILE_SIZE, food.getPosition().getY()
                    * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }else{
            gc.setFill(Color.RED);
            gc.fillRect(food.getPosition().getX() * TILE_SIZE, food.getPosition().getY()
                    * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }
    }
    public KeyCode getKey() {
        return key;
    }
    public void setKey(KeyCode key) {
        this.key = key;
    }
    public ArrayList<Score> getScoreList() {
        return scoreList;
    }
}