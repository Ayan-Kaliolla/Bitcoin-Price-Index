package kz.kaliolla.bitcoinpriceindex.net.model;

import java.util.Map;

public class CurrencyHistory {
    private Map<String, Double> bpi;

    public Map<String, Double> getBpi() {
        return bpi;
    }

    public void setBpi(Map<String, Double> bpi) {
        this.bpi = bpi;
    }
}
