package kz.kaliolla.bitcoinpriceindex.module.home;


import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import kz.kaliolla.bitcoinpriceindex.net.RateApi;
import kz.kaliolla.bitcoinpriceindex.repository.model.Currency;
import kz.kaliolla.bitcoinpriceindex.repository.model.CurrencyHistory;

public class HomePresenterImpl implements HomePresenter {
    private Disposable disposable;
    private RateApi rateApi;
    private HomeView view;

    private Observer subscriber = new Observer() {
        @Override
        public void onSubscribe(Disposable d) {
            disposable = d;
        }

        @Override
        public void onNext(Object data) {
            if (data != null) {
                if (data instanceof Currency) {
                    view.setCurrency((Currency) data);
                } else if (data instanceof CurrencyHistory) {
                    view.setCurrencyHistory((CurrencyHistory) data);
                }
            } else {
                onError(new NullPointerException("error server response null"));
            }
        }

        @Override
        public void onError(Throwable e) {
            view.showError(e.getMessage());
            view.hideLoading();
        }

        @Override
        public void onComplete() {
            view.hideLoading();
        }
    };

    @Inject
    public HomePresenterImpl(HomeView view, RateApi api) {
        this.view = view;
        this.rateApi = api;
    }

    @Override
    public void getRate(String currency, Date start, Date end) {
        view.showLoading();
        //get pair
//        String pair = CurrencyPair.getPair(currency);
        String startDate = getFormattedDate(start, "yyyy-MM-dd");
        String endDate = getFormattedDate(end, "yyyy-MM-dd");
        Observable.merge(rateApi.getCurrentRate(currency).subscribeOn(Schedulers.io()), rateApi.getRateHistory(currency, startDate, endDate).subscribeOn(Schedulers.io()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }

    @Override
    public void onDetach() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    private String getFormattedDate(@NonNull Date date, @NonNull String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.US);
        return formatter.format(date);
    }
}
