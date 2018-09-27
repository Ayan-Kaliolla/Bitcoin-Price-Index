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

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;
import kz.kaliolla.bitcoinpriceindex.App;
import kz.kaliolla.bitcoinpriceindex.R;

public class HomeFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    public static final String TAG = HomeFragment.class.getName();

    public static Fragment getInstance() {
        return new HomeFragment();
    }

    @Inject
    HomePresenter homePresenter;
    @BindView(R.id.currency)
    Spinner currency;

    private static final String PARAM_ON_LOADING = "loading_data";
    private boolean rotation = false;
    private boolean loadingData = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ((App) getActivity().getApplication()).getAppComponents().inject(this);
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
        loadingData = savedInstanceState.getBoolean(PARAM_ON_LOADING);
    }
}
