package kz.kaliolla.bitcoinpriceindex.module.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;
import kz.kaliolla.bitcoinpriceindex.R;
import kz.kaliolla.bitcoinpriceindex.repository.model.BpiItem;
import kz.kaliolla.bitcoinpriceindex.repository.model.Currency;
import kz.kaliolla.bitcoinpriceindex.repository.model.Time;
import kz.kaliolla.bitcoinpriceindex.util.DialogUtil;

import static kz.kaliolla.bitcoinpriceindex.Constants.THE_NUMBER_OF_VALUES_AFTER_THE_POINT;


public class HomeFragment extends DaggerFragment implements AdapterView.OnItemSelectedListener , HomeView{
    public static final String TAG = HomeFragment.class.getName();

    public static Fragment getInstance() {
        return new HomeFragment();
    }

    @Inject
    HomePresenter homePresenter;
    @BindView(R.id.currency)
    Spinner currency;
    @BindView(R.id.updateDate)
    TextView updateDate;
    @BindView(R.id.currencyValue)
    TextView currencyValue;

    private static final String PARAM_ON_LOADING = "loading_data";
    private boolean rotation = false;
    private boolean loadingData = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        if (savedInstanceState != null) {
            rotation = true;
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        currency.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if ((loadingData && rotation) || (!rotation && !loadingData)) {
            if (view != null) {
                String currency = ((TextView) view).getText().toString();
                homePresenter.getRate(currency);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        homePresenter.onDetach();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(PARAM_ON_LOADING, loadingData);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            loadingData = savedInstanceState.getBoolean(PARAM_ON_LOADING);
        }
    }

    @Override
    public void setCurrency(Currency currency) {
        if (currency != null) {
            setUpdatedTime(currency.getTime());
            setCurrencyValue(currency.getBpi());
        }
    }

    private void setCurrencyValue(Map<String, BpiItem> bpi) {
        if (bpi != null && bpi.size() > 0){
            for (Map.Entry<String, BpiItem> itemEntry : bpi.entrySet()){
                if (String.valueOf(currency.getSelectedItem()).equals(itemEntry.getKey())) {
                    BpiItem bpiItem = itemEntry.getValue();
                    if (bpiItem != null){
                        currencyValue.setText((new BigDecimal(bpiItem.getRateF()).setScale(THE_NUMBER_OF_VALUES_AFTER_THE_POINT, BigDecimal.ROUND_DOWN)).toString());
                    }
                }
            }
        }
    }

    public void setUpdatedTime(Time updatedTime) {
        if (updatedTime != null){
            updateDate.setText(updatedTime.getUpdated());
        }
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {
        DialogUtil.showProgressDialog(getActivity(), getString(R.string.loading_message));
    }

    @Override
    public void hideLoading() {
        DialogUtil.hideProgressDialog();
    }

}
