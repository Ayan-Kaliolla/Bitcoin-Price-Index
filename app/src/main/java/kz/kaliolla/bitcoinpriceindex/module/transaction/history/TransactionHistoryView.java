package kz.kaliolla.bitcoinpriceindex.module.transaction.history;

import java.util.List;

import kz.kaliolla.bitcoinpriceindex.module.IView;
import kz.kaliolla.bitcoinpriceindex.net.model.Transaction;

public interface TransactionHistoryView extends IView {
    void setTransactions(List<Transaction> transactions);
}
