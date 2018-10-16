package kz.kaliolla.bitcoinpriceindex.di.modules;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import kz.kaliolla.bitcoinpriceindex.module.transaction.history.TransactionHistoryFragment;

@Module
public abstract class TransactionFragmentProvider {

    @ContributesAndroidInjector(modules = TransactionFragmentModule.class)
    abstract TransactionHistoryFragment provideTransactionHistoryFragment();
}
