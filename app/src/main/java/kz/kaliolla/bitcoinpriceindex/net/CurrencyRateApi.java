package kz.kaliolla.bitcoinpriceindex.net;

import io.reactivex.Observable;
import kz.kaliolla.bitcoinpriceindex.net.model.Currency;
import kz.kaliolla.bitcoinpriceindex.net.model.CurrencyHistory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CurrencyRateApi {
    @GET("bpi/currentprice/{code}.json")
    Observable<Currency> getCurrentRate(@Path("code") String code);

    @GET("bpi/historical/close.json")
    Observable<CurrencyHistory> getRateHistory(@Query("currency") String currency, @Query("start") String start, @Query("end") String end);
}
