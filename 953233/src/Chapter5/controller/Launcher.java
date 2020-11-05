package Chapter5.controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Chapter5.controller.DrawingLoop;
import Chapter5.controller.GameLoop;
import Chapter5.view.Platform;

public class Launcher extends Application {

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) {

        Platform platform = new Platform();
        GameLoop gameLoop = new GameLoop(platform);
        DrawingLoop drawingLoop = new DrawingLoop(platform);

        Scene scene = new Scene(platform,platform.WIDTH,platform.HEIGHT);
        scene.setOnKeyPressed(event-> platform.getKeys().add(event.getCode()));
        scene.setOnKeyReleased(event ->  platform.getKeys().remove(event.getCode()));

        primaryStage.setTitle("platformer");
        primaryStage.setScene(scene);
        primaryStage.show();

        (new Thread(gameLoop)).start();
        (new Thread(drawingLoop)).start();

    }
}
