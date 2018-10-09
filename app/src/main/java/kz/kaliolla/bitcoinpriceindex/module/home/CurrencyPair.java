package kz.kaliolla.bitcoinpriceindex.module.home;

class CurrencyPair {
    public static String getPair(String currency) {
        switch (currency) {
            case "USD":
                return "btcusd";
            case "EUR":
                return "btceur";
            case "KZT":
                return "btckzt";
            default:
                return null;
        }
    }
}
