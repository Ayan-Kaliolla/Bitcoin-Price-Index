package kz.kaliolla.bitcoinpriceindex.module.converter;

import android.support.v4.app.Fragment;

public class CurrencyConverterFragment extends Fragment {
    public static final String TAG = CurrencyConverterFragment.class.getName();

    public static Fragment getInstance() {
        return new CurrencyConverterFragment();
    }
}
