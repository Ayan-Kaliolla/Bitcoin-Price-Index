package kz.kaliolla.bitcoinpriceindex.module.transaction.history;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import kz.kaliolla.bitcoinpriceindex.App;
import kz.kaliolla.bitcoinpriceindex.R;
import kz.kaliolla.bitcoinpriceindex.module.BaseDaggerFragment;
import kz.kaliolla.bitcoinpriceindex.net.model.Transaction;

public class TransactionHistoryFragment extends BaseDaggerFragment implements TransactionHistoryView {
    public static final String TAG = TransactionHistoryFragment.class.getName();
    @Inject
    TransactionPresenter transactionPresenter;
    @BindView(R.id.transaction_list)
    RecyclerView vTransactions;

    private App app;
    private String currency;
    private TransactionAdapter adapter;

    public static Fragment getInstance() {
        return new TransactionHistoryFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        FragmentActivity activity = getActivity();
        if (activity == null) throw new NullPointerException();
        app = (App) activity.getApplication();
        currency = app.getCurrency();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden && !currency.equals(app.getCurrency())) {
                currency = app.getCurrency();
                transactionPresenter.loadTransaction(currency);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transactions, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new TransactionAdapter(getContext(), new ArrayList<Transaction>());
        vTransactions.setLayoutManager(new LinearLayoutManager(getContext()));
        vTransactions.setAdapter(adapter);
        transactionPresenter.loadTransaction(currency);
    }

    @Override
    public void setTransactions(@NonNull List<Transaction> transactions) {
        Collections.sort(transactions, new Comparator<Transaction>() {
            @Override
            public int compare(Transaction t1, Transaction t2) {
                if (t1.getDate() > t2.getDate()) {
                    return -1;
                } else if (t1.getDate() < t2.getDate()){
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        adapter.setTransactions(transactions);
        adapter.notifyDataSetChanged();
        vTransactions.scrollToPosition(0);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        transactionPresenter.onDetach();
    }
}
