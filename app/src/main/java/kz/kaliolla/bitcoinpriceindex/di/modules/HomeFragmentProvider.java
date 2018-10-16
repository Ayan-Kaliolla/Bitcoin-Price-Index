package kz.kaliolla.bitcoinpriceindex.di.modules;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import kz.kaliolla.bitcoinpriceindex.module.home.HomeFragment;

@Module
public abstract class HomeFragmentProvider {

    @ContributesAndroidInjector(modules = HomeFragmentModule.class)
    abstract HomeFragment provideHomeFragment();
}
