package kz.kaliolla.bitcoinpriceindex.module.home;

import android.util.Log;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import kz.kaliolla.bitcoinpriceindex.net.RateApi;
import kz.kaliolla.bitcoinpriceindex.repository.model.Currency;


public class HomePresenter {
    private Disposable disposable;
    RateApi rateApi;

    @Inject
    public HomePresenter(RateApi rateApi){
        this.rateApi = rateApi;
    }

    public void getRate(String currency) {
        rateApi.getCurrentRate(currency)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Currency>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(Currency currency) {
                        Log.i("result", currency.getBpi().toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("result", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void onDetach(){
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
