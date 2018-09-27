package kz.kaliolla.bitcoinpriceindex.module.transaction.history;

import android.support.v4.app.Fragment;

public class TransactionHistoryFragment extends Fragment {
    public static final String TAG = TransactionHistoryFragment.class.getName();

    public static Fragment getInstance() {
        return new TransactionHistoryFragment();
    }
}
