package kz.kaliolla.bitcoinpriceindex.module.home;


import kz.kaliolla.bitcoinpriceindex.module.IView;
import kz.kaliolla.bitcoinpriceindex.repository.model.Currency;
import kz.kaliolla.bitcoinpriceindex.repository.model.CurrencyHistory;

public interface HomeView extends IView {
    void setCurrency(Currency currency);
    void setCurrencyHistory(CurrencyHistory currencyHistory);
}
