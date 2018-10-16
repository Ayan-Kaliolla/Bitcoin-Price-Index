package kz.kaliolla.bitcoinpriceindex.di.modules;

import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import kz.kaliolla.bitcoinpriceindex.net.TransactionHistoryApi;
import kz.kaliolla.bitcoinpriceindex.net.RateApi;
import okhttp3.ConnectionPool;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static kz.kaliolla.bitcoinpriceindex.Constants.OK_HTTP_KEEP_ALIVE_DURATION_SECONDS;
import static kz.kaliolla.bitcoinpriceindex.Constants.OK_HTTP_MAX_IDLE_CONNECTIONS;
import static kz.kaliolla.bitcoinpriceindex.Constants.RATE_API_BASE_URL;
import static kz.kaliolla.bitcoinpriceindex.Constants.TRANSACTION_HISTORY_API_BASE_URL;

@Module
public class ApiModule {
    private static final String TAG = ApiModule.class.getName();

    @Provides
    @Singleton
    RateApi provideRateApi(GsonConverterFactory converterFactory, RxJava2CallAdapterFactory rxJava2CallAdapterFactory) {
        Log.d(TAG, "Created RateApi");
        return new Retrofit.Builder()
                .baseUrl(RATE_API_BASE_URL)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .addConverterFactory(converterFactory)
                .build()
                .create(RateApi.class);
    }

    @Provides
    @Singleton
    TransactionHistoryApi provideTransactionHistoryApi(GsonConverterFactory converterFactory, RxJava2CallAdapterFactory rxJava2CallAdapterFactory) {
        Log.d(TAG, "Created TransactionHistoryApi");
        return new Retrofit.Builder()
                .baseUrl(TRANSACTION_HISTORY_API_BASE_URL)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .addConverterFactory(converterFactory)
                .build()
                .create(TransactionHistoryApi.class);
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
