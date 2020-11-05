package Chapter2.view;

import Chapter2.controller.AllEventHandlers;
import Chapter2.controller.DrawGraphTask;
import Chapter2.model.Currency;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

//2.4.4
public class CurrencyPane extends BorderPane {
    private Currency currency;
    private Button watch;
    private Button unwatch;
    private Button delete;
    public CurrencyPane(Currency currency) {
        this.watch = new Button("Watch");
        this.unwatch = new Button("Unwatch");
        this.delete = new Button("Delete");
        this.watch.setOnAction(event -> AllEventHandlers.onWatch(currency.getShortCode()));
        this.unwatch.setOnAction(event -> AllEventHandlers.onUnWatch(currency.getShortCode()));
        this.delete.setOnAction(event -> AllEventHandlers.onDelete(currency.getShortCode()));
        this.setPadding(new Insets(0));
        this.setPrefSize(640,300);
        this.setStyle("-fx-border-color: black");
        //this.refreshPane(currency);
        try {
            this.refreshPane(currency);
        } catch (ExecutionException e){
            System.out.println("Encountered an execution exception.");
        } catch (InterruptedException e){
            System.out.println("Encountered an interupted exception.");
        }
    }
    //public void refreshPane(Currency currency) { 2.4.5
    public void refreshPane(Currency currency) throws
            ExecutionException, InterruptedException {
        this.currency = currency;
        Pane currencyInfo = genInfoPane();
        //2.4.5 FutureTask, ExecutorService, executor, currencyGraph
        FutureTask futureTask = new FutureTask<VBox>(new DrawGraphTask(currency)); //tomorrow
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(futureTask);
        VBox currencyGraph = (VBox) futureTask.get();
        Pane topArea = genTopArea();
        this.setTop(topArea);
        this.setLeft(currencyInfo);
        //2.4.5 setCenter
        this.setCenter(currencyGraph);
    }
    private Pane genInfoPane() {
        VBox currencyInfoPane = new VBox(10);
        currencyInfoPane.setPadding(new Insets(5, 25, 5, 25));
        currencyInfoPane.setAlignment(Pos.CENTER);
        Label exchangeString = new Label("");
        Label watchString = new Label("");
        exchangeString.setStyle("-fx-font-size: 20;");
        watchString.setStyle("-fx-font-size: 14;");
        if (this.currency != null) {
            exchangeString.setText(String.format("%s: %.4f",
                    currency.getShortCode(),
                    currency.getCurrent().getRate()));
            if(this.currency.getWatch() == true) {
                watchString.setText(String.format("(Watch @%.4f)",
                        this.currency.getWatchRate()));
            }
        }
        currencyInfoPane.getChildren().addAll(exchangeString,watchString);
        return currencyInfoPane;
    }
    private HBox genTopArea() {
        HBox topArea = new HBox(10);
        topArea.setPadding(new Insets(5));
        topArea.getChildren().addAll(watch, unwatch, delete);
        ((HBox) topArea).setAlignment(Pos.CENTER_RIGHT);
        return topArea;
    }
}
