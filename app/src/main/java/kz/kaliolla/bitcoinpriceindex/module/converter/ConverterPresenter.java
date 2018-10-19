package kz.kaliolla.bitcoinpriceindex.module.converter;

import java.math.BigDecimal;

public interface ConverterPresenter {
    void convert(BigDecimal amount, String currency);
    void onDetach();
}
