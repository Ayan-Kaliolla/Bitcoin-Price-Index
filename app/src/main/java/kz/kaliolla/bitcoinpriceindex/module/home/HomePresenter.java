package kz.kaliolla.bitcoinpriceindex.module.home;

public interface HomePresenter {
    void getRate(String currency);
    void onDetach();
}