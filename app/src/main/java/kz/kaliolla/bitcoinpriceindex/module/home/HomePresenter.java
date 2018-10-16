package kz.kaliolla.bitcoinpriceindex.module.home;

import java.util.Date;

public interface HomePresenter {
    void getRate(String currency, Date start, Date en);
    void onDetach();
}