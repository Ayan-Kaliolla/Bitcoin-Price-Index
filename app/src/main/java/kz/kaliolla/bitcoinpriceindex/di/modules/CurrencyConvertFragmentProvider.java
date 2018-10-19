package kz.kaliolla.bitcoinpriceindex.di.modules;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import kz.kaliolla.bitcoinpriceindex.module.converter.CurrencyConverterFragment;

@Module
public abstract class CurrencyConvertFragmentProvider {

    @ContributesAndroidInjector(modules = CurrencyConvertFragmentModule.class)
    abstract CurrencyConverterFragment provideCurrencyConvertFragment();
}
