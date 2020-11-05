package model;

import java.util.ArrayList;
//2.4.4
public class Currency {
    private String shortCode;
    private CurrencyEntity current;
    private ArrayList<CurrencyEntity> historical;
    private Boolean isWatch;
    private Double watchRate;
    public Currency(String shortCode) {
        this.shortCode = shortCode;
        this.isWatch = false;
        this.watchRate = 0.0;
    }

    public CurrencyEntity getCurrent() {
        return current;
    }

    public boolean getWatch() {
        return isWatch;
    }

    public Double getWatchRate() {
        return watchRate;
    }

    public void setHistorical(ArrayList<CurrencyEntity> c_list) { this.historical = c_list; }

    public void setCurrent(CurrencyEntity currencyEntity) { this.current = currencyEntity; }

    public ArrayList<CurrencyEntity> getHistorical() { return historical; }

    public String getShortCode() { return shortCode; }

    public void setWatch(boolean b) {
        this.isWatch = b;
    }

    public void setWatchRate(double rate) {
        this.watchRate = rate;
    }
}