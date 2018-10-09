package kz.kaliolla.bitcoinpriceindex.repository.model;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class Transaction {
    @SerializedName("tid")
    private long id;
    private BigDecimal price;
    private BigDecimal amount;
    private long date;
    private Type type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public enum Type{
        BUY, SELL
    }
}
