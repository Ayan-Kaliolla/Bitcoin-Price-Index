package kz.kaliolla.bitcoinpriceindex.repository.model;

import com.google.gson.annotations.SerializedName;

public class BpiItem {
    private String code;
    private String rate;
    private String description;
    @SerializedName("rate_float")
    private float rateF;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRateF() {
        return rateF;
    }

    public void setRateF(float rateF) {
        this.rateF = rateF;
    }
}
