package kz.kaliolla.bitcoinpriceindex.di.modules;

import android.util.Log;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import kz.kaliolla.bitcoinpriceindex.net.TransactionHistoryApi;
import kz.kaliolla.bitcoinpriceindex.net.RateApi;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static kz.kaliolla.bitcoinpriceindex.Constants.RATE_API_BASE_URL;
import static kz.kaliolla.bitcoinpriceindex.Constants.TRANSACTION_HISTORY_API_BASE_URL;

@Module
public class NetworkModule {
    private static final String TAG = NetworkModule.class.getName();

    @Provides
    @Singleton
    RateApi provideRateApi() {
        Log.d(TAG, "Created RateApi");
        return new Retrofit.Builder()
                .baseUrl(RATE_API_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RateApi.class);
    }


    @Provides
    @Singleton
    TransactionHistoryApi provideTransactionHistoryApi() {
        Log.d(TAG, "Created TransactionHistoryApi");
        return new Retrofit.Builder()
                .baseUrl(TRANSACTION_HISTORY_API_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TransactionHistoryApi.class);
    }
}
