package kz.kaliolla.bitcoinpriceindex.module.converter;

import android.support.annotation.NonNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import kz.kaliolla.bitcoinpriceindex.net.CurrencyRateApi;
import kz.kaliolla.bitcoinpriceindex.net.model.Currency;

public class ConverterPresenterImpl implements ConverterPresenter {
    private Disposable disposable;
    private ConverterView view;
    private CurrencyRateApi api;

    public ConverterPresenterImpl(ConverterView view, CurrencyRateApi api) {
        this.view = view;
        this.api = api;
    }

    @Override
    public void convert(@NonNull final BigDecimal amount, @NonNull final String currency_label) {
        view.showLoading();
        api.getCurrentRate(currency_label)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Currency>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(Currency currency) {
                        float rate = currency.getBpi().get(currency_label).getRateF();
                        view.setConvertValue(amount.intValue() == 1 ? new BigDecimal(rate) : null, amount.divide(new BigDecimal(rate), 4, RoundingMode.HALF_EVEN));
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
    public void onDetach() {
        if (disposable != null && !disposable.isDisposed()){
            disposable.dispose();
        }
    }
}
