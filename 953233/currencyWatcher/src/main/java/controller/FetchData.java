package controller;

import model.CurrencyEntity;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class FetchData {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern
            ("yyyy-MM-dd");
    public static ArrayList<CurrencyEntity> fetch_range(String src, int N) { //src; target currency, N; the number of days before the current date.
        String dateEnd = LocalDate.now().format(formatter); //current date
        String dateStart = LocalDate.now().minusDays(7).format(formatter); //
        String dateStart2 = LocalDate.now().minusDays(14).format(formatter); //
        String apiKey = "9bf3b840f309694ced0d";
        String url_str = String.format(
                "https://free.currconv.com/api/v7/convert?q=%s_THB&compact=ultra&date=%s&endDate=%s&apiKey=%s",
                src, dateStart, dateEnd, apiKey);
        String url_str2 = String.format(
                "https://free.currconv.com/api/v7/convert?q=%s_THB&compact=ultra&date=%s&endDate=%s&apiKey=%s",
                src, dateStart2, dateStart, apiKey);
        ArrayList<CurrencyEntity> histList = new ArrayList<>();
        String retrievedJson = null;
        String retrievedJson2 = null;
        try{
            retrievedJson = IOUtils.toString(new URL(url_str),
                    Charset.defaultCharset());
            retrievedJson2 = IOUtils.toString(new URL(url_str2),
                    Charset.defaultCharset());
        }catch (MalformedURLException e){
            System.out.println("Encountered a Malformed Url exception");
        }catch (IOException e){
            System.out.println("Encounter an IO exception");
        }
        JSONObject jsonOBJ = new JSONObject(retrievedJson).getJSONObject(
                String.format("%s_THB", src));
        JSONObject jsonOBJ2 = new JSONObject(retrievedJson2).getJSONObject(
                String.format("%s_THB", src));
        Iterator keysToCopyIterator = jsonOBJ.keys();
        Iterator keysToCopyIterator2 = jsonOBJ2.keys();
        while(keysToCopyIterator.hasNext() && keysToCopyIterator2.hasNext()) {
            String key = (String) keysToCopyIterator.next();
            Double rate = Double.parseDouble(jsonOBJ.get(key).toString());
            String key2 = (String) keysToCopyIterator2.next();
            Double rate2 = Double.parseDouble(jsonOBJ2.get(key2).toString());
            histList.add(new CurrencyEntity(rate, key));
            histList.add(new CurrencyEntity(rate2, key2));
        }
        histList.sort(new Comparator<CurrencyEntity>() {
            @Override
            public int compare(CurrencyEntity o1, CurrencyEntity o2) {
                return o1.getTimestamp().compareTo(o2.getTimestamp());
            }
        });
        return histList;
    }
}
