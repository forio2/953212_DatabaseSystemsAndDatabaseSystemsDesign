package Chapter5Two.controller;

import Chapter5Two.view.Score;
import Chapter5Two.model.Direction;
import Chapter5Two.model.Food;
import Chapter5Two.model.Snake;
import Chapter5Two.view.Platform;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

public class GameLoop implements Runnable {
    public static int answer = 1;
    private Platform platform;
    private Snake snake;
    private Food food;
    private float interval = 1000.0f / 10;
    private boolean running;
    public GameLoop(Platform platform, Snake snake, Food food) {
        this.snake = snake;
        this.platform = platform;
        this.food = food;
        running = true;
    }
    private void update() {
        KeyCode cur_key = platform.getKey();
        Direction cur_direction = snake.getCurrentDirection();
        if (cur_key == KeyCode.UP && cur_direction != Direction.DOWN)
            snake.setCurrentDirection(Direction.UP);
        else if (cur_key == KeyCode.DOWN && cur_direction != Direction.UP)
            snake.setCurrentDirection(Direction.DOWN);
        else if (cur_key == KeyCode.LEFT && cur_direction != Direction.RIGHT)
            snake.setCurrentDirection(Direction.LEFT);
        else if (cur_key == KeyCode.RIGHT && cur_direction != Direction.LEFT)
            snake.setCurrentDirection(Direction.RIGHT);
        snake.update();
    }
    private void updateScore(ArrayList<Score> scoreList) {
        javafx.application.Platform.runLater(() -> {
            for (int i=0 ; i<scoreList.size() ; i++) {
            scoreList.get(i).setPoint(snake.getScore());
        }
        });
    }
    private void checkCollision() {
        if (snake.isCollidingWith(food)) {
            snake.grow();
            food.respawn();
            Random color = new Random();
            this.answer = color.nextInt(6) + 1;
        }
        if (snake.isDead()) {
            running = true;
            javafx.application.Platform.runLater(() ->{
                running = false;
                ButtonType restart = new ButtonType("Restart", ButtonBar.ButtonData.OK_DONE);
                ButtonType exit = new ButtonType("Exit", ButtonBar.ButtonData.CANCEL_CLOSE);
                Alert dialog = new Alert(Alert.AlertType.INFORMATION, "", restart, exit);
                dialog.setTitle("notify");
                dialog.setContentText("Game Over");
                dialog.setHeaderText(null);
                Optional<ButtonType> result = dialog.showAndWait();
                if(result.get() == exit)
                {
                    System.exit(0);
                }
                Platform platform = new Platform();
                Launcher.getStage().setScene(
                        new Scene(platform,platform.WIDTH*platform.TILE_SIZE,platform.HEIGHT
                                * platform.TILE_SIZE));
                Snake snake = new Snake(new Point2D(platform.WIDTH/2,platform.HEIGHT/2));
                Food food = new Food();
                GameLoop gameLoop = new GameLoop(platform,snake,food);
                Launcher.getStage().getScene().setOnKeyPressed(event-> platform.setKey(event.getCode()));
                Launcher.getStage().getScene().setOnKeyReleased(event -> platform.setKey(null));
                Launcher.getStage().setTitle("Snake");
                Launcher.getStage().setScene(Launcher.getStage().getScene());
                Launcher.getStage().setResizable(false);
                Launcher.getStage().show();
                (new Thread(gameLoop)).start();
            });
        }
    }

    private void redraw() {
        platform.render(snake,food);
    }
    @Override
    public void run() {
        while (running) {
            update();
            updateScore(platform.getScoreList());
            checkCollision();
            redraw();
            try {
                Thread.sleep((long)interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public Snake getSnake() {
        return snake;
    }

    public Platform getPlatform() {
        return platform;
    }

    public int getScore(){
        return snake.getScore();
    }

    public static void setRandom(int random){
        answer = random;
    }

}