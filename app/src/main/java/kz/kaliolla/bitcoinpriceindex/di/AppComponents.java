package kz.kaliolla.bitcoinpriceindex.di;

import javax.inject.Singleton;

import dagger.Component;
import kz.kaliolla.bitcoinpriceindex.di.modules.NetworkModule;
import kz.kaliolla.bitcoinpriceindex.module.home.HomeFragment;
import kz.kaliolla.bitcoinpriceindex.module.home.HomePresenter;

@Singleton
@Component(modules = {
        NetworkModule.class,
})
public interface AppComponents {
    void inject(HomeFragment fragment);
}
