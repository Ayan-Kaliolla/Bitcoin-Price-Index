package kz.kaliolla.bitcoinpriceindex.net.model;


import java.util.Map;

public class Currency {
    private Time time;
    private Map<String, BpiItem> bpi;

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Map<String, BpiItem> getBpi() {
        return bpi;
    }

    public void setBpi(Map<String, BpiItem> bpi) {
        this.bpi = bpi;
    }
}