package kz.kaliolla.bitcoinpriceindex.di.modules;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import kz.kaliolla.bitcoinpriceindex.MainActivity;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {MainActivityModule.class, HomeFragmentProvider.class, TransactionFragmentProvider.class})
    abstract MainActivity bindMainActivity();

}