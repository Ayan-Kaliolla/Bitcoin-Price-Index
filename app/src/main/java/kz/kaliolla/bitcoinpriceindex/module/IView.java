package kz.kaliolla.bitcoinpriceindex.module;

public interface IView {
    void showError(String error);
    void showLoading();
    void hideLoading();
}
