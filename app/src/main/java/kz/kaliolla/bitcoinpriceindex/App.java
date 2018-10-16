package kz.kaliolla.bitcoinpriceindex;


import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import kz.kaliolla.bitcoinpriceindex.di.AppComponent;
import kz.kaliolla.bitcoinpriceindex.di.DaggerAppComponent;


public class App extends DaggerApplication {
    private String currency;

    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
        return appComponent;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}