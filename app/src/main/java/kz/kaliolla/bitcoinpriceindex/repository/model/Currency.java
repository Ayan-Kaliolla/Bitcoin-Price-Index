package kz.kaliolla.bitcoinpriceindex.repository.model;


import java.math.BigDecimal;
import java.util.Map;

public class Currency {
    private Time time;
    private Map<String, BigDecimal> bpi;

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Map<String, BigDecimal> getBpi() {
        return bpi;
    }

    public void setBpi(Map<String, BigDecimal> bpi) {
        this.bpi = bpi;
    }
}