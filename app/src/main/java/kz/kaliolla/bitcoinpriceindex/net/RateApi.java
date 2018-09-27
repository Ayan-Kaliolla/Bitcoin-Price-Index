package kz.kaliolla.bitcoinpriceindex.net;

import io.reactivex.Observable;
import kz.kaliolla.bitcoinpriceindex.repository.model.Currency;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RateApi {
    @GET("v1/bpi/currentprice/{code}.json")
    Observable<Currency> getCurrentRate(@Path("code") String code);
}
