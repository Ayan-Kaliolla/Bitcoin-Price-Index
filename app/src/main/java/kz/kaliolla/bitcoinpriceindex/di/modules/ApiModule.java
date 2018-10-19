package kz.kaliolla.bitcoinpriceindex.di.modules;

import android.util.Log;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import kz.kaliolla.bitcoinpriceindex.net.CurrencyTransactionApi;
import kz.kaliolla.bitcoinpriceindex.net.CurrencyRateApi;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static kz.kaliolla.bitcoinpriceindex.Constants.RATE_API_BASE_URL;
import static kz.kaliolla.bitcoinpriceindex.Constants.TRANSACTION_HISTORY_API_BASE_URL;

@Module
public class ApiModule {
    private static final String TAG = ApiModule.class.getName();

    @Provides
    @Singleton
    CurrencyRateApi provideRateApi(GsonConverterFactory converterFactory, RxJava2CallAdapterFactory rxJava2CallAdapterFactory) {
        Log.d(TAG, "Created CurrencyRateApi");
        return new Retrofit.Builder()
                .baseUrl(RATE_API_BASE_URL)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .addConverterFactory(converterFactory)
                .build()
                .create(CurrencyRateApi.class);
    }

    @Provides
    @Singleton
    CurrencyTransactionApi provideTransactionHistoryApi(GsonConverterFactory converterFactory, RxJava2CallAdapterFactory rxJava2CallAdapterFactory) {
        Log.d(TAG, "Created CurrencyTransactionApi");
        return new Retrofit.Builder()
                .baseUrl(TRANSACTION_HISTORY_API_BASE_URL)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .addConverterFactory(converterFactory)
                .build()
                .create(CurrencyTransactionApi.class);
    }

    @Provides
    @Singleton
    GsonConverterFactory provideGsonConverterFactory() {
        Log.d(TAG, "Created GsonConverterFactory");
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    RxJava2CallAdapterFactory provideRxJava2CallAdapterFactory() {
        Log.d(TAG, "Created RxJava2CallAdapterFactory");
        return RxJava2CallAdapterFactory.create();
    }
}
