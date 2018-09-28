package kz.kaliolla.bitcoinpriceindex.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;
import kz.kaliolla.bitcoinpriceindex.di.modules.ActivityBuilder;
import kz.kaliolla.bitcoinpriceindex.di.modules.ApiModule;
import kz.kaliolla.bitcoinpriceindex.di.modules.AppModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ApiModule.class,
        ActivityBuilder.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }
}
