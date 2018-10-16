package kz.kaliolla.bitcoinpriceindex.net;

import java.util.List;

import io.reactivex.Observable;
import kz.kaliolla.bitcoinpriceindex.repository.model.Transaction;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TransactionHistoryApi {

    /**
     * @param currency Supported values for currency_pair: btcusd, btceur, eurusd, xrpusd, xrpeur, xrpbtc, ltcusd, ltceur, ltcbtc, ethusd, etheur, ethbtc, bchusd, bcheur, bchbtc
     **/
    @GET("transactions/{currency_pair}")
    Observable<List<Transaction>> getTransactions(@Path("currency_pair") String currency);

    /**
     * @param currency Supported values for currency_pair: btcusd, btceur, eurusd, xrpusd, xrpeur, xrpbtc, ltcusd, ltceur, ltcbtc, ethusd, etheur, ethbtc, bchusd, bcheur, bchbtc
     * @param time     The time interval from which we want the transactions to be returned. Possible values are minute, hour (default) or day.
     **/
    @GET("transactions/{currency_pair}")
    Observable<List<Transaction>> getTransactions(@Path("currency_pair") String currency, @Query("time") String time);
}
