package kz.kaliolla.bitcoinpriceindex.module.home;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import kz.kaliolla.bitcoinpriceindex.net.RateApi;
import kz.kaliolla.bitcoinpriceindex.net.TransactionHistoryApi;
import kz.kaliolla.bitcoinpriceindex.repository.model.Currency;
import kz.kaliolla.bitcoinpriceindex.repository.model.Transaction;

public class HomePresenterImpl implements HomePresenter{
    private Disposable disposable;
    private RateApi rateApi;
    private HomeView view;
    private TransactionHistoryApi transactionHistoryApi;
    private String time;

    @Inject
    public HomePresenterImpl(HomeView view, RateApi api, TransactionHistoryApi transactionHistoryApi){
        this.view = view;
        this.rateApi = api;
        this.transactionHistoryApi = transactionHistoryApi;
    }

    @Override
    public void getRate(String currency) {
        view.showLoading();
        String pair = CurrencyPair.getPair(currency);
        Observable<List<Transaction>> transactions;
        if(time != null){
            transactions = transactionHistoryApi.getTransactions(pair, time);
        } else {
            transactions = transactionHistoryApi.getTransactions(pair, time);
        }
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
