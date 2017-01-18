package org.sovas.cache;

import org.sovas.model.Currency;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by masa on 2017-01-12.
 */
public class CurrencyCache {

    private static List<Currency> currencies = new ArrayList<>();

    public static List<Currency> getCurrencies() {
        return currencies;
    }

    public static void setCurrencies(List<Currency> currencies) {
        CurrencyCache.currencies = currencies;
    }
}
