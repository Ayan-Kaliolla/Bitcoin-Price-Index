package kz.kaliolla.bitcoinpriceindex;
import android.app.Application;

import kz.kaliolla.bitcoinpriceindex.di.AppComponents;
import kz.kaliolla.bitcoinpriceindex.di.DaggerAppComponents;

public class App extends Application {
    private AppComponents appComponents;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponents = DaggerAppComponents.create();
    }

    public AppComponents getAppComponents() {
        return appComponents;
    }
}