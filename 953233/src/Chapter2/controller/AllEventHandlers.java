package Chapter2.controller;

import Chapter2.model.Currency;
import Chapter2.model.CurrencyEntity;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class AllEventHandlers {
    public static void onRefresh() {
        try {
            Launcher.refreshPane();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //2.4.7 2.25
    public static void onAdd() {
        try {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Add Currency");
            dialog.setContentText("Currency code:");
            dialog.setHeaderText(null);
            dialog.setGraphic(null);
            Optional<String> code = dialog.showAndWait();
            ArrayList<Currency> currency_list = Launcher.getCurrency();
            Currency c = new Currency(code.get().toUpperCase());

            String retrievedJson = null;
            String url_str = String.format(
                    "https://free.currconv.com/api/v7/currencies?apiKey=9bf3b840f309694ced0d");
            try{
                retrievedJson = IOUtils.toString(new URL(url_str),
                        Charset.defaultCharset());
            }catch (MalformedURLException e){
                System.out.println("Encountered a Malformed Url exception");
            }catch (IOException e){
                System.out.println("Encounter an IO exception") ;
            }

            JSONObject jsonOBJ = new JSONObject(retrievedJson);
            jsonOBJ = jsonOBJ.getJSONObject("results");
            if(jsonOBJ.has(c.getShortCode()) == false){
                Alert dialog2 = new Alert(Alert.AlertType.INFORMATION);
                dialog2.setTitle("notify");
                dialog2.setContentText("No No No");
                dialog2.setHeaderText(null);
                dialog2.showAndWait();
            }
            else {
                if (code.isPresent()) {
                    ArrayList<CurrencyEntity> c_list = FetchData.fetch_range(c.getShortCode(), 8);
                    c.setHistorical(c_list);
                    c.setCurrent(c_list.get(c_list.size() - 1));
                    currency_list.add(c);
                    Launcher.setCurrency(currency_list);
                    Launcher.refreshPane();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void onDelete(String code) {
        try {
            ArrayList<Currency> currency_list = Launcher.getCurrency(); int index = -1;
            for(int i=0 ; i<currency_list.size() ; i++) {
                if (currency_list.get(i).getShortCode().equals(code) ) { index = i;
                    break; }
            }
            if (index != -1) {
                currency_list.remove(index); Launcher.setCurrency(currency_list); Launcher.refreshPane();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace(); }
    }

    public static void onWatch(String code) {
        try {
            ArrayList<Currency> currency_list = Launcher.getCurrency(); int index = -1;
            for(int i=0 ; i<currency_list.size() ; i++) {
                if (currency_list.get(i).getShortCode().equals(code) ) {
                    index = i;
                    break;
                }
            }
            if (index != -1) {
                TextInputDialog dialog = new TextInputDialog(); dialog.setTitle("Add Watch");
                dialog.setContentText("Rate:");
                dialog.setHeaderText(null);
                dialog.setGraphic(null);
                Optional<String> retrievedRate = dialog.showAndWait();
                if (retrievedRate.isPresent()){
                    double rate = Double.parseDouble(retrievedRate.get());
                    currency_list.get(index).setWatch(true);
                    currency_list.get(index).setWatchRate(rate);
                    Launcher.setCurrency(currency_list);
                    Launcher.refreshPane();
                }
                Launcher.setCurrency(currency_list);
                Launcher.refreshPane();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace(); }
    }

    public static void onUnWatch(String code) {
        try {
            ArrayList<Currency> currency_list = Launcher.getCurrency(); int index = -1;
            for(int i=0 ; i<currency_list.size() ; i++) {
                if (currency_list.get(i).getShortCode().equals(code) ) {
                    index = i;
                    break;
                }
            }
            if (index != -1) {
                currency_list.get(index).setWatch(false);
                currency_list.get(index).setWatchRate(0);
                Launcher.setCurrency(currency_list);
                Launcher.refreshPane();
                Launcher.setCurrency(currency_list);
                Launcher.refreshPane();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace(); }
    }
}
