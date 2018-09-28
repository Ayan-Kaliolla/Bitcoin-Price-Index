package kz.kaliolla.bitcoinpriceindex.module.home;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import kz.kaliolla.bitcoinpriceindex.net.RateApi;
import kz.kaliolla.bitcoinpriceindex.repository.model.Currency;

public class HomePresenterImpl implements HomePresenter{
    private Disposable disposable;
    private RateApi rateApi;
    private HomeView view;

    @Inject
    public HomePresenterImpl(HomeView view, RateApi api){
        this.view = view;
        this.rateApi = api;
    }

    @Override
    public void getRate(String currency) {
        view.showLoading();
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
                        view.setCurrency(currency);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        view.hideLoading();
                    }
                });
    }

    @Override
    public void onDetach(){
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
