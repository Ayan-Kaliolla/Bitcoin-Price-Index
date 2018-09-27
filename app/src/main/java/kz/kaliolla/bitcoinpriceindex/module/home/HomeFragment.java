package kz.kaliolla.bitcoinpriceindex.module.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.OnItemSelected;
import kz.kaliolla.bitcoinpriceindex.R;

public class HomeFragment extends Fragment {

    public static Fragment getInstance() {
        return new HomeFragment();
    }
    public static final String TAG = HomeFragment.class.getName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return  view;
    }

    @OnItemSelected(R.id.currency)
    public void currencySelected(View view){
        String currency = ((TextView) view).getText().toString();

    }
}
