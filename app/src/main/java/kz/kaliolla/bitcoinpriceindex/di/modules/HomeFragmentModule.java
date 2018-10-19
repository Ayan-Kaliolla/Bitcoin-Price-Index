package kz.kaliolla.bitcoinpriceindex.di.modules;

import dagger.Module;
import dagger.Provides;
import kz.kaliolla.bitcoinpriceindex.module.home.HomeFragment;
import kz.kaliolla.bitcoinpriceindex.module.home.HomePresenter;
import kz.kaliolla.bitcoinpriceindex.module.home.HomePresenterImpl;
import kz.kaliolla.bitcoinpriceindex.module.home.HomeView;
import kz.kaliolla.bitcoinpriceindex.net.CurrencyRateApi;

@Module
public class HomeFragmentModule {

    @Provides
    HomeView provideHomeFragmentView(HomeFragment homeFragment){
        return homeFragment;
    }

    @Provides
    HomePresenter provideHomeFragmentPresenter(HomeView view, CurrencyRateApi api){
        return new HomePresenterImpl(view, api);
    }
}
