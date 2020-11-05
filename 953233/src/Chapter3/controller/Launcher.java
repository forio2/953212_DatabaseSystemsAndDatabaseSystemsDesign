package Chapter3.controller;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Launcher extends Application {
    public static Stage primaryStage;
    public static HostServices hs;
    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        hs = getHostServices();
        Pane mainPane = FXMLLoader.load(getClass().getResource("../view/mainView.fxml"));
        /* 3.5.4
        primaryStage.setTitle("Indexer");
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setResizable(false);
        primaryStage.show();
        */
        this.primaryStage.setTitle("Indexer");
        this.primaryStage.setScene(new Scene(mainPane));
        this.primaryStage.setResizable(false);
        this.primaryStage.show();
    }
    public static void main(String[] args){
        launch(args);
    }
}
