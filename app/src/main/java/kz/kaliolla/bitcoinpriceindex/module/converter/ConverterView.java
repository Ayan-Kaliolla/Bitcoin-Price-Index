package kz.kaliolla.bitcoinpriceindex.module.converter;

import java.math.BigDecimal;

import kz.kaliolla.bitcoinpriceindex.module.IView;

public interface ConverterView extends IView {
    void setConvertValue(BigDecimal sell, BigDecimal buy);
}