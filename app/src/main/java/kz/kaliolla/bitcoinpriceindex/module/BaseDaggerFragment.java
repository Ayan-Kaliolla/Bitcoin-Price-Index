package kz.kaliolla.bitcoinpriceindex.module;

import android.widget.Toast;

import dagger.android.support.DaggerFragment;
import kz.kaliolla.bitcoinpriceindex.R;
import kz.kaliolla.bitcoinpriceindex.util.DialogUtil;

public abstract class BaseDaggerFragment extends DaggerFragment implements IView {

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
