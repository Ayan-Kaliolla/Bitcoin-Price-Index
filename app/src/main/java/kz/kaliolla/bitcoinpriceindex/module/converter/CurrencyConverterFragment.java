package kz.kaliolla.bitcoinpriceindex.module.converter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.math.BigDecimal;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kz.kaliolla.bitcoinpriceindex.R;
import kz.kaliolla.bitcoinpriceindex.module.BaseDaggerFragment;

public class CurrencyConverterFragment extends BaseDaggerFragment implements ConverterView{
    public static final String TAG = CurrencyConverterFragment.class.getName();

    @Inject
    ConverterPresenter presenter;
    @BindView(R.id.currency)
    Spinner currency;
    @BindView(R.id.amount)
    TextView amount;
    @BindView(R.id.sell)
    TextView sell;
    @BindView(R.id.buy)
    TextView buy;

    public static CurrencyConverterFragment getInstance() {
        return new CurrencyConverterFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_converter, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        amount.addTextChangedListener(new TextChangedListener(){
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                amount.setError(null);
            }
        });
    }

    @OnClick(R.id.convert)
    public void convert(){
        if (amount.getText() == null){
            amount.setError(getString(R.string.empty_error));
        }
        presenter.convert(new BigDecimal(amount.getText().toString()), currency.getSelectedItem().toString());
    }

    @Override
    public void setConvertValue(@Nullable BigDecimal sell, @NonNull BigDecimal buy) {
        if (buy != null) {
            this.buy.setText(buy.toString());
        } else {
            this.sell.setText(sell.toString());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.onDetach();
        }
    }
}
