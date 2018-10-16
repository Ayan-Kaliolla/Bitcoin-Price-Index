package kz.kaliolla.bitcoinpriceindex.module.transaction.history;

public interface TransactionPresenter {
    void loadTransaction(String currency);
    void onDetach();
}
