package kz.kaliolla.bitcoinpriceindex.module.transaction.history;


import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import kz.kaliolla.bitcoinpriceindex.net.TransactionHistoryApi;
import kz.kaliolla.bitcoinpriceindex.repository.model.Transaction;

public class TransactionPresenterImpl implements TransactionPresenter {
    private Disposable disposable;
    private TransactionHistoryApi transactionApi;
    private TransactionHistoryView view;

    public TransactionPresenterImpl(TransactionHistoryView view, TransactionHistoryApi api) {
        this.view = view;
        this.transactionApi = api;
    }

    @Override
    public void loadTransaction(String currency) {
        view.showLoading();
        String pair = CurrencyPair.getPair(currency);
        transactionApi.getTransactions(pair, "day")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Transaction>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(List<Transaction> transactions) {
                        if (transactions != null) {
                            view.setTransactions(transactions);
                        } else {
                            view.showError("ошибка неверные данные");
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
                });
    }

    @Override
    public void onDetach() {
        if (disposable != null && !disposable.isDisposed()){
            disposable.dispose();
        }
    }
}
