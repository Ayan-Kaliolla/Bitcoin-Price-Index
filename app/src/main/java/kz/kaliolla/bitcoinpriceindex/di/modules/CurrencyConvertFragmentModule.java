package kz.kaliolla.bitcoinpriceindex.di.modules;

import dagger.Module;
import dagger.Provides;
import kz.kaliolla.bitcoinpriceindex.module.converter.ConverterPresenter;
import kz.kaliolla.bitcoinpriceindex.module.converter.ConverterPresenterImpl;
import kz.kaliolla.bitcoinpriceindex.module.converter.ConverterView;
import kz.kaliolla.bitcoinpriceindex.module.converter.CurrencyConverterFragment;
import kz.kaliolla.bitcoinpriceindex.net.CurrencyRateApi;
import kz.kaliolla.bitcoinpriceindex.net.CurrencyTransactionApi;

@Module
public class CurrencyConvertFragmentModule {

    @Provides
    ConverterView provideHomeFragmentView(CurrencyConverterFragment currencyConverterFragment){
        return currencyConverterFragment;
    }

    @Provides
    ConverterPresenter provideHomeFragmentPresenter(ConverterView view, CurrencyRateApi api){
        return new ConverterPresenterImpl(view, api);
    }
}
