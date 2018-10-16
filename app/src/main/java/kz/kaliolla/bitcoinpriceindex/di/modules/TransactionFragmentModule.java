package kz.kaliolla.bitcoinpriceindex.di.modules;


import dagger.Module;
import dagger.Provides;
import kz.kaliolla.bitcoinpriceindex.module.transaction.history.TransactionHistoryFragment;
import kz.kaliolla.bitcoinpriceindex.module.transaction.history.TransactionHistoryView;
import kz.kaliolla.bitcoinpriceindex.module.transaction.history.TransactionPresenter;
import kz.kaliolla.bitcoinpriceindex.module.transaction.history.TransactionPresenterImpl;
import kz.kaliolla.bitcoinpriceindex.net.TransactionHistoryApi;

@Module
public class TransactionFragmentModule {

    @Provides
    TransactionHistoryView provideTransactionHistoryView(TransactionHistoryFragment transactionHistoryFragment){
        return transactionHistoryFragment;
    }

    @Provides
    TransactionPresenter provideHomeFragmentPresenter(TransactionHistoryView view, TransactionHistoryApi api){
        return new TransactionPresenterImpl(view, api);
    }
}
