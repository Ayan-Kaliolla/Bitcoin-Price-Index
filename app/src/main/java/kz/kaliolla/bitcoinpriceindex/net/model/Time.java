package kz.kaliolla.bitcoinpriceindex.net.model;

import com.google.gson.annotations.SerializedName;

public class Time {
    private String updated;
    private String updatedISO;
    @SerializedName("updateduk")
    private String updatedUK;

    public String getUpdated() { return this.updated; }

    public void setUpdated(String updated) { this.updated = updated; }

    public String getUpdatedISO() { return this.updatedISO; }

    public void setUpdatedISO(String updatedISO) { this.updatedISO = updatedISO; }

    public String getUpdateduk() { return this.updatedUK; }

    public void setUpdateduk(String updatedUK) { this.updatedUK = updatedUK; }
}
