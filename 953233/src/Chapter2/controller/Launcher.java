package Chapter2.controller;

import Chapter2.model.Currency;
import Chapter2.view.CurrencyPane;
import Chapter2.view.CurrencyParentPane;
import Chapter2.view.TopPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Launcher extends Application {
    private static Stage primaryStage;
    private static Scene mainScene;
    private static FlowPane mainPane;
    private static TopPane topPane;
    //private static CurrencyPane currencyPane; 2.24
    private static CurrencyParentPane currencyParentPane;
    //private static Currency currency; 2.21
    private static ArrayList<Currency> currencyList;


    public static void setCurrency(ArrayList<Currency> c) {
        Launcher.currencyList = c;
    }

    public static void refreshPane() throws
            ExecutionException, InterruptedException {
        topPane.refreshPane();
        //currencyPane.refreshPane(currency); 2.21
        currencyParentPane.refreshPane(currencyList);
        primaryStage.sizeToScene();
    }

    public static ArrayList<Currency> getCurrency() {
        return currencyList;
    }

    @Override
    public void start(Stage primaryStage) throws
            ExecutionException, InterruptedException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Currency Watcher");
        this.primaryStage.setResizable(false);
        //System.out.println(FetchData.fetch_range("USD",6));
        Initialize.initialize_app();
        initMainPane();
        mainScene = new Scene(mainPane);
        this.primaryStage.setScene(mainScene);
        this.primaryStage.show();
        //2.32
        RefreshTask r = new RefreshTask();
        Thread th = new Thread(r);
        th.setDaemon(true);
        th.start();
    }

    public void initMainPane() throws
            ExecutionException, InterruptedException {
        mainPane = new FlowPane();
        topPane = new TopPane();
        //currencyPane = new CurrencyPane(this.currency); 2.24
        currencyParentPane = new CurrencyParentPane(this.currencyList);
        mainPane.getChildren().add(topPane);
        //mainPane.getChildren().add(currencyPane); 2.24
        mainPane.getChildren().add(currencyParentPane);
    }
}
